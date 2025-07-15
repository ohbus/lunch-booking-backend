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

package xyz.subho.lunchbooking.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.exceptions.InvalidConfigurationException;

@ContextConfiguration(classes = {EncryptionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EncryptionServiceImplTest {
  @Autowired private EncryptionServiceImpl encryptionServiceImpl;

  /** Method under test: {@link EncryptionServiceImpl#generateSalt(int)} */
  @Test
  void testGenerateSalt() {
    // TODO: Complete this test.

    assertNotEquals("abc", encryptionServiceImpl.generateSalt(3));
  }

  /** Method under test: {@link EncryptionServiceImpl#generateSalt(int)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGenerateSalt2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NegativeArraySizeException: -1
    //       at java.lang.AbstractStringBuilder.<init>(AbstractStringBuilder.java:88)
    //       at java.lang.StringBuilder.<init>(StringBuilder.java:119)
    //       at
    // xyz.subho.lunchbooking.services.impl.EncryptionServiceImpl.generateSalt(EncryptionServiceImpl.java:43)

    encryptionServiceImpl.generateSalt(-1);
  }

  /** Method under test: {@link EncryptionServiceImpl#hash(char[], byte[])} */
  @Test
  void testHash() throws UnsupportedEncodingException {
    char[] password = "AAAA".toCharArray();
    assertEquals(
        Integer.SIZE, encryptionServiceImpl.hash(password, "AAAAAAAA".getBytes("UTF-8")).length);
  }

  /** Method under test: {@link EncryptionServiceImpl#hash(char[], byte[])} */
  @Test
  @Disabled("TODO: Complete this test")
  void testHash2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: the salt parameter must not be empty
    //       at jakarta.crypto.spec.PBEKeySpec.<init>(PBEKeySpec.java:119)
    //       at
    // xyz.subho.lunchbooking.services.impl.EncryptionServiceImpl.hash(EncryptionServiceImpl.java:50)

    encryptionServiceImpl.hash("AAAA".toCharArray(), new byte[] {});
  }

  /** Method under test: {@link EncryptionServiceImpl#encrypt(String, String)} */
  @Test
  void testEncrypt() {
    assertEquals(
        "O7gthfXmzfbdhrvjk8oJmtvEBuU7rM9rG+TgFerdxIw=",
        encryptionServiceImpl.encrypt("iloveyou", "Salt"));
    assertThrows(
        InvalidConfigurationException.class, () -> encryptionServiceImpl.encrypt("iloveyou", ""));
  }

  /** Method under test: {@link EncryptionServiceImpl#isPasswordValid(String, String, String)} */
  @Test
  void testIsPasswordValid() {
    assertFalse(encryptionServiceImpl.isPasswordValid("iloveyou", "iloveyou", "Salt"));
    assertThrows(
        InvalidConfigurationException.class,
        () -> encryptionServiceImpl.isPasswordValid("iloveyou", "iloveyou", ""));
  }
}
