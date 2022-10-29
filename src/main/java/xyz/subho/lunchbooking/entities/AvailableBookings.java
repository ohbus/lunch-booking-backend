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

package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "available_bookings",
    indexes = {@Index(columnList = "date", name = "date")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AvailableBookings extends BaseEntity implements Serializable {

  @Column(nullable = false, updatable = false)
  LocalDate date;

  @ManyToOne
  @JoinColumn(name = "meal_options_id", updatable = false, nullable = false)
  MealOptions mealOptions;

  int count = 1;

  public AvailableBookings(LocalDate date, MealOptions mealOptions) {
    this.date = date;
    this.mealOptions = mealOptions;
  }

  public boolean isAvailable() {
    return count > 0;
  }

  public int claim() {
    if (isAvailable()) return --count;
    return -1;
  }

  public int add() {
    return ++count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    AvailableBookings that = (AvailableBookings) o;
    return count == that.count
        && Objects.equals(date, that.date)
        && Objects.equals(mealOptions, that.mealOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), date, mealOptions, count);
  }
}
