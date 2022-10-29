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
