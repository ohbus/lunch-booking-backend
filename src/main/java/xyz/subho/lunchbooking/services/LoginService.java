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

import xyz.subho.lunchbooking.entities.UserLogin;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.UserChangePasswordRequestModel;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;

public interface LoginService {

  public void createUser(UserRegistrationModel user);

  public void addUserRole(UserLogin user, long roleId);

  public UserLoginResponseModel login(UserLoginRequestModel userRequest);

  public UserLogin getUserByUsername(String username);

  public UserMetadata getUserByEmail(String email);

  public UserLogin addRole(long userId, long roleId);

  public UserLogin removeRole(long userId, long roleId);

  public void UserChangePassword(UserChangePasswordRequestModel changePasswordRequest);
}
