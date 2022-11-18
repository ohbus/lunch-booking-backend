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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
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
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.repositories.UserMetadataRepository;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
  @MockBean private UserMetadataRepository userMetadataRepository;

  @Autowired private UserServiceImpl userServiceImpl;

  /** Method under test: {@link UserServiceImpl#getUserByEmail(String)} */
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
    assertSame(userMetadata, userServiceImpl.getUserByEmail("42"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail2() {
    when(userMetadataRepository.findByEmailId((String) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByEmail("42"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserByEmail(String)} */
  @Test
  void testGetUserByEmail3() {
    when(userMetadataRepository.findByEmailId((String) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByEmail("42"));
    verify(userMetadataRepository).findByEmailId((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById() {
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
    when(userMetadataRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(userMetadata, userServiceImpl.getUserById(123L));
    verify(userMetadataRepository).findById((Long) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById2() {
    when(userMetadataRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserById(123L));
    verify(userMetadataRepository).findById((Long) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserById(long)} */
  @Test
  void testGetUserById3() {
    when(userMetadataRepository.findById((Long) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserById(123L));
    verify(userMetadataRepository).findById((Long) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByFirstName(String)} */
  @Test
  void testGetUsersByFirstName() {
    when(userMetadataRepository.findByFirstNameContainsIgnoreCase((String) any()))
        .thenReturn(new HashSet<>());
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUsersByFirstName("Jane"));
    verify(userMetadataRepository).findByFirstNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByFirstName(String)} */
  @Test
  void testGetUsersByFirstName2() {
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
    userMetadata.setMobile("Finding Users with First Name:{}");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    HashSet<UserMetadata> userMetadataSet = new HashSet<>();
    userMetadataSet.add(userMetadata);
    when(userMetadataRepository.findByFirstNameContainsIgnoreCase((String) any()))
        .thenReturn(userMetadataSet);
    Set<UserMetadata> actualUsersByFirstName = userServiceImpl.getUsersByFirstName("Jane");
    assertSame(userMetadataSet, actualUsersByFirstName);
    assertEquals(1, actualUsersByFirstName.size());
    verify(userMetadataRepository).findByFirstNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByFirstName(String)} */
  @Test
  void testGetUsersByFirstName3() {
    when(userMetadataRepository.findByFirstNameContainsIgnoreCase((String) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUsersByFirstName("Jane"));
    verify(userMetadataRepository).findByFirstNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByLastName(String)} */
  @Test
  void testGetUsersByLastName() {
    when(userMetadataRepository.findByLastNameContainsIgnoreCase((String) any()))
        .thenReturn(new HashSet<>());
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUsersByLastName("Doe"));
    verify(userMetadataRepository).findByLastNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByLastName(String)} */
  @Test
  void testGetUsersByLastName2() {
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
    userMetadata.setMobile("Finding Users with Last Name:{}");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    HashSet<UserMetadata> userMetadataSet = new HashSet<>();
    userMetadataSet.add(userMetadata);
    when(userMetadataRepository.findByLastNameContainsIgnoreCase((String) any()))
        .thenReturn(userMetadataSet);
    Set<UserMetadata> actualUsersByLastName = userServiceImpl.getUsersByLastName("Doe");
    assertSame(userMetadataSet, actualUsersByLastName);
    assertEquals(1, actualUsersByLastName.size());
    verify(userMetadataRepository).findByLastNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUsersByLastName(String)} */
  @Test
  void testGetUsersByLastName3() {
    when(userMetadataRepository.findByLastNameContainsIgnoreCase((String) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUsersByLastName("Doe"));
    verify(userMetadataRepository).findByLastNameContainsIgnoreCase((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserByMobile(String)} */
  @Test
  void testGetUserByMobile() {
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
    when(userMetadataRepository.findByMobile((String) any())).thenReturn(ofResult);
    assertSame(userMetadata, userServiceImpl.getUserByMobile("Mobile"));
    verify(userMetadataRepository).findByMobile((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserByMobile(String)} */
  @Test
  void testGetUserByMobile2() {
    when(userMetadataRepository.findByMobile((String) any())).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByMobile("Mobile"));
    verify(userMetadataRepository).findByMobile((String) any());
  }

  /** Method under test: {@link UserServiceImpl#getUserByMobile(String)} */
  @Test
  void testGetUserByMobile3() {
    when(userMetadataRepository.findByMobile((String) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByMobile("Mobile"));
    verify(userMetadataRepository).findByMobile((String) any());
  }

  /** Method under test: {@link UserServiceImpl#saveUser(UserMetadata)} */
  @Test
  void testSaveUser() {
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
    when(userMetadataRepository.save((UserMetadata) any())).thenReturn(userMetadata);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(1L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(1L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(1L);
    userMetadata1.setUpdatedAt(1L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(1L);
    assertSame(userMetadata, userServiceImpl.saveUser(userMetadata1));
    verify(userMetadataRepository).save((UserMetadata) any());
  }

  /** Method under test: {@link UserServiceImpl#saveUser(UserMetadata)} */
  @Test
  void testSaveUser2() {
    when(userMetadataRepository.save((UserMetadata) any()))
        .thenThrow(new UserNotFoundException("An error occurred"));

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
    assertThrows(UserNotFoundException.class, () -> userServiceImpl.saveUser(userMetadata));
    verify(userMetadataRepository).save((UserMetadata) any());
  }
}
