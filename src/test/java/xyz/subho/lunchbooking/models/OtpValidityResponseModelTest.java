package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OtpValidityResponseModelTest {
  /** Method under test: {@link OtpValidityResponseModel#OtpValidityResponseModel(boolean)} */
  @Test
  void testConstructor() {
    assertTrue((new OtpValidityResponseModel(true)).valid());
  }

  /** Method under test: {@link OtpValidityResponseModel#valid()} */
  @Test
  void testValid() {
    assertFalse((new OtpValidityResponseModel(false)).valid());
  }
}
