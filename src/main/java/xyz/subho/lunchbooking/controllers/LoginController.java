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

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;
import xyz.subho.lunchbooking.services.LoginService;
import xyz.subho.lunchbooking.services.MailService;

@RestController
@Slf4j
public class LoginController {

  @Autowired private LoginService loginService;

  @Autowired private MailService mailService;

  @PostMapping(EndpointPropertyKey.LOGIN_USER_REGISTRATION)
  @ResponseStatus(code = HttpStatus.CREATED)
  public void registerUser(@RequestBody @Valid UserRegistrationModel user) {
    log.debug("Initializing User Regisration for:{}", user.getEmailId());
    loginService.createUser(user);
    log.debug("Completed User Registration for:{}", user.getEmailId());
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

  public void userChangePassword() {}
}
