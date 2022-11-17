package xyz.subho.lunchbooking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.subho.lunchbooking.entities.Bookings;
import xyz.subho.lunchbooking.entities.UserMetadata;
import xyz.subho.lunchbooking.models.UserResponseModel;

@ContextConfiguration(classes = {UserDetailsMapper.class})
@ExtendWith(SpringExtension.class)
class UserDetailsMapperTest {
  @Autowired private UserDetailsMapper userDetailsMapper;

  /** Method under test: {@link UserDetailsMapper#transform(UserMetadata)} */
  @Test
  void testTransform() {
    UserMetadata userMetadata = new UserMetadata();
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    UserResponseModel actualTransformResult = userDetailsMapper.transform(userMetadata);
    assertEquals("42", actualTransformResult.getEmailId());
    assertEquals("Mobile", actualTransformResult.getMobile());
    assertEquals("Doe", actualTransformResult.getLastName());
    assertEquals(123L, actualTransformResult.getId().longValue());
    assertEquals("Jane", actualTransformResult.getFirstName());
  }

  /** Method under test: {@link UserDetailsMapper#transform(UserMetadata)} */
  @Test
  void testTransform2() {
    UserMetadata userMetadata = mock(UserMetadata.class);
    when(userMetadata.getId()).thenReturn(123L);
    when(userMetadata.getEmailId()).thenReturn("42");
    when(userMetadata.getFirstName()).thenReturn("Jane");
    when(userMetadata.getLastName()).thenReturn("Doe");
    when(userMetadata.getMobile()).thenReturn("Mobile");
    doNothing().when(userMetadata).setCreatedAt((Long) any());
    doNothing().when(userMetadata).setCreatedBy((String) any());
    doNothing().when(userMetadata).setDeletedAt((Long) any());
    doNothing().when(userMetadata).setDeletedBy((String) any());
    doNothing().when(userMetadata).setId((Long) any());
    doNothing().when(userMetadata).setUpdatedAt((Long) any());
    doNothing().when(userMetadata).setUpdatedBy((String) any());
    doNothing().when(userMetadata).setVersion((Long) any());
    doNothing().when(userMetadata).setBookings((Set<Bookings>) any());
    doNothing().when(userMetadata).setEmailId((String) any());
    doNothing().when(userMetadata).setFirstName((String) any());
    doNothing().when(userMetadata).setLastName((String) any());
    doNothing().when(userMetadata).setMobile((String) any());
    doNothing().when(userMetadata).setSubscribedAt((Long) any());
    userMetadata.setBookings(new HashSet<>());
    userMetadata.setCreatedAt(1L);
    userMetadata.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    userMetadata.setDeletedAt(1L);
    userMetadata.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    userMetadata.setEmailId("42");
    userMetadata.setFirstName("Jane");
    userMetadata.setId(123L);
    userMetadata.setLastName("Doe");
    userMetadata.setMobile("Mobile");
    userMetadata.setSubscribedAt(1L);
    userMetadata.setUpdatedAt(1L);
    userMetadata.setUpdatedBy("2020-03-01");
    userMetadata.setVersion(1L);
    UserResponseModel actualTransformResult = userDetailsMapper.transform(userMetadata);
    assertEquals("42", actualTransformResult.getEmailId());
    assertEquals("Mobile", actualTransformResult.getMobile());
    assertEquals("Doe", actualTransformResult.getLastName());
    assertEquals(123L, actualTransformResult.getId().longValue());
    assertEquals("Jane", actualTransformResult.getFirstName());
    verify(userMetadata).getId();
    verify(userMetadata).getEmailId();
    verify(userMetadata).getFirstName();
    verify(userMetadata).getLastName();
    verify(userMetadata).getMobile();
    verify(userMetadata).setCreatedAt((Long) any());
    verify(userMetadata).setCreatedBy((String) any());
    verify(userMetadata).setDeletedAt((Long) any());
    verify(userMetadata).setDeletedBy((String) any());
    verify(userMetadata).setId((Long) any());
    verify(userMetadata).setUpdatedAt((Long) any());
    verify(userMetadata).setUpdatedBy((String) any());
    verify(userMetadata).setVersion((Long) any());
    verify(userMetadata).setBookings((Set<Bookings>) any());
    verify(userMetadata).setEmailId((String) any());
    verify(userMetadata).setFirstName((String) any());
    verify(userMetadata).setLastName((String) any());
    verify(userMetadata).setMobile((String) any());
    verify(userMetadata).setSubscribedAt((Long) any());
  }

  /** Method under test: {@link UserDetailsMapper#transformBack(UserResponseModel)} */
  @Test
  void testTransformBack() {
    UserMetadata actualTransformBackResult =
        userDetailsMapper.transformBack(new UserResponseModel());
    assertTrue(actualTransformBackResult.getBookings().isEmpty());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertNull(actualTransformBackResult.getMobile());
    assertNull(actualTransformBackResult.getLastName());
    assertNull(actualTransformBackResult.getId());
    assertNull(actualTransformBackResult.getFirstName());
    assertNull(actualTransformBackResult.getEmailId());
  }

  /** Method under test: {@link UserDetailsMapper#transformBack(UserResponseModel)} */
  @Test
  @Disabled("TODO: Complete this test")
  void testTransformBack2() {
    // TODO: Complete this test.
    //   Reason: R013 No inputs found that don't throw a trivial exception.
    //   test threw
    //   java.lang.IllegalArgumentException: Source must not be null
    //       at
    // xyz.subho.lunchbooking.mapper.UserDetailsMapper.transformBack(UserDetailsMapper.java:39)

    userDetailsMapper.transformBack(null);
  }

  /** Method under test: {@link UserDetailsMapper#transformBack(UserResponseModel)} */
  @Test
  void testTransformBack3() {
    UserResponseModel userResponseModel = mock(UserResponseModel.class);
    when(userResponseModel.getId()).thenReturn(123L);
    when(userResponseModel.getEmailId()).thenReturn("42");
    when(userResponseModel.getFirstName()).thenReturn("Jane");
    when(userResponseModel.getLastName()).thenReturn("Doe");
    when(userResponseModel.getMobile()).thenReturn("Mobile");
    UserMetadata actualTransformBackResult = userDetailsMapper.transformBack(userResponseModel);
    assertTrue(actualTransformBackResult.getBookings().isEmpty());
    assertEquals(1L, actualTransformBackResult.getVersion().longValue());
    assertEquals("Mobile", actualTransformBackResult.getMobile());
    assertEquals("Doe", actualTransformBackResult.getLastName());
    assertEquals(123L, actualTransformBackResult.getId().longValue());
    assertEquals("Jane", actualTransformBackResult.getFirstName());
    assertEquals("42", actualTransformBackResult.getEmailId());
    verify(userResponseModel).getId();
    verify(userResponseModel).getEmailId();
    verify(userResponseModel).getFirstName();
    verify(userResponseModel).getLastName();
    verify(userResponseModel).getMobile();
  }
}
