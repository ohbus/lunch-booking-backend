package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OtpModelTest {
  /** Method under test: {@link OtpModel#OtpModel(long)} */
  @Test
  void testConstructor() {
    assertEquals(1L, (new OtpModel(1L)).salt());
  }
}
