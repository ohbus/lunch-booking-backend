package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserLoginRequestModel implements Serializable {

  private static final long serialVersionUID = 679919590308705275L;

  @Email(message = "Please provide a valid email ID as username")
  @NotBlank(message = "Username / Email cannot be blank")
  private String username;

  @NotBlank(message = "Password cannot be blank")
  private String password;
}
