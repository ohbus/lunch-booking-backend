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

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.subho.lunchbooking.models.MealAvailableCountModel;
import xyz.subho.lunchbooking.models.MealsModel;
import xyz.subho.lunchbooking.services.MealsService;

@ContextConfiguration(classes = {MealsController.class})
@ExtendWith(SpringExtension.class)
class MealsControllerTest {
  @Autowired private MealsController mealsController;

  @MockBean private MealsService mealsService;

  /** Method under test: {@link MealsController#activateMeal(long)} */
  @Test
  void testActivateMeal() throws Exception {
    when(mealsService.activateMeal(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/meal/activate/{mealId}", 123L);
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(mealsController).build().perform(requestBuilder);
    actualPerformResult
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }

  /** Method under test: {@link MealsController#activateMeal(long)} */
  @Test
  void testActivateMeal2() throws Exception {
    when(mealsService.activateMeal(anyLong())).thenReturn(new MealsModel());
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder =
        SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(mealsController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /** Method under test: {@link MealsController#lockMeal(long)} */
  @Test
  void testLockMeal() throws Exception {
    when(mealsService.lockMeal(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/meal/lock/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }

  /** Method under test: {@link MealsController#getAllMeals(Boolean)} */
  @Test
  void testGetAllMeals() throws Exception {
    when(mealsService.getAllMeals(anyBoolean())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/meal/{today}", true);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link MealsController#addMeal(MealsModel)} */
  @Test
  void testAddMeal() throws Exception {
    MealsModel mealsModel = new MealsModel();
    mealsModel.setActivatedAt(1L);
    mealsModel.setDate(null);
    mealsModel.setId(123L);
    mealsModel.setLockedAt(1L);
    mealsModel.setMealOptions(new HashSet<>());
    mealsModel.setName("Name");
    mealsModel.setReadyAt(1L);
    String content = (new ObjectMapper()).writeValueAsString(mealsModel);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/meal/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(mealsController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link MealsController#deActivateMeal(long)} */
  @Test
  void testDeActivateMeal() throws Exception {
    when(mealsService.deactivateMeal(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/meal/activate/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }

  /** Method under test: {@link MealsController#getAllAvailableMeals()} */
  @Test
  void testGetAllAvailableMeals() throws Exception {
    when(mealsService.getAllAvailableOptionsForToday()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/meal/available");
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link MealsController#getAllAvailableMeals()} */
  @Test
  void testGetAllAvailableMeals2() throws Exception {
    when(mealsService.getAllAvailableOptionsForToday()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/meal/available");
    getResult.characterEncoding("Encoding");
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(getResult)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  /** Method under test: {@link MealsController#getAllAvailableMeals(long)} */
  @Test
  void testGetAllAvailableMeals3() throws Exception {
    when(mealsService.getMealCountByMealId(anyLong()))
        .thenReturn(new MealAvailableCountModel(new HashMap<>()));
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/meal/count/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"mealOptionCountWithId\":{}}"));
  }

  /** Method under test: {@link MealsController#prepareMealListForUser(Principal)} */
  @Test
  void testPrepareMealListForUser() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder =
        SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(mealsController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /** Method under test: {@link MealsController#readyMeal(long)} */
  @Test
  void testReadyMeal() throws Exception {
    when(mealsService.makeMealReady(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/meal/ready/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }

  /** Method under test: {@link MealsController#unLockMeal(long)} */
  @Test
  void testUnLockMeal() throws Exception {
    when(mealsService.unlockMeal(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/meal/lock/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }

  /** Method under test: {@link MealsController#unReadyMeal(long)} */
  @Test
  void testUnReadyMeal() throws Exception {
    when(mealsService.makeMealUnready(anyLong())).thenReturn(new MealsModel());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/meal/ready/{mealId}", 123L);
    MockMvcBuilders.standaloneSetup(mealsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":null,\"name\":null,\"date\":null,\"activatedAt\":null,\"lockedAt\":null,\"readyAt\":null,\"mealOptions"
                        + "\":[]}"));
  }
}
