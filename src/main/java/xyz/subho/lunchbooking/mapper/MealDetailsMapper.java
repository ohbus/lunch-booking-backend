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
    meal.setMealOptions(
        source.getMealOptions().stream()
            .map(mealOptionsModel -> mealOptionsMapper.transformBack(mealOptionsModel))
            .collect(Collectors.toSet()));
    return meal;
  }
}
