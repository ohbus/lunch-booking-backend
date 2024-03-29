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

package xyz.subho.lunchbooking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.AvailableBookings;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.BookingResponseModel;

@ContextConfiguration(classes = {BookingResponseMapper.class})
@ExtendWith(SpringExtension.class)
class BookingResponseMapperTest {
  @Autowired private BookingResponseMapper bookingResponseMapper;

  /** Method under test: {@link BookingResponseMapper#transform(Bookings)} */
  @Test
  void testTransform() {
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
    BookingResponseModel actualTransformResult = bookingResponseMapper.transform(bookings);
    assertEquals(1L, actualTransformResult.availedAt().longValue());
    assertEquals(123L, actualTransformResult.mealOptionId().longValue());
    assertEquals("Name", actualTransformResult.mealOption());
    assertEquals("Doe", actualTransformResult.lastName());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("Jane", actualTransformResult.firstName());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
  }

  /** Method under test: {@link BookingResponseMapper#transform(Bookings)} */
  @Test
  void testTransform2() {
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getClaimedAt()).thenReturn(1L);
    when(bookings.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
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
    when(bookings.getMealOptions()).thenReturn(mealOptions1);
    when(bookings.getUser()).thenReturn(userMetadata1);
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
    BookingResponseModel actualTransformResult = bookingResponseMapper.transform(bookings);
    assertEquals(1L, actualTransformResult.availedAt().longValue());
    assertEquals(123L, actualTransformResult.mealOptionId().longValue());
    assertEquals("Name", actualTransformResult.mealOption());
    assertEquals("Doe", actualTransformResult.lastName());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("Jane", actualTransformResult.firstName());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
    verify(bookings).getId();
    verify(bookings).getClaimedAt();
    verify(bookings).getDate();
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
    verify(bookings).getMealOptions();
    verify(bookings, atLeast(1)).getUser();
  }

