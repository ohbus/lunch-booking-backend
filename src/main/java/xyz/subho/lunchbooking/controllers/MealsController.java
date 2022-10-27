package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
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
  public MealsModel addMeal(@RequestBody MealsModel mealsModel, Principal principal) {
    return mealsService.createMeal(mealsModel);
  }

  @PutMapping(EndpointPropertyKey.MEAL_ACTIVATE)
  @ResponseStatus(HttpStatus.CREATED)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public void activateMeal(@PathVariable("mealId") long mealId) {
    mealsService.activateMeal(mealId);
  }

  @DeleteMapping(EndpointPropertyKey.MEAL_ACTIVATE)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public void deActivateMeal(@PathVariable("mealId") long mealId) {
    mealsService.deactivateMeal(mealId);
  }

  @PutMapping(EndpointPropertyKey.MEAL_LOCK)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public void lockMeal(@PathVariable("mealId") long mealId) {
    mealsService.lockMeal(mealId);
  }

  @DeleteMapping(EndpointPropertyKey.MEAL_LOCK)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public void unLockMeal(@PathVariable("mealId") long mealId) {
    mealsService.unlockMeal(mealId);
  }
}
