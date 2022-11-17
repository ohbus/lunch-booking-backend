/*
 * Lunch Booking - Lunch Booking REST Application
 * Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class UserLoginResponseModelTest {
  /** Method under test: {@link UserLoginResponseModel#canEqual(Object)} */
  @Test
  void testCanEqual() {
    assertFalse((new UserLoginResponseModel()).canEqual("Other"));
  }

  /** Method under test: {@link UserLoginResponseModel#canEqual(Object)} */
  @Test
  void testCanEqual2() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    assertTrue(userLoginResponseModel.canEqual(new UserLoginResponseModel()));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginResponseModel#UserLoginResponseModel()}
   *   <li>{@link UserLoginResponseModel#setJwtToken(String)}
   *   <li>{@link UserLoginResponseModel#setUser(UserResponseModel)}
   *   <li>{@link UserLoginResponseModel#toString()}
   *   <li>{@link UserLoginResponseModel#getJwtToken()}
   *   <li>{@link UserLoginResponseModel#getUser()}
   * </ul>
   */
  @Test
  void testConstructor() {
    UserLoginResponseModel actualUserLoginResponseModel = new UserLoginResponseModel();
    actualUserLoginResponseModel.setJwtToken("ABC123");
    UserResponseModel userResponseModel = new UserResponseModel();
    actualUserLoginResponseModel.setUser(userResponseModel);
    String actualToStringResult = actualUserLoginResponseModel.toString();
    assertEquals("ABC123", actualUserLoginResponseModel.getJwtToken());
    assertSame(userResponseModel, actualUserLoginResponseModel.getUser());
    assertEquals(
        "UserLoginResponseModel(jwtToken=ABC123, user=UserResponseModel(id=null, firstName=null, lastName=null,"
            + " emailId=null, mobile=null, lastLogin=null))",
        actualToStringResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginResponseModel#UserLoginResponseModel(String, UserResponseModel)}
   *   <li>{@link UserLoginResponseModel#setJwtToken(String)}
   *   <li>{@link UserLoginResponseModel#setUser(UserResponseModel)}
   *   <li>{@link UserLoginResponseModel#toString()}
   *   <li>{@link UserLoginResponseModel#getJwtToken()}
   *   <li>{@link UserLoginResponseModel#getUser()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    UserResponseModel userResponseModel = new UserResponseModel();
    UserLoginResponseModel actualUserLoginResponseModel =
        new UserLoginResponseModel("ABC123", userResponseModel);
    actualUserLoginResponseModel.setJwtToken("ABC123");
    UserResponseModel userResponseModel1 = new UserResponseModel();
    actualUserLoginResponseModel.setUser(userResponseModel1);
    String actualToStringResult = actualUserLoginResponseModel.toString();
    assertEquals("ABC123", actualUserLoginResponseModel.getJwtToken());
    UserResponseModel user = actualUserLoginResponseModel.getUser();
    assertSame(userResponseModel1, user);
    assertEquals(userResponseModel, user);
    assertEquals(
        "UserLoginResponseModel(jwtToken=ABC123, user=UserResponseModel(id=null, firstName=null, lastName=null,"
            + " emailId=null, mobile=null, lastLogin=null))",
        actualToStringResult);
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new UserLoginResponseModel(), null);
    assertNotEquals(new UserLoginResponseModel(), "Different type to UserLoginResponseModel");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginResponseModel#equals(Object)}
   *   <li>{@link UserLoginResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals2() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    assertEquals(userLoginResponseModel, userLoginResponseModel);
    int expectedHashCodeResult = userLoginResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginResponseModel.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginResponseModel#equals(Object)}
   *   <li>{@link UserLoginResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    UserLoginResponseModel userLoginResponseModel1 = new UserLoginResponseModel();
    assertEquals(userLoginResponseModel, userLoginResponseModel1);
    int expectedHashCodeResult = userLoginResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginResponseModel1.hashCode());
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals4() {
    UserLoginResponseModel userLoginResponseModel =
        new UserLoginResponseModel("ABC123", new UserResponseModel());
    assertNotEquals(userLoginResponseModel, new UserLoginResponseModel());
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals5() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    assertNotEquals(
        userLoginResponseModel, new UserLoginResponseModel("ABC123", new UserResponseModel()));
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals6() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    userLoginResponseModel.setUser(new UserResponseModel());
    assertNotEquals(userLoginResponseModel, new UserLoginResponseModel());
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals7() {
    UserLoginResponseModel userLoginResponseModel =
        new UserLoginResponseModel("ABC123", mock(UserResponseModel.class));
    assertNotEquals(userLoginResponseModel, new UserLoginResponseModel());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLoginResponseModel#equals(Object)}
   *   <li>{@link UserLoginResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals8() {
    UserLoginResponseModel userLoginResponseModel =
        new UserLoginResponseModel("ABC123", new UserResponseModel());
    UserLoginResponseModel userLoginResponseModel1 =
        new UserLoginResponseModel("ABC123", new UserResponseModel());

    assertEquals(userLoginResponseModel, userLoginResponseModel1);
    int expectedHashCodeResult = userLoginResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userLoginResponseModel1.hashCode());
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals9() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();

    UserLoginResponseModel userLoginResponseModel1 = new UserLoginResponseModel();
    userLoginResponseModel1.setUser(new UserResponseModel());
    assertNotEquals(userLoginResponseModel, userLoginResponseModel1);
  }

  /** Method under test: {@link UserLoginResponseModel#equals(Object)} */
  @Test
  void testEquals10() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    userLoginResponseModel.setUser(mock(UserResponseModel.class));
    assertNotEquals(userLoginResponseModel, new UserLoginResponseModel());
  }

  /** Method under test: {@link UserLoginResponseModel#withJwtToken(String)} */
  @Test
  void testWithJwtToken() {
    UserLoginResponseModel actualWithJwtTokenResult =
        (new UserLoginResponseModel()).withJwtToken("ABC123");
    assertEquals("ABC123", actualWithJwtTokenResult.getJwtToken());
    assertNull(actualWithJwtTokenResult.getUser());
  }

  /** Method under test: {@link UserLoginResponseModel#withJwtToken(String)} */
  @Test
  void testWithJwtToken2() {
    UserLoginResponseModel userLoginResponseModel =
        new UserLoginResponseModel("ABC123", new UserResponseModel());
    assertSame(userLoginResponseModel, userLoginResponseModel.withJwtToken("ABC123"));
  }

  /** Method under test: {@link UserLoginResponseModel#withUser(UserResponseModel)} */
  @Test
  void testWithUser() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    UserLoginResponseModel actualWithUserResult =
        userLoginResponseModel.withUser(new UserResponseModel());
    assertNull(actualWithUserResult.getJwtToken());
    UserResponseModel expectedUser = actualWithUserResult.user;
    assertSame(expectedUser, actualWithUserResult.getUser());
  }

  /** Method under test: {@link UserLoginResponseModel#withUser(UserResponseModel)} */
  @Test
  void testWithUser2() {
    UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();
    assertSame(userLoginResponseModel, userLoginResponseModel.withUser(null));
  }
}
