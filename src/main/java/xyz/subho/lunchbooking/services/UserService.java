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

package xyz.subho.lunchbooking.services;

import java.util.Set;
import xyz.subho.lunchbooking.entities.UserMetadata;

public interface UserService {

  public UserMetadata getUserByEmail(String emailId);

  public UserMetadata getUserById(long id);

  public Set<UserMetadata> getUsersByFirstName(String firstName);

  public Set<UserMetadata> getUsersByLastName(String lastname);

  public UserMetadata getUserByMobile(String mobile);

  public UserMetadata saveUser(UserMetadata userMetadata);
}
