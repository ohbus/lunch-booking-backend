package xyz.subho.lunchbooking.controllers;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
}
