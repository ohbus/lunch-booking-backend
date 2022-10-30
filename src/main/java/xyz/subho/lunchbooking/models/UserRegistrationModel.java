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
  @Size(min = 8, max = 32, message = "Password needs to me between 8 and 32 characters")
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
