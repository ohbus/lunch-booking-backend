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

import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.exceptions.UserNotFoundException;
import xyz.subho.lunchbooking.repositories.UserMetadataRepository;
import xyz.subho.lunchbooking.services.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserMetadataRepository userMetadataRepository;

  @Override
  public UserMetadata getUserByEmail(String emailId) {

    log.debug("Finding User with Email:{}", emailId);
    var userOpt = userMetadataRepository.findByEmailId(emailId);
    if (userOpt.isEmpty()) {
      log.error("Cannot Find User with Email:{}", emailId);
      throw new UserNotFoundException(String.format("Cannot Find User with Email:%s", emailId));
    }
    log.debug("Found user with Email:{}", emailId);
    return userOpt.get();
  }

  @Override
  public UserMetadata getUserById(long id) {

    log.debug("Finding User with ID:{}", id);
    var userOpt = userMetadataRepository.findById(id);
    if (userOpt.isEmpty()) {
      log.error("Cannot Find User with ID:{}", id);
      throw new UserNotFoundException(String.format("Cannot Find User with ID:%s", id));
    }
    log.debug("Found user with ID:{}", id);
    return userOpt.get();
  }

  @Override
  public Set<UserMetadata> getUsersByFirstName(String firstName) {

    log.debug("Finding Users with First Name:{}", firstName);
    var usersList = userMetadataRepository.findByFirstNameContainsIgnoreCase(firstName);
    if (usersList.isEmpty()) {
      log.error("Cannot Find Users with First Name:{}", firstName);
      throw new UserNotFoundException(
          String.format("There are not users with First Name:%s", firstName));
    }
    log.debug("Found {} Users with First Name:{}", usersList.size(), firstName);
    return usersList;
  }

  @Override
  public Set<UserMetadata> getUsersByLastName(String lastname) {

    log.debug("Finding Users with Last Name:{}", lastname);
    var usersList = userMetadataRepository.findByLastNameContainsIgnoreCase(lastname);
    if (usersList.isEmpty()) {
      log.error("Cannot Find Users with Last Name:{}", lastname);
      throw new UserNotFoundException(
          String.format("There are not users with Last Name:%s", lastname));
    }
    log.debug("Found {} Users with Last Name:{}", usersList.size(), lastname);
    return usersList;
  }

  @Override
  public UserMetadata getUserByMobile(String mobile) {

    log.debug("Finding Users with Mobile:{}", mobile);
    var userOpt = userMetadataRepository.findByMobile(mobile);
    if (userOpt.isEmpty()) {
      log.error("Cannot Find Users with Mobile:{}", mobile);
      throw new UserNotFoundException(String.format("There are not users with Mobile:%s", mobile));
    }
    log.debug("Found User for Mobile:{}", mobile);
    return userOpt.get();
  }

  @Override
  @Transactional
  public UserMetadata saveUser(UserMetadata userMetadata) {
    if (Objects.isNull(userMetadata.getId()))
      throw new UserNotFoundException("User Metadata cannot be saved without an ID");
    return userMetadataRepository.save(userMetadata);
  }
}
