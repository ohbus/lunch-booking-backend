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

package xyz.subho.lunchbooking.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.AvailableBookings;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.exceptions.InvalidBookingOperation;
import xyz.subho.lunchbooking.exceptions.SelectionLockedException;
import xyz.subho.lunchbooking.exceptions.SelectionNotAvailableException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.repositories.AvailableBookingsRepository;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.MealsService;
import xyz.subho.lunchbooking.services.UserService;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
  @MockBean private AvailableBookingsRepository availableBookingsRepository;

  @MockBean private BookingRepository bookingRepository;

  @Autowired private BookingServiceImpl bookingServiceImpl;

  @MockBean(name = "BookingResponseMapper")
  private Mapper<Bookings, BookingResponseModel> mapper;

  @MockBean private MealsRepository mealsRepository;

  @MockBean private MealsService mealsService;

  @MockBean private UserService userService;

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking() {
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
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(mealsService).getMealOptionById(anyLong());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking2() {
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
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions);
    when(userService.getUserById(anyLong()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking3() {
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
    when(mealOptions.getMeals()).thenReturn(meals1);
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
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(mealsService).getMealOptionById(anyLong());
    verify(mealOptions).setCreatedAt((Long) any());
    verify(mealOptions).setCreatedBy((String) any());
    verify(mealOptions).setDeletedAt((Long) any());
    verify(mealOptions).setDeletedBy((String) any());
    verify(mealOptions).setId((Long) any());
    verify(mealOptions).setUpdatedAt((Long) any());
    verify(mealOptions).setUpdatedBy((String) any());
    verify(mealOptions).setVersion((Long) any());
    verify(mealOptions).removeBookingById(anyLong());
    verify(mealOptions).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions).setBookings((Set<Bookings>) any());
    verify(mealOptions).setCount(anyInt());
    verify(mealOptions).setMeals((Meals) any());
    verify(mealOptions).setName((String) any());
    verify(mealOptions).getMeals();
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking4() {
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
    Meals meals1 = mock(Meals.class);
    when(meals1.isActivated()).thenReturn(true);
    when(meals1.isLocked()).thenReturn(true);
    when(meals1.getId()).thenReturn(123L);
    when(meals1.getName()).thenReturn("Name");
    doNothing().when(meals1).setCreatedAt((Long) any());
    doNothing().when(meals1).setCreatedBy((String) any());
    doNothing().when(meals1).setDeletedAt((Long) any());
    doNothing().when(meals1).setDeletedBy((String) any());
    doNothing().when(meals1).setId((Long) any());
    doNothing().when(meals1).setUpdatedAt((Long) any());
    doNothing().when(meals1).setUpdatedBy((String) any());
    doNothing().when(meals1).setVersion((Long) any());
    doNothing().when(meals1).setActivatedAt((Long) any());
    doNothing().when(meals1).setDate((LocalDate) any());
    doNothing().when(meals1).setLockedAt((Long) any());
    doNothing().when(meals1).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals1).setName((String) any());
    doNothing().when(meals1).setReadyAt((Long) any());
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
    when(mealOptions.getMeals()).thenReturn(meals1);
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
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(mealsService).getMealOptionById(anyLong());
    verify(mealOptions).setCreatedAt((Long) any());
    verify(mealOptions).setCreatedBy((String) any());
    verify(mealOptions).setDeletedAt((Long) any());
    verify(mealOptions).setDeletedBy((String) any());
    verify(mealOptions).setId((Long) any());
    verify(mealOptions).setUpdatedAt((Long) any());
    verify(mealOptions).setUpdatedBy((String) any());
    verify(mealOptions).setVersion((Long) any());
    verify(mealOptions).removeBookingById(anyLong());
    verify(mealOptions).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions).setBookings((Set<Bookings>) any());
    verify(mealOptions).setCount(anyInt());
    verify(mealOptions).setMeals((Meals) any());
    verify(mealOptions).setName((String) any());
    verify(mealOptions).getMeals();
    verify(meals1).isLocked();
    verify(meals1, atLeast(1)).getId();
    verify(meals1).getName();
    verify(meals1).setCreatedAt((Long) any());
    verify(meals1).setCreatedBy((String) any());
    verify(meals1).setDeletedAt((Long) any());
    verify(meals1).setDeletedBy((String) any());
    verify(meals1).setId((Long) any());
    verify(meals1).setUpdatedAt((Long) any());
    verify(meals1).setUpdatedBy((String) any());
    verify(meals1).setVersion((Long) any());
    verify(meals1).setActivatedAt((Long) any());
    verify(meals1).setDate((LocalDate) any());
    verify(meals1).setLockedAt((Long) any());
    verify(meals1).setMealOptions((Set<MealOptions>) any());
    verify(meals1).setName((String) any());
    verify(meals1).setReadyAt((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking5() {
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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    Optional<Bookings> ofResult = Optional.of(bookings1);
    when(bookingRepository.save((Bookings) any())).thenReturn(bookings);
    when(bookingRepository.findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);

    Meals meals2 = new Meals();
    meals2.setActivatedAt(1L);
    meals2.setCreatedAt(1L);
    meals2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals2.setDate(LocalDate.ofEpochDay(1L));
    meals2.setDeletedAt(1L);
    meals2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals2.setId(123L);
    meals2.setLockedAt(1L);
    meals2.setMealOptions(new HashSet<>());
    meals2.setName("Name");
    meals2.setReadyAt(1L);
    meals2.setUpdatedAt(1L);
    meals2.setUpdatedBy("2020-03-01");
    meals2.setVersion(1L);
    Meals meals3 = mock(Meals.class);
    when(meals3.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals3.isActivated()).thenReturn(true);
    when(meals3.isLocked()).thenReturn(false);
    when(meals3.getId()).thenReturn(123L);
    when(meals3.getName()).thenReturn("Name");
    doNothing().when(meals3).setCreatedAt((Long) any());
    doNothing().when(meals3).setCreatedBy((String) any());
    doNothing().when(meals3).setDeletedAt((Long) any());
    doNothing().when(meals3).setDeletedBy((String) any());
    doNothing().when(meals3).setId((Long) any());
    doNothing().when(meals3).setUpdatedAt((Long) any());
    doNothing().when(meals3).setUpdatedBy((String) any());
    doNothing().when(meals3).setVersion((Long) any());
    doNothing().when(meals3).setActivatedAt((Long) any());
    doNothing().when(meals3).setDate((LocalDate) any());
    doNothing().when(meals3).setLockedAt((Long) any());
    doNothing().when(meals3).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals3).setName((String) any());
    doNothing().when(meals3).setReadyAt((Long) any());
    meals3.setActivatedAt(1L);
    meals3.setCreatedAt(1L);
    meals3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals3.setDate(LocalDate.ofEpochDay(1L));
    meals3.setDeletedAt(1L);
    meals3.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals3.setId(123L);
    meals3.setLockedAt(1L);
    meals3.setMealOptions(new HashSet<>());
    meals3.setName("Name");
    meals3.setReadyAt(1L);
    meals3.setUpdatedAt(1L);
    meals3.setUpdatedBy("2020-03-01");
    meals3.setVersion(1L);
    MealOptions mealOptions2 = mock(MealOptions.class);
    doNothing().when(mealOptions2).setCreatedAt((Long) any());
    doNothing().when(mealOptions2).setCreatedBy((String) any());
    doNothing().when(mealOptions2).setDeletedAt((Long) any());
    doNothing().when(mealOptions2).setDeletedBy((String) any());
    doNothing().when(mealOptions2).setId((Long) any());
    doNothing().when(mealOptions2).setUpdatedAt((Long) any());
    doNothing().when(mealOptions2).setUpdatedBy((String) any());
    doNothing().when(mealOptions2).setVersion((Long) any());
    doNothing().when(mealOptions2).removeBookingById(anyLong());
    doNothing().when(mealOptions2).setAvailableBookings((Set<AvailableBookings>) any());
    doNothing().when(mealOptions2).setBookings((Set<Bookings>) any());
    doNothing().when(mealOptions2).setCount(anyInt());
    doNothing().when(mealOptions2).setMeals((Meals) any());
    doNothing().when(mealOptions2).setName((String) any());
    when(mealOptions2.getMeals()).thenReturn(meals3);
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals2);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);
    when(mealsService.getMealOptionsByBookingId(anyLong()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions2);

    UserMetadata userMetadata2 = new UserMetadata();
    userMetadata2.setBookings(new HashSet<>());
    userMetadata2.setCreatedAt(1L);
    userMetadata2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata2.setDeletedAt(1L);
    userMetadata2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata2.setEmailId("42");
    userMetadata2.setFirstName("Jane");
    userMetadata2.setId(123L);
    userMetadata2.setLastName("Doe");
    userMetadata2.setMobile("Mobile");
    userMetadata2.setSubscribedAt(1L);
    userMetadata2.setUpdatedAt(1L);
    userMetadata2.setUpdatedBy("2020-03-01");
    userMetadata2.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata2);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(bookingRepository)
        .findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
    verify(mealsService).getMealOptionById(anyLong());
    verify(mealsService).getMealOptionsByBookingId(anyLong());
    verify(mealOptions2).setCreatedAt((Long) any());
    verify(mealOptions2).setCreatedBy((String) any());
    verify(mealOptions2).setDeletedAt((Long) any());
    verify(mealOptions2).setDeletedBy((String) any());
    verify(mealOptions2).setId((Long) any());
    verify(mealOptions2).setUpdatedAt((Long) any());
    verify(mealOptions2).setUpdatedBy((String) any());
    verify(mealOptions2).setVersion((Long) any());
    verify(mealOptions2).removeBookingById(anyLong());
    verify(mealOptions2).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions2).setBookings((Set<Bookings>) any());
    verify(mealOptions2).setCount(anyInt());
    verify(mealOptions2).setMeals((Meals) any());
    verify(mealOptions2).setName((String) any());
    verify(mealOptions2).getMeals();
    verify(meals3).isActivated();
    verify(meals3).isLocked();
    verify(meals3, atLeast(1)).getId();
    verify(meals3).getDate();
    verify(meals3).setCreatedAt((Long) any());
    verify(meals3).setCreatedBy((String) any());
    verify(meals3).setDeletedAt((Long) any());
    verify(meals3).setDeletedBy((String) any());
    verify(meals3).setId((Long) any());
    verify(meals3).setUpdatedAt((Long) any());
    verify(meals3).setUpdatedBy((String) any());
    verify(meals3).setVersion((Long) any());
    verify(meals3).setActivatedAt((Long) any());
    verify(meals3).setDate((LocalDate) any());
    verify(meals3).setLockedAt((Long) any());
    verify(meals3).setMealOptions((Set<MealOptions>) any());
    verify(meals3).setName((String) any());
    verify(meals3).setReadyAt((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createBooking(long, long)} */
  @Test
  void testCreateBooking6() {
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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    Optional<Bookings> ofResult = Optional.of(bookings1);
    when(bookingRepository.save((Bookings) any())).thenReturn(bookings);
    when(bookingRepository.findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);

    Meals meals2 = new Meals();
    meals2.setActivatedAt(1L);
    meals2.setCreatedAt(1L);
    meals2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals2.setDate(LocalDate.ofEpochDay(1L));
    meals2.setDeletedAt(1L);
    meals2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals2.setId(123L);
    meals2.setLockedAt(1L);
    meals2.setMealOptions(new HashSet<>());
    meals2.setName("Name");
    meals2.setReadyAt(1L);
    meals2.setUpdatedAt(1L);
    meals2.setUpdatedBy("2020-03-01");
    meals2.setVersion(1L);
    Meals meals3 = mock(Meals.class);
    when(meals3.getDate()).thenThrow(new SelectionLockedException("An error occurred"));
    when(meals3.isActivated()).thenReturn(true);
    when(meals3.isLocked()).thenReturn(false);
    when(meals3.getId()).thenReturn(123L);
    when(meals3.getName()).thenReturn("Name");
    doNothing().when(meals3).setCreatedAt((Long) any());
    doNothing().when(meals3).setCreatedBy((String) any());
    doNothing().when(meals3).setDeletedAt((Long) any());
    doNothing().when(meals3).setDeletedBy((String) any());
    doNothing().when(meals3).setId((Long) any());
    doNothing().when(meals3).setUpdatedAt((Long) any());
    doNothing().when(meals3).setUpdatedBy((String) any());
    doNothing().when(meals3).setVersion((Long) any());
    doNothing().when(meals3).setActivatedAt((Long) any());
    doNothing().when(meals3).setDate((LocalDate) any());
    doNothing().when(meals3).setLockedAt((Long) any());
    doNothing().when(meals3).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals3).setName((String) any());
    doNothing().when(meals3).setReadyAt((Long) any());
    meals3.setActivatedAt(1L);
    meals3.setCreatedAt(1L);
    meals3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals3.setDate(LocalDate.ofEpochDay(1L));
    meals3.setDeletedAt(1L);
    meals3.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals3.setId(123L);
    meals3.setLockedAt(1L);
    meals3.setMealOptions(new HashSet<>());
    meals3.setName("Name");
    meals3.setReadyAt(1L);
    meals3.setUpdatedAt(1L);
    meals3.setUpdatedBy("2020-03-01");
    meals3.setVersion(1L);
    MealOptions mealOptions2 = mock(MealOptions.class);
    doNothing().when(mealOptions2).setCreatedAt((Long) any());
    doNothing().when(mealOptions2).setCreatedBy((String) any());
    doNothing().when(mealOptions2).setDeletedAt((Long) any());
    doNothing().when(mealOptions2).setDeletedBy((String) any());
    doNothing().when(mealOptions2).setId((Long) any());
    doNothing().when(mealOptions2).setUpdatedAt((Long) any());
    doNothing().when(mealOptions2).setUpdatedBy((String) any());
    doNothing().when(mealOptions2).setVersion((Long) any());
    doNothing().when(mealOptions2).removeBookingById(anyLong());
    doNothing().when(mealOptions2).setAvailableBookings((Set<AvailableBookings>) any());
    doNothing().when(mealOptions2).setBookings((Set<Bookings>) any());
    doNothing().when(mealOptions2).setCount(anyInt());
    doNothing().when(mealOptions2).setMeals((Meals) any());
    doNothing().when(mealOptions2).setName((String) any());
    when(mealOptions2.getMeals()).thenReturn(meals3);
    mealOptions2.removeBookingById(123L);
    mealOptions2.setAvailableBookings(new HashSet<>());
    mealOptions2.setBookings(new HashSet<>());
    mealOptions2.setCount(3);
    mealOptions2.setCreatedAt(1L);
    mealOptions2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions2.setDeletedAt(1L);
    mealOptions2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions2.setId(123L);
    mealOptions2.setMeals(meals2);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);
    when(mealsService.getMealOptionsByBookingId(anyLong()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions2);

    UserMetadata userMetadata2 = new UserMetadata();
    userMetadata2.setBookings(new HashSet<>());
    userMetadata2.setCreatedAt(1L);
    userMetadata2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata2.setDeletedAt(1L);
    userMetadata2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata2.setEmailId("42");
    userMetadata2.setFirstName("Jane");
    userMetadata2.setId(123L);
    userMetadata2.setLastName("Doe");
    userMetadata2.setMobile("Mobile");
    userMetadata2.setSubscribedAt(1L);
    userMetadata2.setUpdatedAt(1L);
    userMetadata2.setUpdatedBy("2020-03-01");
    userMetadata2.setVersion(1L);
    when(userService.getUserById(anyLong())).thenReturn(userMetadata2);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.createBooking(123L, 123L));
    verify(mealsService).getMealOptionById(anyLong());
    verify(mealOptions2).setCreatedAt((Long) any());
    verify(mealOptions2).setCreatedBy((String) any());
    verify(mealOptions2).setDeletedAt((Long) any());
    verify(mealOptions2).setDeletedBy((String) any());
    verify(mealOptions2).setId((Long) any());
    verify(mealOptions2).setUpdatedAt((Long) any());
    verify(mealOptions2).setUpdatedBy((String) any());
    verify(mealOptions2).setVersion((Long) any());
    verify(mealOptions2).removeBookingById(anyLong());
    verify(mealOptions2).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions2).setBookings((Set<Bookings>) any());
    verify(mealOptions2).setCount(anyInt());
    verify(mealOptions2).setMeals((Meals) any());
    verify(mealOptions2).setName((String) any());
    verify(mealOptions2).getMeals();
    verify(meals3).isActivated();
    verify(meals3).isLocked();
    verify(meals3, atLeast(1)).getId();
    verify(meals3).getDate();
    verify(meals3).setCreatedAt((Long) any());
    verify(meals3).setCreatedBy((String) any());
    verify(meals3).setDeletedAt((Long) any());
    verify(meals3).setDeletedBy((String) any());
    verify(meals3).setId((Long) any());
    verify(meals3).setUpdatedAt((Long) any());
    verify(meals3).setUpdatedBy((String) any());
    verify(meals3).setVersion((Long) any());
    verify(meals3).setActivatedAt((Long) any());
    verify(meals3).setDate((LocalDate) any());
    verify(meals3).setLockedAt((Long) any());
    verify(meals3).setMealOptions((Set<MealOptions>) any());
    verify(meals3).setName((String) any());
    verify(meals3).setReadyAt((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBookingById(long, long)} */
  @Test
  void testCancelBookingById() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata1);
    assertThrows(
        InvalidBookingOperation.class, () -> bookingServiceImpl.cancelBookingById(123L, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBookingById(long, long)} */
  @Test
  void testCancelBookingById2() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    when(userService.getUserById(anyLong()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.cancelBookingById(123L, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBookingById(long, long)} */
  @Test
  void testCancelBookingById3() {
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getCancelledAt()).thenReturn(1L);
    doNothing().when(bookings).setCreatedAt((Long) any());
    doNothing().when(bookings).setCreatedBy((String) any());
    doNothing().when(bookings).setDeletedAt((Long) any());
    doNothing().when(bookings).setDeletedBy((String) any());
    doNothing().when(bookings).setId((Long) any());
    doNothing().when(bookings).setUpdatedAt((Long) any());
    doNothing().when(bookings).setUpdatedBy((String) any());
    doNothing().when(bookings).setVersion((Long) any());
    doNothing().when(bookings).setCancelledAt((Long) any());
    doNothing().when(bookings).setClaimedAt((Long) any());
    doNothing().when(bookings).setDate((LocalDate) any());
    doNothing().when(bookings).setMealOptions((MealOptions) any());
    doNothing().when(bookings).setUser((UserMetadata) any());
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata1);
    assertThrows(
        InvalidBookingOperation.class, () -> bookingServiceImpl.cancelBookingById(123L, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(bookings).getCancelledAt();
    verify(bookings).setCreatedAt((Long) any());
    verify(bookings).setCreatedBy((String) any());
    verify(bookings).setDeletedAt((Long) any());
    verify(bookings).setDeletedBy((String) any());
    verify(bookings).setId((Long) any());
    verify(bookings).setUpdatedAt((Long) any());
    verify(bookings).setUpdatedBy((String) any());
    verify(bookings).setVersion((Long) any());
    verify(bookings).setCancelledAt((Long) any());
    verify(bookings).setClaimedAt((Long) any());
    verify(bookings).setDate((LocalDate) any());
    verify(bookings).setMealOptions((MealOptions) any());
    verify(bookings).setUser((UserMetadata) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#createAvailableMealForToday(MealOptions)} */
  @Test
  void testCreateAvailableMealForToday() {
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
    Optional<AvailableBookings> ofResult = Optional.of(availableBookings1);
    when(availableBookingsRepository.save((AvailableBookings) any())).thenReturn(availableBookings);
    when(availableBookingsRepository.findByDateAndMealOptions_Id((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);

    Meals meals2 = new Meals();
    meals2.setActivatedAt(1L);
    meals2.setCreatedAt(1L);
    meals2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals2.setDate(LocalDate.ofEpochDay(1L));
    meals2.setDeletedAt(1L);
    meals2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals2.setId(123L);
    meals2.setLockedAt(1L);
    meals2.setMealOptions(new HashSet<>());
    meals2.setName("Name");
    meals2.setReadyAt(1L);
    meals2.setUpdatedAt(1L);
    meals2.setUpdatedBy("2020-03-01");
    meals2.setVersion(1L);

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
    mealOptions2.setMeals(meals2);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);
    bookingServiceImpl.createAvailableMealForToday(mealOptions2);
    verify(availableBookingsRepository)
        .findByDateAndMealOptions_Id((LocalDate) any(), (Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#createAvailableMealForToday(MealOptions)} */
  @Test
  void testCreateAvailableMealForToday2() {
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
    AvailableBookings availableBookings1 = mock(AvailableBookings.class);
    when(availableBookings1.add()).thenReturn(2);
    when(availableBookings1.getId()).thenReturn(123L);
    doNothing().when(availableBookings1).setCount(anyInt());
    doNothing().when(availableBookings1).setDate((LocalDate) any());
    doNothing().when(availableBookings1).setMealOptions((MealOptions) any());
    doNothing().when(availableBookings1).setCreatedAt((Long) any());
    doNothing().when(availableBookings1).setCreatedBy((String) any());
    doNothing().when(availableBookings1).setDeletedAt((Long) any());
    doNothing().when(availableBookings1).setDeletedBy((String) any());
    doNothing().when(availableBookings1).setId((Long) any());
    doNothing().when(availableBookings1).setUpdatedAt((Long) any());
    doNothing().when(availableBookings1).setUpdatedBy((String) any());
    doNothing().when(availableBookings1).setVersion((Long) any());
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
    Optional<AvailableBookings> ofResult = Optional.of(availableBookings1);
    when(availableBookingsRepository.save((AvailableBookings) any())).thenReturn(availableBookings);
    when(availableBookingsRepository.findByDateAndMealOptions_Id((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);

    Meals meals2 = new Meals();
    meals2.setActivatedAt(1L);
    meals2.setCreatedAt(1L);
    meals2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals2.setDate(LocalDate.ofEpochDay(1L));
    meals2.setDeletedAt(1L);
    meals2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals2.setId(123L);
    meals2.setLockedAt(1L);
    meals2.setMealOptions(new HashSet<>());
    meals2.setName("Name");
    meals2.setReadyAt(1L);
    meals2.setUpdatedAt(1L);
    meals2.setUpdatedBy("2020-03-01");
    meals2.setVersion(1L);

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
    mealOptions2.setMeals(meals2);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);
    bookingServiceImpl.createAvailableMealForToday(mealOptions2);
    verify(availableBookingsRepository)
        .findByDateAndMealOptions_Id((LocalDate) any(), (Long) any());
    verify(availableBookings1).add();
    verify(availableBookings1).getId();
    verify(availableBookings1).setCount(anyInt());
    verify(availableBookings1).setDate((LocalDate) any());
    verify(availableBookings1).setMealOptions((MealOptions) any());
    verify(availableBookings1).setCreatedAt((Long) any());
    verify(availableBookings1).setCreatedBy((String) any());
    verify(availableBookings1).setDeletedAt((Long) any());
    verify(availableBookings1).setDeletedBy((String) any());
    verify(availableBookings1).setId((Long) any());
    verify(availableBookings1).setUpdatedAt((Long) any());
    verify(availableBookings1).setUpdatedBy((String) any());
    verify(availableBookings1).setVersion((Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#claimAvailableMeal(long, long)} */
  @Test
  void testClaimAvailableMeal() {
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
    Optional<AvailableBookings> ofResult = Optional.of(availableBookings);
    when(availableBookingsRepository.findByDateAndMealOptions_Id((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    when(bookingRepository.existsByDateAndUser_IdAndCancelledAtNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(true);
    assertThrows(
        InvalidBookingOperation.class, () -> bookingServiceImpl.claimAvailableMeal(123L, 123L));
    verify(bookingRepository)
        .existsByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#claimAvailableMeal(long, long)} */
  @Test
  void testClaimAvailableMeal2() {
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
    Optional<AvailableBookings> ofResult = Optional.of(availableBookings);
    when(availableBookingsRepository.findByDateAndMealOptions_Id((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    when(bookingRepository.existsByDateAndUser_IdAndCancelledAtNull(
            (LocalDate) any(), (Long) any()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.claimAvailableMeal(123L, 123L));
    verify(bookingRepository)
        .existsByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#claimAvailableMeal(long, long)} */
  @Test
  void testClaimAvailableMeal3() {
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
    Optional<AvailableBookings> ofResult = Optional.of(availableBookings);
    when(availableBookingsRepository.findByDateAndMealOptions_Id((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    when(bookingRepository.existsByDateAndUser_IdAndCancelledAtNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(false);

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
    when(mealsService.getMealOptionById(anyLong())).thenReturn(mealOptions1);
    assertThrows(
        SelectionNotAvailableException.class,
        () -> bookingServiceImpl.claimAvailableMeal(123L, 123L));
    verify(availableBookingsRepository)
        .findByDateAndMealOptions_Id((LocalDate) any(), (Long) any());
    verify(bookingRepository)
        .existsByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
    verify(mealsService).getMealOptionById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBooking(Bookings, long)} */
  @Test
  void testCancelBooking() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata1);

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

    UserMetadata userMetadata2 = new UserMetadata();
    userMetadata2.setBookings(new HashSet<>());
    userMetadata2.setCreatedAt(1L);
    userMetadata2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata2.setDeletedAt(1L);
    userMetadata2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata2.setEmailId("42");
    userMetadata2.setFirstName("Jane");
    userMetadata2.setId(123L);
    userMetadata2.setLastName("Doe");
    userMetadata2.setMobile("Mobile");
    userMetadata2.setSubscribedAt(1L);
    userMetadata2.setUpdatedAt(1L);
    userMetadata2.setUpdatedBy("2020-03-01");
    userMetadata2.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata2);
    bookings1.setVersion(1L);
    assertThrows(
        InvalidBookingOperation.class, () -> bookingServiceImpl.cancelBooking(bookings1, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBooking(Bookings, long)} */
  @Test
  void testCancelBooking2() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    when(userService.getUserById(anyLong()))
        .thenThrow(new SelectionLockedException("An error occurred"));

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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    assertThrows(
        SelectionLockedException.class, () -> bookingServiceImpl.cancelBooking(bookings1, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#cancelBooking(Bookings, long)} */
  @Test
  void testCancelBooking3() {
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getCancelledAt()).thenReturn(1L);
    doNothing().when(bookings).setCreatedAt((Long) any());
    doNothing().when(bookings).setCreatedBy((String) any());
    doNothing().when(bookings).setDeletedAt((Long) any());
    doNothing().when(bookings).setDeletedBy((String) any());
    doNothing().when(bookings).setId((Long) any());
    doNothing().when(bookings).setUpdatedAt((Long) any());
    doNothing().when(bookings).setUpdatedBy((String) any());
    doNothing().when(bookings).setVersion((Long) any());
    doNothing().when(bookings).setCancelledAt((Long) any());
    doNothing().when(bookings).setClaimedAt((Long) any());
    doNothing().when(bookings).setDate((LocalDate) any());
    doNothing().when(bookings).setMealOptions((MealOptions) any());
    doNothing().when(bookings).setUser((UserMetadata) any());
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    when(userService.getUserById(anyLong())).thenReturn(userMetadata1);

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

    UserMetadata userMetadata2 = new UserMetadata();
    userMetadata2.setBookings(new HashSet<>());
    userMetadata2.setCreatedAt(1L);
    userMetadata2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata2.setDeletedAt(1L);
    userMetadata2.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata2.setEmailId("42");
    userMetadata2.setFirstName("Jane");
    userMetadata2.setId(123L);
    userMetadata2.setLastName("Doe");
    userMetadata2.setMobile("Mobile");
    userMetadata2.setSubscribedAt(1L);
    userMetadata2.setUpdatedAt(1L);
    userMetadata2.setUpdatedBy("2020-03-01");
    userMetadata2.setVersion(1L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(1L);
    bookings1.setClaimedAt(1L);
    bookings1.setCreatedAt(1L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(1L));
    bookings1.setDeletedAt(1L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata2);
    bookings1.setVersion(1L);
    assertThrows(
        InvalidBookingOperation.class, () -> bookingServiceImpl.cancelBooking(bookings1, 123L));
    verify(bookingRepository).findById((Long) any());
    verify(bookings).getCancelledAt();
    verify(bookings).setCreatedAt((Long) any());
    verify(bookings).setCreatedBy((String) any());
    verify(bookings).setDeletedAt((Long) any());
    verify(bookings).setDeletedBy((String) any());
    verify(bookings).setId((Long) any());
    verify(bookings).setUpdatedAt((Long) any());
    verify(bookings).setUpdatedBy((String) any());
    verify(bookings).setVersion((Long) any());
    verify(bookings).setCancelledAt((Long) any());
    verify(bookings).setClaimedAt((Long) any());
    verify(bookings).setDate((LocalDate) any());
    verify(bookings).setMealOptions((MealOptions) any());
    verify(bookings).setUser((UserMetadata) any());
    verify(userService).getUserById(anyLong());
  }

  /** Method under test: {@link BookingServiceImpl#deleteBookingById(long)} */
  @Test
  void testDeleteBookingById() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    doNothing().when(bookingRepository).delete((Bookings) any());
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    bookingServiceImpl.deleteBookingById(123L);
    verify(bookingRepository).findById((Long) any());
    verify(bookingRepository).delete((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#deleteBookingById(long)} */
  @Test
  void testDeleteBookingById2() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    doThrow(new SelectionLockedException("An error occurred"))
        .when(bookingRepository)
        .delete((Bookings) any());
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(SelectionLockedException.class, () -> bookingServiceImpl.deleteBookingById(123L));
    verify(bookingRepository).findById((Long) any());
    verify(bookingRepository).delete((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#deleteBooking(Bookings)} */
  @Test
  void testDeleteBooking() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    doNothing().when(bookingRepository).delete((Bookings) any());
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    bookingServiceImpl.deleteBooking(bookings1);
    verify(bookingRepository).findById((Long) any());
    verify(bookingRepository).delete((Bookings) any());
    assertEquals(1L, bookings1.getCancelledAt().longValue());
    assertEquals(1L, bookings1.getVersion().longValue());
    assertEquals(userMetadata, bookings1.getUser());
    assertEquals("2020-03-01", bookings1.getUpdatedBy());
    assertEquals(1L, bookings1.getClaimedAt().longValue());
    assertEquals("Jan 1, 2020 8:00am GMT+0100", bookings1.getCreatedBy());
    assertEquals(123L, bookings1.getId().longValue());
    assertEquals(mealOptions, bookings1.getMealOptions());
    assertEquals("1970-01-02", bookings1.getDate().toString());
    assertEquals(1L, bookings1.getUpdatedAt().longValue());
    assertEquals(1L, bookings1.getCreatedAt().longValue());
    assertEquals(1L, bookings1.getDeletedAt().longValue());
    assertEquals("Jan 1, 2020 11:00am GMT+0100", bookings1.getDeletedBy());
  }

  /** Method under test: {@link BookingServiceImpl#deleteBooking(Bookings)} */
  @Test
  void testDeleteBooking2() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    doThrow(new SelectionLockedException("An error occurred"))
        .when(bookingRepository)
        .delete((Bookings) any());
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);

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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);
    assertThrows(SelectionLockedException.class, () -> bookingServiceImpl.deleteBooking(bookings1));
    verify(bookingRepository).findById((Long) any());
    verify(bookingRepository).delete((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getBookingById(long)} */
  @Test
  void testGetBookingById() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(bookings, bookingServiceImpl.getBookingById(123L));
    verify(bookingRepository).findById((Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#availBooking(long)} */
  @Test
  void testAvailBooking() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidBookingOperation.class, () -> bookingServiceImpl.availBooking(123L));
    verify(bookingRepository).findById((Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#availBooking(long)} */
  @Test
  void testAvailBooking2() {
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getClaimedAt()).thenReturn(1L);
    doNothing().when(bookings).setCreatedAt((Long) any());
    doNothing().when(bookings).setCreatedBy((String) any());
    doNothing().when(bookings).setDeletedAt((Long) any());
    doNothing().when(bookings).setDeletedBy((String) any());
    doNothing().when(bookings).setId((Long) any());
    doNothing().when(bookings).setUpdatedAt((Long) any());
    doNothing().when(bookings).setUpdatedBy((String) any());
    doNothing().when(bookings).setVersion((Long) any());
    doNothing().when(bookings).setCancelledAt((Long) any());
    doNothing().when(bookings).setClaimedAt((Long) any());
    doNothing().when(bookings).setDate((LocalDate) any());
    doNothing().when(bookings).setMealOptions((MealOptions) any());
    doNothing().when(bookings).setUser((UserMetadata) any());
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidBookingOperation.class, () -> bookingServiceImpl.availBooking(123L));
    verify(bookingRepository).findById((Long) any());
    verify(bookings).getClaimedAt();
    verify(bookings).setCreatedAt((Long) any());
    verify(bookings).setCreatedBy((String) any());
    verify(bookings).setDeletedAt((Long) any());
    verify(bookings).setDeletedBy((String) any());
    verify(bookings).setId((Long) any());
    verify(bookings).setUpdatedAt((Long) any());
    verify(bookings).setUpdatedBy((String) any());
    verify(bookings).setVersion((Long) any());
    verify(bookings).setCancelledAt((Long) any());
    verify(bookings).setClaimedAt((Long) any());
    verify(bookings).setDate((LocalDate) any());
    verify(bookings).setMealOptions((MealOptions) any());
    verify(bookings).setUser((UserMetadata) any());
  }

  /** Method under test: {@link BookingServiceImpl#getCurrentBooking(long)} */
  @Test
  void testGetCurrentBooking() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    BookingResponseModel bookingResponseModel =
        new BookingResponseModel(
            123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L);

    when(mapper.transform((Bookings) any())).thenReturn(bookingResponseModel);
    assertSame(bookingResponseModel, bookingServiceImpl.getCurrentBooking(123L));
    verify(bookingRepository)
        .findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getCurrentBooking(long)} */
  @Test
  void testGetCurrentBooking2() {
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    when(mapper.transform((Bookings) any()))
        .thenThrow(new SelectionLockedException("An error occurred"));
    assertThrows(SelectionLockedException.class, () -> bookingServiceImpl.getCurrentBooking(123L));
    verify(bookingRepository)
        .findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getCurrentBooking(long)} */
  @Test
  void testGetCurrentBooking3() {
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getId()).thenReturn(123L);
    doNothing().when(bookings).setCreatedAt((Long) any());
    doNothing().when(bookings).setCreatedBy((String) any());
    doNothing().when(bookings).setDeletedAt((Long) any());
    doNothing().when(bookings).setDeletedBy((String) any());
    doNothing().when(bookings).setId((Long) any());
    doNothing().when(bookings).setUpdatedAt((Long) any());
    doNothing().when(bookings).setUpdatedBy((String) any());
    doNothing().when(bookings).setVersion((Long) any());
    doNothing().when(bookings).setCancelledAt((Long) any());
    doNothing().when(bookings).setClaimedAt((Long) any());
    doNothing().when(bookings).setDate((LocalDate) any());
    doNothing().when(bookings).setMealOptions((MealOptions) any());
    doNothing().when(bookings).setUser((UserMetadata) any());
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
    Optional<Bookings> ofResult = Optional.of(bookings);
    when(bookingRepository.findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any()))
        .thenReturn(ofResult);
    BookingResponseModel bookingResponseModel =
        new BookingResponseModel(
            123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L);

    when(mapper.transform((Bookings) any())).thenReturn(bookingResponseModel);
    assertSame(bookingResponseModel, bookingServiceImpl.getCurrentBooking(123L));
    verify(bookingRepository)
        .findByDateAndUser_IdAndCancelledAtNull((LocalDate) any(), (Long) any());
    verify(bookings).getId();
    verify(bookings).setCreatedAt((Long) any());
    verify(bookings).setCreatedBy((String) any());
    verify(bookings).setDeletedAt((Long) any());
    verify(bookings).setDeletedBy((String) any());
    verify(bookings).setId((Long) any());
    verify(bookings).setUpdatedAt((Long) any());
    verify(bookings).setUpdatedBy((String) any());
    verify(bookings).setVersion((Long) any());
    verify(bookings).setCancelledAt((Long) any());
    verify(bookings).setClaimedAt((Long) any());
    verify(bookings).setDate((LocalDate) any());
    verify(bookings).setMealOptions((MealOptions) any());
    verify(bookings).setUser((UserMetadata) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getUpcomingBookings(long)} */
  @Test
  void testGetUpcomingBookings() {
    when(bookingRepository.findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(bookingServiceImpl.getUpcomingBookings(123L).isEmpty());
    verify(bookingRepository)
        .findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#getUpcomingBookings(long)} */
  @Test
  void testGetUpcomingBookings2() {
    Meals meals = new Meals();
    meals.setActivatedAt(3L);
    meals.setCreatedAt(3L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(3L));
    meals.setDeletedAt(3L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(3L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    meals.setReadyAt(3L);
    meals.setUpdatedAt(3L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(3L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(3L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(3L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    mealOptions.setUpdatedAt(3L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(3L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(3L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(3L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Finding Upcoming Bookings for User ID:{} after today:{}");
    userMetadata.setSubscribedAt(3L);
    userMetadata.setUpdatedAt(3L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(3L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(3L);
    bookings.setClaimedAt(3L);
    bookings.setCreatedAt(3L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(3L));
    bookings.setDeletedAt(3L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(3L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(3L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(1, bookingServiceImpl.getUpcomingBookings(123L).size());
    verify(bookingRepository)
        .findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getUpcomingBookings(long)} */
  @Test
  void testGetUpcomingBookings3() {
    Meals meals = new Meals();
    meals.setActivatedAt(3L);
    meals.setCreatedAt(3L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(3L));
    meals.setDeletedAt(3L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(3L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    meals.setReadyAt(3L);
    meals.setUpdatedAt(3L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(3L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(3L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(3L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    mealOptions.setUpdatedAt(3L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(3L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(3L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(3L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Finding Upcoming Bookings for User ID:{} after today:{}");
    userMetadata.setSubscribedAt(3L);
    userMetadata.setUpdatedAt(3L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(3L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(3L);
    bookings.setClaimedAt(3L);
    bookings.setCreatedAt(3L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(3L));
    bookings.setDeletedAt(3L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(3L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(3L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(3L);
    meals1.setCreatedAt(3L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(3L));
    meals1.setDeletedAt(3L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(3L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    meals1.setReadyAt(3L);
    meals1.setUpdatedAt(3L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(3L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(3L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(3L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Finding Upcoming Bookings for User ID:{} after today:{}");
    mealOptions1.setUpdatedAt(3L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(3L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(3L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(3L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Finding Upcoming Bookings for User ID:{} after today:{}");
    userMetadata1.setSubscribedAt(3L);
    userMetadata1.setUpdatedAt(3L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(3L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(3L);
    bookings1.setClaimedAt(3L);
    bookings1.setCreatedAt(3L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(3L));
    bookings1.setDeletedAt(3L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(3L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(3L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings1);
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(2, bookingServiceImpl.getUpcomingBookings(123L).size());
    verify(bookingRepository)
        .findByDateGreaterThanAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
    verify(mapper, atLeast(1)).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getPreviousBookings(long)} */
  @Test
  void testGetPreviousBookings() {
    when(bookingRepository.findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(bookingServiceImpl.getPreviousBookings(123L).isEmpty());
    verify(bookingRepository)
        .findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any());
  }

  /** Method under test: {@link BookingServiceImpl#getPreviousBookings(long)} */
  @Test
  void testGetPreviousBookings2() {
    Meals meals = new Meals();
    meals.setActivatedAt(3L);
    meals.setCreatedAt(3L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(3L));
    meals.setDeletedAt(3L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(3L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Finding Previous Bookings for User ID:{} before today:{}");
    meals.setReadyAt(3L);
    meals.setUpdatedAt(3L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(3L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(3L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(3L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Finding Previous Bookings for User ID:{} before today:{}");
    mealOptions.setUpdatedAt(3L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(3L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(3L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(3L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Finding Previous Bookings for User ID:{} before today:{}");
    userMetadata.setSubscribedAt(3L);
    userMetadata.setUpdatedAt(3L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(3L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(3L);
    bookings.setClaimedAt(3L);
    bookings.setCreatedAt(3L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(3L));
    bookings.setDeletedAt(3L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(3L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(3L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(1, bookingServiceImpl.getPreviousBookings(123L).size());
    verify(bookingRepository)
        .findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getPreviousBookings(long)} */
  @Test
  void testGetPreviousBookings3() {
    Meals meals = new Meals();
    meals.setActivatedAt(3L);
    meals.setCreatedAt(3L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(3L));
    meals.setDeletedAt(3L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(3L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Finding Previous Bookings for User ID:{} before today:{}");
    meals.setReadyAt(3L);
    meals.setUpdatedAt(3L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(3L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(3L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(3L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Finding Previous Bookings for User ID:{} before today:{}");
    mealOptions.setUpdatedAt(3L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(3L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(3L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(3L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Finding Previous Bookings for User ID:{} before today:{}");
    userMetadata.setSubscribedAt(3L);
    userMetadata.setUpdatedAt(3L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(3L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(3L);
    bookings.setClaimedAt(3L);
    bookings.setCreatedAt(3L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(3L));
    bookings.setDeletedAt(3L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(3L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(3L);

    Meals meals1 = new Meals();
    meals1.setActivatedAt(3L);
    meals1.setCreatedAt(3L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(3L));
    meals1.setDeletedAt(3L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(3L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Finding Previous Bookings for User ID:{} before today:{}");
    meals1.setReadyAt(3L);
    meals1.setUpdatedAt(3L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(3L);

    MealOptions mealOptions1 = new MealOptions();
    mealOptions1.removeBookingById(123L);
    mealOptions1.setAvailableBookings(new HashSet<>());
    mealOptions1.setBookings(new HashSet<>());
    mealOptions1.setCount(3);
    mealOptions1.setCreatedAt(3L);
    mealOptions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions1.setDeletedAt(3L);
    mealOptions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions1.setId(123L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Finding Previous Bookings for User ID:{} before today:{}");
    mealOptions1.setUpdatedAt(3L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(3L);

    UserMetadata userMetadata1 = new UserMetadata();
    userMetadata1.setBookings(new HashSet<>());
    userMetadata1.setCreatedAt(3L);
    userMetadata1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata1.setDeletedAt(3L);
    userMetadata1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata1.setEmailId("42");
    userMetadata1.setFirstName("Jane");
    userMetadata1.setId(123L);
    userMetadata1.setLastName("Doe");
    userMetadata1.setMobile("Finding Previous Bookings for User ID:{} before today:{}");
    userMetadata1.setSubscribedAt(3L);
    userMetadata1.setUpdatedAt(3L);
    userMetadata1.setUpdatedBy("2020-03-01");
    userMetadata1.setVersion(3L);

    Bookings bookings1 = new Bookings();
    bookings1.setCancelledAt(3L);
    bookings1.setClaimedAt(3L);
    bookings1.setCreatedAt(3L);
    bookings1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings1.setDate(LocalDate.ofEpochDay(3L));
    bookings1.setDeletedAt(3L);
    bookings1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings1.setId(123L);
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(3L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(3L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings1);
    bookingsList.add(bookings);
    when(bookingRepository.findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(2, bookingServiceImpl.getPreviousBookings(123L).size());
    verify(bookingRepository)
        .findByDateLessThanAndUser_IdAndCancelledAtNullOrderByDateDesc(
            (LocalDate) any(), (Long) any());
    verify(mapper, atLeast(1)).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getBookingsByDate(LocalDate)} */
  @Test
  void testGetBookingsByDate() {
    when(bookingRepository.findByDateAndCancelledAtNull((LocalDate) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(bookingServiceImpl.getBookingsByDate(LocalDate.ofEpochDay(1L)).isEmpty());
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
  }

  /** Method under test: {@link BookingServiceImpl#getBookingsByDate(LocalDate)} */
  @Test
  void testGetBookingsByDate2() {
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
    meals.setName("Finding Bookings on Date:{}");
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
    mealOptions.setName("Finding Bookings on Date:{}");
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
    userMetadata.setMobile("Finding Bookings on Date:{}");
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

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateAndCancelledAtNull((LocalDate) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(1, bookingServiceImpl.getBookingsByDate(LocalDate.ofEpochDay(1L)).size());
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    verify(mapper).transform((Bookings) any());
  }

  /** Method under test: {@link BookingServiceImpl#getBookingsByDate(LocalDate)} */
  @Test
  void testGetBookingsByDate3() {
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
    meals.setName("Finding Bookings on Date:{}");
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
    mealOptions.setName("Finding Bookings on Date:{}");
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
    userMetadata.setMobile("Finding Bookings on Date:{}");
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
    meals1.setActivatedAt(1L);
    meals1.setCreatedAt(1L);
    meals1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals1.setDate(LocalDate.ofEpochDay(1L));
    meals1.setDeletedAt(1L);
    meals1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals1.setId(123L);
    meals1.setLockedAt(1L);
    meals1.setMealOptions(new HashSet<>());
    meals1.setName("Finding Bookings on Date:{}");
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
    mealOptions1.setName("Finding Bookings on Date:{}");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);

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
    userMetadata1.setMobile("Finding Bookings on Date:{}");
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
    bookings1.setMealOptions(mealOptions1);
    bookings1.setUpdatedAt(1L);
    bookings1.setUpdatedBy("2020-03-01");
    bookings1.setUser(userMetadata1);
    bookings1.setVersion(1L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings1);
    bookingsList.add(bookings);
    when(bookingRepository.findByDateAndCancelledAtNull((LocalDate) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertEquals(2, bookingServiceImpl.getBookingsByDate(LocalDate.ofEpochDay(1L)).size());
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    verify(mapper, atLeast(1)).transform((Bookings) any());
  }
}
