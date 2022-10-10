package xyz.subho.lunchbooking.models;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserLoginResponseModel {

  @NotBlank(message = "JWT Token cannot be blank")
  String jwtToken;

  @NotBlank(message = "User Model cannot be blank")
  UserResponseModel user;
}
