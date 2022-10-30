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

package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.subho.lunchbooking.models.*;
import xyz.subho.lunchbooking.services.LoginService;

@RestController
@Slf4j
public class LoginController {

  @Autowired private LoginService loginService;

  @PostMapping(EndpointPropertyKey.LOGIN_USER_REGISTRATION)
  @ResponseStatus(code = HttpStatus.CREATED)
  public OtpModel registerUser(@RequestBody @Valid UserRegistrationModel user) {
    log.debug("Initializing User Registration for:{}", user.getEmailId());
    return loginService.createUser(user);
  }

  @PostMapping(EndpointPropertyKey.LOGIN_USER)
  public UserLoginResponseModel loginUser(
      @RequestBody @Valid UserLoginRequestModel userLoginDetails) {
    log.trace(
        "Applied login details id :{}, password :{}",
        userLoginDetails.getUsername(),
        userLoginDetails);
    return loginService.login(userLoginDetails);
  }

  @PutMapping(EndpointPropertyKey.LOGIN_OTP_VALIDATE)
  public UserLoginResponseModel validateOtp(@RequestBody @Valid OtpRequestModel otpRequestModel) {
    return loginService.validateOtp(otpRequestModel);
  }

  @PostMapping(EndpointPropertyKey.LOGIN_OTP_RESEND)
  public OtpModel resendOtp(@RequestBody @Valid OtpModel otpRequestModel) {
    return loginService.resendOtp(otpRequestModel.salt());
  }

  @PostMapping(EndpointPropertyKey.LOGIN_CHECK_USER_NAME)
  public FoundResponseModel checkUsernameExist(@PathVariable String username) {
    return new FoundResponseModel(loginService.checkUserNameExists(username));
  }

  @PostMapping(EndpointPropertyKey.LOGIN_CHECK_PHONE_NUMBER)
  public FoundResponseModel checkPhoneExist(@PathVariable String phone) {
    return new FoundResponseModel(loginService.checkPhoneExists(phone));
  }

  @PostMapping(EndpointPropertyKey.LOGIN_CHANGE_PASSWORD)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void userChangePassword(
      @RequestBody @Valid UserChangePasswordRequestModel changePasswordRequestModel,
      Principal principal) {
    loginService.userChangePassword(
        changePasswordRequestModel, Long.parseLong(principal.getName()));
  }

  @PostMapping(EndpointPropertyKey.FORGET_PASSWORD)
  public OtpModel forgetPasswordOtpRequest(@PathVariable String username) {
    return loginService.createOtp(username);
  }

  @PostMapping(EndpointPropertyKey.FORGET_PASSWORD_NEW_PASSWORD)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void registerNewPassword(
      @RequestBody @Valid NewPasswordRequest passwordRequest, Principal principal) {
    loginService.forgetPassword(passwordRequest.newPassword(), Long.parseLong(principal.getName()));
  }
}
