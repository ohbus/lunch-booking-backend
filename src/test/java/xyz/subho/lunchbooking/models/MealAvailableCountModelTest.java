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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MealAvailableCountModelTest {
  /** Method under test: {@link MealAvailableCountModel#MealAvailableCountModel(Map)} */
  @Test
  void testConstructor() {
    HashMap<Long, MealOptionCountModel> resultLongMealOptionCountModelMap = new HashMap<>();
    Map<Long, MealOptionCountModel> mealOptionCountWithIdResult =
        (new MealAvailableCountModel(resultLongMealOptionCountModelMap)).mealOptionCountWithId();
    assertSame(resultLongMealOptionCountModelMap, mealOptionCountWithIdResult);
    assertTrue(mealOptionCountWithIdResult.isEmpty());
  }

  /** Method under test: {@link MealAvailableCountModel#mealOptionCountWithId()} */
  @Test
  void testMealOptionCountWithId() {
    HashMap<Long, MealOptionCountModel> resultLongMealOptionCountModelMap = new HashMap<>();
    Map<Long, MealOptionCountModel> actualMealOptionCountWithIdResult =
        (new MealAvailableCountModel(resultLongMealOptionCountModelMap)).mealOptionCountWithId();
    assertSame(resultLongMealOptionCountModelMap, actualMealOptionCountWithIdResult);
    assertTrue(actualMealOptionCountWithIdResult.isEmpty());
  }
}
