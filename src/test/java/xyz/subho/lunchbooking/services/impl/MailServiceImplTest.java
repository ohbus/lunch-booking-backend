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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.models.Email;

@ContextConfiguration(classes = {MailServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MailServiceImplTest {
  @MockBean private JavaMailSender javaMailSender;

  @Autowired private MailServiceImpl mailServiceImpl;

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail() {
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(new Email());
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail2() {
    when(javaMailSender.createMimeMessage()).thenReturn(null);
    mailServiceImpl.sendMail(new Email());
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail3() {
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

    Email email = new Email();
    email.setSubject("Hello from the Dreaming Spires");
    mailServiceImpl.sendMail(email);
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail4() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost"));
    verify(javaMailSender).createMimeMessage();
    verify(javaMailSender).send((MimeMessage) any());
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail5() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(
        new Email(
            "Mail Sent to:{}", "Hello from the Dreaming Spires", "Not all who wander are lost"));
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSendMail6() throws MailException {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.Email.getRecipients()" because "email" is null
    //       at
    // xyz.subho.lunchbooking.services.impl.MailServiceImpl.sendMail(MailServiceImpl.java:62)

    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(null);
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail7() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost",
            "foo.txt",
            new FileDataSource("Mail Sent to:{}")));
    verify(javaMailSender).createMimeMessage();
    verify(javaMailSender).send((MimeMessage) any());
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail8() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

    Email email =
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost");
    email.addCc("Mail Sent to:{}");
    mailServiceImpl.sendMail(email);
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail9() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

    Email email =
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost");
    email.addBcc("Mail Sent to:{}");
    mailServiceImpl.sendMail(email);
    verify(javaMailSender).createMimeMessage();
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail10() throws MailException {
    doThrow(new RuntimeException()).when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost"));
    verify(javaMailSender).createMimeMessage();
    verify(javaMailSender).send((MimeMessage) any());
  }

  /** Method under test: {@link MailServiceImpl#sendMail(Email)} */
  @Test
  void testSendMail11() throws MailException {
    doNothing().when(javaMailSender).send((MimeMessage) any());
    when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    mailServiceImpl.sendMail(
        new Email(
            "alice.liddell@example.org",
            "Hello from the Dreaming Spires",
            "Not all who wander are lost",
            "foo.txt",
            null));
    verify(javaMailSender).createMimeMessage();
  }
}
