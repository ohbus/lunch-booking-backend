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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.junit.jupiter.api.Test;

class EmailTest {
  /** Method under test: {@link Email#Email(String, String, String)} */
  @Test
  void testConstructor() {
    Email actualEmail =
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost");

    assertTrue(actualEmail.getAttachments().isEmpty());
    assertFalse(actualEmail.isHtml());
    assertEquals("Hello from the Dreaming Spires", actualEmail.getSubject());
    assertEquals(1, actualEmail.getRecipients().size());
    assertTrue(actualEmail.getCcList().isEmpty());
    assertEquals("Not all who wander are lost", actualEmail.getBody());
    assertTrue(actualEmail.getBccList().isEmpty());
  }

  /** Method under test: {@link Email#Email(String, String, String, String, DataSource)} */
  @Test
  void testConstructor2() {
    Email actualEmail =
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost",
            "foo.txt",
            new FileDataSource("Name"));

    assertEquals(1, actualEmail.getAttachments().size());
    assertFalse(actualEmail.isHtml());
    assertTrue(actualEmail.getBccList().isEmpty());
    assertEquals("Hello from the Dreaming Spires", actualEmail.getSubject());
    assertEquals(1, actualEmail.getRecipients().size());
    assertTrue(actualEmail.getCcList().isEmpty());
    assertEquals("Not all who wander are lost", actualEmail.getBody());
  }

  /** Method under test: {@link Email#addRecipient(String)} */
  @Test
  void testAddRecipient() {
    Email email = new Email();
    assertSame(email, email.addRecipient("Recipient"));
  }

  /** Method under test: {@link Email#addCc(String)} */
  @Test
  void testAddCc() {
    Email email = new Email();
    assertSame(email, email.addCc("Recipient"));
  }

  /** Method under test: {@link Email#addBcc(String)} */
  @Test
  void testAddBcc() {
    Email email = new Email();
    assertSame(email, email.addBcc("Recipient"));
  }

  /** Method under test: {@link Email#addAttachment(String, DataSource)} */
  @Test
  void testAddAttachment() {
    Email email = new Email();
    assertSame(email, email.addAttachment("foo.txt", new FileDataSource("Name")));
  }
}
