package xyz.subho.lunchbooking.services.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.MealsService;

@Service
public class MealsServiceImpl implements MealsService {

  @Autowired private MealsRepository mealsRepository;

  @Autowired
  @Qualifier("MealDetailsMapper")
  private Mapper<Meals, MealsModel> mealsRequestModelMapper;

  @Override
  @Transactional
  public MealsModel createMeal(MealsModel mealsModel) {
    var meal = mealsRequestModelMapper.transformBack(mealsModel);
    return mealsRequestModelMapper.transform(mealsRepository.save(meal));
  }
}
