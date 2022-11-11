package xyz.subho.lunchbooking.util;

import javax.mail.util.ByteArrayDataSource;
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
