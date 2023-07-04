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

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Table(
    name = "bookings",
    indexes = {
      @Index(name = "date", columnList = "date"),
      @Index(name = "users_id", columnList = "users_id")
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class Bookings extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = -9138314713309636521L;

  @ManyToOne(optional = false)
  @JoinColumn(name = "users_id", updatable = false, nullable = false)
  @NotNull
  private UserMetadata user;

  private LocalDate date;

  private Long claimedAt;

  private Long cancelledAt;

  @ManyToOne(targetEntity = MealOptions.class, optional = false)
  @JoinColumn(name = "meal_options_id", updatable = false, nullable = false)
  private MealOptions mealOptions;

  public long availBooking() {
    claimedAt = System.currentTimeMillis();
    return claimedAt;
  }

  public long cancelBooking() {
    cancelledAt = System.currentTimeMillis();
    return cancelledAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Bookings bookings = (Bookings) o;
    return Objects.equals(user, bookings.user)
        && Objects.equals(date, bookings.date)
        && Objects.equals(claimedAt, bookings.claimedAt)
        && Objects.equals(cancelledAt, bookings.cancelledAt)
        && Objects.equals(mealOptions, bookings.mealOptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), user, date, claimedAt, cancelledAt, mealOptions);
  }
}
