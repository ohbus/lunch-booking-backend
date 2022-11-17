package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserChangePasswordRequestModelTest {
  /**
   * Method under test: {@link UserChangePasswordRequestModel#UserChangePasswordRequestModel(String,
   * String)}
   */
  @Test
  void testConstructor() {
    UserChangePasswordRequestModel actualUserChangePasswordRequestModel =
        new UserChangePasswordRequestModel("iloveyou", "2020-03-01");

    assertEquals("iloveyou", actualUserChangePasswordRequestModel.currentPassword());
    assertEquals("2020-03-01", actualUserChangePasswordRequestModel.updatedPassword());
  }

  /** Method under test: {@link UserChangePasswordRequestModel#currentPassword()} */
  @Test
  void testCurrentPassword() {
    assertEquals(
        "iloveyou",
        (new UserChangePasswordRequestModel("iloveyou", "2020-03-01")).currentPassword());
  }

  /** Method under test: {@link UserChangePasswordRequestModel#updatedPassword()} */
  @Test
  void testUpdatedPassword() {
    assertEquals(
        "2020-03-01",
        (new UserChangePasswordRequestModel("iloveyou", "2020-03-01")).updatedPassword());
  }
}
