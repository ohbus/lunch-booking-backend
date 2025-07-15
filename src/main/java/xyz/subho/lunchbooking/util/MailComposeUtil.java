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

import jakarta.mail.util.ByteArrayDataSource;
import xyz.subho.lunchbooking.entities.Meals;
import xyz.subho.lunchbooking.models.BookingResponseModel;
import xyz.subho.lunchbooking.models.Email;

public class MailComposeUtil {

  private MailComposeUtil() {
    throw new IllegalStateException("Utility Class");
  }

  public static Email prepareReadyEmails(
      String email, BookingResponseModel bookingModel, Meals meal) {
    String bookingId = String.valueOf(bookingModel.id());
    return new Email(
        email,
        String.format("Lunch is Ready for Today %s", meal.getDate()),
        String.format(
            "Hey %s %s,%n%nPFA Coupon for your today's meal %s with Option %s.%n%nCheers!!",
            bookingModel.firstName(),
            bookingModel.lastName(),
            meal.getName(),
            bookingModel.mealOption()),
        bookingId + ".png",
        new ByteArrayDataSource(
            QRUtil.getQRCodeImage(bookingId, 500, 500), "application/octet-stream"));
  }
}
