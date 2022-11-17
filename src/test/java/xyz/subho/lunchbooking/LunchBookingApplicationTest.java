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
