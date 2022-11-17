package xyz.subho.lunchbooking.models;

import java.util.Map;

public record MealAvailableCountModel(Map<Long, MealOptionCountModel> mealOptionCountWithId) {}
