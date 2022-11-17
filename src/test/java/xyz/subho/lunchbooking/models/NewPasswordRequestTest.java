package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NewPasswordRequestTest {
  /** Method under test: {@link NewPasswordRequest#NewPasswordRequest(String)} */
  @Test
  void testConstructor() {
    assertEquals("iloveyou", (new NewPasswordRequest("iloveyou")).newPassword());
  }
}
