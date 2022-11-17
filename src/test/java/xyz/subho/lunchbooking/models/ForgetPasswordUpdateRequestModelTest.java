package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ForgetPasswordUpdateRequestModelTest {
  /**
   * Method under test: {@link
   * ForgetPasswordUpdateRequestModel#ForgetPasswordUpdateRequestModel(String)}
   */
  @Test
  void testConstructor() {
    assertEquals("janedoe", (new ForgetPasswordUpdateRequestModel("janedoe")).username());
  }
}
