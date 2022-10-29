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

package xyz.subho.lunchbooking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.Meals;

public interface MealsRepository extends JpaRepository<Meals, Long> {

  List<Meals> findByNameLikeIgnoreCaseOrderByDateDesc(@NonNull String name);

  Optional<Meals> findByDateAndMealOptions_BookingsMealOptions_Bookings(
      @NonNull LocalDate date, @NonNull Bookings bookings);

  List<Meals> findByDateGreaterThanEqualOrderByDateAsc(@NonNull LocalDate date);

  List<Meals> findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
      @NonNull LocalDate date);

  List<Meals> findByDateLessThanOrderByDateDesc(@NonNull LocalDate date);

  boolean existsByDate(@NonNull LocalDate date);
}
