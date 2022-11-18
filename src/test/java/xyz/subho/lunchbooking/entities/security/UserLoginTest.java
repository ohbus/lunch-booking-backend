package xyz.subho.lunchbooking.entities.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserLoginTest {
  /** Method under test: {@link UserLogin#expireAccount()} */
  @Test
  void testExpireAccount() {
    UserLogin userLogin = new UserLogin();
    userLogin.expireAccount();
    assertFalse(userLogin.isAccountNonExpired());
  }

  /** Method under test: {@link UserLogin#lock()} */
  @Test
  void testLock() {
    UserLogin userLogin = new UserLogin();
    userLogin.lock();
    assertFalse(userLogin.isAccountNonLocked());
  }

  /** Method under test: {@link UserLogin#expireCredentials()} */
  @Test
  void testExpireCredentials() {
    UserLogin userLogin = new UserLogin();
    userLogin.expireCredentials();
    assertFalse(userLogin.isCredentialsNonExpired());
  }

  /** Method under test: {@link UserLogin#enable()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testEnable() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new UserLogin()).enable();
  }

  /** Method under test: {@link UserLogin#secure()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testSecure() {
    // TODO: Complete this test.
    //   Reason: R031 Method may be time-sensitive.
    //   The assertions no longer passed when run at an alternate date, time and
    //   timezone. Try refactoring the method to take a java.time.Clock instance so
    //   that the time can be parameterized during testing.

    (new UserLogin()).secure();
  }

  /** Method under test: {@link UserLogin#makeNewLogin()} */
  @Test
  void testMakeNewLogin() {
    assertEquals(0L, (new UserLogin()).makeNewLogin());
  }

  /** Method under test: {@link UserLogin#makeNewLogin()} */
  @Test
  void testMakeNewLogin2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertEquals(Permissions.CREATE_USER, userLogin.makeNewLogin());
    assertEquals(Permissions.CREATE_USER, userLogin.getLastLogin().longValue());
  }

  /** Method under test: {@link UserLogin#getAuthorities()} */
  @Test
  void testGetAuthorities() {
    assertTrue((new UserLogin()).getAuthorities().isEmpty());
  }

  /** Method under test: {@link UserLogin#getAuthorities()} */
  @Test
  void testGetAuthorities2() {
    Roles roles = new Roles();
    roles.setCreatedAt(Permissions.CREATE_USER);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Permissions.CREATE_USER);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Permissions.CREATE_USER);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Permissions.CREATE_USER);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles);
    assertEquals(
        1,
        (new UserLogin(
                "janedoe",
                "iloveyou",
                "Salt",
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                rolesSet))
            .getAuthorities()
            .size());
  }

  /** Method under test: {@link UserLogin#getAuthorities()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testGetAuthorities3() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: A granted authority textual representation is required
    //       at
    // xyz.subho.lunchbooking.entities.security.UserLogin.lambda$getAuthorities$0(UserLogin.java:138)
    //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
    //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
    //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
    //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
    //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
    //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
    //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
    //       at
    // xyz.subho.lunchbooking.entities.security.UserLogin.getAuthorities(UserLogin.java:139)

    Roles roles = new Roles();
    roles.setCreatedAt(Permissions.CREATE_USER);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Permissions.CREATE_USER);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("");
    roles.setUpdatedAt(Permissions.CREATE_USER);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Permissions.CREATE_USER);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles);
    (new UserLogin(
            "janedoe",
            "iloveyou",
            "Salt",
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            Permissions.CREATE_USER,
            rolesSet))
        .getAuthorities();
  }

  /** Method under test: {@link UserLogin#getAuthorities()} */
  @Test
  void testGetAuthorities4() {
    Roles roles = new Roles();
    roles.setCreatedAt(Permissions.CREATE_USER);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Permissions.CREATE_USER);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Permissions.CREATE_USER);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Permissions.CREATE_USER);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(Permissions.CREATE_USER);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(Permissions.CREATE_USER);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(Permissions.CREATE_USER);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(Permissions.CREATE_USER);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(Permissions.CREATE_USER);

    HashSet<Roles> rolesSet = new HashSet<>();
    rolesSet.add(roles1);
    rolesSet.add(roles);
    assertEquals(
        1,
        (new UserLogin(
                "janedoe",
                "iloveyou",
                "Salt",
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                Permissions.CREATE_USER,
                rolesSet))
            .getAuthorities()
            .size());
  }

  /** Method under test: {@link UserLogin#isAccountNonExpired()} */
  @Test
  void testIsAccountNonExpired() {
    assertTrue((new UserLogin()).isAccountNonExpired());
  }

  /** Method under test: {@link UserLogin#isAccountNonExpired()} */
  @Test
  void testIsAccountNonExpired2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertFalse(userLogin.isAccountNonExpired());
  }

  /** Method under test: {@link UserLogin#isAccountNonLocked()} */
  @Test
  void testIsAccountNonLocked() {
    assertTrue((new UserLogin()).isAccountNonLocked());
  }

  /** Method under test: {@link UserLogin#isAccountNonLocked()} */
  @Test
  void testIsAccountNonLocked2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertFalse(userLogin.isAccountNonLocked());
  }

  /** Method under test: {@link UserLogin#isCredentialsNonExpired()} */
  @Test
  void testIsCredentialsNonExpired() {
    assertTrue((new UserLogin()).isCredentialsNonExpired());
  }

  /** Method under test: {@link UserLogin#isCredentialsNonExpired()} */
  @Test
  void testIsCredentialsNonExpired2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertFalse(userLogin.isCredentialsNonExpired());
  }

  /** Method under test: {@link UserLogin#isEnabled()} */
  @Test
  void testIsEnabled() {
    assertFalse((new UserLogin()).isEnabled());
  }

  /** Method under test: {@link UserLogin#isEnabled()} */
  @Test
  void testIsEnabled2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertTrue(userLogin.isEnabled());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLogin#UserLogin()}
   *   <li>{@link UserLogin#disable()}
   *   <li>{@link UserLogin#unExpireAccount()}
   *   <li>{@link UserLogin#unExpireCredentials()}
   *   <li>{@link UserLogin#unlock()}
   *   <li>{@link UserLogin#unsecure()}
   * </ul>
   */
  @Test
  void testConstructor() {
    UserLogin actualUserLogin = new UserLogin();
    actualUserLogin.disable();
    actualUserLogin.unExpireAccount();
    actualUserLogin.unExpireCredentials();
    actualUserLogin.unlock();
    actualUserLogin.unsecure();
    assertTrue(actualUserLogin.getAuthorities().isEmpty());
    assertTrue(actualUserLogin.isCredentialsNonExpired());
    assertTrue(actualUserLogin.isAccountNonLocked());
    assertTrue(actualUserLogin.isAccountNonExpired());
    assertEquals(Permissions.CREATE_USER, actualUserLogin.getVersion().longValue());
    assertNull(actualUserLogin.getUsername());
    assertNull(actualUserLogin.getUpdatedBy());
    assertNull(actualUserLogin.getUpdatedAt());
    assertNull(actualUserLogin.getSecuredAt());
    assertNull(actualUserLogin.getSalt());
    assertTrue(actualUserLogin.getRoles().isEmpty());
    assertNull(actualUserLogin.getPassword());
    assertNull(actualUserLogin.getLockedAt());
    assertNull(actualUserLogin.getLastLogin());
    assertNull(actualUserLogin.getId());
    assertNull(actualUserLogin.getExpiredAt());
    assertNull(actualUserLogin.getEnabledAt());
    assertNull(actualUserLogin.getDeletedBy());
    assertNull(actualUserLogin.getDeletedAt());
    assertNull(actualUserLogin.getCurrentLogin());
    assertNull(actualUserLogin.getCredentialExpiredAt());
    assertNull(actualUserLogin.getCreatedBy());
    assertNull(actualUserLogin.getCreatedAt());
  }

  /** Method under test: {@link UserLogin#equals(Object)} */
  @Test
  void testEquals() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertNotEquals(userLogin, null);
  }

  /** Method under test: {@link UserLogin#equals(Object)} */
  @Test
  void testEquals2() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertNotEquals(userLogin, "Different type to UserLogin");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLogin#equals(Object)}
   *   <li>{@link UserLogin#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);
    assertEquals(userLogin, userLogin);
    int expectedHashCodeResult = userLogin.hashCode();
    assertEquals(expectedHashCodeResult, userLogin.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link UserLogin#equals(Object)}
   *   <li>{@link UserLogin#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(123L);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);

    UserLogin userLogin1 = new UserLogin();
    userLogin1.setCreatedAt(Permissions.CREATE_USER);
    userLogin1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin1.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin1.setCurrentLogin(Permissions.CREATE_USER);
    userLogin1.setDeletedAt(Permissions.CREATE_USER);
    userLogin1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin1.setEnabledAt(Permissions.CREATE_USER);
    userLogin1.setExpiredAt(Permissions.CREATE_USER);
    userLogin1.setId(123L);
    userLogin1.setLastLogin(Permissions.CREATE_USER);
    userLogin1.setLockedAt(Permissions.CREATE_USER);
    userLogin1.setPassword("iloveyou");
    userLogin1.setRoles(new HashSet<>());
    userLogin1.setSalt("Salt");
    userLogin1.setSecuredAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedBy("2020-03-01");
    userLogin1.setUsername("janedoe");
    userLogin1.setVersion(Permissions.CREATE_USER);
    assertEquals(userLogin, userLogin1);
    int expectedHashCodeResult = userLogin.hashCode();
    assertEquals(expectedHashCodeResult, userLogin1.hashCode());
  }

  /** Method under test: {@link UserLogin#equals(Object)} */
  @Test
  void testEquals5() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(Permissions.CREATE_USER);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);

    UserLogin userLogin1 = new UserLogin();
    userLogin1.setCreatedAt(Permissions.CREATE_USER);
    userLogin1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin1.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin1.setCurrentLogin(Permissions.CREATE_USER);
    userLogin1.setDeletedAt(Permissions.CREATE_USER);
    userLogin1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin1.setEnabledAt(Permissions.CREATE_USER);
    userLogin1.setExpiredAt(Permissions.CREATE_USER);
    userLogin1.setId(123L);
    userLogin1.setLastLogin(Permissions.CREATE_USER);
    userLogin1.setLockedAt(Permissions.CREATE_USER);
    userLogin1.setPassword("iloveyou");
    userLogin1.setRoles(new HashSet<>());
    userLogin1.setSalt("Salt");
    userLogin1.setSecuredAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedBy("2020-03-01");
    userLogin1.setUsername("janedoe");
    userLogin1.setVersion(Permissions.CREATE_USER);
    assertNotEquals(userLogin, userLogin1);
  }

  /** Method under test: {@link UserLogin#equals(Object)} */
  @Test
  void testEquals6() {
    UserLogin userLogin = new UserLogin();
    userLogin.setCreatedAt(Permissions.CREATE_USER);
    userLogin.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin.setCurrentLogin(Permissions.CREATE_USER);
    userLogin.setDeletedAt(Permissions.CREATE_USER);
    userLogin.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin.setEnabledAt(Permissions.CREATE_USER);
    userLogin.setExpiredAt(Permissions.CREATE_USER);
    userLogin.setId(null);
    userLogin.setLastLogin(Permissions.CREATE_USER);
    userLogin.setLockedAt(Permissions.CREATE_USER);
    userLogin.setPassword("iloveyou");
    userLogin.setRoles(new HashSet<>());
    userLogin.setSalt("Salt");
    userLogin.setSecuredAt(Permissions.CREATE_USER);
    userLogin.setUpdatedAt(Permissions.CREATE_USER);
    userLogin.setUpdatedBy("2020-03-01");
    userLogin.setUsername("janedoe");
    userLogin.setVersion(Permissions.CREATE_USER);

    UserLogin userLogin1 = new UserLogin();
    userLogin1.setCreatedAt(Permissions.CREATE_USER);
    userLogin1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userLogin1.setCredentialExpiredAt(Permissions.CREATE_USER);
    userLogin1.setCurrentLogin(Permissions.CREATE_USER);
    userLogin1.setDeletedAt(Permissions.CREATE_USER);
    userLogin1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userLogin1.setEnabledAt(Permissions.CREATE_USER);
    userLogin1.setExpiredAt(Permissions.CREATE_USER);
    userLogin1.setId(123L);
    userLogin1.setLastLogin(Permissions.CREATE_USER);
    userLogin1.setLockedAt(Permissions.CREATE_USER);
    userLogin1.setPassword("iloveyou");
    userLogin1.setRoles(new HashSet<>());
    userLogin1.setSalt("Salt");
    userLogin1.setSecuredAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedAt(Permissions.CREATE_USER);
    userLogin1.setUpdatedBy("2020-03-01");
    userLogin1.setUsername("janedoe");
    userLogin1.setVersion(Permissions.CREATE_USER);
    assertNotEquals(userLogin, userLogin1);
  }
}
