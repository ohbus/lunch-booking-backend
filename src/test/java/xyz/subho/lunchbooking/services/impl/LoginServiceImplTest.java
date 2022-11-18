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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.OtpEntity;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.entities.security.Roles;
import xyz.subho.lunchbooking.entities.security.UserLogin;
import xyz.subho.lunchbooking.exceptions.InvalidLoginException;
import xyz.subho.lunchbooking.exceptions.InvalidOtpConfiguration;
import xyz.subho.lunchbooking.exceptions.InvalidUsernameException;
import xyz.subho.lunchbooking.exceptions.RoleNotFoundException;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.Email;
import xyz.subho.lunchbooking.models.OtpRequestModel;
import xyz.subho.lunchbooking.models.UserChangePasswordRequestModel;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;
import xyz.subho.lunchbooking.models.UserResponseModel;
import xyz.subho.lunchbooking.repositories.OtpRepository;
import xyz.subho.lunchbooking.repositories.RolesRepository;
import xyz.subho.lunchbooking.repositories.UserLoginRepository;
import xyz.subho.lunchbooking.repositories.UserMetadataRepository;
import xyz.subho.lunchbooking.security.JwtHelper;
import xyz.subho.lunchbooking.services.EncryptionService;
import xyz.subho.lunchbooking.services.MailService;
import xyz.subho.lunchbooking.services.UserService;

