/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.services.impl;

import io.micrometer.core.instrument.util.StringUtils;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.entities.UserLogin;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.exceptions.InvalidLoginException;
import xyz.subho.lunchbooking.exceptions.InvalidUsernameException;
import xyz.subho.lunchbooking.exceptions.RoleNotFoundException;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.UserChangePasswordRequestModel;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;
import xyz.subho.lunchbooking.models.UserResponseModel;
import xyz.subho.lunchbooking.repositories.RolesRepository;
import xyz.subho.lunchbooking.repositories.UserLoginRepository;
import xyz.subho.lunchbooking.repositories.UserMetadataRepository;
import xyz.subho.lunchbooking.security.JwtHelper;
import xyz.subho.lunchbooking.services.EncryptionService;
import xyz.subho.lunchbooking.services.LoginService;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService, UserDetailsService {

  @Autowired private UserLoginRepository loginRepository;

  @Autowired private UserMetadataRepository metadataRepository;

  @Autowired private RolesRepository rolesRepository;

  @Autowired private EncryptionService encryptionService;

  @Autowired
  @Qualifier("UserDetailsMapper")
  private Mapper<UserMetadata, UserResponseModel> loginMapper;

  @Value("${app.security.jwt.salt.length}")
  private String saltSize;

  @Autowired private JwtHelper jwtHelper;

  @Override
  @Transactional
  public void createUser(UserRegistrationModel user) {

    log.debug("Starting User Registration for:{}", user.getEmailId());
    checkIfUserExists(user.getEmailId(), user.getMobile());

    var userDetails =
        new UserMetadata()
            .withFirstName(user.getFirstName())
            .withLastName(user.getLastName())
            .withEmailId(user.getEmailId())
            .withMobile(user.getMobile());

    userDetails = metadataRepository.save(userDetails);
    log.debug("Created User Details for User ID:{}", userDetails.getId());

    final String salt = encryptionService.generateSalt(Integer.parseInt(saltSize));

    var userLogin =
        new UserLogin()
            .withUsername(user.getEmailId())
            .withPassword(encryptionService.encrypt(user.getPassword(), salt))
            .withSalt(salt);

    userLogin.setId(userDetails.getId());

    addUserRole(userLogin, Roles.EMPLOYEE);

    userLogin = loginRepository.save(userLogin);
    log.debug("Created Login Details for User ID:{}", userLogin.getId());
  }

  private void checkIfUserExists(String username, String mobile) {
    if (loginRepository.existsByUsername(username)) {
      log.error("Email ID already exists for {}", username);
      throw new InvalidUsernameException("Email ID already exists");
    } else if (metadataRepository.existsByMobileIgnoreCase(mobile)) {
      log.error("Mobile Number already exists for {}", mobile);
      throw new InvalidUsernameException("Mobile Number already exists");
    }
  }

  @Transactional
  public void addUserRole(UserLogin user, long roleId) {
    var role = getRoleById(roleId);
    user.getRoles().add(role);
    log.debug("Role:{} added for UserID:{}", role.getRole(), user.getId());
  }

  @Override
  @Transactional
  public UserLoginResponseModel login(UserLoginRequestModel userRequest) {
    if (StringUtils.isBlank(userRequest.getUsername())
        || StringUtils.isBlank(userRequest.getPassword())) {
      log.error("Username or Password cannot be blank");
      throw new InvalidLoginException("Username or Password cannot be blank");
    }

    var userLogin = getUserByUsername(userRequest.getUsername());
    log.info("Login Request Recieved from:{}", userLogin.getUsername());

    checkUserAccountStatus(userLogin);

    if (encryptionService.isPasswordValid(
        userRequest.getPassword(), userLogin.getPassword(), userLogin.getSalt())) {
      log.debug("User:{} has validated thier password", userLogin.getUsername());

      var userMetadata = getUserByEmail(userLogin.getUsername());
      log.debug("Fetched User Details for email:{}", userMetadata.getEmailId());

      final String jwtToken = jwtHelper.createJwt(userLogin);
      log.trace("JWT:{} issued for username:{}", jwtToken, userMetadata.getEmailId());
      log.info("Login Successful for Username:{} with JWT", userMetadata.getEmailId());
      var userResponse =
          loginMapper.transform(userMetadata).withLastLogin(userLogin.makeNewLogin());
      return new UserLoginResponseModel(jwtToken, userResponse);
    } else {
      log.error("Password is Invalid for username:{}", userRequest.getUsername());
      throw new InvalidLoginException(
          String.format("Invalid Password for username:%s", userRequest.getUsername()));
    }
  }

  private boolean checkUserAccountStatus(UserLogin userLogin) {

    final String username = userLogin.getUsername();
    log.debug("Starting validation checks for user:{}", username);
    final String msgTemplate = "Username:%s is %s";
    String errorMsg = "";

    if (!userLogin.isEnabled()) {
      errorMsg = String.format(msgTemplate, username, "Not Enabled");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    } else if (userLogin.isExpired()) {
      errorMsg = String.format(msgTemplate, username, "Expired");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    } else if (userLogin.isLocked()) {
      errorMsg = String.format(msgTemplate, username, "Locked");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    } else if (userLogin.isCredentialExpired()) {
      errorMsg = String.format(msgTemplate, username, "Credential Expired");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    }
    log.debug(String.format(msgTemplate, username, "Green Status"));
    return true;
  }

  @Override
  @Transactional
  public UserLogin addRole(long userId, long roleId) {

    log.debug("Adding Role ID:{} with User ID:{}", roleId, userId);
    var role = getRoleById(roleId);
    var user = getUserById(userId);

    user.getRoles().add(role);
    log.info("Added Role:{} to username:{}", role.getRole(), user.getUsername());
    return user;
  }

  @Override
  @Transactional
  public UserLogin removeRole(long userId, long roleId) {
    log.debug("Removing Role ID:{} with User ID:{}", roleId, userId);
    var role = getRoleById(roleId);
    var user = getUserById(userId);

    user.getRoles().remove(role);
    log.info("Removed Role:{} for username:{}", role.getRole(), user.getUsername());
    return user;
  }

  private UserLogin getUserById(long userId) {

    log.debug("Finding user with ID:{}", userId);
    var userLoginOpt = loginRepository.findById(userId);
    if (userLoginOpt.isEmpty()) {
      log.error("Cannot Find User with ID:{}", userId);
      throw new UserNotFoundException(String.format("Invalid User ID:%s", userId));
    }
    log.debug("Found user with ID:{}", userId);
    return userLoginOpt.get();
  }

  private Roles getRoleById(long roleId) {

    log.debug("Finding role with ID:{}", roleId);
    var roleOpt = rolesRepository.findById(roleId);
    if (roleOpt.isEmpty()) {
      log.error("Role is not present for role id:{}", roleId);
      throw new RoleNotFoundException("Role cannot be found");
    }
    log.debug("Found Role with ID:{}", roleId);
    return roleOpt.get();
  }

  @Override
  public UserLogin getUserByUsername(String username) {

    if (StringUtils.isBlank(username)) {
      log.error("Username cannot be Blank");
      throw new UserNotFoundException("Username cannot be Blank");
    }
    log.debug("Finding Login User with username:{}", username);

    Optional<UserLogin> userLoginOpt = loginRepository.findByUsername(username);
    if (userLoginOpt.isPresent()) {
      log.debug("Found User Login Details with username:{}", username);
      return userLoginOpt.get();
    }
    log.error("User Login Details NOT found for username:{}", username);
    throw new UserNotFoundException(
        String.format("User Login Details NOT found for username:%s", username));
  }

  @Override
  public UserMetadata getUserByEmail(String email) {

    if (StringUtils.isBlank(email)) {
      log.error("Email cannot be Blank");
      throw new UserNotFoundException("Email cannot be Blank");
    }
    log.debug("Finding Login User with email:{}", email);

    Optional<UserMetadata> userOpt = metadataRepository.findByEmailId(email);
    if (userOpt.isPresent()) {
      log.debug("Found User Login Details with email:{}", email);
      return userOpt.get();
    }
    log.error("User Metadata NOT found for email:{}", email);
    throw new UserNotFoundException(
        String.format("User Login Metadata NOT found for email:%s", email));
  }

  @Override
  public void UserChangePassword(UserChangePasswordRequestModel changePasswordRequest) {
    // TODO Auto-generated method stub

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUserByUsername(username);
  }
}
