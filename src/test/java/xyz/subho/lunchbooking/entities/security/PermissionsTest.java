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

import org.junit.jupiter.api.Test;

class PermissionsTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Permissions#Permissions(String, boolean, String)}
   *   <li>{@link Permissions#getAuthority()}
   * </ul>
   */
  @Test
  void testConstructor() {
    assertEquals("Name", (new Permissions("Name", true, "Note")).getAuthority());
  }

  /** Method under test: {@link Permissions#Permissions(Long, String)} */
  @Test
  void testConstructor2() {
    Permissions actualPermissions = new Permissions(123L, "Name");

    assertEquals("Name", actualPermissions.getAuthority());
    assertTrue(actualPermissions.isEnabled());
    assertEquals(Permissions.CREATE_USER, actualPermissions.getVersion().longValue());
    assertEquals(123L, actualPermissions.getId().longValue());
  }

  /** Method under test: {@link Permissions#equals(Object)} */
  @Test
  void testEquals() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(123L);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);
    assertNotEquals(permissions, null);
  }

  /** Method under test: {@link Permissions#equals(Object)} */
  @Test
  void testEquals2() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(123L);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);
    assertNotEquals(permissions, "Different type to Permissions");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Permissions#equals(Object)}
   *   <li>{@link Permissions#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(123L);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);
    assertEquals(permissions, permissions);
    int expectedHashCodeResult = permissions.hashCode();
    assertEquals(expectedHashCodeResult, permissions.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link Permissions#equals(Object)}
   *   <li>{@link Permissions#hashCode()}
   * </ul>
   */
  @Test
  void testEquals4() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(123L);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);

    Permissions permissions1 = new Permissions();
    permissions1.setCreatedAt(Permissions.CREATE_USER);
    permissions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions1.setDeletedAt(Permissions.CREATE_USER);
    permissions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions1.setEnabled(true);
    permissions1.setId(123L);
    permissions1.setName("Name");
    permissions1.setNote("Note");
    permissions1.setUpdatedAt(Permissions.CREATE_USER);
    permissions1.setUpdatedBy("2020-03-01");
    permissions1.setVersion(Permissions.CREATE_USER);
    assertEquals(permissions, permissions1);
    int expectedHashCodeResult = permissions.hashCode();
    assertEquals(expectedHashCodeResult, permissions1.hashCode());
  }

  /** Method under test: {@link Permissions#equals(Object)} */
  @Test
  void testEquals5() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(Permissions.CREATE_USER);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);

    Permissions permissions1 = new Permissions();
    permissions1.setCreatedAt(Permissions.CREATE_USER);
    permissions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions1.setDeletedAt(Permissions.CREATE_USER);
    permissions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions1.setEnabled(true);
    permissions1.setId(123L);
    permissions1.setName("Name");
    permissions1.setNote("Note");
    permissions1.setUpdatedAt(Permissions.CREATE_USER);
    permissions1.setUpdatedBy("2020-03-01");
    permissions1.setVersion(Permissions.CREATE_USER);
    assertNotEquals(permissions, permissions1);
  }

  /** Method under test: {@link Permissions#equals(Object)} */
  @Test
  void testEquals6() {
    Permissions permissions = new Permissions();
    permissions.setCreatedAt(Permissions.CREATE_USER);
    permissions.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions.setDeletedAt(Permissions.CREATE_USER);
    permissions.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions.setEnabled(true);
    permissions.setId(null);
    permissions.setName("Name");
    permissions.setNote("Note");
    permissions.setUpdatedAt(Permissions.CREATE_USER);
    permissions.setUpdatedBy("2020-03-01");
    permissions.setVersion(Permissions.CREATE_USER);

    Permissions permissions1 = new Permissions();
    permissions1.setCreatedAt(Permissions.CREATE_USER);
    permissions1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    permissions1.setDeletedAt(Permissions.CREATE_USER);
    permissions1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    permissions1.setEnabled(true);
    permissions1.setId(123L);
    permissions1.setName("Name");
    permissions1.setNote("Note");
    permissions1.setUpdatedAt(Permissions.CREATE_USER);
    permissions1.setUpdatedBy("2020-03-01");
    permissions1.setVersion(Permissions.CREATE_USER);
    assertNotEquals(permissions, permissions1);
  }
}
