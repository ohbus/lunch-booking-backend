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

package xyz.subho.lunchbooking.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.security.auth.Subject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LunchBookingPrincipalTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LunchBookingPrincipal#LunchBookingPrincipal()}
   *   <li>{@link LunchBookingPrincipal#toString()}
   * </ul>
   */
  @Test
  void testConstructor() {
    assertEquals(
        "LunchBookingPrincipal{id=null, username='null', authorities=null}",
        (new LunchBookingPrincipal()).toString());
  }

  /** Method under test: {@link LunchBookingPrincipal#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new LunchBookingPrincipal(), null);
    assertNotEquals(new LunchBookingPrincipal(), "Different type to LunchBookingPrincipal");
    assertThrows(
        NullPointerException.class,
        () -> (new LunchBookingPrincipal()).equals(new LunchBookingPrincipal()));
  }

  /** Method under test: {@link LunchBookingPrincipal#equals(Object)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testEquals2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because
    // "this.id" is null
    //       at
    // xyz.subho.lunchbooking.security.LunchBookingPrincipal.equals(LunchBookingPrincipal.java:43)

    LunchBookingPrincipal lunchBookingPrincipal = new LunchBookingPrincipal();
    assertThrows(
        NullPointerException.class,
        () -> lunchBookingPrincipal.equals(new LunchBookingPrincipal()));
  }

  /** Method under test: {@link LunchBookingPrincipal#getName()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetName() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.toString()" because "this.id"
    // is null
    //       at
    // xyz.subho.lunchbooking.security.LunchBookingPrincipal.getName(LunchBookingPrincipal.java:68)

    (new LunchBookingPrincipal()).getName();
  }

  /** Method under test: {@link LunchBookingPrincipal#getName()} */
  @Test
  void testGetName2() {
    LunchBookingPrincipal lunchBookingPrincipal = new LunchBookingPrincipal();
    lunchBookingPrincipal.setId(123L);
    assertEquals("123", lunchBookingPrincipal.getName());
  }

  /** Method under test: {@link LunchBookingPrincipal#implies(Subject)} */
  @Test
  void testImplies() {
    LunchBookingPrincipal lunchBookingPrincipal = new LunchBookingPrincipal();
    assertFalse(lunchBookingPrincipal.implies(new Subject()));
  }

  /** Method under test: {@link LunchBookingPrincipal#equals(Object)} */
  @Test
  void testEquals3() {
    LunchBookingPrincipal lunchBookingPrincipal = new LunchBookingPrincipal();
    lunchBookingPrincipal.setId(123L);
    assertNotEquals(lunchBookingPrincipal, new LunchBookingPrincipal());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link LunchBookingPrincipal#equals(Object)}
   *   <li>{@link LunchBookingPrincipal#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    LunchBookingPrincipal lunchBookingPrincipal = new LunchBookingPrincipal();
    lunchBookingPrincipal.setId(123L);

    LunchBookingPrincipal lunchBookingPrincipal1 = new LunchBookingPrincipal();
    lunchBookingPrincipal1.setId(123L);
    assertEquals(lunchBookingPrincipal, lunchBookingPrincipal1);
    int expectedHashCodeResult = lunchBookingPrincipal.hashCode();
    assertEquals(expectedHashCodeResult, lunchBookingPrincipal1.hashCode());
  }
}
