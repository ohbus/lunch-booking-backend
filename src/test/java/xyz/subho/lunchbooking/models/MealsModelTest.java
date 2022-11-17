package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MealsModelTest {
  /** Method under test: {@link MealsModel#MealsModel(String, LocalDate)} */
  @Test
  void testConstructor() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    MealsModel actualMealsModel = new MealsModel("Name", ofEpochDayResult);

    assertNull(actualMealsModel.getActivatedAt());
    assertNull(actualMealsModel.getReadyAt());
    assertEquals("Name", actualMealsModel.getName());
    assertTrue(actualMealsModel.getMealOptions().isEmpty());
    assertNull(actualMealsModel.getLockedAt());
    assertNull(actualMealsModel.getId());
    LocalDate date = actualMealsModel.getDate();
    assertSame(ofEpochDayResult, date);
    assertEquals("1970-01-02", date.toString());
  }

  /** Method under test: {@link MealsModel#MealsModel(String, LocalDate, Set)} */
  @Test
  void testConstructor2() {
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    HashSet<MealOptionsModel> mealOptionsModelSet = new HashSet<>();
    MealsModel actualMealsModel = new MealsModel("Name", ofEpochDayResult, mealOptionsModelSet);

    assertNull(actualMealsModel.getActivatedAt());
    assertNull(actualMealsModel.getReadyAt());
    assertEquals("Name", actualMealsModel.getName());
    Set<MealOptionsModel> mealOptions = actualMealsModel.getMealOptions();
    assertSame(mealOptionsModelSet, mealOptions);
    assertTrue(mealOptions.isEmpty());
    assertNull(actualMealsModel.getLockedAt());
    assertNull(actualMealsModel.getId());
    LocalDate date = actualMealsModel.getDate();
    assertSame(ofEpochDayResult, date);
    assertEquals("1970-01-02", date.toString());
  }
}
