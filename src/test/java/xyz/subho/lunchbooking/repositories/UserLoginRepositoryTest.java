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
