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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
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
import xyz.subho.lunchbooking.exceptions.InvalidMealOperation;
import xyz.subho.lunchbooking.exceptions.MealNotFoundException;
import xyz.subho.lunchbooking.exceptions.MealOptionsNotFoundException;
import xyz.subho.lunchbooking.exceptions.SelectionNotAvailableException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.models.Email;
import xyz.subho.lunchbooking.models.MealOptionCountModel;
import xyz.subho.lunchbooking.models.MealOptionsModel;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.repositories.AvailableBookingsRepository;
import xyz.subho.lunchbooking.repositories.BookingRepository;
import xyz.subho.lunchbooking.repositories.MealOptionsRepository;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.MailService;

@ContextConfiguration(classes = {MealsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MealsServiceImplTest {
  @MockBean private AvailableBookingsRepository availableBookingsRepository;

  @MockBean private BookingRepository bookingRepository;

  @MockBean private MailService mailService;

  @MockBean(name = "BookingResponseMapper")
  private Mapper<Bookings, BookingResponseModel> mapper;

  @MockBean(name = "AvailableOptionsMapper")
  private Mapper<AvailableBookings, AvailableOptionsResponseModel> mapper1;

  @MockBean(name = "MealDetailsMapper")
  private Mapper<Meals, MealsModel> mapper2;

  @MockBean private MealOptionsRepository mealOptionsRepository;

  @MockBean private MealsRepository mealsRepository;

  @Autowired private MealsServiceImpl mealsServiceImpl;

  /** Method under test: {@link MealsServiceImpl#createMeal(MealsModel)} */
  @Test
  void testCreateMeal() {
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
    when(mealsRepository.existsByDate((LocalDate) any())).thenReturn(true);
    when(mealsRepository.save((Meals) any())).thenReturn(meals);
    assertThrows(
        SelectionNotAvailableException.class, () -> mealsServiceImpl.createMeal(new MealsModel()));
    verify(mealsRepository).existsByDate((LocalDate) any());
  }

  /** Method under test: {@link MealsServiceImpl#createMeal(MealsModel)} */
  @Test
  void testCreateMeal2() {
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
    when(mealsRepository.existsByDate((LocalDate) any())).thenReturn(false);
    when(mealsRepository.save((Meals) any())).thenReturn(meals);

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
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    when(mapper2.transformBack((MealsModel) any())).thenReturn(meals1);
    assertSame(mealsModel, mealsServiceImpl.createMeal(new MealsModel()));
    verify(mealsRepository).existsByDate((LocalDate) any());
    verify(mealsRepository).save((Meals) any());
    verify(mapper2).transform((Meals) any());
    verify(mapper2).transformBack((MealsModel) any());
  }

  /** Method under test: {@link MealsServiceImpl#createMeal(MealsModel)} */
  @Test
  void testCreateMeal3() {
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
    when(mealsRepository.existsByDate((LocalDate) any())).thenReturn(false);
    when(mealsRepository.save((Meals) any())).thenReturn(meals);
    Meals meals1 = mock(Meals.class);
    when(meals1.getMealOptions()).thenReturn(new HashSet<>());
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
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    when(mapper2.transformBack((MealsModel) any())).thenReturn(meals1);
    assertSame(mealsModel, mealsServiceImpl.createMeal(new MealsModel()));
    verify(mealsRepository).existsByDate((LocalDate) any());
    verify(mealsRepository).save((Meals) any());
    verify(mapper2).transform((Meals) any());
    verify(mapper2).transformBack((MealsModel) any());
    verify(meals1).getMealOptions();
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
  }

  /** Method under test: {@link MealsServiceImpl#createMeal(MealsModel)} */
  @Test
  void testCreateMeal4() {
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
    when(mealsRepository.existsByDate((LocalDate) any())).thenReturn(false);
    when(mealsRepository.save((Meals) any())).thenReturn(meals);

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
    meals1.setName("Creating Meals:{}");
    meals1.setReadyAt(1L);
    meals1.setUpdatedAt(1L);
    meals1.setUpdatedBy("2020-03-01");
    meals1.setVersion(1L);

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
    mealOptions.setMeals(meals1);
    mealOptions.setName("Creating Meals:{}");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    HashSet<MealOptions> mealOptionsSet = new HashSet<>();
    mealOptionsSet.add(mealOptions);
    Meals meals2 = mock(Meals.class);
    when(meals2.getMealOptions()).thenReturn(mealOptionsSet);
    doNothing().when(meals2).setCreatedAt((Long) any());
    doNothing().when(meals2).setCreatedBy((String) any());
    doNothing().when(meals2).setDeletedAt((Long) any());
    doNothing().when(meals2).setDeletedBy((String) any());
    doNothing().when(meals2).setId((Long) any());
    doNothing().when(meals2).setUpdatedAt((Long) any());
    doNothing().when(meals2).setUpdatedBy((String) any());
    doNothing().when(meals2).setVersion((Long) any());
    doNothing().when(meals2).setActivatedAt((Long) any());
    doNothing().when(meals2).setDate((LocalDate) any());
    doNothing().when(meals2).setLockedAt((Long) any());
    doNothing().when(meals2).setMealOptions((Set<MealOptions>) any());
    doNothing().when(meals2).setName((String) any());
    doNothing().when(meals2).setReadyAt((Long) any());
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
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    when(mapper2.transformBack((MealsModel) any())).thenReturn(meals2);
    assertSame(mealsModel, mealsServiceImpl.createMeal(new MealsModel()));
    verify(mealsRepository).existsByDate((LocalDate) any());
    verify(mealsRepository).save((Meals) any());
    verify(mapper2).transform((Meals) any());
    verify(mapper2).transformBack((MealsModel) any());
    verify(meals2).getMealOptions();
    verify(meals2).setCreatedAt((Long) any());
    verify(meals2).setCreatedBy((String) any());
    verify(meals2).setDeletedAt((Long) any());
    verify(meals2).setDeletedBy((String) any());
    verify(meals2).setId((Long) any());
    verify(meals2).setUpdatedAt((Long) any());
    verify(meals2).setUpdatedBy((String) any());
    verify(meals2).setVersion((Long) any());
    verify(meals2).setActivatedAt((Long) any());
    verify(meals2).setDate((LocalDate) any());
    verify(meals2).setLockedAt((Long) any());
    verify(meals2).setMealOptions((Set<MealOptions>) any());
    verify(meals2).setName((String) any());
    verify(meals2).setReadyAt((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealById(long)} */
  @Test
  void testGetMealById() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(meals, mealsServiceImpl.getMealById(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealById(long)} */
  @Test
  void testGetMealById2() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.getMealById(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealById(long)} */
  @Test
  void testGetMealById3() {
    when(mealsRepository.findById((Long) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.getMealById(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionById(long)} */
  @Test
  void testGetMealOptionById() {
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
    Optional<MealOptions> ofResult = Optional.of(mealOptions);
    when(mealOptionsRepository.findById((Long) any())).thenReturn(ofResult);
    assertSame(mealOptions, mealsServiceImpl.getMealOptionById(123L));
    verify(mealOptionsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionById(long)} */
  @Test
  void testGetMealOptionById2() {
    when(mealOptionsRepository.findById((Long) any())).thenReturn(Optional.empty());
    assertThrows(
        MealOptionsNotFoundException.class, () -> mealsServiceImpl.getMealOptionById(123L));
    verify(mealOptionsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionById(long)} */
  @Test
  void testGetMealOptionById3() {
    when(mealOptionsRepository.findById((Long) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(
        SelectionNotAvailableException.class, () -> mealsServiceImpl.getMealOptionById(123L));
    verify(mealOptionsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#updateMeal(Meals)} */
  @Test
  void testUpdateMeal() {
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
    when(mealsRepository.save((Meals) any())).thenReturn(meals);

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
    assertSame(meals, mealsServiceImpl.updateMeal(meals1));
    verify(mealsRepository).save((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#updateMeal(Meals)} */
  @Test
  void testUpdateMeal2() {
    when(mealsRepository.save((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));

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
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.updateMeal(meals));
    verify(mealsRepository).save((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#updateMealOptions(MealOptions)} */
  @Test
  void testUpdateMealOptions() {
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
    when(mealOptionsRepository.save((MealOptions) any())).thenReturn(mealOptions);

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
    assertSame(mealOptions, mealsServiceImpl.updateMealOptions(mealOptions1));
    verify(mealOptionsRepository).save((MealOptions) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionsByBookingId(long)} */
  @Test
  void testGetMealOptionsByBookingId() {
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
    Optional<MealOptions> ofResult = Optional.of(mealOptions);
    when(mealOptionsRepository.findByBookings_Id((Long) any())).thenReturn(ofResult);
    assertSame(mealOptions, mealsServiceImpl.getMealOptionsByBookingId(123L));
    verify(mealOptionsRepository).findByBookings_Id((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionsByBookingId(long)} */
  @Test
  void testGetMealOptionsByBookingId2() {
    when(mealOptionsRepository.findByBookings_Id((Long) any())).thenReturn(Optional.empty());
    assertThrows(
        MealOptionsNotFoundException.class, () -> mealsServiceImpl.getMealOptionsByBookingId(123L));
    verify(mealOptionsRepository).findByBookings_Id((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealOptionsByBookingId(long)} */
  @Test
  void testGetMealOptionsByBookingId3() {
    when(mealOptionsRepository.findByBookings_Id((Long) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(
        SelectionNotAvailableException.class,
        () -> mealsServiceImpl.getMealOptionsByBookingId(123L));
    verify(mealOptionsRepository).findByBookings_Id((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.lockMeal(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal2() {
    Meals meals = mock(Meals.class);
    when(meals.isActivated()).thenReturn(true);
    when(meals.isLocked()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.lockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal3() {
    doNothing().when(mailService).sendMail((Email) any());
    Meals meals = mock(Meals.class);
    when(meals.lock()).thenReturn(1L);
    when(meals.isActivated()).thenReturn(true);
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.lockMeal(123L));
    verify(mailService).sendMail((Email) any());
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).isLocked();
    verify(meals).lock();
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
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal4() {
    doThrow(new SelectionNotAvailableException("An error occurred"))
        .when(mailService)
        .sendMail((Email) any());
    Meals meals = mock(Meals.class);
    when(meals.lock()).thenReturn(1L);
    when(meals.isActivated()).thenReturn(true);
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.lockMeal(123L));
    verify(mailService).sendMail((Email) any());
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).isLocked();
    verify(meals).lock();
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
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal5() {
    doNothing().when(mailService).sendMail((Email) any());
    Meals meals = mock(Meals.class);
    when(meals.lock()).thenReturn(1L);
    when(meals.isActivated()).thenReturn(false);
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.lockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).isLocked();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  void testLockMeal6() {
    doNothing().when(mailService).sendMail((Email) any());
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    when(meals.lock()).thenReturn(1L);
    when(meals.isActivated()).thenReturn(true);
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.lockMeal(123L));
    verify(mealsRepository).findById((Long) any());
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

  /** Method under test: {@link MealsServiceImpl#lockMeal(long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testLockMeal7() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.MealsModel.getMealOptions()" because "meal" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.sendCountEmails(MealsServiceImpl.java:189)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lockMeal(MealsServiceImpl.java:181)
    //   See https://diff.blue/R013 to resolve this issue.

    doNothing().when(mailService).sendMail((Email) any());
    Meals meals = mock(Meals.class);
    when(meals.lock()).thenReturn(1L);
    when(meals.isActivated()).thenReturn(true);
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(null);
    mealsServiceImpl.lockMeal(123L);
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  void testSendCountEmails() {
    doNothing().when(mailService).sendMail((Email) any());
    mealsServiceImpl.sendCountEmails(new MealsModel());
    verify(mailService).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSendCountEmails2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.MealsModel.getMealOptions()" because "meal" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.sendCountEmails(MealsServiceImpl.java:189)
    //   See https://diff.blue/R013 to resolve this issue.

    doNothing().when(mailService).sendMail((Email) any());
    mealsServiceImpl.sendCountEmails(null);
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  void testSendCountEmails3() {
    doThrow(new SelectionNotAvailableException("An error occurred"))
        .when(mailService)
        .sendMail((Email) any());
    assertThrows(
        SelectionNotAvailableException.class,
        () -> mealsServiceImpl.sendCountEmails(new MealsModel()));
    verify(mailService).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSendCountEmails4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.IllegalArgumentException: First must not be null
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$sendCountEmails$1(MealsServiceImpl.java:200)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.sendCountEmails(MealsServiceImpl.java:204)
    //   See https://diff.blue/R013 to resolve this issue.

    doNothing().when(mailService).sendMail((Email) any());

    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    mealOptionsModelSet.add(new MealOptionsModel());
    mealsServiceImpl.sendCountEmails(
        new MealsModel(
            "Hi,%n%nMeal Details: %s%nOptions with Count as follows:%n",
            LocalDate.ofEpochDay(4L), mealOptionsModelSet));
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  void testSendCountEmails5() {
    doNothing().when(mailService).sendMail((Email) any());

    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    mealOptionsModelSet.add(
        new MealOptionsModel(
            123L, "Hi,%n%nMeal Details: %s%nOptions with Count as follows:%n", true, 3));
    mealsServiceImpl.sendCountEmails(
        new MealsModel(
            "Hi,%n%nMeal Details: %s%nOptions with Count as follows:%n",
            LocalDate.ofEpochDay(4L), mealOptionsModelSet));
    verify(mailService).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#sendCountEmails(MealsModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSendCountEmails6() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.IntPipeline.reduce(IntPipeline.java:515)
    //       at java.util.stream.IntPipeline.sum(IntPipeline.java:473)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.sendCountEmails(MealsServiceImpl.java:192)
    //   See https://diff.blue/R013 to resolve this issue.

    doNothing().when(mailService).sendMail((Email) any());

    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    mealOptionsModelSet.add(null);
    mealsServiceImpl.sendCountEmails(
        new MealsModel(
            "Hi,%n%nMeal Details: %s%nOptions with Count as follows:%n",
            LocalDate.ofEpochDay(4L), mealOptionsModelSet));
  }

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal2() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal3() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).unlock();
    when(meals.isLocked()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
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
    verify(meals).unlock();
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal4() {
    Meals meals = mock(Meals.class);
    doThrow(new SelectionNotAvailableException("An error occurred")).when(meals).unlock();
    when(meals.isLocked()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
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
    verify(meals).unlock();
  }

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal5() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).unlock();
    when(meals.isLocked()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#unlockMeal(long)} */
  @Test
  void testUnlockMeal6() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    doNothing().when(meals).unlock();
    when(meals.isLocked()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.unlockMeal(123L));
    verify(mealsRepository).findById((Long) any());
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

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal2() {
    Meals meals = mock(Meals.class);
    when(meals.getName()).thenReturn("Name");
    when(meals.isActivated()).thenReturn(true);
    when(meals.activate()).thenReturn(1L);
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal3() {
    Meals meals = mock(Meals.class);
    when(meals.getName()).thenThrow(new SelectionNotAvailableException("An error occurred"));
    when(meals.isActivated()).thenReturn(true);
    when(meals.activate()).thenReturn(1L);
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal4() {
    Meals meals = mock(Meals.class);
    when(meals.getName()).thenReturn("Name");
    when(meals.isActivated()).thenReturn(false);
    when(meals.activate()).thenReturn(1L);
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).activate();
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
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal5() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    when(meals.getName()).thenReturn("Name");
    when(meals.isActivated()).thenReturn(false);
    when(meals.activate()).thenReturn(1L);
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
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
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

  /** Method under test: {@link MealsServiceImpl#activateMeal(long)} */
  @Test
  void testActivateMeal6() {
    Meals meals = mock(Meals.class);
    when(meals.getName()).thenReturn("Name");
    when(meals.isActivated()).thenReturn(false);
    when(meals.activate()).thenReturn(1L);
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.activateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).activate();
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
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal2() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal3() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).deactivate();
    when(meals.isActivated()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).deactivate();
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal4() {
    Meals meals = mock(Meals.class);
    doThrow(new SelectionNotAvailableException("An error occurred")).when(meals).deactivate();
    when(meals.isActivated()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).deactivate();
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal5() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).deactivate();
    when(meals.isActivated()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isActivated();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#deactivateMeal(long)} */
  @Test
  void testDeactivateMeal6() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    doNothing().when(meals).deactivate();
    when(meals.isActivated()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.deactivateMeal(123L));
    verify(mealsRepository).findById((Long) any());
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

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady2() {
    Meals meals = mock(Meals.class);
    when(meals.isLocked()).thenReturn(true);
    when(meals.isReady()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isReady();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady3() {
    Meals meals = mock(Meals.class);
    when(meals.getId()).thenReturn(123L);
    when(meals.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals.isLocked()).thenReturn(true);
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
    verify(meals).isReady();
    verify(meals).getId();
    verify(meals).getName();
    verify(meals, atLeast(1)).getDate();
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

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady4() {
    Meals meals = mock(Meals.class);
    when(meals.getId()).thenThrow(new SelectionNotAvailableException("An error occurred"));
    when(meals.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals.isLocked()).thenReturn(true);
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
    verify(meals).isReady();
    verify(meals).getId();
    verify(meals).getDate();
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

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testMakeMealReady5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.time.chrono.ChronoLocalDate.toEpochDay()" because "other" is null
    //       at java.time.chrono.ChronoLocalDate.isEqual(ChronoLocalDate.java:765)
    //       at java.time.LocalDate.isEqual(LocalDate.java:2112)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.makeMealReady(MealsServiceImpl.java:270)
    //   See https://diff.blue/R013 to resolve this issue.

    Meals meals = mock(Meals.class);
    when(meals.getId()).thenReturn(123L);
    when(meals.getDate()).thenReturn(null);
    when(meals.isLocked()).thenReturn(true);
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    mealsServiceImpl.makeMealReady(123L);
  }

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady6() {
    Meals meals = mock(Meals.class);
    when(meals.getId()).thenReturn(123L);
    when(meals.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals.isLocked()).thenReturn(false);
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isLocked();
    verify(meals).isReady();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#makeMealReady(long)} */
  @Test
  void testMakeMealReady7() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    when(meals.getId()).thenReturn(123L);
    when(meals.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals.isLocked()).thenReturn(true);
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.makeMealReady(123L));
    verify(mealsRepository).findById((Long) any());
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

  /** Method under test: {@link MealsServiceImpl#sendReadyEmails(Meals)} */
  @Test
  void testSendReadyEmails() {
    when(bookingRepository.findByDateAndCancelledAtNull((LocalDate) any()))
        .thenReturn(new ArrayList<>());

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
    mealsServiceImpl.sendReadyEmails(meals);
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    assertEquals(1L, meals.getActivatedAt().longValue());
    assertEquals(1L, meals.getVersion().longValue());
    assertEquals("2020-03-01", meals.getUpdatedBy());
    assertEquals(1L, meals.getUpdatedAt().longValue());
    assertEquals(1L, meals.getReadyAt().longValue());
    assertEquals("Name", meals.getName());
    assertTrue(meals.getMealOptions().isEmpty());
    assertEquals(1L, meals.getLockedAt().longValue());
    assertEquals(123L, meals.getId().longValue());
    assertEquals("Jan 1, 2020 11:00am GMT+0100", meals.getDeletedBy());
    assertEquals(1L, meals.getDeletedAt().longValue());
    assertEquals("1970-01-02", meals.getDate().toString());
    assertEquals("Jan 1, 2020 8:00am GMT+0100", meals.getCreatedBy());
    assertEquals(1L, meals.getCreatedAt().longValue());
    assertTrue(mealsServiceImpl.getAllAvailableOptionsForToday().isEmpty());
  }

  /** Method under test: {@link MealsServiceImpl#sendReadyEmails(Meals)} */
  @Test
  void testSendReadyEmails2() {
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
    meals.setName("Sending QR Booking Information to {} users");
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
    mealOptions.setName("Sending QR Booking Information to {} users");
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
    userMetadata.setMobile("Sending QR Booking Information to {} users");
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
    doNothing().when(mailService).sendMail((Email) any());

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
    mealsServiceImpl.sendReadyEmails(meals1);
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    verify(mapper).transform((Bookings) any());
    verify(mailService).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#sendReadyEmails(Meals)} */
  @Test
  void testSendReadyEmails3() {
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
    meals.setName("Sending QR Booking Information to {} users");
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
    mealOptions.setName("Sending QR Booking Information to {} users");
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
    userMetadata.setMobile("Sending QR Booking Information to {} users");
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
    doThrow(new SelectionNotAvailableException("An error occurred"))
        .when(mailService)
        .sendMail((Email) any());

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
    assertThrows(
        SelectionNotAvailableException.class, () -> mealsServiceImpl.sendReadyEmails(meals1));
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    verify(mapper).transform((Bookings) any());
    verify(mailService).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#sendReadyEmails(Meals)} */
  @Test
  void testSendReadyEmails4() {
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
    meals.setName("Sending QR Booking Information to {} users");
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
    mealOptions.setName("Sending QR Booking Information to {} users");
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
    userMetadata.setMobile("Sending QR Booking Information to {} users");
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
    meals1.setName("Sending QR Booking Information to {} users");
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
    mealOptions1.setName("Sending QR Booking Information to {} users");
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
    userMetadata1.setMobile("Sending QR Booking Information to {} users");
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
    doNothing().when(mailService).sendMail((Email) any());

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
    mealsServiceImpl.sendReadyEmails(meals2);
    verify(bookingRepository).findByDateAndCancelledAtNull((LocalDate) any());
    verify(mapper, atLeast(1)).transform((Bookings) any());
    verify(mailService, atLeast(1)).sendMail((Email) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready2() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(
        SelectionNotAvailableException.class, () -> mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready3() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).markUnReady();
    when(meals.isReady()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    MealsModel mealsModel = new MealsModel();
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertSame(mealsModel, mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isReady();
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).markUnReady();
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready4() {
    Meals meals = mock(Meals.class);
    doThrow(new SelectionNotAvailableException("An error occurred")).when(meals).markUnReady();
    when(meals.isReady()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(
        SelectionNotAvailableException.class, () -> mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isReady();
    verify(meals).setCreatedAt((Long) any());
    verify(meals).setCreatedBy((String) any());
    verify(meals).setDeletedAt((Long) any());
    verify(meals).setDeletedBy((String) any());
    verify(meals).setId((Long) any());
    verify(meals).setUpdatedAt((Long) any());
    verify(meals).setUpdatedBy((String) any());
    verify(meals).setVersion((Long) any());
    verify(meals).markUnReady();
    verify(meals).setActivatedAt((Long) any());
    verify(meals).setDate((LocalDate) any());
    verify(meals).setLockedAt((Long) any());
    verify(meals).setMealOptions((Set<MealOptions>) any());
    verify(meals).setName((String) any());
    verify(meals).setReadyAt((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready5() {
    Meals meals = mock(Meals.class);
    doNothing().when(meals).markUnReady();
    when(meals.isReady()).thenReturn(false);
    when(meals.getName()).thenReturn("Name");
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(InvalidMealOperation.class, () -> mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
    verify(meals).isReady();
    verify(meals).getName();
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

  /** Method under test: {@link MealsServiceImpl#makeMealUnready(long)} */
  @Test
  void testMakeMealUnready6() {
    when(mealsRepository.findById((Long) any())).thenReturn(Optional.empty());
    Meals meals = mock(Meals.class);
    doNothing().when(meals).markUnReady();
    when(meals.isReady()).thenReturn(true);
    when(meals.getName()).thenReturn("Name");
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
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(MealNotFoundException.class, () -> mealsServiceImpl.makeMealUnready(123L));
    verify(mealsRepository).findById((Long) any());
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

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings() {
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(
        mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L).isEmpty());
    verify(mealsRepository)
        .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
            (LocalDate) any());
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings2() {
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(new ArrayList<>());

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertEquals(
        1, mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L).size());
    verify(bookingRepository)
        .findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
    verify(mealsRepository)
        .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
            (LocalDate) any());
    verify(mapper2).transform((Meals) any());
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings3() {
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(new ArrayList<>());

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    assertThrows(
        SelectionNotAvailableException.class,
        () -> mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L));
    verify(mealsRepository)
        .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
            (LocalDate) any());
    verify(mapper2).transform((Meals) any());
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.time.chrono.ChronoLocalDate.toEpochDay()" because "other" is null
    //       at java.time.chrono.ChronoLocalDate.isEqual(ChronoLocalDate.java:765)
    //       at java.time.LocalDate.isEqual(LocalDate.java:2112)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$6(MealsServiceImpl.java:335)
    //       at java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
    //       at java.util.AbstractList$RandomAccessSpliterator.tryAdvance(AbstractList.java:706)
    //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
    //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.anyMatch(ReferencePipeline.java:632)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$7(MealsServiceImpl.java:335)
    //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
    //       at
    // java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(MealsServiceImpl.java:336)
    //   See https://diff.blue/R013 to resolve this issue.

    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals1);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L);
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings5() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.time.chrono.ChronoLocalDate.toEpochDay()" because "other" is null
    //       at java.time.chrono.ChronoLocalDate.isEqual(ChronoLocalDate.java:765)
    //       at java.time.LocalDate.isEqual(LocalDate.java:2112)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$6(MealsServiceImpl.java:335)
    //       at java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
    //       at java.util.AbstractList$RandomAccessSpliterator.tryAdvance(AbstractList.java:706)
    //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
    //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.anyMatch(ReferencePipeline.java:632)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$7(MealsServiceImpl.java:335)
    //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
    //       at
    // java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(MealsServiceImpl.java:336)
    //   See https://diff.blue/R013 to resolve this issue.

    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

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

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings1);
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals2);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L);
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings6() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.BookingResponseModel.date()" because "bookings" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$6(MealsServiceImpl.java:335)
    //       at java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
    //       at java.util.AbstractList$RandomAccessSpliterator.tryAdvance(AbstractList.java:706)
    //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
    //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.anyMatch(ReferencePipeline.java:632)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$7(MealsServiceImpl.java:335)
    //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
    //       at
    // java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(MealsServiceImpl.java:336)
    //   See https://diff.blue/R013 to resolve this issue.

    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any())).thenReturn(null);

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals1);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L);
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings7() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.time.chrono.ChronoLocalDate.toEpochDay()" because "other" is null
    //       at java.time.chrono.ChronoLocalDate.isEqual(ChronoLocalDate.java:765)
    //       at java.time.LocalDate.isEqual(LocalDate.java:2112)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$6(MealsServiceImpl.java:335)
    //       at java.util.stream.MatchOps$1MatchSink.accept(MatchOps.java:90)
    //       at java.util.AbstractList$RandomAccessSpliterator.tryAdvance(AbstractList.java:706)
    //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
    //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:230)
    //       at java.util.stream.MatchOps$MatchOp.evaluateSequential(MatchOps.java:196)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.anyMatch(ReferencePipeline.java:632)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.lambda$getMealsAvailableForBookingWithAlreadyMarkedBookings$7(MealsServiceImpl.java:335)
    //       at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:178)
    //       at
    // java.util.AbstractList$RandomAccessSpliterator.forEachRemaining(AbstractList.java:720)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
    //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
    //       at
    // xyz.subho.lunchbooking.services.impl.MealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(MealsServiceImpl.java:336)
    //   See https://diff.blue/R013 to resolve this issue.

    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals2);
    mealsList.add(meals1);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L);
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings8() {
    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals1);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);

    MealsModel mealsModel = new MealsModel();
    mealsModel.setDate(LocalDate.ofEpochDay(1L));
    when(mapper2.transform((Meals) any())).thenReturn(mealsModel);
    assertEquals(
        1, mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L).size());
    verify(bookingRepository)
        .findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
    verify(mealsRepository)
        .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
            (LocalDate) any());
    verify(mapper2).transform((Meals) any());
  }

  /**
   * Method under test: {@link
   * MealsServiceImpl#getMealsAvailableForBookingWithAlreadyMarkedBookings(long)}
   */
  @Test
  void testGetMealsAvailableForBookingWithAlreadyMarkedBookings9() {
    Meals meals = new Meals();
    meals.setActivatedAt(6L);
    meals.setCreatedAt(6L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(6L));
    meals.setDeletedAt(6L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(6L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(6L);
    meals.setUpdatedAt(6L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(6L);

    MealOptions mealOptions = new MealOptions();
    mealOptions.removeBookingById(123L);
    mealOptions.setAvailableBookings(new HashSet<>());
    mealOptions.setBookings(new HashSet<>());
    mealOptions.setCount(3);
    mealOptions.setCreatedAt(6L);
    mealOptions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    mealOptions.setDeletedAt(6L);
    mealOptions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    mealOptions.setId(123L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(6L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(6L);

    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(6L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(6L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(6L);
    userMetadata.setUpdatedAt(6L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(6L);

    Bookings bookings = new Bookings();
    bookings.setCancelledAt(6L);
    bookings.setClaimedAt(6L);
    bookings.setCreatedAt(6L);
    bookings.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    bookings.setDate(LocalDate.ofEpochDay(6L));
    bookings.setDeletedAt(6L);
    bookings.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    bookings.setId(123L);
    bookings.setMealOptions(mealOptions);
    bookings.setUpdatedAt(6L);
    bookings.setUpdatedBy("2020-03-01");
    bookings.setUser(userMetadata);
    bookings.setVersion(6L);

    ArrayList<Bookings> bookingsList = new ArrayList<>();
    bookingsList.add(bookings);
    when(bookingRepository.findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any()))
        .thenReturn(bookingsList);
    when(mapper.transform((Bookings) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));

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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals1);
    when(mealsRepository
            .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
                (LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(
        SelectionNotAvailableException.class,
        () -> mealsServiceImpl.getMealsAvailableForBookingWithAlreadyMarkedBookings(123L));
    verify(bookingRepository)
        .findByDateGreaterThanEqualAndUser_IdAndCancelledAtNullOrderByDateAsc(
            (LocalDate) any(), (Long) any());
    verify(mapper).transform((Bookings) any());
    verify(mealsRepository)
        .findByDateGreaterThanEqualAndLockedAtNullAndActivatedAtNotNullOrderByDateAsc(
            (LocalDate) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllMeals(boolean)} */
  @Test
  void testGetAllMeals() {
    when(mealsRepository.findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(mealsServiceImpl.getAllMeals(true).isEmpty());
    verify(mealsRepository).findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllMeals(boolean)} */
  @Test
  void testGetAllMeals2() {
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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals);
    when(mealsRepository.findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertEquals(1, mealsServiceImpl.getAllMeals(true).size());
    verify(mealsRepository).findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any());
    verify(mapper2).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllMeals(boolean)} */
  @Test
  void testGetAllMeals3() {
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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals1);
    mealsList.add(meals);
    when(mealsRepository.findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertEquals(2, mealsServiceImpl.getAllMeals(true).size());
    verify(mealsRepository).findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any());
    verify(mapper2, atLeast(1)).transform((Meals) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllMeals(boolean)} */
  @Test
  void testGetAllMeals4() {
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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals);
    when(mealsRepository.findByDateLessThanOrderByDateDesc((LocalDate) any()))
        .thenReturn(new ArrayList<>());
    when(mealsRepository.findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertTrue(mealsServiceImpl.getAllMeals(false).isEmpty());
    verify(mealsRepository).findByDateLessThanOrderByDateDesc((LocalDate) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllMeals(boolean)} */
  @Test
  void testGetAllMeals5() {
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

    ArrayList<Meals> mealsList = new ArrayList<>();
    mealsList.add(meals);
    when(mealsRepository.findByDateLessThanOrderByDateDesc((LocalDate) any()))
        .thenThrow(new SelectionNotAvailableException("An error occurred"));
    when(mealsRepository.findByDateGreaterThanEqualOrderByDateAsc((LocalDate) any()))
        .thenReturn(mealsList);
    when(mapper2.transform((Meals) any())).thenReturn(new MealsModel());
    assertThrows(SelectionNotAvailableException.class, () -> mealsServiceImpl.getAllMeals(false));
    verify(mealsRepository).findByDateLessThanOrderByDateDesc((LocalDate) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealCountByMealId(long)} */
  @Test
  void testGetMealCountByMealId() {
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertTrue(mealsServiceImpl.getMealCountByMealId(123L).mealOptionCountWithId().isEmpty());
    verify(mealsRepository).findById((Long) any());
  }

  /** Method under test: {@link MealsServiceImpl#getMealCountByMealId(long)} */
  @Test
  void testGetMealCountByMealId2() {
    Meals meals = mock(Meals.class);
    when(meals.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals.getMealOptions()).thenReturn(new HashSet<>());
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
    Optional<Meals> ofResult = Optional.of(meals);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    assertTrue(mealsServiceImpl.getMealCountByMealId(123L).mealOptionCountWithId().isEmpty());
    verify(mealsRepository).findById((Long) any());
    verify(meals).getDate();
    verify(meals).getMealOptions();
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

  /** Method under test: {@link MealsServiceImpl#getMealCountByMealId(long)} */
  @Test
  void testGetMealCountByMealId3() {
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(3);
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(3);

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
    meals.setName("Finding Meal with ID:{}");
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
    mealOptions.setName("Finding Meal with ID:{}");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    HashSet<MealOptions> mealOptionsSet = new HashSet<>();
    mealOptionsSet.add(mealOptions);
    Meals meals1 = mock(Meals.class);
    when(meals1.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals1.getMealOptions()).thenReturn(mealOptionsSet);
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
    Optional<Meals> ofResult = Optional.of(meals1);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    Map<Long, MealOptionCountModel> mealOptionCountWithIdResult =
        mealsServiceImpl.getMealCountByMealId(123L).mealOptionCountWithId();
    assertEquals(1, mealOptionCountWithIdResult.size());
    MealOptionCountModel getResult = mealOptionCountWithIdResult.get(123L);
    assertEquals(3, getResult.available());
    assertEquals(3, getResult.total());
    assertEquals(3, getResult.redeemed());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any());
    verify(mealsRepository).findById((Long) any());
    verify(meals1).getDate();
    verify(meals1).getMealOptions();
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
  }

  /** Method under test: {@link MealsServiceImpl#getMealCountByMealId(long)} */
  @Test
  void testGetMealCountByMealId4() {
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(1);
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(3);

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
    meals.setName("Finding Meal with ID:{}");
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
    mealOptions.setName("Finding Meal with ID:{}");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    HashSet<MealOptions> mealOptionsSet = new HashSet<>();
    mealOptionsSet.add(mealOptions);
    Meals meals1 = mock(Meals.class);
    when(meals1.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals1.getMealOptions()).thenReturn(mealOptionsSet);
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
    Optional<Meals> ofResult = Optional.of(meals1);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    Map<Long, MealOptionCountModel> mealOptionCountWithIdResult =
        mealsServiceImpl.getMealCountByMealId(123L).mealOptionCountWithId();
    assertEquals(1, mealOptionCountWithIdResult.size());
    MealOptionCountModel getResult = mealOptionCountWithIdResult.get(123L);
    assertEquals(3, getResult.available());
    assertEquals(3, getResult.total());
    assertEquals(1, getResult.redeemed());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any());
    verify(mealsRepository).findById((Long) any());
    verify(meals1).getDate();
    verify(meals1).getMealOptions();
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
  }

  /** Method under test: {@link MealsServiceImpl#getMealCountByMealId(long)} */
  @Test
  void testGetMealCountByMealId5() {
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(3);
    when(bookingRepository.countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any()))
        .thenReturn(3);

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
    meals.setName("Finding Meal with ID:{}");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(0L);

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
    mealOptions.setName("Finding Meal with ID:{}");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    HashSet<MealOptions> mealOptionsSet = new HashSet<>();
    mealOptionsSet.add(mealOptions);
    Meals meals1 = mock(Meals.class);
    when(meals1.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(meals1.getMealOptions()).thenReturn(mealOptionsSet);
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
    Optional<Meals> ofResult = Optional.of(meals1);
    when(mealsRepository.findById((Long) any())).thenReturn(ofResult);
    Map<Long, MealOptionCountModel> mealOptionCountWithIdResult =
        mealsServiceImpl.getMealCountByMealId(123L).mealOptionCountWithId();
    assertEquals(1, mealOptionCountWithIdResult.size());
    MealOptionCountModel getResult = mealOptionCountWithIdResult.get(123L);
    assertEquals(3, getResult.available());
    assertEquals(3, getResult.total());
    assertEquals(3, getResult.redeemed());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNotNull(
            (LocalDate) any(), (Long) any());
    verify(bookingRepository)
        .countByDateAndMealOptions_IdAndCancelledAtNullAndClaimedAtNull(
            (LocalDate) any(), (Long) any());
    verify(mealsRepository).findById((Long) any());
    verify(meals1).getDate();
    verify(meals1).getMealOptions();
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
  }

  /** Method under test: {@link MealsServiceImpl#getAllAvailableOptionsForToday()} */
  @Test
  void testGetAllAvailableOptionsForToday() {
    when(availableBookingsRepository.findByDateOrderByCountDesc((LocalDate) any()))
        .thenReturn(new ArrayList<>());
    assertTrue(mealsServiceImpl.getAllAvailableOptionsForToday().isEmpty());
    verify(availableBookingsRepository).findByDateOrderByCountDesc((LocalDate) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllAvailableOptionsForToday()} */
  @Test
  void testGetAllAvailableOptionsForToday2() {
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

    ArrayList<AvailableBookings> availableBookingsList = new ArrayList<>();
    availableBookingsList.add(availableBookings);
    when(availableBookingsRepository.findByDateOrderByCountDesc((LocalDate) any()))
        .thenReturn(availableBookingsList);
    when(mapper1.transform((AvailableBookings) any()))
        .thenReturn(
            new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3));
    assertEquals(1, mealsServiceImpl.getAllAvailableOptionsForToday().size());
    verify(availableBookingsRepository).findByDateOrderByCountDesc((LocalDate) any());
    verify(mapper1).transform((AvailableBookings) any());
  }

  /** Method under test: {@link MealsServiceImpl#getAllAvailableOptionsForToday()} */
  @Test
  void testGetAllAvailableOptionsForToday3() {
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

    ArrayList<AvailableBookings> availableBookingsList = new ArrayList<>();
    availableBookingsList.add(availableBookings1);
    availableBookingsList.add(availableBookings);
    when(availableBookingsRepository.findByDateOrderByCountDesc((LocalDate) any()))
        .thenReturn(availableBookingsList);
    when(mapper1.transform((AvailableBookings) any()))
        .thenReturn(
            new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3));
    assertEquals(2, mealsServiceImpl.getAllAvailableOptionsForToday().size());
    verify(availableBookingsRepository).findByDateOrderByCountDesc((LocalDate) any());
    verify(mapper1, atLeast(1)).transform((AvailableBookings) any());
  }
}
