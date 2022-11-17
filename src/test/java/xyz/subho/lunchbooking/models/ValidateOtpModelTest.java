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
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidateOtpModelTest {
  /** Method under test: {@link ValidateOtpModel#ValidateOtpModel(boolean, long)} */
  @Test
  void testConstructor() {
    ValidateOtpModel actualValidateOtpModel = new ValidateOtpModel(true, 1L);

    assertEquals(1L, actualValidateOtpModel.salt());
    assertTrue(actualValidateOtpModel.validity());
  }

  /** Method under test: {@link ValidateOtpModel#salt()} */
  @Test
  void testSalt() {
    assertEquals(1L, (new ValidateOtpModel(true, 1L)).salt());
  }

  /** Method under test: {@link ValidateOtpModel#validity()} */
  @Test
  void testValidity() {
    assertTrue((new ValidateOtpModel(true, 1L)).validity());
    assertFalse((new ValidateOtpModel(false, 1L)).validity());
  }
}
