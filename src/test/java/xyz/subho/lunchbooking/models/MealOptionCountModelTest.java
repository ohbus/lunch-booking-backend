package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MealOptionCountModelTest {
  /** Method under test: {@link MealOptionCountModel#available()} */
  @Test
  void testAvailable() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).available());
  }

  /** Method under test: {@link MealOptionCountModel#MealOptionCountModel(int, int, int)} */
  @Test
  void testConstructor() {
    MealOptionCountModel actualMealOptionCountModel = new MealOptionCountModel(1, 1, 1);

    assertEquals(1, actualMealOptionCountModel.available());
    assertEquals(1, actualMealOptionCountModel.total());
    assertEquals(1, actualMealOptionCountModel.redeemed());
  }

  /** Method under test: {@link MealOptionCountModel#redeemed()} */
  @Test
  void testRedeemed() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).redeemed());
  }

  /** Method under test: {@link MealOptionCountModel#total()} */
  @Test
  void testTotal() {
    assertEquals(1, (new MealOptionCountModel(1, 1, 1)).total());
  }
}
