package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserResponseModel implements Serializable {

  private static final long serialVersionUID = -4738759449624774954L;

  private Long id;

  @NotBlank(message = "First Name cannot be blank")
  private String firstName;

  @NotBlank(message = "Last Name cannot be blank")
  private String lastName;

  @NotBlank(message = "Email ID cannot be blank")
  private String emailId;

  @NotBlank(message = "Mobile Number cannot be blank")
  private String mobile;

  @Max(value = Long.MAX_VALUE, message = "Login epoch value too big")
  @Min(value = Long.MIN_VALUE, message = "Login epoch value too low")
  private Long lastLogin;
}
