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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingResponseModelTest {
  /** Method under test: {@link BookingResponseModel#availedAt()} */
  @Test
  void testAvailedAt() {
    assertEquals(
        1L,
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .availedAt()
            .longValue());
  }

  /**
   * Method under test: {@link BookingResponseModel#BookingResponseModel(long, String, String,
   * LocalDate, String, Long, Long)}
   */
  @Test
  void testConstructor() {
    BookingResponseModel actualBookingResponseModel =
        new BookingResponseModel(
            123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L);

    assertEquals(1L, actualBookingResponseModel.availedAt().longValue());
    assertEquals(123L, actualBookingResponseModel.mealOptionId().longValue());
    assertEquals("Meal Option", actualBookingResponseModel.mealOption());
    assertEquals("Doe", actualBookingResponseModel.lastName());
    assertEquals(123L, actualBookingResponseModel.id());
    assertEquals("Jane", actualBookingResponseModel.firstName());
    assertEquals("1970-01-02", actualBookingResponseModel.date().toString());
  }

  /** Method under test: {@link BookingResponseModel#date()} */
  @Test
  void testDate() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    LocalDate actualDateResult =
        (new BookingResponseModel(123L, "Jane", "Doe", ofEpochDayResult, "Meal Option", 123L, 1L))
            .date();
    assertSame(ofEpochDayResult, actualDateResult);
    assertEquals("1970-01-02", actualDateResult.toString());
  }

  /** Method under test: {@link BookingResponseModel#firstName()} */
  @Test
  void testFirstName() {
    assertEquals(
        "Jane",
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .firstName());
  }

  /** Method under test: {@link BookingResponseModel#id()} */
  @Test
  void testId() {
    assertEquals(
        123L,
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .id());
  }

  /** Method under test: {@link BookingResponseModel#lastName()} */
  @Test
  void testLastName() {
    assertEquals(
        "Doe",
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .lastName());
  }

  /** Method under test: {@link BookingResponseModel#mealOption()} */
  @Test
  void testMealOption() {
    assertEquals(
        "Meal Option",
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .mealOption());
  }

  /** Method under test: {@link BookingResponseModel#mealOptionId()} */
  @Test
  void testMealOptionId() {
    assertEquals(
        123L,
        (new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L))
            .mealOptionId()
            .longValue());
  }
}
