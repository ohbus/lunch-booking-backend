package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidateOtpModelTest {
  /** Method under test: {@link ValidateOtpModel#ValidateOtpModel(boolean, long)} */
  @Test
  void testConstructor() {
    ValidateOtpModel actualValidateOtpModel = new ValidateOtpModel(true, 1L);

    assertEquals(1L, actualValidateOtpModel.salt());
    assertTrue(actualValidateOtpModel.validity());
  }

  /** Method under test: {@link ValidateOtpModel#salt()} */
  @Test
  void testSalt() {
    assertEquals(1L, (new ValidateOtpModel(true, 1L)).salt());
  }

  /** Method under test: {@link ValidateOtpModel#validity()} */
  @Test
  void testValidity() {
    assertTrue((new ValidateOtpModel(true, 1L)).validity());
    assertFalse((new ValidateOtpModel(false, 1L)).validity());
  }
}
