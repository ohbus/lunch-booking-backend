package xyz.subho.lunchbooking.controllers;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.subho.lunchbooking.models.Email;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserLoginResponseModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;
import xyz.subho.lunchbooking.services.LoginService;
import xyz.subho.lunchbooking.services.MailService;
import xyz.subho.lunchbooking.util.QRUtil;

import java.util.HashMap;
import java.util.Map;

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

    mailService.sendMail(
            new Email(
                    "subhrodipmohanta@gmail.com",
                    "QR Subject",
                    "QR Body"
            ));
    mailService.sendMail(
        new Email(
            "subhrodipmohanta@gmail.com",
            "QR Subject",
            "QR Body",
            "12345.png",
            new ByteArrayDataSource(
                QRUtil.getQRCodeImage("12345", 400, 400), "application/octet-stream")));

    Map<String, DataSource> attachments = new HashMap<>(10,1);
    for (int i = 201; i < 210 ; i++) {
      String name = String.valueOf(i);
      attachments.put(name + ".png", new ByteArrayDataSource(
              QRUtil.getQRCodeImage(name, 400, 400), "application/octet-stream"));
    }
    mailService.sendMail(
            new Email(
                    "subhrodipmohanta@gmail.com",
                    "QR Subject",
                    "QR Body")
                    .withAttachments(attachments));
    return loginService.login(userLoginDetails);
  }

  public void userChangePassword() {}
}
