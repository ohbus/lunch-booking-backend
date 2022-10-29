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

import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.MealOptionsModel;
import xyz.subho.lunchbooking.models.MealsModel;

@Component("MealDetailsMapper")
public class MealDetailsMapper implements Mapper<Meals, MealsModel> {

  @Autowired
  @Qualifier("MealOptionsMapper")
  private Mapper<MealOptions, MealOptionsModel> mealOptionsMapper;

  @Override
  public MealsModel transform(Meals source) {
    var model = new MealsModel();
    BeanUtils.copyProperties(source, model);
    model.setMealOptions(
        source.getMealOptions().stream()
            .map(mealOptions -> mealOptionsMapper.transform(mealOptions))
            .collect(Collectors.toSet()));
    return model;
  }

  @Override
  public Meals transformBack(MealsModel source) {
    var meal = new Meals();
    BeanUtils.copyProperties(source, meal);
    source
        .getMealOptions()
        .forEach(
            mealOptionsModel ->
                meal.addMealOptions(mealOptionsMapper.transformBack(mealOptionsModel)));
    return meal;
  }
}
