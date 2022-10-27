package xyz.subho.lunchbooking.services.impl;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import xyz.subho.lunchbooking.entities.MealOptions;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.exceptions.*;
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
  public MealsModel createMeal(@NonNull MealsModel mealsModel) {
    log.debug("Creating Meals:{}", mealsModel.toString());
    if (mealsRepository.existsByDate(mealsModel.getDate())) {
      log.error("Meal already exists for the date:{}", mealsModel.getDate());
      throw new SelectionNotAvailableException(
          String.format(
              "Meal:%s cannot be saved as there already exists a meal for date:%s",
              mealsModel.getName(), mealsModel.getDate()));
    }
    var meal = mealsRequestModelMapper.transformBack(mealsModel);
    var mealModel = mealsRequestModelMapper.transform(mealsRepository.save(meal));
    addMealToMealOptions(meal.getMealOptions(), meal);
    return mealModel;
  }

  private void addMealToMealOptions(
      @NonNull Set<MealOptions> mealOptionsSet, @NonNull Meals meals) {
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
          String.format("Meal Options with ID:%s is not present", id));
    }
    log.debug("Found Meal Options with ID:{}", id);
    return mealOptionOpt.get();
  }

  @Transactional
  public Meals updateMeal(@NonNull Meals meals) {
    if (Objects.isNull(meals.getId())) {
      log.error("Meal ID cannot be none while updating");
      throw new MealNotFoundException("Meal ID cannot be blank");
    }
    log.debug("Updating Meal with ID:{}", meals.getId());
    return mealsRepository.save(meals);
  }

  @Transactional
  public MealOptions updateMealOptions(@NonNull MealOptions mealOptions) {
    if (Objects.isNull(mealOptions.getId())) {
      log.error("Meal Options ID cannot be blank while updating");
      throw new MealOptionsNotFoundException("Meal Options ID cannot be blank");
    }
    log.debug("Updating Meal Options with ID:{}", mealOptions.getId());
    return mealOptionsRepository.save(mealOptions);
  }

  @Override
  public MealOptions getMealOptionsByBookingId(long id) {
    var mealOptionOpt = mealOptionsRepository.findByBookingsMealOptions_Bookings_Id(id);
    if (mealOptionOpt.isEmpty()) {
      log.error("Cannot Find Meal Options with Booking ID:{}", id);
      throw new MealOptionsNotFoundException(
          String.format("Meal Options with Booking ID:%s is not present", id));
    }
    log.debug("Found Meal Options with Booking ID:{}", id);
    return mealOptionOpt.get();
  }

  @Override
  public void lockMeal(long mealId) {
    var meal = getMealById(mealId);
    if (meal.isLocked()) {
      log.error("Meal:{} is already Locked!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is already Locked!", meal.getName()));
    }
    if (!LocalDate.now().equals(meal.getDate())) {
      log.error("Meal Date for:{} cannot be locked today!", meal.getDate());
      throw new InvalidMealOperation(
          String.format(
              "Cannot Lock Meal:%s today. Can be locked only on:%s",
              meal.getName(), meal.getDate()));
    }
    meal.lock();
  }

  @Override
  public void unlockMeal(long mealId) {
    var meal = getMealById(mealId);
    if (!meal.isLocked()) {
      log.error("Meal:{} is not Locked!", mealId);
      throw new InvalidMealOperation(String.format("Meal:%s is not Locked!", meal.getName()));
    }
    meal.lock();
  }

  @Override
  public void activateMeal(long mealId) {
    var meal = getMealById(mealId);
    if (meal.isActivated()) {
      log.error("Meal:{} is already Activated!", mealId);
      throw new InvalidMealOperation(
          String.format("Meal:%s is already Activated!", meal.getName()));
    }
    meal.deactivate();
  }

  @Override
  public void deactivateMeal(long mealId) {
    var meal = getMealById(mealId);
    if (!meal.isActivated()) {
      log.error("Meal:{} is already Deactivated!", mealId);
      throw new InvalidMealOperation(
          String.format("Meal:%s is already Deactivated!", meal.getName()));
    }
    meal.deactivate();
  }
}
