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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class MealsModel implements Serializable {

  private Long id;

  @NotBlank(message = "Name cannot be Blank")
  private String name;

  @NotNull(message = "Date Field cannot be null")
  private LocalDate date;

  private Long activatedAt;

  private Long lockedAt;

  private Long readyAt;

  @Size(min = 1, message = "Meal Options should contain greater than equals to 1 option")
  private Set<MealOptionsModel> mealOptions = new HashSet<>();

  public MealsModel(String name, LocalDate date, Set<MealOptionsModel> mealOptions) {
    this.name = name;
    this.date = date;
    this.mealOptions = mealOptions;
  }

  public MealsModel(String name, LocalDate date) {
    this.name = name;
    this.date = date;
  }
}
