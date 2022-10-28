package xyz.subho.lunchbooking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import xyz.subho.lunchbooking.entities.AvailableBookings;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;

@Component("AvailableOptionsMapper")
public class AvailableOptionsMapper
    implements Mapper<AvailableBookings, AvailableOptionsResponseModel> {

  @Override
  public AvailableOptionsResponseModel transform(AvailableBookings source) {
    return new AvailableOptionsResponseModel(
        source.getId(),
        source.getDate(),
        source.getMealOptions().getId(),
        source.getMealOptions().getName(),
        source.getCount());
  }

  @Override
  public AvailableBookings transformBack(AvailableOptionsResponseModel source) {
    var mealOption = new MealOptions().withName(source.mealOptionName());
    mealOption.setId(source.mealOptionId());
    var entity = new AvailableBookings(source.date(), mealOption);
    BeanUtils.copyProperties(source, entity);
    return entity;
  }
}
