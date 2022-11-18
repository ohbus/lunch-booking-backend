package xyz.subho.lunchbooking.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class OtpEntityTest {
  /** Method under test: {@link OtpEntity#OtpEntity(Long)} */
  @Test
  void testConstructor() {
    OtpEntity actualOtpEntity = new OtpEntity(123L);
    assertEquals(123L, actualOtpEntity.getUserId().longValue());
    LocalDateTime expectedIssuesAt = actualOtpEntity.issuesAt;
    assertSame(expectedIssuesAt, actualOtpEntity.getIssuesAt());
  }

  /** Method under test: {@link OtpEntity#isSent()} */
  @Test
  void testIsSent() {
    assertFalse((new OtpEntity()).isSent());
  }

  /** Method under test: {@link OtpEntity#isSent()} */
  @Test
  void testIsSent2() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertTrue(otpEntity.isSent());
  }

  /** Method under test: {@link OtpEntity#isIssued()} */
  @Test
  void testIsIssued() {
    assertTrue((new OtpEntity()).isIssued());
  }

  /** Method under test: {@link OtpEntity#isIssued()} */
  @Test
  void testIsIssued2() {
    LocalDateTime sentAt = LocalDateTime.of(4, 4, 4, 4, 1);
    LocalDateTime verifiedAt = LocalDateTime.of(4, 4, 4, 4, 1);
    LocalDateTime resentAt = LocalDateTime.of(4, 4, 4, 4, 1);
    assertFalse(
        (new OtpEntity(
                123L, 4, 123L, sentAt, null, verifiedAt, resentAt, LocalDateTime.of(4, 4, 4, 4, 1)))
            .isIssued());
  }

  /** Method under test: {@link OtpEntity#isVerified()} */
  @Test
  void testIsVerified() {
    assertFalse((new OtpEntity()).isVerified());
  }

  /** Method under test: {@link OtpEntity#isVerified()} */
  @Test
  void testIsVerified2() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertTrue(otpEntity.isVerified());
  }

  /** Method under test: {@link OtpEntity#isResent()} */
  @Test
  void testIsResent() {
    assertFalse((new OtpEntity()).isResent());
  }

  /** Method under test: {@link OtpEntity#isResent()} */
  @Test
  void testIsResent2() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertTrue(otpEntity.isResent());
  }

  /** Method under test: {@link OtpEntity#isExpired()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testIsExpired() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "java.time.chrono.ChronoLocalDateTime.toLocalDate()" because "other" is null
    //       at java.time.chrono.ChronoLocalDateTime.isAfter(ChronoLocalDateTime.java:533)
    //       at java.time.LocalDateTime.isAfter(LocalDateTime.java:1854)
    //       at xyz.subho.lunchbooking.entities.OtpEntity.isExpired(OtpEntity.java:84)

    (new OtpEntity()).isExpired();
  }

  /** Method under test: {@link OtpEntity#isExpired()} */
  @Test
  void testIsExpired2() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertTrue(otpEntity.isExpired());
  }

  /** Method under test: {@link OtpEntity#send()} */
  @Test
  void testSend() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.send();
    LocalDateTime expectedSentAt = otpEntity.sentAt;
    assertSame(expectedSentAt, otpEntity.getSentAt());
  }

  /** Method under test: {@link OtpEntity#issue()} */
  @Test
  void testIssue() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.issue();
    LocalDateTime expectedIssuesAt = otpEntity.issuesAt;
    assertSame(expectedIssuesAt, otpEntity.getIssuesAt());
  }

  /** Method under test: {@link OtpEntity#verify()} */
  @Test
  void testVerify() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.verify();
    LocalDateTime expectedVerifiedAt = otpEntity.verifiedAt;
    assertSame(expectedVerifiedAt, otpEntity.getVerifiedAt());
  }

  /** Method under test: {@link OtpEntity#resend()} */
  @Test
  void testResend() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.resend();
    LocalDateTime expectedResentAt = otpEntity.resentAt;
    assertSame(expectedResentAt, otpEntity.getResentAt());
  }

  /** Method under test: {@link OtpEntity#equals(Object)} */
  @Test
  void testEquals() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertNotEquals(otpEntity, null);
  }

  /** Method under test: {@link OtpEntity#equals(Object)} */
  @Test
  void testEquals2() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertNotEquals(otpEntity, "Different type to OtpEntity");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link OtpEntity#equals(Object)}
   *   <li>{@link OtpEntity#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertEquals(otpEntity, otpEntity);
    int expectedHashCodeResult = otpEntity.hashCode();
    assertEquals(expectedHashCodeResult, otpEntity.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link OtpEntity#equals(Object)}
   *   <li>{@link OtpEntity#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(123L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertEquals(otpEntity, otpEntity1);
    int expectedHashCodeResult = otpEntity.hashCode();
    assertEquals(expectedHashCodeResult, otpEntity1.hashCode());
  }

  /** Method under test: {@link OtpEntity#equals(Object)} */
  @Test
  void testEquals5() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(1L);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertNotEquals(otpEntity, otpEntity1);
  }

  /** Method under test: {@link OtpEntity#equals(Object)} */
  @Test
  void testEquals6() {
    OtpEntity otpEntity = new OtpEntity();
    otpEntity.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setId(null);
    otpEntity.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setOtp(1);
    otpEntity.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity.setUserId(123L);
    otpEntity.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

    OtpEntity otpEntity1 = new OtpEntity();
    otpEntity1.setExpiresAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setId(123L);
    otpEntity1.setIssuesAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setOtp(1);
    otpEntity1.setResentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setSentAt(LocalDateTime.of(1, 1, 1, 1, 1));
    otpEntity1.setUserId(123L);
    otpEntity1.setVerifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
    assertNotEquals(otpEntity, otpEntity1);
  }
}
