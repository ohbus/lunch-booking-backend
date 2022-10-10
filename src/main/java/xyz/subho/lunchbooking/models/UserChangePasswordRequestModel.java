package xyz.subho.lunchbooking.models;

public record UserChangePasswordRequestModel(String currentPassword, String updatedPassword) {}
