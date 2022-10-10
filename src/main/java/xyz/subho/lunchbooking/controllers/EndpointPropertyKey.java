package xyz.subho.lunchbooking.controllers;

public final class EndpointPropertyKey {

  private EndpointPropertyKey() {
    throw new IllegalStateException("Utility Class");
  }

  public static final String LOGIN_USER_REGISTRATION = "/login/register";
  public static final String LOGIN_USER = "/login";
}
