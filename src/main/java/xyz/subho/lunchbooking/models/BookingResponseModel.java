package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import java.time.LocalDate;

public record BookingResponseModel(
    long id,
    String firstName,
    String lastName,
    LocalDate date,
    String mealOption,
    Long mealOptionId,
    Long availedAt)
    implements Serializable {}
