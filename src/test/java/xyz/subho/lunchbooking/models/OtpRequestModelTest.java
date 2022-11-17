package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OtpRequestModelTest {
  /** Method under test: {@link OtpRequestModel#OtpRequestModel(long, Integer)} */
  @Test
  void testConstructor() {
    OtpRequestModel actualOtpRequestModel = new OtpRequestModel(1L, 1);

    assertEquals(1, actualOtpRequestModel.otp().intValue());
    assertEquals(1L, actualOtpRequestModel.salt());
  }

  /** Method under test: {@link OtpRequestModel#otp()} */
  @Test
  void testOtp() {
    assertEquals(1, (new OtpRequestModel(1L, 1)).otp().intValue());
  }

  /** Method under test: {@link OtpRequestModel#salt()} */
  @Test
  void testSalt() {
    assertEquals(1L, (new OtpRequestModel(1L, 1)).salt());
  }
}
