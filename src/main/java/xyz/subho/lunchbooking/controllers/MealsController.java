package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.services.MealsService;

@Slf4j
@RestController
public class MealsController {

  @Autowired private MealsService mealsService;

  @PostMapping(EndpointPropertyKey.MEAL_CREATE)
  @ResponseStatus(HttpStatus.CREATED)
  public MealsModel addMeal(@RequestBody @Valid MealsModel mealsModel, Principal principal) {
    return mealsService.createMeal(mealsModel);
  }

  @PutMapping(EndpointPropertyKey.MEAL_ACTIVATE)
  @ResponseStatus(HttpStatus.CREATED)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel activateMeal(@PathVariable("mealId") long mealId) {
    return mealsService.activateMeal(mealId);
  }

  @DeleteMapping(EndpointPropertyKey.MEAL_ACTIVATE)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel deActivateMeal(@PathVariable("mealId") long mealId) {
    return mealsService.deactivateMeal(mealId);
  }

  @PutMapping(EndpointPropertyKey.MEAL_LOCK)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel lockMeal(@PathVariable("mealId") long mealId) {
    return mealsService.lockMeal(mealId);
  }

  @DeleteMapping(EndpointPropertyKey.MEAL_LOCK)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel unLockMeal(@PathVariable("mealId") long mealId) {
    return mealsService.unlockMeal(mealId);
  }

  @GetMapping(EndpointPropertyKey.MEAL_FOR_USERS)
  public List<MealsModel> prepareMealListForUser(Principal principal) {
    return mealsService.getMealsAvailableForBookingWithAlreadyMarkedBookings(
        Long.parseLong(principal.getName()));
  }

  @GetMapping(EndpointPropertyKey.MEALS)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR, Roles.ROLE_CATERER})
  public List<MealsModel> getAllMeals(@PathVariable("today") Boolean today) {
    return mealsService.getAllMeals(today);
  }
}
