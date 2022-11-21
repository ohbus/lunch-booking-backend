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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AvailableBookingsTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link AvailableBookings#AvailableBookings(LocalDate, MealOptions)}
   *   <li>{@link AvailableBookings#add()}
   * </ul>
   */
  @Test
  void testConstructor() {
    LocalDate date = LocalDate.ofEpochDay(1L);

    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);
    assertEquals(2, (new AvailableBookings(date, mealOptions)).add());
  }

  /** Method under test: {@link AvailableBookings#isAvailable()} */
  @Test
  void testIsAvailable() {
    assertTrue((new AvailableBookings()).isAvailable());
  }

  /** Method under test: {@link AvailableBookings#isAvailable()} */
  @Test
  void testIsAvailable2() {
    LocalDate date = LocalDate.ofEpochDay(1L);
    assertFalse((new AvailableBookings(date, new MealOptions(), 0)).isAvailable());
  }

  /** Method under test: {@link AvailableBookings#claim()} */
  @Test
  void testClaim() {
    AvailableBookings availableBookings = new AvailableBookings();
    assertEquals(0, availableBookings.claim());
    assertEquals(0, availableBookings.getCount());
  }

  /** Method under test: {@link AvailableBookings#claim()} */
  @Test
  void testClaim2() {
    LocalDate date = LocalDate.ofEpochDay(1L);
    assertEquals(-1, (new AvailableBookings(date, new MealOptions(), 0)).claim());
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);
    assertNotEquals(availableBookings, null);
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals2() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);
    assertNotEquals(availableBookings, "Different type to AvailableBookings");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link AvailableBookings#equals(Object)}
   *   <li>{@link AvailableBookings#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);
    assertEquals(availableBookings, availableBookings);
    int expectedHashCodeResult = availableBookings.hashCode();
    assertEquals(expectedHashCodeResult, availableBookings.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link AvailableBookings#equals(Object)}
   *   <li>{@link AvailableBookings#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(1L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(1L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions1);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    assertEquals(availableBookings, availableBookings1);
    int expectedHashCodeResult = availableBookings.hashCode();
    assertEquals(expectedHashCodeResult, availableBookings1.hashCode());
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals5() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(1);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(1L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(1L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions1);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    assertNotEquals(availableBookings, availableBookings1);
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals6() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(3L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(1L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(1L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions1);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    assertNotEquals(availableBookings, availableBookings1);
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals7() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(3L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(1L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(1L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions1);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    assertNotEquals(availableBookings, availableBookings1);
  }

  /** Method under test: {@link AvailableBookings#equals(Object)} */
  @Test
  void testEquals8() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);
    MealOptions mealOptions = mock(MealOptions.class);
    doNothing().when(mealOptions).setCreatedAt((Long) any());
    doNothing().when(mealOptions).setCreatedBy((String) any());
    doNothing().when(mealOptions).setDeletedAt((Long) any());
    doNothing().when(mealOptions).setDeletedBy((String) any());
    doNothing().when(mealOptions).setId((Long) any());
    doNothing().when(mealOptions).setUpdatedAt((Long) any());
    doNothing().when(mealOptions).setUpdatedBy((String) any());
    doNothing().when(mealOptions).setVersion((Long) any());
    doNothing().when(mealOptions).removeBookingById(anyLong());
    doNothing().when(mealOptions).setAvailableBookings((Set<AvailableBookings>) any());
    doNothing().when(mealOptions).setBookings((Set<Bookings>) any());
    doNothing().when(mealOptions).setCount(anyInt());
    doNothing().when(mealOptions).setMeals((Meals) any());
    doNothing().when(mealOptions).setName((String) any());
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(1L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(1L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(1L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(1L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions1);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    assertNotEquals(availableBookings, availableBookings1);
  }
}
