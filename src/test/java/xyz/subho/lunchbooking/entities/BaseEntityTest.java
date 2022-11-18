package xyz.subho.lunchbooking.entities;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BaseEntityTest {
  /** Method under test: {@link BaseEntity#onCreate()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testOnCreate() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.springframework.security.core.Authentication.getName()" because "authentication" is null
    //       at xyz.subho.lunchbooking.entities.BaseEntity.getCurrentUser(BaseEntity.java:89)
    //       at xyz.subho.lunchbooking.entities.BaseEntity.onCreate(BaseEntity.java:75)

    (new BaseEntity()).onCreate();
  }

  /** Method under test: {@link BaseEntity#onUpdate()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testOnUpdate() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.springframework.security.core.Authentication.getName()" because "authentication" is null
    //       at xyz.subho.lunchbooking.entities.BaseEntity.getCurrentUser(BaseEntity.java:89)
    //       at xyz.subho.lunchbooking.entities.BaseEntity.onUpdate(BaseEntity.java:82)

    (new BaseEntity()).onUpdate();
  }

  /** Method under test: {@link BaseEntity#delete()} */
  @Test
  @Disabled("TODO: Complete this test")
  void testDelete() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.springframework.security.core.Authentication.getName()" because "authentication" is null
    //       at xyz.subho.lunchbooking.entities.BaseEntity.getCurrentUser(BaseEntity.java:89)
    //       at xyz.subho.lunchbooking.entities.BaseEntity.delete(BaseEntity.java:95)

    (new BaseEntity()).delete();
  }
}
