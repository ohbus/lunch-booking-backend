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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.entities.security.Roles;
import xyz.subho.lunchbooking.entities.security.UserLogin;
import xyz.subho.lunchbooking.exceptions.InvalidUsernameException;
import xyz.subho.lunchbooking.exceptions.RoleNotFoundException;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.mapper.Mapper;
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
}
