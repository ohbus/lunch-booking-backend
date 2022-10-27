package xyz.subho.lunchbooking.services;

import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.MealsModel;

public interface MealsService {

  public MealsModel createMeal(@NonNull MealsModel mealsModel);

  public Meals getMealById(long id);

  public MealOptions getMealOptionById(long id);

  public Meals updateMeal(@NonNull Meals meals);

  public MealOptions updateMealOptions(@NonNull MealOptions mealOptions);

  public MealOptions getMealOptionsByBookingId(long id);

  public void lockMeal(long mealId);

  public void unlockMeal(long mealId);

  public void activateMeal(long mealId);

  public void deactivateMeal(long mealId);
}
