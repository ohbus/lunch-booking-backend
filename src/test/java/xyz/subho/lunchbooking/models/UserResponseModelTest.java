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

import org.junit.jupiter.api.Test;

class UserResponseModelTest {
  /** Method under test: {@link UserResponseModel#canEqual(Object)} */
  @Test
  void testCanEqual() {
    assertFalse((new UserResponseModel()).canEqual("Other"));
  }

  /** Method under test: {@link UserResponseModel#canEqual(Object)} */
  @Test
  void testCanEqual2() {
    UserResponseModel userResponseModel = new UserResponseModel();
    assertTrue(userResponseModel.canEqual(new UserResponseModel()));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserResponseModel#UserResponseModel()}
   *   <li>{@link UserResponseModel#setEmailId(String)}
   *   <li>{@link UserResponseModel#setFirstName(String)}
   *   <li>{@link UserResponseModel#setId(Long)}
   *   <li>{@link UserResponseModel#setLastLogin(Long)}
   *   <li>{@link UserResponseModel#setLastName(String)}
   *   <li>{@link UserResponseModel#setMobile(String)}
   *   <li>{@link UserResponseModel#toString()}
   *   <li>{@link UserResponseModel#getEmailId()}
   *   <li>{@link UserResponseModel#getFirstName()}
   *   <li>{@link UserResponseModel#getId()}
   *   <li>{@link UserResponseModel#getLastLogin()}
   *   <li>{@link UserResponseModel#getLastName()}
   *   <li>{@link UserResponseModel#getMobile()}
   * </ul>
   */
  @Test
  void testConstructor() {
    UserResponseModel actualUserResponseModel = new UserResponseModel();
    actualUserResponseModel.setEmailId("42");
    actualUserResponseModel.setFirstName("Jane");
    actualUserResponseModel.setId(123L);
    actualUserResponseModel.setLastLogin(1L);
    actualUserResponseModel.setLastName("Doe");
    actualUserResponseModel.setMobile("Mobile");
    String actualToStringResult = actualUserResponseModel.toString();
    assertEquals("42", actualUserResponseModel.getEmailId());
    assertEquals("Jane", actualUserResponseModel.getFirstName());
    assertEquals(123L, actualUserResponseModel.getId().longValue());
    assertEquals(1L, actualUserResponseModel.getLastLogin().longValue());
    assertEquals("Doe", actualUserResponseModel.getLastName());
    assertEquals("Mobile", actualUserResponseModel.getMobile());
    assertEquals(
        "UserResponseModel(id=123, firstName=Jane, lastName=Doe, emailId=42, mobile=Mobile, lastLogin=1)",
        actualToStringResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserResponseModel#UserResponseModel(Long, String, String, String, String, Long)}
   *   <li>{@link UserResponseModel#setEmailId(String)}
   *   <li>{@link UserResponseModel#setFirstName(String)}
   *   <li>{@link UserResponseModel#setId(Long)}
   *   <li>{@link UserResponseModel#setLastLogin(Long)}
   *   <li>{@link UserResponseModel#setLastName(String)}
   *   <li>{@link UserResponseModel#setMobile(String)}
   *   <li>{@link UserResponseModel#toString()}
   *   <li>{@link UserResponseModel#getEmailId()}
   *   <li>{@link UserResponseModel#getFirstName()}
   *   <li>{@link UserResponseModel#getId()}
   *   <li>{@link UserResponseModel#getLastLogin()}
   *   <li>{@link UserResponseModel#getLastName()}
   *   <li>{@link UserResponseModel#getMobile()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    UserResponseModel actualUserResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    actualUserResponseModel.setEmailId("42");
    actualUserResponseModel.setFirstName("Jane");
    actualUserResponseModel.setId(123L);
    actualUserResponseModel.setLastLogin(1L);
    actualUserResponseModel.setLastName("Doe");
    actualUserResponseModel.setMobile("Mobile");
    String actualToStringResult = actualUserResponseModel.toString();
    assertEquals("42", actualUserResponseModel.getEmailId());
    assertEquals("Jane", actualUserResponseModel.getFirstName());
    assertEquals(123L, actualUserResponseModel.getId().longValue());
    assertEquals(1L, actualUserResponseModel.getLastLogin().longValue());
    assertEquals("Doe", actualUserResponseModel.getLastName());
    assertEquals("Mobile", actualUserResponseModel.getMobile());
    assertEquals(
        "UserResponseModel(id=123, firstName=Jane, lastName=Doe, emailId=42, mobile=Mobile, lastLogin=1)",
        actualToStringResult);
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new UserResponseModel(), null);
    assertNotEquals(new UserResponseModel(), "Different type to UserResponseModel");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserResponseModel#equals(Object)}
   *   <li>{@link UserResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals2() {
    UserResponseModel userResponseModel = new UserResponseModel();
    assertEquals(userResponseModel, userResponseModel);
    int expectedHashCodeResult = userResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userResponseModel.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserResponseModel#equals(Object)}
   *   <li>{@link UserResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    UserResponseModel userResponseModel = new UserResponseModel();
    UserResponseModel userResponseModel1 = new UserResponseModel();
    assertEquals(userResponseModel, userResponseModel1);
    int expectedHashCodeResult = userResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userResponseModel1.hashCode());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals4() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals5() {
    UserResponseModel userResponseModel = new UserResponseModel();
    assertNotEquals(
        userResponseModel, new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L));
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals6() {
    UserResponseModel userResponseModel = new UserResponseModel();
    userResponseModel.setFirstName("Jane");
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals7() {
    UserResponseModel userResponseModel = new UserResponseModel();
    userResponseModel.setLastName("Doe");
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals8() {
    UserResponseModel userResponseModel = new UserResponseModel();
    userResponseModel.setEmailId("42");
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals9() {
    UserResponseModel userResponseModel = new UserResponseModel();
    userResponseModel.setMobile("Mobile");
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals10() {
    UserResponseModel userResponseModel = new UserResponseModel();
    userResponseModel.setLastLogin(1L);
    assertNotEquals(userResponseModel, new UserResponseModel());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserResponseModel#equals(Object)}
   *   <li>{@link UserResponseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals11() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    UserResponseModel userResponseModel1 =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);

    assertEquals(userResponseModel, userResponseModel1);
    int expectedHashCodeResult = userResponseModel.hashCode();
    assertEquals(expectedHashCodeResult, userResponseModel1.hashCode());
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals12() {
    UserResponseModel userResponseModel = new UserResponseModel();

    UserResponseModel userResponseModel1 = new UserResponseModel();
    userResponseModel1.setFirstName("Jane");
    assertNotEquals(userResponseModel, userResponseModel1);
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals13() {
    UserResponseModel userResponseModel = new UserResponseModel();

    UserResponseModel userResponseModel1 = new UserResponseModel();
    userResponseModel1.setLastName("Doe");
    assertNotEquals(userResponseModel, userResponseModel1);
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals14() {
    UserResponseModel userResponseModel = new UserResponseModel();

    UserResponseModel userResponseModel1 = new UserResponseModel();
    userResponseModel1.setEmailId("42");
    assertNotEquals(userResponseModel, userResponseModel1);
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals15() {
    UserResponseModel userResponseModel = new UserResponseModel();

    UserResponseModel userResponseModel1 = new UserResponseModel();
    userResponseModel1.setMobile("Mobile");
    assertNotEquals(userResponseModel, userResponseModel1);
  }

  /** Method under test: {@link UserResponseModel#equals(Object)} */
  @Test
  void testEquals16() {
    UserResponseModel userResponseModel = new UserResponseModel();

    UserResponseModel userResponseModel1 = new UserResponseModel();
    userResponseModel1.setLastLogin(1L);
    assertNotEquals(userResponseModel, userResponseModel1);
  }

  /** Method under test: {@link UserResponseModel#withEmailId(String)} */
  @Test
  void testWithEmailId() {
    UserResponseModel actualWithEmailIdResult = (new UserResponseModel()).withEmailId("42");
    assertEquals("42", actualWithEmailIdResult.getEmailId());
    assertNull(actualWithEmailIdResult.getMobile());
    assertNull(actualWithEmailIdResult.getLastName());
    assertNull(actualWithEmailIdResult.getLastLogin());
    assertNull(actualWithEmailIdResult.getId());
    assertNull(actualWithEmailIdResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withEmailId(String)} */
  @Test
  void testWithEmailId2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withEmailId("42"));
  }

  /** Method under test: {@link UserResponseModel#withFirstName(String)} */
  @Test
  void testWithFirstName() {
    UserResponseModel actualWithFirstNameResult = (new UserResponseModel()).withFirstName("Jane");
    assertNull(actualWithFirstNameResult.getEmailId());
    assertNull(actualWithFirstNameResult.getMobile());
    assertNull(actualWithFirstNameResult.getLastName());
    assertNull(actualWithFirstNameResult.getLastLogin());
    assertNull(actualWithFirstNameResult.getId());
    assertEquals("Jane", actualWithFirstNameResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withFirstName(String)} */
  @Test
  void testWithFirstName2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withFirstName("Jane"));
  }

  /** Method under test: {@link UserResponseModel#withId(Long)} */
  @Test
  void testWithId() {
    UserResponseModel actualWithIdResult = (new UserResponseModel()).withId(123L);
    assertNull(actualWithIdResult.getEmailId());
    assertNull(actualWithIdResult.getMobile());
    assertNull(actualWithIdResult.getLastName());
    assertNull(actualWithIdResult.getLastLogin());
    assertEquals(123L, actualWithIdResult.getId().longValue());
    assertNull(actualWithIdResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withId(Long)} */
  @Test
  void testWithId2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withId(123L));
  }

  /** Method under test: {@link UserResponseModel#withLastLogin(Long)} */
  @Test
  void testWithLastLogin() {
    UserResponseModel actualWithLastLoginResult = (new UserResponseModel()).withLastLogin(1L);
    assertNull(actualWithLastLoginResult.getEmailId());
    assertNull(actualWithLastLoginResult.getMobile());
    assertNull(actualWithLastLoginResult.getLastName());
    assertEquals(1L, actualWithLastLoginResult.getLastLogin().longValue());
    assertNull(actualWithLastLoginResult.getId());
    assertNull(actualWithLastLoginResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withLastLogin(Long)} */
  @Test
  void testWithLastLogin2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withLastLogin(1L));
  }

  /** Method under test: {@link UserResponseModel#withLastName(String)} */
  @Test
  void testWithLastName() {
    UserResponseModel actualWithLastNameResult = (new UserResponseModel()).withLastName("Doe");
    assertNull(actualWithLastNameResult.getEmailId());
    assertNull(actualWithLastNameResult.getMobile());
    assertEquals("Doe", actualWithLastNameResult.getLastName());
    assertNull(actualWithLastNameResult.getLastLogin());
    assertNull(actualWithLastNameResult.getId());
    assertNull(actualWithLastNameResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withLastName(String)} */
  @Test
  void testWithLastName2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withLastName("Doe"));
  }

  /** Method under test: {@link UserResponseModel#withMobile(String)} */
  @Test
  void testWithMobile() {
    UserResponseModel actualWithMobileResult = (new UserResponseModel()).withMobile("Mobile");
    assertNull(actualWithMobileResult.getEmailId());
    assertEquals("Mobile", actualWithMobileResult.getMobile());
    assertNull(actualWithMobileResult.getLastName());
    assertNull(actualWithMobileResult.getLastLogin());
    assertNull(actualWithMobileResult.getId());
    assertNull(actualWithMobileResult.getFirstName());
  }

  /** Method under test: {@link UserResponseModel#withMobile(String)} */
  @Test
  void testWithMobile2() {
    UserResponseModel userResponseModel =
        new UserResponseModel(123L, "Jane", "Doe", "42", "Mobile", 1L);
    assertSame(userResponseModel, userResponseModel.withMobile("Mobile"));
  }
}
