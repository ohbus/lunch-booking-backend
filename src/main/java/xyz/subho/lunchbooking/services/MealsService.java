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

package xyz.subho.lunchbooking.services;

import java.util.List;
import org.springframework.lang.NonNull;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;
import xyz.subho.lunchbooking.models.MealAvailableCountModel;
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

  public MealsModel makeMealReady(long mealId);

  public MealsModel makeMealUnready(long mealId);

  public List<MealsModel> getMealsAvailableForBookingWithAlreadyMarkedBookings(long userId);

  public List<MealsModel> getAllMeals(boolean today);

  public MealAvailableCountModel getMealCountByMealId(long mealId);

  public List<AvailableOptionsResponseModel> getAllAvailableOptionsForToday();
}
