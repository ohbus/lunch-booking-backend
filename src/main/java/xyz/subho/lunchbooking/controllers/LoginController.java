package xyz.subho.lunchbooking.controllers;

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

@RestController
@Slf4j
public class LoginController {

  @Autowired private LoginService loginService;

  @PostMapping(EndpointPropertyKey.LOGIN_USER_REGISTRATION)
  @ResponseStatus(code = HttpStatus.CREATED)
  public void registerUser(@RequestBody UserRegistrationModel user) {
    log.debug("Initializing User Regisration for:{}", user.getEmailId());
    loginService.createUser(user);
    log.debug("Completed User Registration for:{}", user.getEmailId());
  }

  @PostMapping(EndpointPropertyKey.LOGIN)
  public UserLoginResponseModel loginUser(@RequestBody UserLoginRequestModel userLoginDetails) {
    log.trace(
        "Applied login details id :{}, password :{}",
        userLoginDetails.getUsername(),
        userLoginDetails);
    return loginService.login(userLoginDetails);
  }

  public void userChangePassword() {}
}
