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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.models.Email;

class MailComposeUtilTest {
  /**
   * Method under test: {@link MailComposeUtil#prepareReadyEmails(String, BookingResponseModel,
   * Meals)}
   */
  @Test
  void testPrepareReadyEmails() {
    BookingResponseModel bookingModel =
        new BookingResponseModel(
            123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L);

    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);
    Email actualPrepareReadyEmailsResult =
        MailComposeUtil.prepareReadyEmails("jane.doe@example.org", bookingModel, meals);
    assertEquals(1, actualPrepareReadyEmailsResult.getAttachments().size());
    assertFalse(actualPrepareReadyEmailsResult.isHtml());
    assertTrue(actualPrepareReadyEmailsResult.getBccList().isEmpty());
    assertEquals(
        "Lunch is Ready for Today 1970-01-02", actualPrepareReadyEmailsResult.getSubject());
    assertEquals(1, actualPrepareReadyEmailsResult.getRecipients().size());
    assertTrue(actualPrepareReadyEmailsResult.getCcList().isEmpty());
    assertEquals(
        "Hey Jane Doe,\n\nPFA Coupon for your today's meal Name with Option Meal Option.\n\nCheers!!",
        actualPrepareReadyEmailsResult.getBody());
  }

  /**
   * Method under test: {@link MailComposeUtil#prepareReadyEmails(String, BookingResponseModel,
   * Meals)}
   */
  @Test
  void testPrepareReadyEmails2() {
    BookingResponseModel bookingModel =
        new BookingResponseModel(
            123L, "Jane", "Doe", LocalDate.ofEpochDay(1L), "Meal Option", 123L, 1L);

    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);
    Email actualPrepareReadyEmailsResult =
        MailComposeUtil.prepareReadyEmails("foo", bookingModel, meals);
    assertEquals(1, actualPrepareReadyEmailsResult.getAttachments().size());
    assertFalse(actualPrepareReadyEmailsResult.isHtml());
    assertTrue(actualPrepareReadyEmailsResult.getBccList().isEmpty());
    assertEquals(
        "Lunch is Ready for Today 1970-01-02", actualPrepareReadyEmailsResult.getSubject());
    assertEquals(1, actualPrepareReadyEmailsResult.getRecipients().size());
    assertTrue(actualPrepareReadyEmailsResult.getCcList().isEmpty());
    assertEquals(
        "Hey Jane Doe,\n\nPFA Coupon for your today's meal Name with Option Meal Option.\n\nCheers!!",
        actualPrepareReadyEmailsResult.getBody());
  }

  /**
   * Method under test: {@link MailComposeUtil#prepareReadyEmails(String, BookingResponseModel,
   * Meals)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testPrepareReadyEmails3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "xyz.subho.lunchbooking.models.BookingResponseModel.id()" because "bookingModel" is null
    //       at
    // xyz.subho.lunchbooking.util.MailComposeUtil.prepareReadyEmails(MailComposeUtil.java:34)

    Meals meals = new Meals();
    meals.setActivatedAt(1L);
    meals.setCreatedAt(1L);
    meals.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    meals.setDate(LocalDate.ofEpochDay(1L));
    meals.setDeletedAt(1L);
    meals.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    meals.setId(123L);
    meals.setLockedAt(1L);
    meals.setMealOptions(new HashSet<>());
    meals.setName("Name");
    meals.setReadyAt(1L);
    meals.setUpdatedAt(1L);
    meals.setUpdatedBy("2020-03-01");
    meals.setVersion(1L);
    MailComposeUtil.prepareReadyEmails("jane.doe@example.org", null, meals);
  }
}
