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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserRegistrationModelTest {
  /** Method under test: {@link UserRegistrationModel#canEqual(Object)} */
  @Test
  void testCanEqual() {
    assertFalse(
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).canEqual("Other"));
  }

  /** Method under test: {@link UserRegistrationModel#canEqual(Object)} */
  @Test
  void testCanEqual2() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertTrue(
        userRegistrationModel.canEqual(
            new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#UserRegistrationModel()}
   *   <li>{@link UserRegistrationModel#setEmailId(String)}
   *   <li>{@link UserRegistrationModel#setFirstName(String)}
   *   <li>{@link UserRegistrationModel#setLastName(String)}
   *   <li>{@link UserRegistrationModel#setMobile(String)}
   *   <li>{@link UserRegistrationModel#setPassword(String)}
   *   <li>{@link UserRegistrationModel#toString()}
   *   <li>{@link UserRegistrationModel#getEmailId()}
   *   <li>{@link UserRegistrationModel#getFirstName()}
   *   <li>{@link UserRegistrationModel#getLastName()}
   *   <li>{@link UserRegistrationModel#getMobile()}
   *   <li>{@link UserRegistrationModel#getPassword()}
   * </ul>
   */
  @Test
  void testConstructor() {
    UserRegistrationModel actualUserRegistrationModel = new UserRegistrationModel();
    actualUserRegistrationModel.setEmailId("42");
    actualUserRegistrationModel.setFirstName("Jane");
    actualUserRegistrationModel.setLastName("Doe");
    actualUserRegistrationModel.setMobile("Mobile");
    actualUserRegistrationModel.setPassword("iloveyou");
    String actualToStringResult = actualUserRegistrationModel.toString();
    assertEquals("42", actualUserRegistrationModel.getEmailId());
    assertEquals("Jane", actualUserRegistrationModel.getFirstName());
    assertEquals("Doe", actualUserRegistrationModel.getLastName());
    assertEquals("Mobile", actualUserRegistrationModel.getMobile());
    assertEquals("iloveyou", actualUserRegistrationModel.getPassword());
    assertEquals(
        "UserRegistrationModel(emailId=42, password=iloveyou, firstName=Jane, lastName=Doe, mobile=Mobile)",
        actualToStringResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#UserRegistrationModel(String, String, String, String,
   *       String)}
   *   <li>{@link UserRegistrationModel#setEmailId(String)}
   *   <li>{@link UserRegistrationModel#setFirstName(String)}
   *   <li>{@link UserRegistrationModel#setLastName(String)}
   *   <li>{@link UserRegistrationModel#setMobile(String)}
   *   <li>{@link UserRegistrationModel#setPassword(String)}
   *   <li>{@link UserRegistrationModel#toString()}
   *   <li>{@link UserRegistrationModel#getEmailId()}
   *   <li>{@link UserRegistrationModel#getFirstName()}
   *   <li>{@link UserRegistrationModel#getLastName()}
   *   <li>{@link UserRegistrationModel#getMobile()}
   *   <li>{@link UserRegistrationModel#getPassword()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    UserRegistrationModel actualUserRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    actualUserRegistrationModel.setEmailId("42");
    actualUserRegistrationModel.setFirstName("Jane");
    actualUserRegistrationModel.setLastName("Doe");
    actualUserRegistrationModel.setMobile("Mobile");
    actualUserRegistrationModel.setPassword("iloveyou");
    String actualToStringResult = actualUserRegistrationModel.toString();
    assertEquals("42", actualUserRegistrationModel.getEmailId());
    assertEquals("Jane", actualUserRegistrationModel.getFirstName());
    assertEquals("Doe", actualUserRegistrationModel.getLastName());
    assertEquals("Mobile", actualUserRegistrationModel.getMobile());
    assertEquals("iloveyou", actualUserRegistrationModel.getPassword());
    assertEquals(
        "UserRegistrationModel(emailId=42, password=iloveyou, firstName=Jane, lastName=Doe, mobile=Mobile)",
        actualToStringResult);
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"), null);
    assertNotEquals(
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"),
        "Different type to UserRegistrationModel");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals2() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertEquals(userRegistrationModel, userRegistrationModel);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    UserRegistrationModel userRegistrationModel1 =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");

    assertEquals(userRegistrationModel, userRegistrationModel1);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel1.hashCode());
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals4() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("jane.doe@example.org", "iloveyou", "Jane", "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals5() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel(null, "iloveyou", "Jane", "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals6() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "42", "Jane", "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals7() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", null, "Jane", "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals8() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "42", "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals9() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", null, "Doe", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals10() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "42", "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals11() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", null, "Mobile");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals12() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "42");
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#equals(Object)} */
  @Test
  void testEquals13() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", null);
    assertNotEquals(
        userRegistrationModel,
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile"));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals14() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel(null, "iloveyou", "Jane", "Doe", "Mobile");
    UserRegistrationModel userRegistrationModel1 =
        new UserRegistrationModel(null, "iloveyou", "Jane", "Doe", "Mobile");

    assertEquals(userRegistrationModel, userRegistrationModel1);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel1.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals15() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", null, "Jane", "Doe", "Mobile");
    UserRegistrationModel userRegistrationModel1 =
        new UserRegistrationModel("42", null, "Jane", "Doe", "Mobile");

    assertEquals(userRegistrationModel, userRegistrationModel1);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel1.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals16() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", null, "Doe", "Mobile");
    UserRegistrationModel userRegistrationModel1 =
        new UserRegistrationModel("42", "iloveyou", null, "Doe", "Mobile");

    assertEquals(userRegistrationModel, userRegistrationModel1);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel1.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserRegistrationModel#equals(Object)}
   *   <li>{@link UserRegistrationModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals17() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", null, "Mobile");
    UserRegistrationModel userRegistrationModel1 =
        new UserRegistrationModel("42", "iloveyou", "Jane", null, "Mobile");

    assertEquals(userRegistrationModel, userRegistrationModel1);
    int expectedHashCodeResult = userRegistrationModel.hashCode();
    assertEquals(expectedHashCodeResult, userRegistrationModel1.hashCode());
  }

  /** Method under test: {@link UserRegistrationModel#withEmailId(String)} */
  @Test
  void testWithEmailId() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertSame(userRegistrationModel, userRegistrationModel.withEmailId("42"));
  }

  /** Method under test: {@link UserRegistrationModel#withEmailId(String)} */
  @Test
  void testWithEmailId2() {
    UserRegistrationModel actualWithEmailIdResult =
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).withEmailId("foo");
    assertEquals("foo", actualWithEmailIdResult.getEmailId());
    assertEquals("iloveyou", actualWithEmailIdResult.getPassword());
    assertEquals("Mobile", actualWithEmailIdResult.getMobile());
    assertEquals("Doe", actualWithEmailIdResult.getLastName());
    assertEquals("Jane", actualWithEmailIdResult.getFirstName());
  }

  /** Method under test: {@link UserRegistrationModel#withFirstName(String)} */
  @Test
  void testWithFirstName() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertSame(userRegistrationModel, userRegistrationModel.withFirstName("Jane"));
  }

  /** Method under test: {@link UserRegistrationModel#withFirstName(String)} */
  @Test
  void testWithFirstName2() {
    UserRegistrationModel actualWithFirstNameResult =
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).withFirstName("foo");
    assertEquals("42", actualWithFirstNameResult.getEmailId());
    assertEquals("iloveyou", actualWithFirstNameResult.getPassword());
    assertEquals("Mobile", actualWithFirstNameResult.getMobile());
    assertEquals("Doe", actualWithFirstNameResult.getLastName());
    assertEquals("foo", actualWithFirstNameResult.getFirstName());
  }

  /** Method under test: {@link UserRegistrationModel#withLastName(String)} */
  @Test
  void testWithLastName() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertSame(userRegistrationModel, userRegistrationModel.withLastName("Doe"));
  }

  /** Method under test: {@link UserRegistrationModel#withLastName(String)} */
  @Test
  void testWithLastName2() {
    UserRegistrationModel actualWithLastNameResult =
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).withLastName("foo");
    assertEquals("42", actualWithLastNameResult.getEmailId());
    assertEquals("iloveyou", actualWithLastNameResult.getPassword());
    assertEquals("Mobile", actualWithLastNameResult.getMobile());
    assertEquals("foo", actualWithLastNameResult.getLastName());
    assertEquals("Jane", actualWithLastNameResult.getFirstName());
  }

  /** Method under test: {@link UserRegistrationModel#withMobile(String)} */
  @Test
  void testWithMobile() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertSame(userRegistrationModel, userRegistrationModel.withMobile("Mobile"));
  }

  /** Method under test: {@link UserRegistrationModel#withMobile(String)} */
  @Test
  void testWithMobile2() {
    UserRegistrationModel actualWithMobileResult =
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).withMobile("foo");
    assertEquals("42", actualWithMobileResult.getEmailId());
    assertEquals("iloveyou", actualWithMobileResult.getPassword());
    assertEquals("foo", actualWithMobileResult.getMobile());
    assertEquals("Doe", actualWithMobileResult.getLastName());
    assertEquals("Jane", actualWithMobileResult.getFirstName());
  }

  /** Method under test: {@link UserRegistrationModel#withPassword(String)} */
  @Test
  void testWithPassword() {
    UserRegistrationModel userRegistrationModel =
        new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile");
    assertSame(userRegistrationModel, userRegistrationModel.withPassword("iloveyou"));
  }

  /** Method under test: {@link UserRegistrationModel#withPassword(String)} */
  @Test
  void testWithPassword2() {
    UserRegistrationModel actualWithPasswordResult =
        (new UserRegistrationModel("42", "iloveyou", "Jane", "Doe", "Mobile")).withPassword("foo");
    assertEquals("42", actualWithPasswordResult.getEmailId());
    assertEquals("foo", actualWithPasswordResult.getPassword());
    assertEquals("Mobile", actualWithPasswordResult.getMobile());
    assertEquals("Doe", actualWithPasswordResult.getLastName());
    assertEquals("Jane", actualWithPasswordResult.getFirstName());
  }
}
