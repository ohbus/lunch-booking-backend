package xyz.subho.lunchbooking.services;

import xyz.subho.lunchbooking.models.OtpRequestModel;
import xyz.subho.lunchbooking.models.OtpResponseModel;

public interface OtpService {

  public OtpResponseModel createOtp(long userId);

  public OtpResponseModel resendOtp(long salt);

  public boolean validateOtp(OtpRequestModel requestModel);
}
