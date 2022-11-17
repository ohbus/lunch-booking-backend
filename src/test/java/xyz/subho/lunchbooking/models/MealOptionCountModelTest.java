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

import org.junit.jupiter.api.Test;

class MealOptionCountModelTest {
  /** Method under test: {@link MealOptionCountModel#available()} */
  @Test
  void testAvailable() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).available());
  }

  /** Method under test: {@link MealOptionCountModel#MealOptionCountModel(int, int, int)} */
  @Test
  void testConstructor() {
    MealOptionCountModel actualMealOptionCountModel = new MealOptionCountModel(1, 1, 1);

    assertEquals(1, actualMealOptionCountModel.available());
    assertEquals(1, actualMealOptionCountModel.total());
    assertEquals(1, actualMealOptionCountModel.redeemed());
  }

  /** Method under test: {@link MealOptionCountModel#redeemed()} */
  @Test
  void testRedeemed() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).redeemed());
  }

  /** Method under test: {@link MealOptionCountModel#total()} */
  @Test
  void testTotal() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).total());
  }
}
