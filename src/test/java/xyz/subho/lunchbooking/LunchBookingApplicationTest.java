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

package xyz.subho.lunchbooking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LunchBookingApplicationTest {
  /** Method under test: {@link LunchBookingApplication#setTimeZone()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSetTimeZone() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke "String.length()" because "id" is null
    //       at java.util.TimeZone.parseCustomTimeZone(TimeZone.java:801)
    //       at java.util.TimeZone.getTimeZone(TimeZone.java:580)
    //       at java.util.TimeZone.getTimeZone(TimeZone.java:518)
    //       at
    // xyz.subho.lunchbooking.LunchBookingApplication.setTimeZone(LunchBookingApplication.java:39)

    (new LunchBookingApplication()).setTimeZone();
  }
}
