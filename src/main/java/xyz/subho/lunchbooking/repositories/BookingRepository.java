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
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

  boolean existsByDateAndUser_IdAndCancelledAtNull(@NonNull LocalDate date, @NonNull Long id);

  Set<Bookings> findByUser_IdAndDate(@NonNull Long id, @NonNull LocalDate date);

  Optional<Bookings> findByDateAndUser_IdAndCancelledAtNull(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateGreaterThanEqualAndUser_IdOrderByDateAsc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
      @NonNull LocalDate date, @NonNull Long id);

  List<Bookings> findByDateAndCancelledAtNull(@NonNull LocalDate date);

  int countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
      @NonNull LocalDate date, @NonNull Long id);

  int countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
      @NonNull LocalDate date, @NonNull Long id);
}
