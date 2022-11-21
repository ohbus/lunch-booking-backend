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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Principal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.subho.lunchbooking.models.ForgetPasswordUpdateRequestModel;
import xyz.subho.lunchbooking.models.NewPasswordRequest;
import xyz.subho.lunchbooking.models.OtpModel;
import xyz.subho.lunchbooking.models.OtpRequestModel;
import xyz.subho.lunchbooking.models.UserChangePasswordRequestModel;
import xyz.subho.lunchbooking.models.UserLoginRequestModel;
import xyz.subho.lunchbooking.models.UserRegistrationModel;
import xyz.subho.lunchbooking.services.LoginService;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
class LoginControllerTest {
  @Autowired private LoginController loginController;

  @MockBean private LoginService loginService;

  /** Method under test: {@link LoginController#checkPhoneExist(String)} */
  @Test
  void testCheckPhoneExist() throws Exception {
    when(loginService.checkPhoneExists((String) any())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/login/check/phone/{phone}", "4105551212");
    MockMvcBuilders.standaloneSetup(loginController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"found\":true}"));
  }

  /** Method under test: {@link LoginController#resendOtp(OtpModel)} */
  @Test
  void testResendOtp() throws Exception {
    when(loginService.resendOtp(anyLong())).thenReturn(new OtpModel(1L));
    MockHttpServletRequestBuilder contentTypeResult =
        MockMvcRequestBuilders.post("/login/otp/resend").contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(objectMapper.writeValueAsString(new OtpModel(1L)));
    MockMvcBuilders.standaloneSetup(loginController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"salt\":1}"));
  }

  /** Method under test: {@link LoginController#checkPhoneExist(String)} */
  @Test
  void testCheckPhoneExist2() throws Exception {
    when(loginService.checkPhoneExists((String) any())).thenReturn(true);
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder =
        SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link LoginController#checkUsernameExist(String)} */
  @Test
  void testCheckUsernameExist() throws Exception {
    when(loginService.checkUserNameExists((String) any())).thenReturn(true);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/login/check/username/{username}", "janedoe");
    MockMvcBuilders.standaloneSetup(loginController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"found\":true}"));
  }

  /**
   * Method under test: {@link
   * LoginController#forgetPasswordOtpRequest(ForgetPasswordUpdateRequestModel)}
   */
  @Test
  void testForgetPasswordOtpRequest() throws Exception {
    when(loginService.createOtp((String) any())).thenReturn(new OtpModel(1L));
    MockHttpServletRequestBuilder contentTypeResult =
        MockMvcRequestBuilders.post("/login/forget").contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(
            objectMapper.writeValueAsString(new ForgetPasswordUpdateRequestModel("janedoe")));
    MockMvcBuilders.standaloneSetup(loginController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"salt\":1}"));
  }

  /**
   * Method under test: {@link
   * LoginController#forgetPasswordOtpRequest(ForgetPasswordUpdateRequestModel)}
   */
  @Test
  void testForgetPasswordOtpRequest2() throws Exception {
    when(loginService.createOtp((String) any())).thenReturn(new OtpModel(1L));
    MockHttpServletRequestBuilder contentTypeResult =
        MockMvcRequestBuilders.post("/login/forget").contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(
            objectMapper.writeValueAsString(new ForgetPasswordUpdateRequestModel("")));
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link LoginController#loginUser(UserLoginRequestModel)} */
  @Test
  void testLoginUser() throws Exception {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel();
    userLoginRequestModel.setPassword("iloveyou");
    userLoginRequestModel.setUsername("janedoe");
    String content = (new ObjectMapper()).writeValueAsString(userLoginRequestModel);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link LoginController#registerNewPassword(NewPasswordRequest, Principal)}
   */
  @Test
  void testRegisterNewPassword() throws Exception {
    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/login/forget/new");
    postResult.characterEncoding("Encoding");
    MockHttpServletRequestBuilder contentTypeResult =
        postResult.contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(
            objectMapper.writeValueAsString(new NewPasswordRequest("iloveyou")));
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
  }

  /** Method under test: {@link LoginController#registerUser(UserRegistrationModel)} */
  @Test
  void testRegisterUser() throws Exception {
    UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
    userRegistrationModel.setEmailId("42");
    userRegistrationModel.setFirstName("Jane");
    userRegistrationModel.setLastName("Doe");
    userRegistrationModel.setMobile("Mobile");
    userRegistrationModel.setPassword("iloveyou");
    String content = (new ObjectMapper()).writeValueAsString(userRegistrationModel);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/login/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link LoginController#userChangePassword(UserChangePasswordRequestModel,
   * Principal)}
   */
  @Test
  void testUserChangePassword() throws Exception {
    MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/login/passwd");
    postResult.characterEncoding("Encoding");
    MockHttpServletRequestBuilder contentTypeResult =
        postResult.contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(
            objectMapper.writeValueAsString(
                new UserChangePasswordRequestModel("iloveyou", "2020-03-01")));
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
  }

  /** Method under test: {@link LoginController#validateOtp(OtpRequestModel, boolean)} */
  @Test
  void testValidateOtp() throws Exception {
    MockHttpServletRequestBuilder contentTypeResult =
        MockMvcRequestBuilders.put("/login/otp/validate")
            .header("x-otp-validate-forget", true)
            .contentType(MediaType.APPLICATION_JSON);

    ObjectMapper objectMapper = new ObjectMapper();
    MockHttpServletRequestBuilder requestBuilder =
        contentTypeResult.content(objectMapper.writeValueAsString(new OtpRequestModel(1L, 1)));
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(loginController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}
