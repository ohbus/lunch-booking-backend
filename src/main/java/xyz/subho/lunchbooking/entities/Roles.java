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

package xyz.subho.lunchbooking.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(
    name = "roles",
    indexes = {@Index(name = "role", columnList = "role", unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
@DynamicInsert
@DynamicUpdate
public class Roles extends BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = 1466436830227018993L;

  public static final long ADMINISTRATOR = 1;
  public static final long MANAGER = 2;
  public static final long EMPLOYEE = 3;
  public static final long CATERER = 4;

  public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";
  public static final String ROLE_MANAGER = "ROLE_MANAGER";
  public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
  public static final String ROLE_CATERER = "ROLE_CATERER";

  @Column(name = "role", nullable = false, length = 32)
  @NaturalId
  private String role;

  public Roles(Long id, String role) {
    this.setId(id);
    this.role = role;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "permissions_roles",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  @ToString.Exclude
  private Set<Permissions> permissions = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Roles roles = (Roles) o;
    return getId() != null && Objects.equals(getId(), roles.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
