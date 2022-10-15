package xyz.subho.lunchbooking.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Disabled
class UserLoginRepositoryTest {

  @Mock private UserLoginRepository userLoginRepository;

  /** Method under test: {@link UserLoginRepository#existsByUsername(String)} */
  @Test
  void testExistsByUsername() {

    userLoginRepository.existsByUsername("foo");
  }

  /** Method under test: {@link UserLoginRepository#countByUsername(String)} */
  @Test
  void testCountByUsername() {
    userLoginRepository.countByUsername("foo");
  }

  /** Method under test: {@link UserLoginRepository#findByUsername(String)} */
  @Test
  void testFindByUsername() {
    userLoginRepository.findByUsername("foo");
  }
}
