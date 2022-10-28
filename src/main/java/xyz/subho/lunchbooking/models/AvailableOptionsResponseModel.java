package xyz.subho.lunchbooking.models;

import java.io.Serializable;
import java.time.LocalDate;

public record AvailableOptionsResponseModel(
    long id, LocalDate date, long mealOptionId, String mealOptionName, int count)
    implements Serializable {}
