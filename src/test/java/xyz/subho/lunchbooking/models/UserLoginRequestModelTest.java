package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserLoginRequestModelTest {
  /** Method under test: {@link UserLoginRequestModel#canEqual(Object)} */
  @Test
  void testCanEqual() {
    assertFalse((new UserLoginRequestModel("janedoe", "iloveyou")).canEqual("Other"));
  }

  /** Method under test: {@link UserLoginRequestModel#canEqual(Object)} */
  @Test
  void testCanEqual2() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "iloveyou");
    assertTrue(userLoginRequestModel.canEqual(new UserLoginRequestModel("janedoe", "iloveyou")));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#UserLoginRequestModel()}
   *   <li>{@link UserLoginRequestModel#setPassword(String)}
   *   <li>{@link UserLoginRequestModel#setUsername(String)}
   *   <li>{@link UserLoginRequestModel#toString()}
   *   <li>{@link UserLoginRequestModel#getPassword()}
   *   <li>{@link UserLoginRequestModel#getUsername()}
   * </ul>
   */
  @Test
  void testConstructor() {
    UserLoginRequestModel actualUserLoginRequestModel = new UserLoginRequestModel();
    actualUserLoginRequestModel.setPassword("iloveyou");
    actualUserLoginRequestModel.setUsername("janedoe");
    String actualToStringResult = actualUserLoginRequestModel.toString();
    assertEquals("iloveyou", actualUserLoginRequestModel.getPassword());
    assertEquals("janedoe", actualUserLoginRequestModel.getUsername());
    assertEquals(
        "UserLoginRequestModel(username=janedoe, password=iloveyou)", actualToStringResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#UserLoginRequestModel(String, String)}
   *   <li>{@link UserLoginRequestModel#setPassword(String)}
   *   <li>{@link UserLoginRequestModel#setUsername(String)}
   *   <li>{@link UserLoginRequestModel#toString()}
   *   <li>{@link UserLoginRequestModel#getPassword()}
   *   <li>{@link UserLoginRequestModel#getUsername()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    UserLoginRequestModel actualUserLoginRequestModel =
        new UserLoginRequestModel("janedoe", "iloveyou");
    actualUserLoginRequestModel.setPassword("iloveyou");
    actualUserLoginRequestModel.setUsername("janedoe");
    String actualToStringResult = actualUserLoginRequestModel.toString();
    assertEquals("iloveyou", actualUserLoginRequestModel.getPassword());
    assertEquals("janedoe", actualUserLoginRequestModel.getUsername());
    assertEquals(
        "UserLoginRequestModel(username=janedoe, password=iloveyou)", actualToStringResult);
  }

  /** Method under test: {@link UserLoginRequestModel#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new UserLoginRequestModel("janedoe", "iloveyou"), null);
    assertNotEquals(
        new UserLoginRequestModel("janedoe", "iloveyou"),
        "Different type to UserLoginRequestModel");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#equals(Object)}
   *   <li>{@link UserLoginRequestModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals2() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "iloveyou");
    assertEquals(userLoginRequestModel, userLoginRequestModel);
    int expectedHashCodeResult = userLoginRequestModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginRequestModel.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#equals(Object)}
   *   <li>{@link UserLoginRequestModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "iloveyou");
    UserLoginRequestModel userLoginRequestModel1 = new UserLoginRequestModel("janedoe", "iloveyou");

    assertEquals(userLoginRequestModel, userLoginRequestModel1);
    int expectedHashCodeResult = userLoginRequestModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginRequestModel1.hashCode());
  }

  /** Method under test: {@link UserLoginRequestModel#equals(Object)} */
  @Test
  void testEquals4() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("iloveyou", "iloveyou");
    assertNotEquals(userLoginRequestModel, new UserLoginRequestModel("janedoe", "iloveyou"));
  }

  /** Method under test: {@link UserLoginRequestModel#equals(Object)} */
  @Test
  void testEquals5() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel(null, "iloveyou");
    assertNotEquals(userLoginRequestModel, new UserLoginRequestModel("janedoe", "iloveyou"));
  }

  /** Method under test: {@link UserLoginRequestModel#equals(Object)} */
  @Test
  void testEquals6() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "janedoe");
    assertNotEquals(userLoginRequestModel, new UserLoginRequestModel("janedoe", "iloveyou"));
  }

  /** Method under test: {@link UserLoginRequestModel#equals(Object)} */
  @Test
  void testEquals7() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", null);
    assertNotEquals(userLoginRequestModel, new UserLoginRequestModel("janedoe", "iloveyou"));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#equals(Object)}
   *   <li>{@link UserLoginRequestModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals8() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel(null, "iloveyou");
    UserLoginRequestModel userLoginRequestModel1 = new UserLoginRequestModel(null, "iloveyou");

    assertEquals(userLoginRequestModel, userLoginRequestModel1);
    int expectedHashCodeResult = userLoginRequestModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginRequestModel1.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginRequestModel#equals(Object)}
   *   <li>{@link UserLoginRequestModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals9() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", null);
    UserLoginRequestModel userLoginRequestModel1 = new UserLoginRequestModel("janedoe", null);

    assertEquals(userLoginRequestModel, userLoginRequestModel1);
    int expectedHashCodeResult = userLoginRequestModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginRequestModel1.hashCode());
  }

  /** Method under test: {@link UserLoginRequestModel#withPassword(String)} */
  @Test
  void testWithPassword() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "iloveyou");
    assertSame(userLoginRequestModel, userLoginRequestModel.withPassword("iloveyou"));
  }

  /** Method under test: {@link UserLoginRequestModel#withPassword(String)} */
  @Test
  void testWithPassword2() {
    UserLoginRequestModel actualWithPasswordResult =
        (new UserLoginRequestModel("janedoe", "iloveyou")).withPassword("foo");
    assertEquals("foo", actualWithPasswordResult.getPassword());
    assertEquals("janedoe", actualWithPasswordResult.getUsername());
  }

  /** Method under test: {@link UserLoginRequestModel#withUsername(String)} */
  @Test
  void testWithUsername() {
    UserLoginRequestModel userLoginRequestModel = new UserLoginRequestModel("janedoe", "iloveyou");
    assertSame(userLoginRequestModel, userLoginRequestModel.withUsername("janedoe"));
  }

  /** Method under test: {@link UserLoginRequestModel#withUsername(String)} */
  @Test
  void testWithUsername2() {
    UserLoginRequestModel actualWithUsernameResult =
        (new UserLoginRequestModel("janedoe", "iloveyou")).withUsername("foo");
    assertEquals("iloveyou", actualWithUsernameResult.getPassword());
    assertEquals("foo", actualWithUsernameResult.getUsername());
  }
}
