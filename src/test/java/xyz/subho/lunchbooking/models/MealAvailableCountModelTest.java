package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MealAvailableCountModelTest {
  /** Method under test: {@link MealAvailableCountModel#MealAvailableCountModel(Map)} */
  @Test
  void testConstructor() {
    HashMap<Long, MealOptionCountModel> resultLongMealOptionCountModelMap = new HashMap<>();
    Map<Long, MealOptionCountModel> mealOptionCountWithIdResult =
        (new MealAvailableCountModel(resultLongMealOptionCountModelMap)).mealOptionCountWithId();
    assertSame(resultLongMealOptionCountModelMap, mealOptionCountWithIdResult);
    assertTrue(mealOptionCountWithIdResult.isEmpty());
  }

  /** Method under test: {@link MealAvailableCountModel#mealOptionCountWithId()} */
  @Test
  void testMealOptionCountWithId() {
    HashMap<Long, MealOptionCountModel> resultLongMealOptionCountModelMap = new HashMap<>();
    Map<Long, MealOptionCountModel> actualMealOptionCountWithIdResult =
        (new MealAvailableCountModel(resultLongMealOptionCountModelMap)).mealOptionCountWithId();
    assertSame(resultLongMealOptionCountModelMap, actualMealOptionCountWithIdResult);
    assertTrue(actualMealOptionCountWithIdResult.isEmpty());
  }
}
