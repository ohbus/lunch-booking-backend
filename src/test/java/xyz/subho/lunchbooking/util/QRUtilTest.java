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

package xyz.subho.lunchbooking.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class QRUtilTest {
  /** Method under test: {@link QRUtil#getQRCodeImage(String, int, int)} */
  @Test
  void testGetQRCodeImage() {
    assertEquals(236, QRUtil.getQRCodeImage("Text", 1, 1).length);
    assertEquals(235, QRUtil.getQRCodeImage("foo", 0, 0).length);
    assertEquals(232, QRUtil.getQRCodeImage("UTF-8", 1, 1).length);
    assertEquals(426, QRUtil.getQRCodeImage("com.google.zxing.EncodeHintType", 1, 1).length);
    assertEquals(237, QRUtil.getQRCodeImage("42", 1, 1).length);
    assertEquals(510, QRUtil.getQRCodeImage("xyz.subho.lunchbooking.util.QRUtil", 1, 1).length);
  }

  /** Method under test: {@link QRUtil#getQRCodeImage(String, int, int)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetQRCodeImage2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Found empty contents
    //       at com.google.zxing.qrcode.QRCodeWriter.encode(QRCodeWriter.java:55)
    //       at xyz.subho.lunchbooking.util.QRUtil.getQRCodeImage(QRUtil.java:56)

    QRUtil.getQRCodeImage("", 1, 1);
  }

  /** Method under test: {@link QRUtil#getQRCodeImage(String, int, int)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetQRCodeImage3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Requested dimensions are too small: -16777214x1
    //       at com.google.zxing.qrcode.QRCodeWriter.encode(QRCodeWriter.java:63)
    //       at xyz.subho.lunchbooking.util.QRUtil.getQRCodeImage(QRUtil.java:56)

    QRUtil.getQRCodeImage("Text", -16777214, 1);
  }

  /** Method under test: {@link QRUtil#getQRCodeImage(String, int, int)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetQRCodeImage4() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Requested dimensions are too small: 1x-16777214
    //       at com.google.zxing.qrcode.QRCodeWriter.encode(QRCodeWriter.java:63)
    //       at xyz.subho.lunchbooking.util.QRUtil.getQRCodeImage(QRUtil.java:56)

    QRUtil.getQRCodeImage("Text", 1, -16777214);
  }
}