  /** Method under test: {@link BookingResponseMapper#transform(Bookings)} */
  @Test
  void testTransform3() {
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
    MealOptions mealOptions1 = mock(MealOptions.class);
    when(mealOptions1.getId()).thenReturn(123L);
    when(mealOptions1.getName()).thenReturn("Name");
    doNothing().when(mealOptions1).setCreatedAt((Long) any());
    doNothing().when(mealOptions1).setCreatedBy((String) any());
    doNothing().when(mealOptions1).setDeletedAt((Long) any());
    doNothing().when(mealOptions1).setDeletedBy((String) any());
    doNothing().when(mealOptions1).setId((Long) any());
    doNothing().when(mealOptions1).setUpdatedAt((Long) any());
    doNothing().when(mealOptions1).setUpdatedBy((String) any());
    doNothing().when(mealOptions1).setVersion((Long) any());
    doNothing().when(mealOptions1).removeBookingById(anyLong());
    doNothing().when(mealOptions1).setAvailableBookings((Set<AvailableBookings>) any());
    doNothing().when(mealOptions1).setBookings((Set<Bookings>) any());
    doNothing().when(mealOptions1).setCount(anyInt());
    doNothing().when(mealOptions1).setMeals((Meals) any());
    doNothing().when(mealOptions1).setName((String) any());
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getClaimedAt()).thenReturn(1L);
    when(bookings.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
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
    when(bookings.getMealOptions()).thenReturn(mealOptions1);
    when(bookings.getUser()).thenReturn(userMetadata1);
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
    BookingResponseModel actualTransformResult = bookingResponseMapper.transform(bookings);
    assertEquals(1L, actualTransformResult.availedAt().longValue());
    assertEquals(123L, actualTransformResult.mealOptionId().longValue());
    assertEquals("Name", actualTransformResult.mealOption());
    assertEquals("Doe", actualTransformResult.lastName());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("Jane", actualTransformResult.firstName());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
    verify(bookings).getId();
    verify(bookings).getClaimedAt();
    verify(bookings).getDate();
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
    verify(bookings).getMealOptions();
    verify(bookings, atLeast(1)).getUser();
    verify(mealOptions1).getId();
    verify(mealOptions1).getName();
    verify(mealOptions1).setCreatedAt((Long) any());
    verify(mealOptions1).setCreatedBy((String) any());
    verify(mealOptions1).setDeletedAt((Long) any());
    verify(mealOptions1).setDeletedBy((String) any());
    verify(mealOptions1).setId((Long) any());
    verify(mealOptions1).setUpdatedAt((Long) any());
    verify(mealOptions1).setUpdatedBy((String) any());
    verify(mealOptions1).setVersion((Long) any());
    verify(mealOptions1).removeBookingById(anyLong());
    verify(mealOptions1).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions1).setBookings((Set<Bookings>) any());
    verify(mealOptions1).setCount(anyInt());
    verify(mealOptions1).setMeals((Meals) any());
    verify(mealOptions1).setName((String) any());
  }

  /** Method under test: {@link BookingResponseMapper#transform(Bookings)} */
  @Test
  void testTransform4() {
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
    MealOptions mealOptions1 = mock(MealOptions.class);
    when(mealOptions1.getId()).thenReturn(123L);
    when(mealOptions1.getName()).thenReturn("Name");
    doNothing().when(mealOptions1).setCreatedAt((Long) any());
    doNothing().when(mealOptions1).setCreatedBy((String) any());
    doNothing().when(mealOptions1).setDeletedAt((Long) any());
    doNothing().when(mealOptions1).setDeletedBy((String) any());
    doNothing().when(mealOptions1).setId((Long) any());
    doNothing().when(mealOptions1).setUpdatedAt((Long) any());
    doNothing().when(mealOptions1).setUpdatedBy((String) any());
    doNothing().when(mealOptions1).setVersion((Long) any());
    doNothing().when(mealOptions1).removeBookingById(anyLong());
    doNothing().when(mealOptions1).setAvailableBookings((Set<AvailableBookings>) any());
    doNothing().when(mealOptions1).setBookings((Set<Bookings>) any());
    doNothing().when(mealOptions1).setCount(anyInt());
    doNothing().when(mealOptions1).setMeals((Meals) any());
    doNothing().when(mealOptions1).setName((String) any());
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
    UserMetadata userMetadata1 = mock(UserMetadata.class);
    when(userMetadata1.getFirstName()).thenReturn("Jane");
    when(userMetadata1.getLastName()).thenReturn("Doe");
    doNothing().when(userMetadata1).setCreatedAt((Long) any());
    doNothing().when(userMetadata1).setCreatedBy((String) any());
    doNothing().when(userMetadata1).setDeletedAt((Long) any());
    doNothing().when(userMetadata1).setDeletedBy((String) any());
    doNothing().when(userMetadata1).setId((Long) any());
    doNothing().when(userMetadata1).setUpdatedAt((Long) any());
    doNothing().when(userMetadata1).setUpdatedBy((String) any());
    doNothing().when(userMetadata1).setVersion((Long) any());
    doNothing().when(userMetadata1).setBookings((Set<Bookings>) any());
    doNothing().when(userMetadata1).setEmailId((String) any());
    doNothing().when(userMetadata1).setFirstName((String) any());
    doNothing().when(userMetadata1).setLastName((String) any());
    doNothing().when(userMetadata1).setMobile((String) any());
    doNothing().when(userMetadata1).setSubscribedAt((Long) any());
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
    Bookings bookings = mock(Bookings.class);
    when(bookings.getClaimedAt()).thenReturn(1L);
    when(bookings.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
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
    when(bookings.getMealOptions()).thenReturn(mealOptions1);
    when(bookings.getUser()).thenReturn(userMetadata1);
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
    BookingResponseModel actualTransformResult = bookingResponseMapper.transform(bookings);
    assertEquals(1L, actualTransformResult.availedAt().longValue());
    assertEquals(123L, actualTransformResult.mealOptionId().longValue());
    assertEquals("Name", actualTransformResult.mealOption());
    assertEquals("Doe", actualTransformResult.lastName());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("Jane", actualTransformResult.firstName());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
    verify(bookings).getId();
    verify(bookings).getClaimedAt();
    verify(bookings).getDate();
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
    verify(bookings).getMealOptions();
    verify(bookings, atLeast(1)).getUser();
    verify(mealOptions1).getId();
    verify(mealOptions1).getName();
    verify(mealOptions1).setCreatedAt((Long) any());
    verify(mealOptions1).setCreatedBy((String) any());
    verify(mealOptions1).setDeletedAt((Long) any());
    verify(mealOptions1).setDeletedBy((String) any());
    verify(mealOptions1).setId((Long) any());
    verify(mealOptions1).setUpdatedAt((Long) any());
    verify(mealOptions1).setUpdatedBy((String) any());
    verify(mealOptions1).setVersion((Long) any());
    verify(mealOptions1).removeBookingById(anyLong());
    verify(mealOptions1).setAvailableBookings((Set<AvailableBookings>) any());
    verify(mealOptions1).setBookings((Set<Bookings>) any());
    verify(mealOptions1).setCount(anyInt());
    verify(mealOptions1).setMeals((Meals) any());
    verify(mealOptions1).setName((String) any());
    verify(userMetadata1).getFirstName();
    verify(userMetadata1).getLastName();
    verify(userMetadata1).setCreatedAt((Long) any());
    verify(userMetadata1).setCreatedBy((String) any());
    verify(userMetadata1).setDeletedAt((Long) any());
    verify(userMetadata1).setDeletedBy((String) any());
    verify(userMetadata1).setId((Long) any());
    verify(userMetadata1).setUpdatedAt((Long) any());
    verify(userMetadata1).setUpdatedBy((String) any());
    verify(userMetadata1).setVersion((Long) any());
    verify(userMetadata1).setBookings((Set<Bookings>) any());
    verify(userMetadata1).setEmailId((String) any());
    verify(userMetadata1).setFirstName((String) any());
    verify(userMetadata1).setLastName((String) any());
    verify(userMetadata1).setMobile((String) any());
    verify(userMetadata1).setSubscribedAt((Long) any());
  }

  /** Method under test: {@link BookingResponseMapper#transformBack(BookingResponseModel)} */
  @Test
  void testTransformBack() {
    Bookings actualTransformBackResult =
        bookingResponseMapper.transformBack(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    assertNull(actualTransformBackResult.getCancelledAt());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals(1L, actualTransformBackResult.getClaimedAt().longValue());
    assertEquals("1970-01-02", actualTransformBackResult.getDate().toString());
    MealOptions mealOptions = actualTransformBackResult.getMealOptions();
    assertEquals(123L, mealOptions.getId().longValue());
    assertEquals(0, mealOptions.getCount());
    assertTrue(mealOptions.getBookings().isEmpty());
    assertTrue(mealOptions.getAvailableBookings().isEmpty());
    UserMetadata user = actualTransformBackResult.getUser();
    assertEquals("Doe", user.getLastName());
    assertEquals("Jane", user.getFirstName());
    assertNull(user.getEmailId());
    assertTrue(user.getBookings().isEmpty());
    assertNull(user.getSubscribedAt());
    assertEquals(1L, user.getVersion().longValue());
    assertNull(user.getMobile());
    assertEquals("Meal Option", mealOptions.getName());
    assertEquals(1L, mealOptions.getVersion().longValue());
  }

  /** Method under test: {@link BookingResponseMapper#transformBack(BookingResponseModel)} */
  @Test
  void testTransformBack2() {
    Bookings actualTransformBackResult =
        bookingResponseMapper.transformBack(
            new BookingResponseModel(123L, "Jane", "Doe", null, "Meal Option", 123L, 1L));
    assertNull(actualTransformBackResult.getCancelledAt());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals(1L, actualTransformBackResult.getClaimedAt().longValue());
    assertNull(actualTransformBackResult.getDate());
    MealOptions mealOptions = actualTransformBackResult.getMealOptions();
    assertEquals(123L, mealOptions.getId().longValue());
    assertEquals(0, mealOptions.getCount());
    assertTrue(mealOptions.getBookings().isEmpty());
    assertTrue(mealOptions.getAvailableBookings().isEmpty());
    UserMetadata user = actualTransformBackResult.getUser();
    assertEquals("Doe", user.getLastName());
    assertEquals("Jane", user.getFirstName());
    assertNull(user.getEmailId());
    assertTrue(user.getBookings().isEmpty());
    assertNull(user.getSubscribedAt());
    assertEquals(1L, user.getVersion().longValue());
    assertNull(user.getMobile());
    assertEquals("Meal Option", mealOptions.getName());
    assertEquals(1L, mealOptions.getVersion().longValue());
  }

  /** Method under test: {@link BookingResponseMapper#transformBack(BookingResponseModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testTransformBack3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.BookingResponseModel.id()" because "source" is null
    //       at
    // xyz.subho.lunchbooking.mapper.BookingResponseMapper.transformBack(BookingResponseMapper.java:46)

    bookingResponseMapper.transformBack(null);
  }
}
