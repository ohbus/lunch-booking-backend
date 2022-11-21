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

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.services.BookingService;

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
class BookingControllerTest {
  @Autowired private BookingController bookingController;

  @MockBean private BookingService bookingService;

  /** Method under test: {@link BookingController#availBooking(long)} */
  @Test
  void testAvailBooking() throws Exception {
    when(bookingService.availBooking(anyLong()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/booking/avail/{bookingId}", 123L);
    MockMvcBuilders.standaloneSetup(bookingController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"date\":[1970,1,2],\"mealOption\":\"Meal Option\",\"mealOptionId"
                        + "\":123,\"availedAt\":1}"));
  }

  /** Method under test: {@link BookingController#availBooking(long)} */
  @Test
  void testAvailBooking2() throws Exception {
    when(bookingService.availBooking(anyLong()))
        .thenReturn(
            new BookingResponseModel(
                123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L));
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder =
        SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  /** Method under test: {@link BookingController#cancelBooking(long, Principal)} */
  @Test
  void testCancelBooking() throws Exception {
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/booking/{bookingId}", "Uri Variables", "Uri Variables");
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link BookingController#createBooking(long, Principal)} */
  @Test
  void testCreateBooking() throws Exception {
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(
            "/booking/create/{mealOptionId}", "Uri Variables", "Uri Variables");
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link BookingController#createBookingFromAvailableOptions(long, Principal)}
   */
  @Test
  void testCreateBookingFromAvailableOptions() throws Exception {
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(
            "/booking/pickup/{mealOptionId}", "Uri Variables", "Uri Variables");
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link BookingController#getAllBookingsForDate(LocalDate)} */
  @Test
  void testGetAllBookingsForDate() throws Exception {
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/booking/{date}", LocalDate.ofEpochDay(1L));
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /** Method under test: {@link BookingController#getTodayBookingForUser(Principal)} */
  @Test
  void testGetTodayBookingForUser() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder =
        SecurityMockMvcRequestBuilders.formLogin();
    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(bookingController).build().perform(requestBuilder);
    actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
