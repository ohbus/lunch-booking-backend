package xyz.subho.lunchbooking.models;

import java.io.Serializable;

public record OtpResponseModel(long salt) implements Serializable {}
