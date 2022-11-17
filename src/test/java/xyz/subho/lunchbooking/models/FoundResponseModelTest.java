package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FoundResponseModelTest {
  /** Method under test: {@link FoundResponseModel#FoundResponseModel(boolean)} */
  @Test
  void testConstructor() {
    assertTrue((new FoundResponseModel(true)).found());
  }

  /** Method under test: {@link FoundResponseModel#found()} */
  @Test
  void testFound() {
    assertFalse((new FoundResponseModel(false)).found());
  }
}
