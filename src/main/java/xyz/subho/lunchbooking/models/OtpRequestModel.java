package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequestModel implements Serializable {

  @NotBlank(message = "Salt Cannot be Blank")
  Long salt;

  @Max(value = 6, message = "OTP cannot be more than 6 digits")
  @Min(value = 6, message = "OTP cannot be less than 6 digits")
  Integer otp;
}
