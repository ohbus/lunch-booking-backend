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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.MealOptionsModel;
import xyz.subho.lunchbooking.models.MealsModel;

@ContextConfiguration(classes = {MealDetailsMapper.class})
@ExtendWith(SpringExtension.class)
class MealDetailsMapperTest {
  @MockBean(name = "MealOptionsMapper")
  private Mapper<MealOptions, MealOptionsModel> mapper;

  @Autowired private MealDetailsMapper mealDetailsMapper;

  /** Method under test: {@link MealDetailsMapper#transform(Meals)} */
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
    MealsModel actualTransformResult = mealDetailsMapper.transform(meals);
    assertEquals(1L, actualTransformResult.getActivatedAt().longValue());
    assertEquals(1L, actualTransformResult.getReadyAt().longValue());
    assertEquals("Name", actualTransformResult.getName());
    assertTrue(actualTransformResult.getMealOptions().isEmpty());
    assertEquals(1L, actualTransformResult.getLockedAt().longValue());
    assertEquals(123L, actualTransformResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformResult.getDate().toString());
  }

  /** Method under test: {@link MealDetailsMapper#transform(Meals)} */
  @Test
  void testTransform2() {
    Meals meals = mock(Meals.class);
    when(meals.getId()).thenReturn(123L);
    when(meals.getActivatedAt()).thenReturn(1L);
    when(meals.getLockedAt()).thenReturn(1L);
    when(meals.getReadyAt()).thenReturn(1L);
    when(meals.getName()).thenReturn("Name");
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
    MealsModel actualTransformResult = mealDetailsMapper.transform(meals);
    assertEquals(1L, actualTransformResult.getActivatedAt().longValue());
    assertEquals(1L, actualTransformResult.getReadyAt().longValue());
    assertEquals("Name", actualTransformResult.getName());
    assertTrue(actualTransformResult.getMealOptions().isEmpty());
    assertEquals(1L, actualTransformResult.getLockedAt().longValue());
    assertEquals(123L, actualTransformResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformResult.getDate().toString());
    verify(meals).getId();
    verify(meals).getActivatedAt();
    verify(meals).getLockedAt();
    verify(meals).getReadyAt();
    verify(meals).getName();
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

  /** Method under test: {@link MealDetailsMapper#transform(Meals)} */
  @Test
  void testTransform3() {
    when(mapper.transform((MealOptions) any())).thenReturn(new MealOptionsModel());

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

    HashSet<MealOptions> mealOptionsSet = new HashSet<>();
    mealOptionsSet.add(mealOptions);
    Meals meals1 = mock(Meals.class);
    when(meals1.getId()).thenReturn(123L);
    when(meals1.getActivatedAt()).thenReturn(1L);
    when(meals1.getLockedAt()).thenReturn(1L);
    when(meals1.getReadyAt()).thenReturn(1L);
    when(meals1.getName()).thenReturn("Name");
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
    MealsModel actualTransformResult = mealDetailsMapper.transform(meals1);
    assertEquals(1L, actualTransformResult.getActivatedAt().longValue());
    assertEquals(1L, actualTransformResult.getReadyAt().longValue());
    assertEquals("Name", actualTransformResult.getName());
    assertEquals(1, actualTransformResult.getMealOptions().size());
    assertEquals(1L, actualTransformResult.getLockedAt().longValue());
    assertEquals(123L, actualTransformResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformResult.getDate().toString());
    verify(mapper).transform((MealOptions) any());
    verify(meals1).getId();
    verify(meals1).getActivatedAt();
    verify(meals1).getLockedAt();
    verify(meals1).getReadyAt();
    verify(meals1).getName();
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

  /** Method under test: {@link MealDetailsMapper#transformBack(MealsModel)} */
  @Test
  void testTransformBack() {
    Meals actualTransformBackResult = mealDetailsMapper.transformBack(new MealsModel());
    assertNull(actualTransformBackResult.getActivatedAt());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertNull(actualTransformBackResult.getReadyAt());
    assertNull(actualTransformBackResult.getName());
    assertTrue(actualTransformBackResult.getMealOptions().isEmpty());
    assertNull(actualTransformBackResult.getLockedAt());
    assertNull(actualTransformBackResult.getId());
    assertNull(actualTransformBackResult.getDate());
  }

  /** Method under test: {@link MealDetailsMapper#transformBack(MealsModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testTransformBack2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Source must not be null
    //       at
    // xyz.subho.lunchbooking.mapper.MealDetailsMapper.transformBack(MealDetailsMapper.java:52)

    mealDetailsMapper.transformBack(null);
  }

  /** Method under test: {@link MealDetailsMapper#transformBack(MealsModel)} */
  @Test
  void testTransformBack3() {
    MealsModel mealsModel = mock(MealsModel.class);
    when(mealsModel.getActivatedAt()).thenReturn(1L);
    when(mealsModel.getId()).thenReturn(123L);
    when(mealsModel.getLockedAt()).thenReturn(1L);
    when(mealsModel.getReadyAt()).thenReturn(1L);
    when(mealsModel.getName()).thenReturn("Name");
    when(mealsModel.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(mealsModel.getMealOptions()).thenReturn(new HashSet<>());
    Meals actualTransformBackResult = mealDetailsMapper.transformBack(mealsModel);
    assertEquals(1L, actualTransformBackResult.getActivatedAt().longValue());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals(1L, actualTransformBackResult.getReadyAt().longValue());
    assertEquals("Name", actualTransformBackResult.getName());
    assertTrue(actualTransformBackResult.getMealOptions().isEmpty());
    assertEquals(1L, actualTransformBackResult.getLockedAt().longValue());
    assertEquals(123L, actualTransformBackResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformBackResult.getDate().toString());
    verify(mealsModel).getActivatedAt();
    verify(mealsModel).getId();
    verify(mealsModel).getLockedAt();
    verify(mealsModel).getReadyAt();
    verify(mealsModel).getName();
    verify(mealsModel).getDate();
    verify(mealsModel).getMealOptions();
  }

  /** Method under test: {@link MealDetailsMapper#transformBack(MealsModel)} */
  @Test
  void testTransformBack4() {
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
    when(mapper.transformBack((MealOptionsModel) any())).thenReturn(mealOptions);

    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    mealOptionsModelSet.add(new MealOptionsModel());
    MealsModel mealsModel = mock(MealsModel.class);
    when(mealsModel.getActivatedAt()).thenReturn(1L);
    when(mealsModel.getId()).thenReturn(123L);
    when(mealsModel.getLockedAt()).thenReturn(1L);
    when(mealsModel.getReadyAt()).thenReturn(1L);
    when(mealsModel.getName()).thenReturn("Name");
    when(mealsModel.getDate()).thenReturn(LocalDate.ofEpochDay(1L));
    when(mealsModel.getMealOptions()).thenReturn(mealOptionsModelSet);
    Meals actualTransformBackResult = mealDetailsMapper.transformBack(mealsModel);
    assertEquals(1L, actualTransformBackResult.getActivatedAt().longValue());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals(1L, actualTransformBackResult.getReadyAt().longValue());
    assertEquals("Name", actualTransformBackResult.getName());
    assertEquals(1, actualTransformBackResult.getMealOptions().size());
    assertEquals(1L, actualTransformBackResult.getLockedAt().longValue());
    assertEquals(123L, actualTransformBackResult.getId().longValue());
    assertEquals("1970-01-02", actualTransformBackResult.getDate().toString());
    verify(mapper).transformBack((MealOptionsModel) any());
    verify(mealsModel).getActivatedAt();
    verify(mealsModel).getId();
    verify(mealsModel).getLockedAt();
    verify(mealsModel).getReadyAt();
    verify(mealsModel).getName();
    verify(mealsModel).getDate();
    verify(mealsModel).getMealOptions();
  }
}
