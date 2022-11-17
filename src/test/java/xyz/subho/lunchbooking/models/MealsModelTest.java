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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MealsModelTest {
  /** Method under test: {@link MealsModel#MealsModel(String, LocalDate)} */
  @Test
  void testConstructor() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    MealsModel actualMealsModel = new MealsModel("Name", ofEpochDayResult);

    assertNull(actualMealsModel.getActivatedAt());
    assertNull(actualMealsModel.getReadyAt());
    assertEquals("Name", actualMealsModel.getName());
    assertTrue(actualMealsModel.getMealOptions().isEmpty());
    assertNull(actualMealsModel.getLockedAt());
    assertNull(actualMealsModel.getId());
    LocalDate date = actualMealsModel.getDate();
    assertSame(ofEpochDayResult, date);
    assertEquals("1970-01-02", date.toString());
  }

  /** Method under test: {@link MealsModel#MealsModel(String, LocalDate, Set)} */
  @Test
  void testConstructor2() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    MealsModel actualMealsModel = new MealsModel("Name", ofEpochDayResult, mealOptionsModelSet);

    assertNull(actualMealsModel.getActivatedAt());
    assertNull(actualMealsModel.getReadyAt());
    assertEquals("Name", actualMealsModel.getName());
    Set<MealOptionsModel> mealOptions = actualMealsModel.getMealOptions();
    assertSame(mealOptionsModelSet, mealOptions);
    assertTrue(mealOptions.isEmpty());
    assertNull(actualMealsModel.getLockedAt());
    assertNull(actualMealsModel.getId());
    LocalDate date = actualMealsModel.getDate();
    assertSame(ofEpochDayResult, date);
    assertEquals("1970-01-02", date.toString());
  }
}
