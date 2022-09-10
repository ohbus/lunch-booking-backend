package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class UserRegistrationModel implements Serializable {

  private static final long serialVersionUID = 3887084628994969316L;

  @Email(message = "Please provide a valid Email ID")
  @NotBlank(message = "Email ID field cannot be blank")
  private String emailId;

  @NotBlank(message = "Password field cannot be blank")
  private String password;

  @NotBlank(message = "First Name field cannot be blank")
  private String firstName;

  @NotBlank(message = "Last Name field cannot be blank")
  private String lastName;

  @Pattern(
      regexp = "^[6-9][0-9]{9}$|^[0][6-9][0-9]{9}$|^[+][9][1][6-9][0-9]{9}$",
      message = "Please enter a valid phone number")
  @Size(min = 6, max = 15, message = "Please enter a valid phone number")
  private String mobile;
}
