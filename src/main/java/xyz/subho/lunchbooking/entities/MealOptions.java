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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "meal_options",
    indexes = {@Index(columnList = "name", name = "name")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class MealOptions extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = -6473537837965565355L;

  @Column(length = 30, nullable = false)
  private String name;

  @ManyToOne(targetEntity = Meals.class)
  private Meals meals;

  private int count = 0;

  @OneToMany(mappedBy = "mealOptions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Bookings> bookings = new HashSet<>();

  @OneToMany(mappedBy = "mealOptions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<AvailableBookings> availableBookings = new HashSet<>();

  public MealOptions(long id, String name) {
    this(name);
    this.setId(id);
  }

  public MealOptions(String name) {
    this.name = name;
    count = 0;
  }

  public void addBookings(List<Bookings> bookings) {
    if (Objects.nonNull(bookings))
      bookings.forEach(
          booking -> {
            this.bookings.add(booking);
            incrementCount();
          });
  }

  public void addAvailableBooking(AvailableBookings availableBookings) {
    if (Objects.nonNull(availableBookings)) {
      this.availableBookings.add(availableBookings);
    }
  }

  private void incrementCount() {
    count++;
  }

  public void addBooking(Bookings booking) {
    if (Objects.nonNull(booking)) addBookings(Arrays.asList(booking));
  }

  public void removeBooking(Bookings booking) {
    if (Objects.nonNull(booking) && Objects.nonNull(booking.getId()))
      removeBookingById(booking.getId());
  }

  public void removeBookingById(long bookingId) {
    Set<Bookings> toBeDeleted =
        bookings.stream()
            .filter(booking -> booking.getId().equals(bookingId))
            .collect(Collectors.toSet());
    bookings.removeAll(toBeDeleted);
    count -= toBeDeleted.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MealOptions)) return false;
    return this.getId() != null && this.getId().equals(((MealOptions) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
