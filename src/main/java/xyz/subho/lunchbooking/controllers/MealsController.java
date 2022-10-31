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

package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.security.auth.Subject;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import xyz.subho.lunchbooking.entities.Roles;
import xyz.subho.lunchbooking.models.AvailableOptionsResponseModel;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.security.LunchBookingPrincipal;
import xyz.subho.lunchbooking.services.MealsService;

@Slf4j
@RestController
public class MealsController {

  @Autowired private MealsService mealsService;

  @PostMapping(EndpointPropertyKey.MEAL_CREATE)
  @ResponseStatus(HttpStatus.CREATED)
  @Secured({Roles.ROLE_ADMINISTRATOR, Roles.ROLE_MANAGER})
  public MealsModel addMeal(@RequestBody @Valid MealsModel mealsModel) {
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

  @PutMapping(EndpointPropertyKey.MEAL_READY)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel readyMeal(@PathVariable("mealId") long mealId) {
    return mealsService.makeMealReady(mealId);
  }

  @DeleteMapping(EndpointPropertyKey.MEAL_READY)
  @Secured({Roles.ROLE_MANAGER, Roles.ROLE_ADMINISTRATOR})
  public MealsModel unReadyMeal(@PathVariable("mealId") long mealId) {
    return mealsService.makeMealUnready(mealId);
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

  @GetMapping(EndpointPropertyKey.MEAL_AVAILABLE)
  public List<AvailableOptionsResponseModel> getAllAvailableMeals() {
    return mealsService.getAllAvailableOptionsForToday();
  }
}
