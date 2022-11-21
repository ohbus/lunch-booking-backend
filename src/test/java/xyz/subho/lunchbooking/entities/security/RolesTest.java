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

package xyz.subho.lunchbooking.entities.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

class RolesTest {
  /** Method under test: {@link Roles#Roles(Long, String)} */
  @Test
  void testConstructor() {
    Roles actualRoles = new Roles(123L, "Role");

    assertEquals(Roles.ADMINISTRATOR, actualRoles.getVersion().longValue());
    assertEquals("Role", actualRoles.getRole());
    assertTrue(actualRoles.getPermissions().isEmpty());
    assertEquals(123L, actualRoles.getId().longValue());
  }

  /** Method under test: {@link Roles#equals(Object)} */
  @Test
  void testEquals() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);
    assertNotEquals(roles, null);
  }

  /** Method under test: {@link Roles#equals(Object)} */
  @Test
  void testEquals2() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);
    assertNotEquals(roles, "Different type to Roles");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Roles#equals(Object)}
   *   <li>{@link Roles#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);
    assertEquals(roles, roles);
    int expectedHashCodeResult = roles.hashCode();
    assertEquals(expectedHashCodeResult, roles.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Roles#equals(Object)}
   *   <li>{@link Roles#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(123L);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(Roles.ADMINISTRATOR);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(Roles.ADMINISTRATOR);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(Roles.ADMINISTRATOR);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(Roles.ADMINISTRATOR);
    assertEquals(roles, roles1);
    int expectedHashCodeResult = roles.hashCode();
    assertEquals(expectedHashCodeResult, roles1.hashCode());
  }

  /** Method under test: {@link Roles#equals(Object)} */
  @Test
  void testEquals5() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(Roles.ADMINISTRATOR);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(Roles.ADMINISTRATOR);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(Roles.ADMINISTRATOR);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(Roles.ADMINISTRATOR);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(Roles.ADMINISTRATOR);
    assertNotEquals(roles, roles1);
  }

  /** Method under test: {@link Roles#equals(Object)} */
  @Test
  void testEquals6() {
    Roles roles = new Roles();
    roles.setCreatedAt(Roles.ADMINISTRATOR);
    roles.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles.setDeletedAt(Roles.ADMINISTRATOR);
    roles.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles.setId(null);
    roles.setPermissions(new HashSet<>());
    roles.setRole("Role");
    roles.setUpdatedAt(Roles.ADMINISTRATOR);
    roles.setUpdatedBy("2020-03-01");
    roles.setVersion(Roles.ADMINISTRATOR);

    Roles roles1 = new Roles();
    roles1.setCreatedAt(Roles.ADMINISTRATOR);
    roles1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    roles1.setDeletedAt(Roles.ADMINISTRATOR);
    roles1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    roles1.setId(123L);
    roles1.setPermissions(new HashSet<>());
    roles1.setRole("Role");
    roles1.setUpdatedAt(Roles.ADMINISTRATOR);
    roles1.setUpdatedBy("2020-03-01");
    roles1.setVersion(Roles.ADMINISTRATOR);
    assertNotEquals(roles, roles1);
  }
}
