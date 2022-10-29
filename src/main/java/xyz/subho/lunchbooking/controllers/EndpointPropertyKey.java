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

public final class EndpointPropertyKey {

  private EndpointPropertyKey() {
    throw new IllegalStateException("Utility Class");
  }

  public static final String LOGIN_USER_REGISTRATION = "/login/register";
  public static final String LOGIN_USER = "/login";

  public static final String MEAL_CREATE = "/meal/create";
  public static final String MEAL_ACTIVATE = "/meal/activate/{mealId}";
  public static final String MEAL_LOCK = "/meal/lock/{mealId}";
  public static final String MEAL_READY = "/meal/ready/{mealId}";
  public static final String MEAL_FOR_USERS = "/meal";
  public static final String MEALS = "/meal/{today}";
  public static final String MEAL_AVAILABLE = "/meal/available";

  public static final String BOOKING_FOR_TODAY = "/booking";
  public static final String BOOKING_PICK_UP = "/booking/pickup/{mealOptionId}";
  public static final String BOOKING_CREATE = "/booking/create/{mealOptionId}";
  public static final String BOOKING_AVAIL = "/booking/avail/{bookingId}";
  public static final String BOOKING_ID = "/booking/{bookingId}";
  public static final String BOOKING_FETCH_FOR_USER = "/booking/fetch";
  public static final String BOOKINGS_BY_DATE = "/booking/{date}";
}
