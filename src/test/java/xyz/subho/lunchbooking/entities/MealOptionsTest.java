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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MealOptionsTest {
  /** Method under test: {@link MealOptions#MealOptions(String)} */
  @Test
  void testConstructor() {
    MealOptions actualMealOptions = new MealOptions("Name");
    assertTrue(actualMealOptions.getAvailableBookings().isEmpty());
    assertEquals(1L, actualMealOptions.getVersion().longValue());
    assertNull(actualMealOptions.getUpdatedBy());
    assertNull(actualMealOptions.getUpdatedAt());
    assertEquals("Name", actualMealOptions.getName());
    assertNull(actualMealOptions.getMeals());
    assertNull(actualMealOptions.getId());
    assertNull(actualMealOptions.getDeletedBy());
    assertNull(actualMealOptions.getDeletedAt());
    assertNull(actualMealOptions.getCreatedBy());
    assertNull(actualMealOptions.getCreatedAt());
    assertEquals(0, actualMealOptions.getCount());
    assertTrue(actualMealOptions.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#MealOptions(long, String)} */
  @Test
  void testConstructor2() {
    MealOptions actualMealOptions = new MealOptions(123L, "Name");

    assertTrue(actualMealOptions.getAvailableBookings().isEmpty());
    assertEquals(1L, actualMealOptions.getVersion().longValue());
    assertEquals("Name", actualMealOptions.getName());
    assertEquals(123L, actualMealOptions.getId().longValue());
    assertEquals(0, actualMealOptions.getCount());
    assertTrue(actualMealOptions.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#addBookings(List)} */
  @Test
  void testAddBookings() {
    MealOptions mealOptions = new MealOptions();
    mealOptions.addBookings(new ArrayList<>());
    assertTrue(mealOptions.getAvailableBookings().isEmpty());
    assertEquals(1L, mealOptions.getVersion().longValue());
    assertEquals(0, mealOptions.getCount());
    assertTrue(mealOptions.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#addAvailableBooking(AvailableBookings)} */
  @Test
  void testAddAvailableBooking() {
    // TODO: Complete this test.

    MealOptions mealOptions = new MealOptions();

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
    mealOptions1.setMeals(meals);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    AvailableBookings availableBookings = new AvailableBookings();
    availableBookings.setCount(3);
    availableBookings.setCreatedAt(1L);
    availableBookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings.setDate(LocalDate.ofEpochDay(1L));
    availableBookings.setDeletedAt(1L);
    availableBookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings.setId(123L);
    availableBookings.setMealOptions(mealOptions1);
    availableBookings.setUpdatedAt(1L);
    availableBookings.setUpdatedBy("2020-03-01");
    availableBookings.setVersion(1L);
    mealOptions.addAvailableBooking(availableBookings);
  }

  /** Method under test: {@link MealOptions#addAvailableBooking(AvailableBookings)} */
  @Test
  void testAddAvailableBooking2() {
    // TODO: Complete this test.

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

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addAvailableBooking(availableBookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions2);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    mealOptions1.addAvailableBooking(availableBookings1);
  }

  /** Method under test: {@link MealOptions#addAvailableBooking(AvailableBookings)} */
  @Test
  void testAddAvailableBooking3() {
    // TODO: Complete this test.

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
    mealOptions.setId(1L);
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

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addAvailableBooking(availableBookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    AvailableBookings availableBookings1 = new AvailableBookings();
    availableBookings1.setCount(3);
    availableBookings1.setCreatedAt(1L);
    availableBookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    availableBookings1.setDate(LocalDate.ofEpochDay(1L));
    availableBookings1.setDeletedAt(1L);
    availableBookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    availableBookings1.setId(123L);
    availableBookings1.setMealOptions(mealOptions2);
    availableBookings1.setUpdatedAt(1L);
    availableBookings1.setUpdatedBy("2020-03-01");
    availableBookings1.setVersion(1L);
    mealOptions1.addAvailableBooking(availableBookings1);
  }

  /** Method under test: {@link MealOptions#addBooking(Bookings)} */
  @Test
  void testAddBooking() {
    // TODO: Complete this test.

    MealOptions mealOptions = new MealOptions();

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
    mealOptions1.setMeals(meals);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions1);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);
    mealOptions.addBooking(bookings);
  }

  /** Method under test: {@link MealOptions#addBooking(Bookings)} */
  @Test
  void testAddBooking2() {
    // TODO: Complete this test.

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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(1L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(1L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(1L);
    userMetadata1.setUpdatedAt(1L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions2);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    mealOptions1.addBooking(bookings1);
  }

  /** Method under test: {@link MealOptions#addBooking(Bookings)} */
  @Test
  void testAddBooking3() {
    // TODO: Complete this test.

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
    mealOptions.setId(1L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(1L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(1L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(1L);
    userMetadata1.setUpdatedAt(1L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions2);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    mealOptions1.addBooking(bookings1);
  }

  /** Method under test: {@link MealOptions#addBooking(Bookings)} */
  @Test
  void testAddBooking4() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).setCreatedAt((Long) any());
    doNothing().when(meals).setCreatedBy((String) any());
    doNothing().when(meals).setDeletedAt((Long) any());
    doNothing().when(meals).setDeletedBy((String) any());
    doNothing().when(meals).setId((Long) any());
    doNothing().when(meals).setUpdatedAt((Long) any());
    doNothing().when(meals).setUpdatedBy((String) any());
    doNothing().when(meals).setVersion((Long) any());
    doNothing().when(meals).setActivatedAt((Long) any());
    doNothing().when(meals).setDate((LocalDate) any());
    doNothing().when(meals).setLockedAt((Long) any());
    doNothing().when(meals).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals).setName((String) any());
    doNothing().when(meals).setReadyAt((Long) any());
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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions1);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);
    mealOptions.addBooking(bookings);
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
  }

  /** Method under test: {@link MealOptions#removeBooking(Bookings)} */
  @Test
  void testRemoveBooking() {
    // TODO: Complete this test.

    MealOptions mealOptions = new MealOptions();

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
    mealOptions1.setMeals(meals);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions1);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);
    mealOptions.removeBooking(bookings);
  }

  /** Method under test: {@link MealOptions#removeBooking(Bookings)} */
  @Test
  void testRemoveBooking2() {
    // TODO: Complete this test.

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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(1L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(1L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(1L);
    userMetadata1.setUpdatedAt(1L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions2);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    mealOptions1.removeBooking(bookings1);
  }

  /** Method under test: {@link MealOptions#removeBooking(Bookings)} */
  @Test
  void testRemoveBooking3() {
    // TODO: Complete this test.

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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(1L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);

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

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals1);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(1L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(1L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(1L);
    userMetadata1.setUpdatedAt(1L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions2);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    mealOptions1.removeBooking(bookings1);
  }

  /** Method under test: {@link MealOptions#removeBooking(Bookings)} */
  @Test
  void testRemoveBooking4() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).setCreatedAt((Long) any());
    doNothing().when(meals).setCreatedBy((String) any());
    doNothing().when(meals).setDeletedAt((Long) any());
    doNothing().when(meals).setDeletedBy((String) any());
    doNothing().when(meals).setId((Long) any());
    doNothing().when(meals).setUpdatedAt((Long) any());
    doNothing().when(meals).setUpdatedBy((String) any());
    doNothing().when(meals).setVersion((Long) any());
    doNothing().when(meals).setActivatedAt((Long) any());
    doNothing().when(meals).setDate((LocalDate) any());
    doNothing().when(meals).setLockedAt((Long) any());
    doNothing().when(meals).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals).setName((String) any());
    doNothing().when(meals).setReadyAt((Long) any());
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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions1);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);
    mealOptions.removeBooking(bookings);
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
  }

  /** Method under test: {@link MealOptions#removeBookingById(long)} */
  @Test
  void testRemoveBookingById() {
    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    assertEquals(0, mealOptions.getCount());
    assertTrue(mealOptions.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#removeBookingById(long)} */
  @Test
  void testRemoveBookingById2() {
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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);
    mealOptions1.removeBookingById(123L);
    assertEquals(0, mealOptions1.getCount());
    assertTrue(mealOptions1.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#removeBookingById(long)} */
  @Test
  void testRemoveBookingById3() {
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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(6L);
    meals1.setCreatedAt(6L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(6L));
    meals1.setDeletedAt(6L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(6L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Name");
    meals1.setReadyAt(6L);
    meals1.setUpdatedAt(6L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(6L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(6L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(6L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(6L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(6L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(6L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(6L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Mobile");
    userMetadata1.setSubscribedAt(6L);
    userMetadata1.setUpdatedAt(6L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(6L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(6L);
    bookings1.setClaimedAt(6L);
    bookings1.setCreatedAt(6L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(6L));
    bookings1.setDeletedAt(6L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(6L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(6L);

    MealOptions mealOptions2 = new MealOptions();
    mealOptions2.addBooking(bookings1);
    mealOptions2.addBooking(bookings);
    mealOptions2.removeBookingById(123L);
    assertEquals(0, mealOptions2.getCount());
    assertTrue(mealOptions2.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptions#removeBookingById(long)} */
  @Test
  void testRemoveBookingById4() {
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

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(1L);
    bookings.setClaimedAt(1L);
    bookings.setCreatedAt(1L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(1L));
    bookings.setDeletedAt(1L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(6L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(1L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(1L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.addBooking(bookings);
    mealOptions1.removeBookingById(123L);
    assertEquals(1, mealOptions1.getCount());
    assertEquals(1, mealOptions1.getBookings().size());
  }

  /** Method under test: {@link MealOptions#equals(Object)} */
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
    assertNotEquals(mealOptions, null);
  }

  /** Method under test: {@link MealOptions#equals(Object)} */
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
    assertNotEquals(mealOptions, "Different type to MealOptions");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link MealOptions#equals(Object)}
   *   <li>{@link MealOptions#hashCode()}
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
    assertEquals(mealOptions, mealOptions);
    int expectedHashCodeResult = mealOptions.hashCode();
    assertEquals(expectedHashCodeResult, mealOptions.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link MealOptions#equals(Object)}
   *   <li>{@link MealOptions#hashCode()}
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
    assertEquals(mealOptions, mealOptions1);
    int expectedHashCodeResult = mealOptions.hashCode();
    assertEquals(expectedHashCodeResult, mealOptions1.hashCode());
  }
}
