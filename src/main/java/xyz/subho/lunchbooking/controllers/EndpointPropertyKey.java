package xyz.subho.lunchbooking.controllers;

public final class EndpointPropertyKey {

  private EndpointPropertyKey() {
    throw new IllegalStateException("Utility Class");
  }

  public static final String LOGIN_USER_REGISTRATION = "/login/register";
  public static final String LOGIN_USER = "/login";

  public static final String MEAL_CREATE = "/meal/create";

  public static final String BOOKING_CREATE = "/booking/create/{mealOptionId}";
  public static final String BOOKING_AVAIL = "/booking/avail/{bookingId}";
  public static final String BOOKING_DELETE = "/booking/{bookingId}";
}
