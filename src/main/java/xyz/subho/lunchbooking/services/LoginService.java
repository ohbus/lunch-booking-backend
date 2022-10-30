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

import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.UserLogin;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.*;

public interface LoginService {

  public OtpModel createUser(@NonNull UserRegistrationModel user);

  public void addUserRole(@NonNull UserLogin user, long roleId);

  public UserLoginResponseModel login(@NonNull UserLoginRequestModel userRequest);

  public UserLogin getUserByUsername(@NonNull String username);

  public UserMetadata getUserByEmail(@NonNull String email);

  public UserLogin getUserById(long userId);

  public UserLogin addRole(long userId, long roleId);

  public UserLogin removeRole(long userId, long roleId);

  public boolean checkUserNameExists(String username);

  public void forgetPassword(@NonNull String newPassword, long userId);

  public boolean checkPhoneExists(String phone);

  public void userChangePassword(
      @NonNull UserChangePasswordRequestModel changePasswordRequest, long userId);

  public OtpModel createOtp(long userId, String email);

  public OtpModel createOtp(String username);

  public OtpModel resendOtp(long salt);

  public UserLoginResponseModel validateOtp(OtpRequestModel requestModel);
}
