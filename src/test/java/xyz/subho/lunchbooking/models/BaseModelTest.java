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

package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BaseModelTest {
  /** Method under test: {@link BaseModel#canEqual(Object)} */
  @Test
  void testCanEqual() {
    assertFalse((new BaseModel()).canEqual("Other"));
  }

  /** Method under test: {@link BaseModel#canEqual(Object)} */
  @Test
  void testCanEqual2() {
    BaseModel baseModel = new BaseModel();
    assertTrue(baseModel.canEqual(new BaseModel()));
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BaseModel#BaseModel()}
   *   <li>{@link BaseModel#setCreatedAt(Long)}
   *   <li>{@link BaseModel#setCreatedBy(String)}
   *   <li>{@link BaseModel#setDeletedAt(Long)}
   *   <li>{@link BaseModel#setDeletedBy(String)}
   *   <li>{@link BaseModel#setId(Long)}
   *   <li>{@link BaseModel#setUpdatedAt(Long)}
   *   <li>{@link BaseModel#setUpdatedBy(String)}
   *   <li>{@link BaseModel#setVersion(Long)}
   *   <li>{@link BaseModel#toString()}
   *   <li>{@link BaseModel#getCreatedAt()}
   *   <li>{@link BaseModel#getCreatedBy()}
   *   <li>{@link BaseModel#getDeletedAt()}
   *   <li>{@link BaseModel#getDeletedBy()}
   *   <li>{@link BaseModel#getId()}
   *   <li>{@link BaseModel#getUpdatedAt()}
   *   <li>{@link BaseModel#getUpdatedBy()}
   *   <li>{@link BaseModel#getVersion()}
   * </ul>
   */
  @Test
  void testConstructor() {
    BaseModel actualBaseModel = new BaseModel();
    actualBaseModel.setCreatedAt(1L);
    actualBaseModel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    actualBaseModel.setDeletedAt(1L);
    actualBaseModel.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    actualBaseModel.setId(123L);
    actualBaseModel.setUpdatedAt(1L);
    actualBaseModel.setUpdatedBy("2020-03-01");
    actualBaseModel.setVersion(1L);
    String actualToStringResult = actualBaseModel.toString();
    assertEquals(1L, actualBaseModel.getCreatedAt().longValue());
    assertEquals("Jan 1, 2020 8:00am GMT+0100", actualBaseModel.getCreatedBy());
    assertEquals(1L, actualBaseModel.getDeletedAt().longValue());
    assertEquals("Jan 1, 2020 11:00am GMT+0100", actualBaseModel.getDeletedBy());
    assertEquals(123L, actualBaseModel.getId().longValue());
    assertEquals(1L, actualBaseModel.getUpdatedAt().longValue());
    assertEquals("2020-03-01", actualBaseModel.getUpdatedBy());
    assertEquals(1L, actualBaseModel.getVersion().longValue());
    assertEquals(
        "BaseModel(id=123, createdAt=1, createdBy=Jan 1, 2020 8:00am GMT+0100, updatedAt=1, updatedBy=2020-03-01,"
            + " version=1, deletedAt=1, deletedBy=Jan 1, 2020 11:00am GMT+0100)",
        actualToStringResult);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BaseModel#BaseModel(Long, Long, String, Long, String, Long, Long, String)}
   *   <li>{@link BaseModel#setCreatedAt(Long)}
   *   <li>{@link BaseModel#setCreatedBy(String)}
   *   <li>{@link BaseModel#setDeletedAt(Long)}
   *   <li>{@link BaseModel#setDeletedBy(String)}
   *   <li>{@link BaseModel#setId(Long)}
   *   <li>{@link BaseModel#setUpdatedAt(Long)}
   *   <li>{@link BaseModel#setUpdatedBy(String)}
   *   <li>{@link BaseModel#setVersion(Long)}
   *   <li>{@link BaseModel#toString()}
   *   <li>{@link BaseModel#getCreatedAt()}
   *   <li>{@link BaseModel#getCreatedBy()}
   *   <li>{@link BaseModel#getDeletedAt()}
   *   <li>{@link BaseModel#getDeletedBy()}
   *   <li>{@link BaseModel#getId()}
   *   <li>{@link BaseModel#getUpdatedAt()}
   *   <li>{@link BaseModel#getUpdatedBy()}
   *   <li>{@link BaseModel#getVersion()}
   * </ul>
   */
  @Test
  void testConstructor2() {
    BaseModel actualBaseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    actualBaseModel.setCreatedAt(1L);
    actualBaseModel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    actualBaseModel.setDeletedAt(1L);
    actualBaseModel.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    actualBaseModel.setId(123L);
    actualBaseModel.setUpdatedAt(1L);
    actualBaseModel.setUpdatedBy("2020-03-01");
    actualBaseModel.setVersion(1L);
    String actualToStringResult = actualBaseModel.toString();
    assertEquals(1L, actualBaseModel.getCreatedAt().longValue());
    assertEquals("Jan 1, 2020 8:00am GMT+0100", actualBaseModel.getCreatedBy());
    assertEquals(1L, actualBaseModel.getDeletedAt().longValue());
    assertEquals("Jan 1, 2020 11:00am GMT+0100", actualBaseModel.getDeletedBy());
    assertEquals(123L, actualBaseModel.getId().longValue());
    assertEquals(1L, actualBaseModel.getUpdatedAt().longValue());
    assertEquals("2020-03-01", actualBaseModel.getUpdatedBy());
    assertEquals(1L, actualBaseModel.getVersion().longValue());
    assertEquals(
        "BaseModel(id=123, createdAt=1, createdBy=Jan 1, 2020 8:00am GMT+0100, updatedAt=1, updatedBy=2020-03-01,"
            + " version=1, deletedAt=1, deletedBy=Jan 1, 2020 11:00am GMT+0100)",
        actualToStringResult);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals() {
    assertNotEquals(new BaseModel(), null);
    assertNotEquals(new BaseModel(), "Different type to BaseModel");
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BaseModel#equals(Object)}
   *   <li>{@link BaseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals2() {
    BaseModel baseModel = new BaseModel();
    assertEquals(baseModel, baseModel);
    int expectedHashCodeResult = baseModel.hashCode();
    assertEquals(expectedHashCodeResult, baseModel.hashCode());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BaseModel#equals(Object)}
   *   <li>{@link BaseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals3() {
    BaseModel baseModel = new BaseModel();
    BaseModel baseModel1 = new BaseModel();
    assertEquals(baseModel, baseModel1);
    int expectedHashCodeResult = baseModel.hashCode();
    assertEquals(expectedHashCodeResult, baseModel1.hashCode());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals4() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals5() {
    BaseModel baseModel = new BaseModel();
    assertNotEquals(
        baseModel,
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100"));
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals6() {
    BaseModel baseModel = new BaseModel();
    baseModel.setCreatedAt(1L);
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals7() {
    BaseModel baseModel = new BaseModel();
    baseModel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals8() {
    BaseModel baseModel = new BaseModel();
    baseModel.setUpdatedAt(1L);
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals9() {
    BaseModel baseModel = new BaseModel();
    baseModel.setUpdatedBy("2020-03-01");
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals10() {
    BaseModel baseModel = new BaseModel();
    baseModel.setVersion(1L);
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals11() {
    BaseModel baseModel = new BaseModel();
    baseModel.setDeletedAt(1L);
    assertNotEquals(baseModel, new BaseModel());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals12() {
    BaseModel baseModel = new BaseModel();
    baseModel.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    assertNotEquals(baseModel, new BaseModel());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>{@link BaseModel#equals(Object)}
   *   <li>{@link BaseModel#hashCode()}
   * </ul>
   */
  @Test
  void testEquals13() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    BaseModel baseModel1 =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");

    assertEquals(baseModel, baseModel1);
    int expectedHashCodeResult = baseModel.hashCode();
    assertEquals(expectedHashCodeResult, baseModel1.hashCode());
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals14() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setCreatedAt(1L);
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals15() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals16() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setUpdatedAt(1L);
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals17() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setUpdatedBy("2020-03-01");
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals18() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setVersion(1L);
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals19() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setDeletedAt(1L);
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#equals(Object)} */
  @Test
  void testEquals20() {
    BaseModel baseModel = new BaseModel();

    BaseModel baseModel1 = new BaseModel();
    baseModel1.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    assertNotEquals(baseModel, baseModel1);
  }

  /** Method under test: {@link BaseModel#withCreatedAt(Long)} */
  @Test
  void testWithCreatedAt() {
    BaseModel actualWithCreatedAtResult = (new BaseModel()).withCreatedAt(1L);
    assertEquals(1L, actualWithCreatedAtResult.getCreatedAt().longValue());
    assertNull(actualWithCreatedAtResult.getVersion());
    assertNull(actualWithCreatedAtResult.getUpdatedBy());
    assertNull(actualWithCreatedAtResult.getUpdatedAt());
    assertNull(actualWithCreatedAtResult.getId());
    assertNull(actualWithCreatedAtResult.getDeletedBy());
    assertNull(actualWithCreatedAtResult.getDeletedAt());
    assertNull(actualWithCreatedAtResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withCreatedAt(Long)} */
  @Test
  void testWithCreatedAt2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withCreatedAt(1L));
  }

  /** Method under test: {@link BaseModel#withCreatedBy(String)} */
  @Test
  void testWithCreatedBy() {
    BaseModel actualWithCreatedByResult =
        (new BaseModel()).withCreatedBy("Jan 1, 2020 8:00am GMT+0100");
    assertNull(actualWithCreatedByResult.getCreatedAt());
    assertNull(actualWithCreatedByResult.getVersion());
    assertNull(actualWithCreatedByResult.getUpdatedBy());
    assertNull(actualWithCreatedByResult.getUpdatedAt());
    assertNull(actualWithCreatedByResult.getId());
    assertNull(actualWithCreatedByResult.getDeletedBy());
    assertNull(actualWithCreatedByResult.getDeletedAt());
    assertEquals("Jan 1, 2020 8:00am GMT+0100", actualWithCreatedByResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withCreatedBy(String)} */
  @Test
  void testWithCreatedBy2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withCreatedBy("Jan 1, 2020 8:00am GMT+0100"));
  }

  /** Method under test: {@link BaseModel#withDeletedAt(Long)} */
  @Test
  void testWithDeletedAt() {
    BaseModel actualWithDeletedAtResult = (new BaseModel()).withDeletedAt(1L);
    assertNull(actualWithDeletedAtResult.getCreatedAt());
    assertNull(actualWithDeletedAtResult.getVersion());
    assertNull(actualWithDeletedAtResult.getUpdatedBy());
    assertNull(actualWithDeletedAtResult.getUpdatedAt());
    assertNull(actualWithDeletedAtResult.getId());
    assertNull(actualWithDeletedAtResult.getDeletedBy());
    assertEquals(1L, actualWithDeletedAtResult.getDeletedAt().longValue());
    assertNull(actualWithDeletedAtResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withDeletedAt(Long)} */
  @Test
  void testWithDeletedAt2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withDeletedAt(1L));
  }

  /** Method under test: {@link BaseModel#withDeletedBy(String)} */
  @Test
  void testWithDeletedBy() {
    BaseModel actualWithDeletedByResult =
        (new BaseModel()).withDeletedBy("Jan 1, 2020 11:00am GMT+0100");
    assertNull(actualWithDeletedByResult.getCreatedAt());
    assertNull(actualWithDeletedByResult.getVersion());
    assertNull(actualWithDeletedByResult.getUpdatedBy());
    assertNull(actualWithDeletedByResult.getUpdatedAt());
    assertNull(actualWithDeletedByResult.getId());
    assertEquals("Jan 1, 2020 11:00am GMT+0100", actualWithDeletedByResult.getDeletedBy());
    assertNull(actualWithDeletedByResult.getDeletedAt());
    assertNull(actualWithDeletedByResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withDeletedBy(String)} */
  @Test
  void testWithDeletedBy2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withDeletedBy("Jan 1, 2020 11:00am GMT+0100"));
  }

  /** Method under test: {@link BaseModel#withId(Long)} */
  @Test
  void testWithId() {
    BaseModel actualWithIdResult = (new BaseModel()).withId(123L);
    assertNull(actualWithIdResult.getCreatedAt());
    assertNull(actualWithIdResult.getVersion());
    assertNull(actualWithIdResult.getUpdatedBy());
    assertNull(actualWithIdResult.getUpdatedAt());
    assertEquals(123L, actualWithIdResult.getId().longValue());
    assertNull(actualWithIdResult.getDeletedBy());
    assertNull(actualWithIdResult.getDeletedAt());
    assertNull(actualWithIdResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withId(Long)} */
  @Test
  void testWithId2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withId(123L));
  }

  /** Method under test: {@link BaseModel#withUpdatedAt(Long)} */
  @Test
  void testWithUpdatedAt() {
    BaseModel actualWithUpdatedAtResult = (new BaseModel()).withUpdatedAt(1L);
    assertNull(actualWithUpdatedAtResult.getCreatedAt());
    assertNull(actualWithUpdatedAtResult.getVersion());
    assertNull(actualWithUpdatedAtResult.getUpdatedBy());
    assertEquals(1L, actualWithUpdatedAtResult.getUpdatedAt().longValue());
    assertNull(actualWithUpdatedAtResult.getId());
    assertNull(actualWithUpdatedAtResult.getDeletedBy());
    assertNull(actualWithUpdatedAtResult.getDeletedAt());
    assertNull(actualWithUpdatedAtResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withUpdatedAt(Long)} */
  @Test
  void testWithUpdatedAt2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withUpdatedAt(1L));
  }

  /** Method under test: {@link BaseModel#withUpdatedBy(String)} */
  @Test
  void testWithUpdatedBy() {
    BaseModel actualWithUpdatedByResult = (new BaseModel()).withUpdatedBy("2020-03-01");
    assertNull(actualWithUpdatedByResult.getCreatedAt());
    assertNull(actualWithUpdatedByResult.getVersion());
    assertEquals("2020-03-01", actualWithUpdatedByResult.getUpdatedBy());
    assertNull(actualWithUpdatedByResult.getUpdatedAt());
    assertNull(actualWithUpdatedByResult.getId());
    assertNull(actualWithUpdatedByResult.getDeletedBy());
    assertNull(actualWithUpdatedByResult.getDeletedAt());
    assertNull(actualWithUpdatedByResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withUpdatedBy(String)} */
  @Test
  void testWithUpdatedBy2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withUpdatedBy("2020-03-01"));
  }

  /** Method under test: {@link BaseModel#withVersion(Long)} */
  @Test
  void testWithVersion() {
    BaseModel actualWithVersionResult = (new BaseModel()).withVersion(1L);
    assertNull(actualWithVersionResult.getCreatedAt());
    assertEquals(1L, actualWithVersionResult.getVersion().longValue());
    assertNull(actualWithVersionResult.getUpdatedBy());
    assertNull(actualWithVersionResult.getUpdatedAt());
    assertNull(actualWithVersionResult.getId());
    assertNull(actualWithVersionResult.getDeletedBy());
    assertNull(actualWithVersionResult.getDeletedAt());
    assertNull(actualWithVersionResult.getCreatedBy());
  }

  /** Method under test: {@link BaseModel#withVersion(Long)} */
  @Test
  void testWithVersion2() {
    BaseModel baseModel =
        new BaseModel(
            123L,
            1L,
            "Jan 1, 2020 8:00am GMT+0100",
            1L,
            "2020-03-01",
            1L,
            1L,
            "Jan 1, 2020 11:00am GMT+0100");
    assertSame(baseModel, baseModel.withVersion(1L));
  }
}
