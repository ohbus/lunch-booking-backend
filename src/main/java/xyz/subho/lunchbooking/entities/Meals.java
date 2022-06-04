package xyz.subho.lunchbooking.entities;

import xyz.subho.lunchbooking.exceptions.InvalidMealTypeException;

public enum Meals {
  NON_VEG(1),
  VEG(2);

  private int meal;

  Meals(int mealType) {
    this.meal = mealType;
  }

  public int getGender() {
    return meal;
  }

  public static Meals getValidGender(String mealType) {
    Meals meal;
    try {
      meal = Meals.valueOf(mealType);
    } catch (IllegalArgumentException ex) {
      throw new InvalidMealTypeException(
          String.format(
              "Invalid meal string %s. ONLY SUPPORTED: NON_VEG or VEG strings", mealType));
    }
    return meal;
  }
}
