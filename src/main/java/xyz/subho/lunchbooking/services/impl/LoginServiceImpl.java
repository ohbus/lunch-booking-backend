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
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.OtpEntity;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.entities.security.Roles;
import xyz.subho.lunchbooking.entities.security.UserLogin;
import xyz.subho.lunchbooking.exceptions.*;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.*;
import xyz.subho.lunchbooking.repositories.OtpRepository;
import xyz.subho.lunchbooking.repositories.RolesRepository;
import xyz.subho.lunchbooking.repositories.UserLoginRepository;
import xyz.subho.lunchbooking.repositories.UserMetadataRepository;
import xyz.subho.lunchbooking.security.JwtHelper;
import xyz.subho.lunchbooking.services.*;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService, UserDetailsService {

  @Value("${otp.validity.mins:2}")
  private String expiryInMinutes;

  @Autowired private MailService mailService;

  @Autowired private UserService userService;

  @Autowired private OtpRepository otpRepository;

  @Autowired private UserLoginRepository loginRepository;

  @Autowired private UserMetadataRepository metadataRepository;

  @Autowired private RolesRepository rolesRepository;

  @Autowired private EncryptionService encryptionService;

  @Autowired
  @Qualifier("UserDetailsMapper")
  private Mapper<UserMetadata, UserResponseModel> loginMapper;

  @Value("${app.security.jwt.salt.length}")
  private String saltSize;

  @Value("${generated.password.size}:16")
  private String generatedPasswordSize;

  @Autowired private JwtHelper jwtHelper;

  @Override
  @Transactional
  public OtpModel createUser(UserRegistrationModel user) {

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
    return createOtp(userLogin.getId(), userLogin.getUsername());
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
  public UserLoginResponseModel login(
      UserLoginRequestModel userRequest, boolean bypassPasswordCheck) {
    if (StringUtils.isBlank(userRequest.getUsername())
        || StringUtils.isBlank(userRequest.getPassword())) {
      log.error("Username or Password cannot be blank");
      throw new InvalidLoginException("Username or Password cannot be blank");
    }

    var userLogin = getUserByUsername(userRequest.getUsername());
    log.info("Login Request Received from:{}", userLogin.getUsername());

    checkUserAccountStatus(userLogin);

    if (bypassPasswordCheck
        || encryptionService.isPasswordValid(
            userRequest.getPassword(), userLogin.getPassword(), userLogin.getSalt())) {
      log.debug("User:{} has validated their password", userLogin.getUsername());

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
    } else if (!userLogin.isAccountNonExpired()) {
      errorMsg = String.format(msgTemplate, username, "Expired");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    } else if (!userLogin.isAccountNonLocked()) {
      errorMsg = String.format(msgTemplate, username, "Locked");
      log.error(errorMsg);
      throw new InvalidLoginException(errorMsg);
    } else if (!userLogin.isCredentialsNonExpired()) {
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

  @Override
  public boolean checkUserNameExists(String username) {
    if (StringUtils.isBlank(username)) {
      log.warn("Username is being being checked against blank data.");
      return false;
    }
    return loginRepository.existsByUsername(username);
  }

  @Override
  public boolean checkPhoneExists(String phone) {
    if (StringUtils.isBlank(phone)) {
      log.warn("Phone number is being being checked against blank data.");
      return false;
    }
    return metadataRepository.existsByMobileIgnoreCase(phone);
  }

  public UserLogin getUserById(long userId) {

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
  @Transactional
  public void userChangePassword(
      @NonNull @Valid UserChangePasswordRequestModel changePasswordRequest, long userId) {
    log.debug("Changing Password for User ID:{}", userId);
    var user = getUserById(userId);
    if (encryptionService.isPasswordValid(
        changePasswordRequest.currentPassword(), user.getPassword(), user.getSalt())) {

      final String salt = encryptionService.generateSalt(Integer.parseInt(saltSize));
      user.setPassword(encryptionService.encrypt(changePasswordRequest.updatedPassword(), salt));
      user.setSalt(salt);
      log.debug("Changed Password for User ID:{}", userId);
    } else {
      log.error("Password Change FAILED for User ID:{}. Mismatched Password!", userId);
      throw new InvalidLoginException("Passwords did NOT match!");
    }
  }

  @Override
  @Transactional
  public void forgetPassword(@NonNull String newPassword, long userId) {
    log.debug("Changing Password for User ID:{}", userId);
    var user = getUserById(userId);
    final String salt = encryptionService.generateSalt(Integer.parseInt(saltSize));
    user.setPassword(encryptionService.encrypt(newPassword, salt));
    user.setSalt(salt);
    user.unExpireCredentials();
    log.debug("Password has been updated for User ID:{}", userId);
  }

  @Override
  @Transactional
  public OtpModel createOtp(long userId, String email) {
    var newOtp = new OtpEntity(userId);
    newOtp.setExpiresAt(LocalDateTime.now().plusMinutes(Long.parseLong(expiryInMinutes)));

    var otp = new SecureRandom().nextInt(100000, 999999);
    newOtp.setOtp(otp);

    mailService.sendMail(
        new Email(
            email,
            String.format("Your OTP is %s", otp),
            String.format("Hello!%n%nYour OTP for Lunch Application is: %s%n%nCheers!", otp)));
    newOtp.send();

    newOtp.issue();
    otpRepository.save(newOtp);
    return new OtpModel(newOtp.getId());
  }

  @Override
  @Transactional
  public OtpModel createOtp(String username) {
    var user = getUserByUsername(username);
    return createOtp(user.getId(), username);
  }

  @Override
  @Transactional
  public OtpModel resendOtp(long salt) {
    var otpOpt = otpRepository.findById(salt);
    if (otpOpt.isPresent()) {

      var otp = otpOpt.get();
      var user = userService.getUserById(otp.getUserId());

      otp.resend();

      return createOtp(otp.getUserId(), user.getEmailId());
    } else {
      log.error("Wrong ID sent for previous Otp:{}", salt);
      throw new InvalidOtpConfiguration("Please try after sometime.");
    }
  }

  @Override
  @Transactional
  public UserLoginResponseModel validateOtp(
      @NonNull @Valid OtpRequestModel requestModel, boolean invalidateCredentials) {
    var otpEntityOpt = otpRepository.findById(requestModel.salt());
    if (otpEntityOpt.isPresent()) {

      var otp = otpEntityOpt.get();
      if (otp.getOtp().equals(requestModel.otp())) {

        if (otp.isVerified()) {
          log.warn("OTP {} has been already verified!", requestModel);
          throw new InvalidOtpConfiguration("OTP is already used!");
        }

        if (!otp.isExpired()) {

          otp.verify();
          log.debug(
              "OTP:{} has been verified for User ID:{}", requestModel.salt(), otp.getUserId());

          var user = getUserById(otp.getUserId());
          user.enable();
          user.secure();
          mailService.sendMail(
              new Email(
                  user.getUsername(),
                  "Lunch Booking Account Activated",
                  "Hey!\n\nYour account is now READY to use.\n\nCheers!"));

          if (invalidateCredentials) {
            user.expireCredentials();
          }
          return login(new UserLoginRequestModel(user.getUsername(), user.getPassword()), true);
        }
        log.error("OTP:{} has expired", requestModel);
        throw new InvalidOtpConfiguration("OTP has expired!");
      }
      log.error("OTP:{} did not match!", requestModel);
      throw new InvalidOtpConfiguration("OTP did not match!");
    }
    log.error("OTP Invalid:{}", requestModel);
    throw new InvalidOtpConfiguration("Invalid OTP!");
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return getUserByUsername(username);
  }
}
