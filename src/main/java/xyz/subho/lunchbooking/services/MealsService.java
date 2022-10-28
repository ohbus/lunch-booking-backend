package xyz.subho.lunchbooking.services;

import java.util.List;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;
import xyz.subho.lunchbooking.models.MealsModel;

public interface MealsService {

  public MealsModel createMeal(@NonNull MealsModel mealsModel);

  public Meals getMealById(long id);

  public MealOptions getMealOptionById(long id);

  public Meals updateMeal(@NonNull Meals meals);

  public MealOptions updateMealOptions(@NonNull MealOptions mealOptions);

  public MealOptions getMealOptionsByBookingId(long id);

  public MealsModel lockMeal(long mealId);

  public MealsModel unlockMeal(long mealId);

  public MealsModel activateMeal(long mealId);

  public MealsModel deactivateMeal(long mealId);

  public List<MealsModel> getMealsAvailableForBookingWithAlreadyMarkedBookings(long userId);

  public List<MealsModel> getAllMeals(boolean today);

  public List<AvailableOptionsResponseModel> getAllAvailableOptionsForToday();
}
