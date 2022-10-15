package xyz.subho.lunchbooking.services.impl;

import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.exceptions.MealNotFoundException;
import xyz.subho.lunchbooking.exceptions.MealOptionsNotFoundException;
import xyz.subho.lunchbooking.mapper.Mapper;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.repositories.MealOptionsRepository;
import xyz.subho.lunchbooking.repositories.MealsRepository;
import xyz.subho.lunchbooking.services.MealsService;

@Slf4j
@Service
public class MealsServiceImpl implements MealsService {

  @Autowired private MealsRepository mealsRepository;

  @Autowired private MealOptionsRepository mealOptionsRepository;

  @Autowired
  @Qualifier("MealDetailsMapper")
  private Mapper<Meals, MealsModel> mealsRequestModelMapper;

  @Override
  @Transactional
  public MealsModel createMeal(MealsModel mealsModel) {
    log.debug("Creating Meals:{}", mealsModel.toString());
    var meal = mealsRequestModelMapper.transformBack(mealsModel);
    var mealModel = mealsRequestModelMapper.transform(mealsRepository.save(meal));
    addMealToMealOptions(meal.getMealOptions(), meal);
    return mealModel;
  }

  private void addMealToMealOptions(Set<MealOptions> mealOptionsSet, Meals meals) {
    mealOptionsSet.forEach(mealOptions -> mealOptions.setMeals(meals));
  }

  @Override
  public Meals getMealById(long id) {

    log.debug("Finding Meal with ID:{}", id);
    var mealOpt = mealsRepository.findById(id);
    if (mealOpt.isEmpty()) {
      log.error("Cannot Find Meal with ID:{}", id);
      throw new MealNotFoundException(String.format("There is not Meal with ID:%s", id));
    }
    log.debug("Found Meal with ID:{}", id);
    return mealOpt.get();
  }

  @Override
  public MealOptions getMealOptionById(long id) {

    log.debug("Finding Meal Option with ID:{}", id);
    var mealOptionOpt = mealOptionsRepository.findById(id);
    if (mealOptionOpt.isEmpty()) {
      log.error("Cannot Find Meal Options with ID:{}", id);
      throw new MealOptionsNotFoundException(
          String.format("Meal Options with ID:%s is not present"));
    }
    log.debug("Found Meal Options with ID:{}", id);
    return mealOptionOpt.get();
  }

  @Transactional
  public Meals updateMeal(Meals meals) {
    if (Objects.isNull(meals.getId())) {
      log.error("Meal ID cannot be none while updating");
      throw new MealNotFoundException("Meal ID cannot be blank");
    }
    log.debug("Updating Meal with ID:{}", meals.getId());
    return mealsRepository.save(meals);
  }

  @Transactional
  public MealOptions updateMealOptions(MealOptions mealOptions) {
    if (Objects.isNull(mealOptions.getId())) {
      log.error("Meal Options ID cannot be blank while updating");
      throw new MealOptionsNotFoundException("Meal Options ID cannot be blank");
    }
    log.debug("Updating Meal Options with ID:{}", mealOptions.getId());
    return mealOptionsRepository.save(mealOptions);
  }
}
