package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MealOptionsModelTest {
  /** Method under test: {@link MealOptionsModel#MealOptionsModel(String)} */
  @Test
  void testConstructor() {
    MealOptionsModel actualMealOptionsModel = new MealOptionsModel("Name");
    assertEquals(0, actualMealOptionsModel.getCount());
    assertNull(actualMealOptionsModel.getSelected());
    assertEquals("Name", actualMealOptionsModel.getName());
    assertNull(actualMealOptionsModel.getId());
  }

  /** Method under test: {@link MealOptionsModel#MealOptionsModel(String, Integer)} */
  @Test
  void testConstructor2() {
    MealOptionsModel actualMealOptionsModel = new MealOptionsModel("Name", 3);

    assertEquals(3, actualMealOptionsModel.getCount());
    assertNull(actualMealOptionsModel.getSelected());
    assertEquals("Name", actualMealOptionsModel.getName());
    assertNull(actualMealOptionsModel.getId());
  }
}
