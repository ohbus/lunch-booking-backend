package xyz.subho.lunchbooking.services;

import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.MealsModel;

public interface MealsService {

  public MealsModel createMeal(MealsModel mealsModel);

  public Meals getMealById(long id);

  public MealOptions getMealOptionById(long id);

  public Meals updateMeal(Meals meals);

  public MealOptions updateMealOptions(MealOptions mealOptions);
}