@ContextConfiguration(classes = {LoginServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LoginServiceImplTest {
  @MockBean private EncryptionService encryptionService;

  @MockBean private JwtHelper jwtHelper;

  @Autowired private LoginServiceImpl loginServiceImpl;

  @MockBean private MailService mailService;

  @MockBean(name = "UserDetailsMapper")
  private Mapper<UserMetadata, UserResponseModel> mapper;

  @MockBean private OtpRepository otpRepository;

  @MockBean private RolesRepository rolesRepository;

  @MockBean private UserLoginRepository userLoginRepository;

  @MockBean private UserMetadataRepository userMetadataRepository;

  @MockBean private UserService userService;

  /** Method under test: {@link LoginServiceImpl#createUser(UserRegistrationModel)} */
  @Test
  void testCreateUser() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(true);
    assertThrows(
        InvalidUsernameException.class,
        () ->
            loginServiceImpl.createUser(
                new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")));
    verify(userLoginRepository).existsByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#createUser(UserRegistrationModel)} */
  @Test
  void testCreateUser2() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(false);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(true);
    when(userMetadataRepository.save((UserMetadata) any())).thenReturn(userMetadata);
    assertThrows(
        InvalidUsernameException.class,
        () ->
            loginServiceImpl.createUser(
                new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")));
    verify(userLoginRepository).existsByUsername((String) any());
    verify(userMetadataRepository).existsByMobileIgnoreCase((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#createUser(UserRegistrationModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateUser3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NumberFormatException: For input string: "${app.security.jwt.salt.length}"
    //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    //       at java.lang.Integer.parseInt(Integer.java:654)
    //       at java.lang.Integer.parseInt(Integer.java:786)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createUser(LoginServiceImpl.java:100)

    when(userLoginRepository.existsByUsername((String) any())).thenReturn(false);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(false);
    when(userMetadataRepository.save((UserMetadata) any())).thenReturn(userMetadata);
    loginServiceImpl.createUser(
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link LoginServiceImpl#createUser(UserRegistrationModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateUser4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.UserRegistrationModel.getEmailId()" because "user" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createUser(LoginServiceImpl.java:87)

    when(userLoginRepository.existsByUsername((String) any())).thenReturn(false);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(true);
    when(userMetadataRepository.save((UserMetadata) any())).thenReturn(userMetadata);
    loginServiceImpl.createUser(null);
  }

  /** Method under test: {@link LoginServiceImpl#createUser(UserRegistrationModel)} */
  @Test
  void testCreateUser5() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(false);
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any()))
        .thenThrow(new UsernameNotFoundException("Starting User Registration for:{}"));
    when(userMetadataRepository.save((UserMetadata) any()))
        .thenThrow(new UsernameNotFoundException("Starting User Registration for:{}"));
    assertThrows(
        UsernameNotFoundException.class,
        () ->
            loginServiceImpl.createUser(
                new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")));
    verify(userLoginRepository).existsByUsername((String) any());
    verify(userMetadataRepository).existsByMobileIgnoreCase((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#addUserRole(UserLogin, long)} */
  @Test
  void testAddUserRole() {
    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    loginServiceImpl.addUserRole(userLogin, 123L);
    verify(rolesRepository).findById((Long) any());
    assertEquals(1, userLogin.getRoles().size());
  }

  /** Method under test: {@link LoginServiceImpl#addUserRole(UserLogin, long)} */
  @Test
  void testAddUserRole2() {
    when(rolesRepository.findById((Long) any())).thenReturn(Optional.empty());

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    assertThrows(RoleNotFoundException.class, () -> loginServiceImpl.addUserRole(userLogin, 123L));
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addUserRole(UserLogin, long)} */
  @Test
  void testAddUserRole3() {
    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getId()).thenReturn(123L);
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    loginServiceImpl.addUserRole(userLogin, 123L);
    verify(rolesRepository).findById((Long) any());
    verify(userLogin).getId();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#addUserRole(UserLogin, long)} */
  @Test
  void testAddUserRole4() {
    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(1L);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(1L);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Finding role with ID:{}");
    roles1.setUpdatedAt(1L);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(1L);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles1);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getId()).thenReturn(123L);
    when(userLogin.getRoles()).thenReturn(rolesSet);
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    loginServiceImpl.addUserRole(userLogin, 123L);
    verify(rolesRepository).findById((Long) any());
    verify(userLogin).getId();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    assertThrows(
        InvalidLoginException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin2() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(new UserResponseModel());
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    UserLoginResponseModel actualLoginResult =
        loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true);
    assertEquals("Create Jwt", actualLoginResult.getJwtToken());
    UserResponseModel user = actualLoginResult.getUser();
    assertNull(user.getEmailId());
    assertNull(user.getMobile());
    assertNull(user.getLastName());
    assertEquals(1L, user.getLastLogin().longValue());
    assertNull(user.getId());
    assertNull(user.getFirstName());
    verify(jwtHelper).createJwt((UserLogin) any());
    verify(mapper).transform((UserMetadata) any());
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).makeNewLogin();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin3() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(new UserResponseModel());
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenThrow(new InvalidUsernameException("An error occurred"));
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    assertThrows(
        InvalidUsernameException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(jwtHelper).createJwt((UserLogin) any());
    verify(mapper).transform((UserMetadata) any());
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).makeNewLogin();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin4() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Finding Login User with username:{}", 1L);

    when(mapper.transform((UserMetadata) any())).thenReturn(userResponseModel);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    UserLoginResponseModel actualLoginResult =
        loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true);
    assertEquals("Create Jwt", actualLoginResult.getJwtToken());
    assertSame(userResponseModel, actualLoginResult.getUser());
    verify(jwtHelper).createJwt((UserLogin) any());
    verify(mapper).transform((UserMetadata) any());
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).makeNewLogin();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testLogin5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.UserResponseModel.withLastLogin(java.lang.Long)" because the
    // return value of "xyz.subho.lunchbooking.mapper.Mapper.transform(Object)" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.login(LoginServiceImpl.java:161)

    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(null);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true);
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin6() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    UserResponseModel userResponseModel = mock(UserResponseModel.class);
    UserResponseModel userResponseModel1 = new UserResponseModel();
    when(userResponseModel.withLastLogin((Long) any())).thenReturn(userResponseModel1);
    when(mapper.transform((UserMetadata) any())).thenReturn(userResponseModel);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    UserLoginResponseModel actualLoginResult =
        loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true);
    assertEquals("Create Jwt", actualLoginResult.getJwtToken());
    assertSame(userResponseModel1, actualLoginResult.getUser());
    verify(jwtHelper).createJwt((UserLogin) any());
    verify(mapper).transform((UserMetadata) any());
    verify(userResponseModel).withLastLogin((Long) any());
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).makeNewLogin();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin7() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(mock(UserResponseModel.class));
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(false);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    assertThrows(
        InvalidLoginException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin8() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(mock(UserResponseModel.class));
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(false);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    assertThrows(
        InvalidLoginException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin9() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(mock(UserResponseModel.class));
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(false);
    when(userLogin.getUsername()).thenReturn("janedoe");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    assertThrows(
        InvalidLoginException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#login(UserLoginRequestModel, boolean)} */
  @Test
  void testLogin10() {
    when(jwtHelper.createJwt((UserLogin) any())).thenReturn("Create Jwt");
    when(mapper.transform((UserMetadata) any())).thenReturn(mock(UserResponseModel.class));
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.makeNewLogin()).thenReturn(1L);
    when(userLogin.isCredentialsNonExpired()).thenReturn(true);
    when(userLogin.isAccountNonLocked()).thenReturn(true);
    when(userLogin.isAccountNonExpired()).thenReturn(true);
    when(userLogin.isEnabled()).thenReturn(true);
    when(userLogin.getUsername()).thenReturn("");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult1 = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult1);
    assertThrows(
        UserNotFoundException.class,
        () -> loginServiceImpl.login(new UserLoginRequestModel("janedoe", "iloveyou"), true));
    verify(userLoginRepository).findByUsername((String) any());
    verify(userLogin).isAccountNonExpired();
    verify(userLogin).isAccountNonLocked();
    verify(userLogin).isCredentialsNonExpired();
    verify(userLogin).isEnabled();
    verify(userLogin, atLeast(1)).getUsername();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    assertSame(userLogin, loginServiceImpl.addRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole2() {
    when(userLoginRepository.findById((Long) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidUsernameException.class, () -> loginServiceImpl.addRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole3() {
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    loginServiceImpl.addRole(123L, 123L);
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).getUsername();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole4() {
    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Adding Role ID:{} with User ID:{}");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(rolesSet);
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(1L);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(1L);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(1L);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles1);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    loginServiceImpl.addRole(123L, 123L);
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).getUsername();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole5() {
    when(userLoginRepository.findById((Long) any())).thenReturn(Optional.empty());
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.addRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#addRole(long, long)} */
  @Test
  void testAddRole6() {
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    when(rolesRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(RoleNotFoundException.class, () -> loginServiceImpl.addRole(123L, 123L));
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    assertSame(userLogin, loginServiceImpl.removeRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole2() {
    when(userLoginRepository.findById((Long) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidUsernameException.class, () -> loginServiceImpl.removeRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole3() {
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    loginServiceImpl.removeRole(123L, 123L);
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).getUsername();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole4() {
    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Removing Role ID:{} with User ID:{}");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(rolesSet);
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(1L);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(1L);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(1L);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(1L);
    Optional<Roles> ofResult1 = Optional.of(roles1);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult1);
    loginServiceImpl.removeRole(123L, 123L);
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).getUsername();
    verify(userLogin).getRoles();
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole5() {
    when(userLoginRepository.findById((Long) any())).thenReturn(Optional.empty());
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);

    Roles roles = new Roles();
    roles.setCreatedAt(1L);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(1L);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(1L);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(1L);
    Optional<Roles> ofResult = Optional.of(roles);
    when(rolesRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.removeRole(123L, 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#removeRole(long, long)} */
  @Test
  void testRemoveRole6() {
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getUsername()).thenReturn("janedoe");
    when(userLogin.getRoles()).thenReturn(new HashSet<>());
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    when(rolesRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(RoleNotFoundException.class, () -> loginServiceImpl.removeRole(123L, 123L));
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
    verify(rolesRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkUserNameExists(String)} */
  @Test
  void testCheckUserNameExists() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(true);
    assertTrue(loginServiceImpl.checkUserNameExists("janedoe"));
    verify(userLoginRepository).existsByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkUserNameExists(String)} */
  @Test
  void testCheckUserNameExists2() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(false);
    assertFalse(loginServiceImpl.checkUserNameExists("janedoe"));
    verify(userLoginRepository).existsByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkUserNameExists(String)} */
  @Test
  void testCheckUserNameExists3() {
    when(userLoginRepository.existsByUsername((String) any())).thenReturn(true);
    assertFalse(loginServiceImpl.checkUserNameExists(""));
  }

  /** Method under test: {@link LoginServiceImpl#checkUserNameExists(String)} */
  @Test
  void testCheckUserNameExists4() {
    when(userLoginRepository.existsByUsername((String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class, () -> loginServiceImpl.checkUserNameExists("janedoe"));
    verify(userLoginRepository).existsByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkPhoneExists(String)} */
  @Test
  void testCheckPhoneExists() {
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(true);
    assertTrue(loginServiceImpl.checkPhoneExists("4105551212"));
    verify(userMetadataRepository).existsByMobileIgnoreCase((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkPhoneExists(String)} */
  @Test
  void testCheckPhoneExists2() {
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(false);
    assertFalse(loginServiceImpl.checkPhoneExists("4105551212"));
    verify(userMetadataRepository).existsByMobileIgnoreCase((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#checkPhoneExists(String)} */
  @Test
  void testCheckPhoneExists3() {
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any())).thenReturn(true);
    assertFalse(loginServiceImpl.checkPhoneExists(""));
  }

  /** Method under test: {@link LoginServiceImpl#checkPhoneExists(String)} */
  @Test
  void testCheckPhoneExists4() {
    when(userMetadataRepository.existsByMobileIgnoreCase((String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class, () -> loginServiceImpl.checkPhoneExists("4105551212"));
    verify(userMetadataRepository).existsByMobileIgnoreCase((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(userLogin, loginServiceImpl.getUserById(123L));
    verify(userLoginRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById2() {
    when(userLoginRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.getUserById(123L));
    verify(userLoginRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById3() {
    when(userLoginRepository.findById((Long) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(InvalidUsernameException.class, () -> loginServiceImpl.getUserById(123L));
    verify(userLoginRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByUsername(String)} */
  @Test
  void testGetUserByUsername() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    assertSame(userLogin, loginServiceImpl.getUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByUsername(String)} */
  @Test
  void testGetUserByUsername2() {
    when(userLoginRepository.findByUsername((String) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.getUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByUsername(String)} */
  @Test
  void testGetUserByUsername3() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.getUserByUsername(""));
  }

  /** Method under test: {@link LoginServiceImpl#getUserByUsername(String)} */
  @Test
  void testGetUserByUsername4() {
    when(userLoginRepository.findByUsername((String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class, () -> loginServiceImpl.getUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail() {
    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult);
    assertSame(userMetadata, loginServiceImpl.getUserByEmail("jane.doe@example.org"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail2() {
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(Optional.empty());
    assertThrows(
        UserNotFoundException.class, () -> loginServiceImpl.getUserByEmail("jane.doe@example.org"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail3() {
    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    Optional<UserMetadata> ofResult = Optional.of(userMetadata);
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(ofResult);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.getUserByEmail(""));
  }

  /** Method under test: {@link LoginServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail4() {
    when(userMetadataRepository.findByEmailId((String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class,
        () -> loginServiceImpl.getUserByEmail("jane.doe@example.org"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUserChangePassword() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NumberFormatException: For input string: "${app.security.jwt.salt.length}"
    //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    //       at java.lang.Integer.parseInt(Integer.java:654)
    //       at java.lang.Integer.parseInt(Integer.java:786)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.userChangePassword(LoginServiceImpl.java:312)

    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenReturn(true);

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    loginServiceImpl.userChangePassword(
        new UserChangePasswordRequestModel("iloveyou", "2020-03-01"), 123L);
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  void testUserChangePassword2() {
    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(
        InvalidUsernameException.class,
        () ->
            loginServiceImpl.userChangePassword(
                new UserChangePasswordRequestModel("iloveyou", "2020-03-01"), 123L));
    verify(encryptionService).isPasswordValid((String) any(), (String) any(), (String) any());
    verify(userLoginRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  void testUserChangePassword3() {
    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenReturn(false);

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(
        InvalidLoginException.class,
        () ->
            loginServiceImpl.userChangePassword(
                new UserChangePasswordRequestModel("iloveyou", "2020-03-01"), 123L));
    verify(encryptionService).isPasswordValid((String) any(), (String) any(), (String) any());
    verify(userLoginRepository).findById((Long) any());
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUserChangePassword4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NumberFormatException: For input string: "${app.security.jwt.salt.length}"
    //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    //       at java.lang.Integer.parseInt(Integer.java:654)
    //       at java.lang.Integer.parseInt(Integer.java:786)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.userChangePassword(LoginServiceImpl.java:312)

    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenReturn(true);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getPassword()).thenReturn("iloveyou");
    when(userLogin.getSalt()).thenReturn("Salt");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    loginServiceImpl.userChangePassword(
        new UserChangePasswordRequestModel("iloveyou", "2020-03-01"), 123L);
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  void testUserChangePassword5() {
    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenReturn(true);
    when(userLoginRepository.findById((Long) any())).thenReturn(Optional.empty());
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getPassword()).thenReturn("iloveyou");
    when(userLogin.getSalt()).thenReturn("Salt");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    assertThrows(
        UserNotFoundException.class,
        () ->
            loginServiceImpl.userChangePassword(
                new UserChangePasswordRequestModel("iloveyou", "2020-03-01"), 123L));
    verify(userLoginRepository).findById((Long) any());
    verify(userLogin).setCreatedAt((Long) any());
    verify(userLogin).setCreatedBy((String) any());
    verify(userLogin).setDeletedAt((Long) any());
    verify(userLogin).setDeletedBy((String) any());
    verify(userLogin).setId((Long) any());
    verify(userLogin).setUpdatedAt((Long) any());
    verify(userLogin).setUpdatedBy((String) any());
    verify(userLogin).setVersion((Long) any());
    verify(userLogin).setCredentialExpiredAt((Long) any());
    verify(userLogin).setCurrentLogin((Long) any());
    verify(userLogin).setEnabledAt((Long) any());
    verify(userLogin).setExpiredAt((Long) any());
    verify(userLogin).setLastLogin((Long) any());
    verify(userLogin).setLockedAt((Long) any());
    verify(userLogin).setPassword((String) any());
    verify(userLogin).setRoles((Set<Roles>) any());
    verify(userLogin).setSalt((String) any());
    verify(userLogin).setSecuredAt((Long) any());
    verify(userLogin).setUsername((String) any());
  }

  /**
   * Method under test: {@link LoginServiceImpl#userChangePassword(UserChangePasswordRequestModel,
   * long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testUserChangePassword6() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.UserChangePasswordRequestModel.currentPassword()" because
    // "changePasswordRequest" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.userChangePassword(LoginServiceImpl.java:310)

    when(encryptionService.isPasswordValid((String) any(), (String) any(), (String) any()))
        .thenReturn(true);
    UserLogin userLogin = mock(UserLogin.class);
    when(userLogin.getPassword()).thenReturn("iloveyou");
    when(userLogin.getSalt()).thenReturn("Salt");
    doNothing().when(userLogin).setCreatedAt((Long) any());
    doNothing().when(userLogin).setCreatedBy((String) any());
    doNothing().when(userLogin).setDeletedAt((Long) any());
    doNothing().when(userLogin).setDeletedBy((String) any());
    doNothing().when(userLogin).setId((Long) any());
    doNothing().when(userLogin).setUpdatedAt((Long) any());
    doNothing().when(userLogin).setUpdatedBy((String) any());
    doNothing().when(userLogin).setVersion((Long) any());
    doNothing().when(userLogin).setCredentialExpiredAt((Long) any());
    doNothing().when(userLogin).setCurrentLogin((Long) any());
    doNothing().when(userLogin).setEnabledAt((Long) any());
    doNothing().when(userLogin).setExpiredAt((Long) any());
    doNothing().when(userLogin).setLastLogin((Long) any());
    doNothing().when(userLogin).setLockedAt((Long) any());
    doNothing().when(userLogin).setPassword((String) any());
    doNothing().when(userLogin).setRoles((Set<Roles>) any());
    doNothing().when(userLogin).setSalt((String) any());
    doNothing().when(userLogin).setSecuredAt((Long) any());
    doNothing().when(userLogin).setUsername((String) any());
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    loginServiceImpl.userChangePassword(null, 123L);
  }

  /** Method under test: {@link LoginServiceImpl#forgetPassword(String, long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testForgetPassword() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NumberFormatException: For input string: "${app.security.jwt.salt.length}"
    //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
    //       at java.lang.Integer.parseInt(Integer.java:654)
    //       at java.lang.Integer.parseInt(Integer.java:786)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.forgetPassword(LoginServiceImpl.java:327)

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    loginServiceImpl.forgetPassword("iloveyou", 123L);
  }

  /** Method under test: {@link LoginServiceImpl#forgetPassword(String, long)} */
  @Test
  void testForgetPassword2() {
    when(userLoginRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(
        UserNotFoundException.class, () -> loginServiceImpl.forgetPassword("iloveyou", 123L));
    verify(userLoginRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#forgetPassword(String, long)} */
  @Test
  void testForgetPassword3() {
    when(userLoginRepository.findById((Long) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class, () -> loginServiceImpl.forgetPassword("iloveyou", 123L));
    verify(userLoginRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(long, String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateOtp() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the
    // return value of "xyz.subho.lunchbooking.entities.OtpEntity.getId()" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:352)

    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity);
    loginServiceImpl.createOtp(123L, "jane.doe@example.org");
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(long, String)} */
  @Test
  void testCreateOtp2() {
    doNothing().when(mailService).sendMail((Email) any());
    when(otpRepository.save((OtpEntity) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class,
        () -> loginServiceImpl.createOtp(123L, "jane.doe@example.org"));
    verify(mailService).sendMail((Email) any());
    verify(otpRepository).save((OtpEntity) any());
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(String)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateOtp3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the
    // return value of "xyz.subho.lunchbooking.entities.OtpEntity.getId()" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:352)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:359)

    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity);
    loginServiceImpl.createOtp("janedoe");
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(String)} */
  @Test
  void testCreateOtp4() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    doNothing().when(mailService).sendMail((Email) any());
    when(otpRepository.save((OtpEntity) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(InvalidUsernameException.class, () -> loginServiceImpl.createOtp("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
    verify(mailService).sendMail((Email) any());
    verify(otpRepository).save((OtpEntity) any());
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(String)} */
  @Test
  void testCreateOtp5() {
    when(userLoginRepository.findByUsername((String) any())).thenReturn(Optional.empty());
    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.createOtp("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#createOtp(String)} */
  @Test
  void testCreateOtp6() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    doNothing().when(mailService).sendMail((Email) any());
    when(otpRepository.save((OtpEntity) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.createOtp(""));
  }

  /** Method under test: {@link LoginServiceImpl#resendOtp(long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testResendOtp() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the
    // return value of "xyz.subho.lunchbooking.entities.OtpEntity.getId()" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:352)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.resendOtp(LoginServiceImpl.java:373)

    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult = Optional.of(otpEntity);

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity1);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    loginServiceImpl.resendOtp(1L);
  }

  /** Method under test: {@link LoginServiceImpl#resendOtp(long)} */
  @Test
  void testResendOtp2() {
    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult = Optional.of(otpEntity);
    when(otpRepository.save((OtpEntity) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    when(otpRepository.findById((Long) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    assertThrows(InvalidUsernameException.class, () -> loginServiceImpl.resendOtp(1L));
    verify(mailService).sendMail((Email) any());
    verify(otpRepository).save((OtpEntity) any());
    verify(otpRepository).findById((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link LoginServiceImpl#resendOtp(long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testResendOtp3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the
    // return value of "xyz.subho.lunchbooking.entities.OtpEntity.getId()" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:352)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.resendOtp(LoginServiceImpl.java:373)

    doNothing().when(mailService).sendMail((Email) any());
    OtpEntity otpEntity = mock(OtpEntity.class);
    when(otpEntity.getUserId()).thenReturn(123L);
    doNothing().when(otpEntity).resend();
    doNothing().when(otpEntity).setExpiresAt((LocalDateTime) any());
    doNothing().when(otpEntity).setId((Long) any());
    doNothing().when(otpEntity).setIssuesAt((LocalDateTime) any());
    doNothing().when(otpEntity).setOtp((Integer) any());
    doNothing().when(otpEntity).setResentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setSentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setUserId((Long) any());
    doNothing().when(otpEntity).setVerifiedAt((LocalDateTime) any());
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult = Optional.of(otpEntity);

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity1);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    loginServiceImpl.resendOtp(1L);
  }

  /** Method under test: {@link LoginServiceImpl#resendOtp(long)} */
  @Test
  void testResendOtp4() {
    doNothing().when(mailService).sendMail((Email) any());

    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity);
    when(otpRepository.findById((Long) any())).thenReturn(Optional.empty());
    OtpEntity otpEntity1 = mock(OtpEntity.class);
    when(otpEntity1.getUserId()).thenReturn(123L);
    doNothing().when(otpEntity1).resend();
    doNothing().when(otpEntity1).setExpiresAt((LocalDateTime) any());
    doNothing().when(otpEntity1).setId((Long) any());
    doNothing().when(otpEntity1).setIssuesAt((LocalDateTime) any());
    doNothing().when(otpEntity1).setOtp((Integer) any());
    doNothing().when(otpEntity1).setResentAt((LocalDateTime) any());
    doNothing().when(otpEntity1).setSentAt((LocalDateTime) any());
    doNothing().when(otpEntity1).setUserId((Long) any());
    doNothing().when(otpEntity1).setVerifiedAt((LocalDateTime) any());
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    assertThrows(InvalidOtpConfiguration.class, () -> loginServiceImpl.resendOtp(1L));
    verify(otpRepository).findById((Long) any());
    verify(otpEntity1).setExpiresAt((LocalDateTime) any());
    verify(otpEntity1).setId((Long) any());
    verify(otpEntity1).setIssuesAt((LocalDateTime) any());
    verify(otpEntity1).setOtp((Integer) any());
    verify(otpEntity1).setResentAt((LocalDateTime) any());
    verify(otpEntity1).setSentAt((LocalDateTime) any());
    verify(otpEntity1).setUserId((Long) any());
    verify(otpEntity1).setVerifiedAt((LocalDateTime) any());
  }

  /** Method under test: {@link LoginServiceImpl#resendOtp(long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testResendOtp5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the
    // return value of "xyz.subho.lunchbooking.entities.OtpEntity.getId()" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.createOtp(LoginServiceImpl.java:352)
    //       at
    // xyz.subho.lunchbooking.services.impl.LoginServiceImpl.resendOtp(LoginServiceImpl.java:373)

    doNothing().when(mailService).sendMail((Email) any());
    OtpEntity otpEntity = mock(OtpEntity.class);
    when(otpEntity.getUserId()).thenReturn(123L);
    doNothing().when(otpEntity).resend();
    doNothing().when(otpEntity).setExpiresAt((LocalDateTime) any());
    doNothing().when(otpEntity).setId((Long) any());
    doNothing().when(otpEntity).setIssuesAt((LocalDateTime) any());
    doNothing().when(otpEntity).setOtp((Integer) any());
    doNothing().when(otpEntity).setResentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setSentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setUserId((Long) any());
    doNothing().when(otpEntity).setVerifiedAt((LocalDateTime) any());
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult = Optional.of(otpEntity);

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    when(otpRepository.save((OtpEntity) any())).thenReturn(otpEntity1);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult);
    UserMetadata userMetadata = mock(UserMetadata.class);
    when(userMetadata.getEmailId()).thenReturn("42");
    doNothing().when(userMetadata).setCreatedAt((Long) any());
    doNothing().when(userMetadata).setCreatedBy((String) any());
    doNothing().when(userMetadata).setDeletedAt((Long) any());
    doNothing().when(userMetadata).setDeletedBy((String) any());
    doNothing().when(userMetadata).setId((Long) any());
    doNothing().when(userMetadata).setUpdatedAt((Long) any());
    doNothing().when(userMetadata).setUpdatedBy((String) any());
    doNothing().when(userMetadata).setVersion((Long) any());
    doNothing().when(userMetadata).setBookings((Set<Bookings>) any());
    doNothing().when(userMetadata).setEmailId((String) any());
    doNothing().when(userMetadata).setFirstName((String) any());
    doNothing().when(userMetadata).setLastName((String) any());
    doNothing().when(userMetadata).setMobile((String) any());
    doNothing().when(userMetadata).setSubscribedAt((Long) any());
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    loginServiceImpl.resendOtp(1L);
  }

  /** Method under test: {@link LoginServiceImpl#validateOtp(OtpRequestModel, boolean)} */
  @Test
  void testValidateOtp() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult = Optional.of(otpEntity);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(
        InvalidOtpConfiguration.class,
        () -> loginServiceImpl.validateOtp(new OtpRequestModel(1L, 1), true));
    verify(otpRepository).findById((Long) any());
  }

  /** Method under test: {@link LoginServiceImpl#validateOtp(OtpRequestModel, boolean)} */
  @Test
  void testValidateOtp2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);

    UserLogin userLogin1 = new UserLogin();
    userLogin1.setCreatedAt(1L);
    userLogin1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin1.setCredentialExpiredAt(1L);
    userLogin1.setCurrentLogin(1L);
    userLogin1.setDeletedAt(1L);
    userLogin1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin1.setEnabledAt(1L);
    userLogin1.setExpiredAt(1L);
    userLogin1.setId(123L);
    userLogin1.setLastLogin(1L);
    userLogin1.setLockedAt(1L);
    userLogin1.setPassword("iloveyou");
    userLogin1.setRoles(new HashSet<>());
    userLogin1.setSalt("Salt");
    userLogin1.setSecuredAt(1L);
    userLogin1.setUpdatedAt(1L);
    userLogin1.setUpdatedBy("2020-03-01");
    userLogin1.setUsername("janedoe");
    userLogin1.setVersion(1L);
    Optional<UserLogin> ofResult1 = Optional.of(userLogin1);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult1);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    doNothing().when(mailService).sendMail((Email) any());
    OtpEntity otpEntity = mock(OtpEntity.class);
    when(otpEntity.isExpired()).thenReturn(true);
    when(otpEntity.isVerified()).thenReturn(true);
    when(otpEntity.getUserId()).thenReturn(123L);
    doNothing().when(otpEntity).verify();
    when(otpEntity.getOtp()).thenReturn(1);
    doNothing().when(otpEntity).setExpiresAt((LocalDateTime) any());
    doNothing().when(otpEntity).setId((Long) any());
    doNothing().when(otpEntity).setIssuesAt((LocalDateTime) any());
    doNothing().when(otpEntity).setOtp((Integer) any());
    doNothing().when(otpEntity).setResentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setSentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setUserId((Long) any());
    doNothing().when(otpEntity).setVerifiedAt((LocalDateTime) any());
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult2 = Optional.of(otpEntity);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult2);
    assertThrows(
        InvalidOtpConfiguration.class,
        () -> loginServiceImpl.validateOtp(new OtpRequestModel(1L, 1), true));
    verify(otpRepository).findById((Long) any());
    verify(otpEntity).isVerified();
    verify(otpEntity).getOtp();
    verify(otpEntity).setExpiresAt((LocalDateTime) any());
    verify(otpEntity).setId((Long) any());
    verify(otpEntity).setIssuesAt((LocalDateTime) any());
    verify(otpEntity).setOtp((Integer) any());
    verify(otpEntity).setResentAt((LocalDateTime) any());
    verify(otpEntity).setSentAt((LocalDateTime) any());
    verify(otpEntity).setUserId((Long) any());
    verify(otpEntity).setVerifiedAt((LocalDateTime) any());
  }

  /** Method under test: {@link LoginServiceImpl#validateOtp(OtpRequestModel, boolean)} */
  @Test
  void testValidateOtp3() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);

    UserLogin userLogin1 = new UserLogin();
    userLogin1.setCreatedAt(1L);
    userLogin1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin1.setCredentialExpiredAt(1L);
    userLogin1.setCurrentLogin(1L);
    userLogin1.setDeletedAt(1L);
    userLogin1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin1.setEnabledAt(1L);
    userLogin1.setExpiredAt(1L);
    userLogin1.setId(123L);
    userLogin1.setLastLogin(1L);
    userLogin1.setLockedAt(1L);
    userLogin1.setPassword("iloveyou");
    userLogin1.setRoles(new HashSet<>());
    userLogin1.setSalt("Salt");
    userLogin1.setSecuredAt(1L);
    userLogin1.setUpdatedAt(1L);
    userLogin1.setUpdatedBy("2020-03-01");
    userLogin1.setUsername("janedoe");
    userLogin1.setVersion(1L);
    Optional<UserLogin> ofResult1 = Optional.of(userLogin1);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult1);
    when(userLoginRepository.findById((Long) any())).thenReturn(ofResult);
    doNothing().when(mailService).sendMail((Email) any());
    OtpEntity otpEntity = mock(OtpEntity.class);
    when(otpEntity.isExpired())
        .thenThrow(new UsernameNotFoundException("OTP:{} has been verified for User ID:{}"));
    when(otpEntity.isVerified())
        .thenThrow(new UsernameNotFoundException("OTP:{} has been verified for User ID:{}"));
    when(otpEntity.getUserId())
        .thenThrow(new UsernameNotFoundException("OTP:{} has been verified for User ID:{}"));
    doThrow(new UsernameNotFoundException("OTP:{} has been verified for User ID:{}"))
        .when(otpEntity)
        .verify();
    when(otpEntity.getOtp()).thenReturn(1);
    doNothing().when(otpEntity).setExpiresAt((LocalDateTime) any());
    doNothing().when(otpEntity).setId((Long) any());
    doNothing().when(otpEntity).setIssuesAt((LocalDateTime) any());
    doNothing().when(otpEntity).setOtp((Integer) any());
    doNothing().when(otpEntity).setResentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setSentAt((LocalDateTime) any());
    doNothing().when(otpEntity).setUserId((Long) any());
    doNothing().when(otpEntity).setVerifiedAt((LocalDateTime) any());
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    Optional<OtpEntity> ofResult2 = Optional.of(otpEntity);
    when(otpRepository.findById((Long) any())).thenReturn(ofResult2);
    assertThrows(
        UsernameNotFoundException.class,
        () -> loginServiceImpl.validateOtp(new OtpRequestModel(1L, 1), true));
    verify(otpRepository).findById((Long) any());
    verify(otpEntity).isVerified();
    verify(otpEntity).getOtp();
    verify(otpEntity).setExpiresAt((LocalDateTime) any());
    verify(otpEntity).setId((Long) any());
    verify(otpEntity).setIssuesAt((LocalDateTime) any());
    verify(otpEntity).setOtp((Integer) any());
    verify(otpEntity).setResentAt((LocalDateTime) any());
    verify(otpEntity).setSentAt((LocalDateTime) any());
    verify(otpEntity).setUserId((Long) any());
    verify(otpEntity).setVerifiedAt((LocalDateTime) any());
  }

  /** Method under test: {@link LoginServiceImpl#loadUserByUsername(String)} */
  @Test
  void testLoadUserByUsername() throws UsernameNotFoundException {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    assertSame(userLogin, loginServiceImpl.loadUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#loadUserByUsername(String)} */
  @Test
  void testLoadUserByUsername2() throws UsernameNotFoundException {
    when(userLoginRepository.findByUsername((String) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.loadUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }

  /** Method under test: {@link LoginServiceImpl#loadUserByUsername(String)} */
  @Test
  void testLoadUserByUsername3() throws UsernameNotFoundException {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(1L);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(1L);
    userLogin.setCurrentLogin(1L);
    userLogin.setDeletedAt(1L);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(1L);
    userLogin.setExpiredAt(1L);
    userLogin.setId(123L);
    userLogin.setLastLogin(1L);
    userLogin.setLockedAt(1L);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(1L);
    userLogin.setUpdatedAt(1L);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(1L);
    Optional<UserLogin> ofResult = Optional.of(userLogin);
    when(userLoginRepository.findByUsername((String) any())).thenReturn(ofResult);
    assertThrows(UserNotFoundException.class, () -> loginServiceImpl.loadUserByUsername(""));
  }

  /** Method under test: {@link LoginServiceImpl#loadUserByUsername(String)} */
  @Test
  void testLoadUserByUsername4() throws UsernameNotFoundException {
    when(userLoginRepository.findByUsername((String) any()))
        .thenThrow(new InvalidUsernameException("An error occurred"));
    assertThrows(
        InvalidUsernameException.class, () -> loginServiceImpl.loadUserByUsername("janedoe"));
    verify(userLoginRepository).findByUsername((String) any());
  }
}
