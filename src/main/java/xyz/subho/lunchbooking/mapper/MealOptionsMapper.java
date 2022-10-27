package xyz.subho.lunchbooking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.models.MealOptionsModel;

@Component("MealOptionsMapper")
public class MealOptionsMapper implements Mapper<MealOptions, MealOptionsModel> {

  @Override
  public MealOptionsModel transform(MealOptions source) {
    var model = new MealOptionsModel();
    BeanUtils.copyProperties(source, model);
    return model;
  }

  @Override
  public MealOptions transformBack(MealOptionsModel source) {
    var mealOptions = new MealOptions();
    BeanUtils.copyProperties(source, mealOptions);
    return mealOptions;
  }
}
