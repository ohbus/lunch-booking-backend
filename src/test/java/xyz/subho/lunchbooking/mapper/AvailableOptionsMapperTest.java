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
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;

@ContextConfiguration(classes = {AvailableOptionsMapper.class})
@ExtendWith(SpringExtension.class)
class AvailableOptionsMapperTest {
  @Autowired private AvailableOptionsMapper availableOptionsMapper;

  /** Method under test: {@link AvailableOptionsMapper#transform(AvailableBookings)} */
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
    AvailableOptionsResponseModel actualTransformResult =
        availableOptionsMapper.transform(availableBookings);
    assertEquals(3, actualTransformResult.count());
    assertEquals("Name", actualTransformResult.mealOptionName());
    assertEquals(123L, actualTransformResult.mealOptionId());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
  }

  /** Method under test: {@link AvailableOptionsMapper#transform(AvailableBookings)} */
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
    AvailableBookings availableBookings = mock(AvailableBookings.class);
    when(availableBookings.getCount()).thenReturn(3);
    when(availableBookings.getId()).thenReturn(123L);
    when(availableBookings.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    doNothing().when(availableBookings).setCount(anyInt());
    doNothing().when(availableBookings).setDate((LocalDate) any());
    doNothing().when(availableBookings).setMealOptions((MealOptions) any());
    doNothing().when(availableBookings).setCreatedAt((Long) any());
    doNothing().when(availableBookings).setCreatedBy((String) any());
    doNothing().when(availableBookings).setDeletedAt((Long) any());
    doNothing().when(availableBookings).setDeletedBy((String) any());
    doNothing().when(availableBookings).setId((Long) any());
    doNothing().when(availableBookings).setUpdatedAt((Long) any());
    doNothing().when(availableBookings).setUpdatedBy((String) any());
    doNothing().when(availableBookings).setVersion((Long) any());
    when(availableBookings.getMealOptions()).thenReturn(mealOptions1);
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
    AvailableOptionsResponseModel actualTransformResult =
        availableOptionsMapper.transform(availableBookings);
    assertEquals(3, actualTransformResult.count());
    assertEquals("Name", actualTransformResult.mealOptionName());
    assertEquals(123L, actualTransformResult.mealOptionId());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
    verify(availableBookings).getCount();
    verify(availableBookings).getId();
    verify(availableBookings).getDate();
    verify(availableBookings).setCount(anyInt());
    verify(availableBookings).setDate((LocalDate) any());
    verify(availableBookings).setMealOptions((MealOptions) any());
    verify(availableBookings).setCreatedAt((Long) any());
    verify(availableBookings).setCreatedBy((String) any());
    verify(availableBookings).setDeletedAt((Long) any());
    verify(availableBookings).setDeletedBy((String) any());
    verify(availableBookings).setId((Long) any());
    verify(availableBookings).setUpdatedAt((Long) any());
    verify(availableBookings).setUpdatedBy((String) any());
    verify(availableBookings).setVersion((Long) any());
    verify(availableBookings, atLeast(1)).getMealOptions();
  }

  /** Method under test: {@link AvailableOptionsMapper#transform(AvailableBookings)} */
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
    AvailableBookings availableBookings = mock(AvailableBookings.class);
    when(availableBookings.getCount()).thenReturn(3);
    when(availableBookings.getId()).thenReturn(123L);
    when(availableBookings.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    doNothing().when(availableBookings).setCount(anyInt());
    doNothing().when(availableBookings).setDate((LocalDate) any());
    doNothing().when(availableBookings).setMealOptions((MealOptions) any());
    doNothing().when(availableBookings).setCreatedAt((Long) any());
    doNothing().when(availableBookings).setCreatedBy((String) any());
    doNothing().when(availableBookings).setDeletedAt((Long) any());
    doNothing().when(availableBookings).setDeletedBy((String) any());
    doNothing().when(availableBookings).setId((Long) any());
    doNothing().when(availableBookings).setUpdatedAt((Long) any());
    doNothing().when(availableBookings).setUpdatedBy((String) any());
    doNothing().when(availableBookings).setVersion((Long) any());
    when(availableBookings.getMealOptions()).thenReturn(mealOptions1);
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
    AvailableOptionsResponseModel actualTransformResult =
        availableOptionsMapper.transform(availableBookings);
    assertEquals(3, actualTransformResult.count());
    assertEquals("Name", actualTransformResult.mealOptionName());
    assertEquals(123L, actualTransformResult.mealOptionId());
    assertEquals(123L, actualTransformResult.id());
    assertEquals("1970-01-02", actualTransformResult.date().toString());
    verify(availableBookings).getCount();
    verify(availableBookings).getId();
    verify(availableBookings).getDate();
    verify(availableBookings).setCount(anyInt());
    verify(availableBookings).setDate((LocalDate) any());
    verify(availableBookings).setMealOptions((MealOptions) any());
    verify(availableBookings).setCreatedAt((Long) any());
    verify(availableBookings).setCreatedBy((String) any());
    verify(availableBookings).setDeletedAt((Long) any());
    verify(availableBookings).setDeletedBy((String) any());
    verify(availableBookings).setId((Long) any());
    verify(availableBookings).setUpdatedAt((Long) any());
    verify(availableBookings).setUpdatedBy((String) any());
    verify(availableBookings).setVersion((Long) any());
    verify(availableBookings, atLeast(1)).getMealOptions();
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

  /**
   * Method under test: {@link AvailableOptionsMapper#transformBack(AvailableOptionsResponseModel)}
   */
  @Test
  void testTransformBack() {
    AvailableBookings actualTransformBackResult =
        availableOptionsMapper.transformBack(
            new AvailableOptionsResponseModel(
                123L, LocalDate.ofEpochDay(1L), 123L, "Meal Option Name", 3));
    assertEquals(3, actualTransformBackResult.getCount());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals(123L, actualTransformBackResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformBackResult.getDate().toString());
    MealOptions mealOptions = actualTransformBackResult.getMealOptions();
    assertEquals(123L, mealOptions.getId().longValue());
    assertEquals(0, mealOptions.getCount());
    assertTrue(mealOptions.getBookings().isEmpty());
    assertTrue(mealOptions.getAvailableBookings().isEmpty());
    assertEquals("Meal Option Name", mealOptions.getName());
    assertEquals(1L, mealOptions.getVersion().longValue());
    assertNull(mealOptions.getMeals());
  }

  /**
   * Method under test: {@link AvailableOptionsMapper#transformBack(AvailableOptionsResponseModel)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testTransformBack2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.AvailableOptionsResponseModel.mealOptionName()" because
    // "source" is null
    //       at
    // xyz.subho.lunchbooking.mapper.AvailableOptionsMapper.transformBack(AvailableOptionsMapper.java:43)

    availableOptionsMapper.transformBack(null);
  }
}
