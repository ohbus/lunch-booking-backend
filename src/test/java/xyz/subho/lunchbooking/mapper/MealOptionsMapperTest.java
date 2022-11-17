package xyz.subho.lunchbooking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
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
import xyz.subho.lunchbooking.models.MealOptionsModel;

@ContextConfiguration(classes = {MealOptionsMapper.class})
@ExtendWith(SpringExtension.class)
class MealOptionsMapperTest {
  @Autowired private MealOptionsMapper mealOptionsMapper;

  /** Method under test: {@link MealOptionsMapper#transform(MealOptions)} */
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
    MealOptionsModel actualTransformResult = mealOptionsMapper.transform(mealOptions);
    assertEquals(3, actualTransformResult.getCount());
    assertEquals("Name", actualTransformResult.getName());
    assertEquals(123L, actualTransformResult.getId().longValue());
  }

  /** Method under test: {@link MealOptionsMapper#transform(MealOptions)} */
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
    MealOptions mealOptions = mock(MealOptions.class);
    when(mealOptions.getCount()).thenReturn(3);
    when(mealOptions.getId()).thenReturn(123L);
    when(mealOptions.getName()).thenReturn("Name");
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
    MealOptionsModel actualTransformResult = mealOptionsMapper.transform(mealOptions);
    assertEquals(3, actualTransformResult.getCount());
    assertEquals("Name", actualTransformResult.getName());
    assertEquals(123L, actualTransformResult.getId().longValue());
    verify(mealOptions).getCount();
    verify(mealOptions).getId();
    verify(mealOptions).getName();
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

  /** Method under test: {@link MealOptionsMapper#transformBack(MealOptionsModel)} */
  @Test
  void testTransformBack() {
    MealOptions actualTransformBackResult = mealOptionsMapper.transformBack(new MealOptionsModel());
    assertTrue(actualTransformBackResult.getAvailableBookings().isEmpty());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertNull(actualTransformBackResult.getName());
    assertNull(actualTransformBackResult.getId());
    assertEquals(0, actualTransformBackResult.getCount());
    assertTrue(actualTransformBackResult.getBookings().isEmpty());
  }

  /** Method under test: {@link MealOptionsMapper#transformBack(MealOptionsModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testTransformBack2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Source must not be null
    //       at
    // xyz.subho.lunchbooking.mapper.MealOptionsMapper.transformBack(MealOptionsMapper.java:39)

    mealOptionsMapper.transformBack(null);
  }

  /** Method under test: {@link MealOptionsMapper#transformBack(MealOptionsModel)} */
  @Test
  void testTransformBack3() {
    MealOptionsModel mealOptionsModel = mock(MealOptionsModel.class);
    when(mealOptionsModel.getCount()).thenReturn(3);
    when(mealOptionsModel.getId()).thenReturn(123L);
    when(mealOptionsModel.getName()).thenReturn("Name");
    MealOptions actualTransformBackResult = mealOptionsMapper.transformBack(mealOptionsModel);
    assertTrue(actualTransformBackResult.getAvailableBookings().isEmpty());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals("Name", actualTransformBackResult.getName());
    assertEquals(123L, actualTransformBackResult.getId().longValue());
    assertEquals(3, actualTransformBackResult.getCount());
    assertTrue(actualTransformBackResult.getBookings().isEmpty());
    verify(mealOptionsModel).getCount();
    verify(mealOptionsModel).getId();
    verify(mealOptionsModel).getName();
  }
}
