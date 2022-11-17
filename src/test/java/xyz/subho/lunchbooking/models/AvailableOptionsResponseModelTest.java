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

class AvailableOptionsResponseModelTest {
  /**
   * Method under test: {@link AvailableOptionsResponseModel#AvailableOptionsResponseModel(long,
   * LocalDate, long, String, int)}
   */
  @Test
  void testConstructor() {
    AvailableOptionsResponseModel actualAvailableOptionsResponseModel =
        new AvailableOptionsResponseModel(
            123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3);

    assertEquals(3, actualAvailableOptionsResponseModel.count());
    assertEquals("Meal Option Name", actualAvailableOptionsResponseModel.mealOptionName());
    assertEquals(123L, actualAvailableOptionsResponseModel.mealOptionId());
    assertEquals(123L, actualAvailableOptionsResponseModel.id());
    assertEquals("1970-01-02", actualAvailableOptionsResponseModel.date().toString());
  }

  /** Method under test: {@link AvailableOptionsResponseModel#count()} */
  @Test
  void testCount() {
    assertEquals(
        3,
        (new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3))
            .count());
  }

  /** Method under test: {@link AvailableOptionsResponseModel#date()} */
  @Test
  void testDate() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    LocalDate actualDateResult =
        (new AvailableOptionsResponseModel(123L, ofEpochDayResult, 123L, "Meal Option Name", 3))
            .date();
    assertSame(ofEpochDayResult, actualDateResult);
    assertEquals("1970-01-02", actualDateResult.toString());
  }

  /** Method under test: {@link AvailableOptionsResponseModel#id()} */
  @Test
  void testId() {
    assertEquals(
        123L,
        (new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3))
            .id());
  }

  /** Method under test: {@link AvailableOptionsResponseModel#mealOptionId()} */
  @Test
  void testMealOptionId() {
    assertEquals(
        123L,
        (new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3))
            .mealOptionId());
  }

  /** Method under test: {@link AvailableOptionsResponseModel#mealOptionName()} */
  @Test
  void testMealOptionName() {
    assertEquals(
        "Meal Option Name",
        (new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3))
            .mealOptionName());
  }
}
