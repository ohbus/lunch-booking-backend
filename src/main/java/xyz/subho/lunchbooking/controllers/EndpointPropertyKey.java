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
