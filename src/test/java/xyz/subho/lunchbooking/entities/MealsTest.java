package xyz.subho.lunchbooking.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MealsTest {
  /** Method under test: {@link Meals#lock()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testLock() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new Meals()).lock();
  }

  /** Method under test: {@link Meals#lock()} */
  @Test
  void testLock2() {
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

    Meals meals1 = new Meals();
    meals1.addMealOptions(mealOptions);
    meals1.lock();
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
  }

  /** Method under test: {@link Meals#isLocked()} */
  @Test
  void testIsLocked() {
    assertFalse((new Meals()).isLocked());
  }

  /** Method under test: {@link Meals#isLocked()} */
  @Test
  void testIsLocked2() {
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
    assertTrue(meals.isLocked());
  }

  /** Method under test: {@link Meals#markReady()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testMarkReady() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new Meals()).markReady();
  }

  /** Method under test: {@link Meals#markReady()} */
  @Test
  void testMarkReady2() {
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

    Meals meals1 = new Meals();
    meals1.addMealOptions(mealOptions);
    meals1.markReady();
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
  }

  /** Method under test: {@link Meals#isReady()} */
  @Test
  void testIsReady() {
    assertFalse((new Meals()).isReady());
  }

  /** Method under test: {@link Meals#isReady()} */
  @Test
  void testIsReady2() {
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
    assertTrue(meals.isReady());
  }

  /** Method under test: {@link Meals#activate()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testActivate() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new Meals()).activate();
  }

  /** Method under test: {@link Meals#activate()} */
  @Test
  void testActivate2() {
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

    Meals meals1 = new Meals();
    meals1.addMealOptions(mealOptions);
    meals1.activate();
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
  }

  /** Method under test: {@link Meals#isActivated()} */
  @Test
  void testIsActivated() {
    assertFalse((new Meals()).isActivated());
  }

  /** Method under test: {@link Meals#isActivated()} */
  @Test
  void testIsActivated2() {
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
    assertTrue(meals.isActivated());
  }

  /** Method under test: {@link Meals#addMealOptions(MealOptions)} */
  @Test
  void testAddMealOptions() {
    Meals meals = new Meals();

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
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);
    assertEquals(1, meals.addMealOptions(mealOptions));
    assertEquals(1, meals.getMealOptions().size());
  }

  /** Method under test: {@link Meals#removeMealOptions(MealOptions)} */
  @Test
  void testRemoveMealOptions() {
    Meals meals = new Meals();

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
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);
    assertEquals(0, meals.removeMealOptions(mealOptions));
    assertTrue(meals.getMealOptions().isEmpty());
  }

  /** Method under test: {@link Meals#removeMealOptions(MealOptions)} */
  @Test
  void testRemoveMealOptions2() {
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
    meals1.addMealOptions(mealOptions);

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
    mealOptions1.setMeals(meals2);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);
    assertEquals(0, meals1.removeMealOptions(mealOptions1));
    assertTrue(meals1.getMealOptions().isEmpty());
  }

  /** Method under test: {@link Meals#removeMealOptions(MealOptions)} */
  @Test
  void testRemoveMealOptions3() {
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
    mealOptions.setId(6L);
    mealOptions.setMeals(meals);
    mealOptions.setName("Name");
    mealOptions.setUpdatedAt(1L);
    mealOptions.setUpdatedBy("2020-03-01");
    mealOptions.setVersion(1L);

    Meals meals1 = new Meals();
    meals1.addMealOptions(mealOptions);

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
    mealOptions1.setMeals(meals2);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(1L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(1L);
    assertEquals(1, meals1.removeMealOptions(mealOptions1));
    assertEquals(1, meals1.getMealOptions().size());
  }

  /** Method under test: {@link Meals#removeMealOptions(MealOptions)} */
  @Test
  void testRemoveMealOptions4() {
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
    mealOptions1.setId(1L);
    mealOptions1.setMeals(meals1);
    mealOptions1.setName("Name");
    mealOptions1.setUpdatedAt(6L);
    mealOptions1.setUpdatedBy("2020-03-01");
    mealOptions1.setVersion(6L);

    Meals meals2 = new Meals();
    meals2.addMealOptions(mealOptions1);
    meals2.addMealOptions(mealOptions);

    Meals meals3 = new Meals();
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
    mealOptions2.setMeals(meals3);
    mealOptions2.setName("Name");
    mealOptions2.setUpdatedAt(1L);
    mealOptions2.setUpdatedBy("2020-03-01");
    mealOptions2.setVersion(1L);
    assertEquals(1, meals2.removeMealOptions(mealOptions2));
    assertEquals(1, meals2.getMealOptions().size());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Meals#Meals()}
   *   <li>{@link Meals#deactivate()}
   *   <li>{@link Meals#markUnReady()}
   *   <li>{@link Meals#unlock()}
   * </ul>
   */
  @Test
  void testConstructor() {
    Meals actualMeals = new Meals();
    actualMeals.deactivate();
    actualMeals.markUnReady();
    actualMeals.unlock();
    assertNull(actualMeals.getActivatedAt());
    assertEquals(1L, actualMeals.getVersion().longValue());
    assertNull(actualMeals.getUpdatedBy());
    assertNull(actualMeals.getUpdatedAt());
    assertNull(actualMeals.getReadyAt());
    assertNull(actualMeals.getName());
    assertTrue(actualMeals.getMealOptions().isEmpty());
    assertNull(actualMeals.getLockedAt());
    assertNull(actualMeals.getId());
    assertNull(actualMeals.getDeletedBy());
    assertNull(actualMeals.getDeletedAt());
    assertNull(actualMeals.getDate());
    assertNull(actualMeals.getCreatedBy());
    assertNull(actualMeals.getCreatedAt());
  }

  /** Method under test: {@link Meals#equals(Object)} */
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
    assertNotEquals(meals, null);
  }

  /** Method under test: {@link Meals#equals(Object)} */
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
    assertNotEquals(meals, "Different type to Meals");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Meals#equals(Object)}
   *   <li>{@link Meals#hashCode()}
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
    assertEquals(meals, meals);
    int expectedHashCodeResult = meals.hashCode();
    assertEquals(expectedHashCodeResult, meals.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Meals#equals(Object)}
   *   <li>{@link Meals#hashCode()}
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
    assertEquals(meals, meals1);
    int expectedHashCodeResult = meals.hashCode();
    assertEquals(expectedHashCodeResult, meals1.hashCode());
  }

  /** Method under test: {@link Meals#equals(Object)} */
  @Test
  void testEquals5() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(1L);
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
    assertNotEquals(meals, meals1);
  }

  /** Method under test: {@link Meals#equals(Object)} */
  @Test
  void testEquals6() {
    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(null);
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
    assertNotEquals(meals, meals1);
  }
}
