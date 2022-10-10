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
