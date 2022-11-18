package xyz.subho.lunchbooking.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BookingsTest {
  /** Method under test: {@link Bookings#availBooking()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testAvailBooking() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new Bookings()).availBooking();
  }

  /** Method under test: {@link Bookings#cancelBooking()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testCancelBooking() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new Bookings()).cancelBooking();
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    assertNotEquals(bookings, null);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    assertNotEquals(bookings, "Different type to Bookings");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Bookings#equals(Object)}
   *   <li>{@link Bookings#hashCode()}
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
    assertEquals(bookings, bookings);
    int expectedHashCodeResult = bookings.hashCode();
    assertEquals(expectedHashCodeResult, bookings.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Bookings#equals(Object)}
   *   <li>{@link Bookings#hashCode()}
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
    assertEquals(bookings, bookings1);
    int expectedHashCodeResult = bookings.hashCode();
    assertEquals(expectedHashCodeResult, bookings1.hashCode());
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    bookings.setCancelledAt(3L);
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
    assertNotEquals(bookings, bookings1);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    bookings.setClaimedAt(3L);
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
    assertNotEquals(bookings, bookings1);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    bookings.setCreatedAt(3L);
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
    assertNotEquals(bookings, bookings1);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
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
    bookings.setDate(LocalDate.ofEpochDay(3L));
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
    assertNotEquals(bookings, bookings1);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
  @Test
  void testEquals9() {
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
    assertNotEquals(bookings, bookings1);
  }

  /** Method under test: {@link Bookings#equals(Object)} */
  @Test
  void testEquals10() {
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
    UserMetadata userMetadata = mock(UserMetadata.class);
    doNothing().when(userMetadata).setCreatedAt((Long) any());
    doNothing().when(userMetadata).setCreatedBy((String) any());
    doNothing().when(userMetadata).setDeletedAt((Long) any());
    doNothing().when(userMetadata).setDeletedBy((String) any());
    doNothing().when(userMetadata).setId((Long) any());
    doNothing().when(userMetadata).setUpdatedAt((Long) any());
    doNothing().when(userMetadata).setUpdatedBy((String) any());
    doNothing().when(userMetadata).setVersion((Long) any());
    doNothing().when(userMetadata).setBookings((Set<Bookings>) any());
    doNothing().when(userMetadata).setEmailId((String) any());
    doNothing().when(userMetadata).setFirstName((String) any());
    doNothing().when(userMetadata).setLastName((String) any());
    doNothing().when(userMetadata).setMobile((String) any());
    doNothing().when(userMetadata).setSubscribedAt((Long) any());
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
    assertNotEquals(bookings, bookings1);
  }
}
