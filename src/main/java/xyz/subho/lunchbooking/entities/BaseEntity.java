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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@DynamicInsert
@DynamicUpdate
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @Column(name = "id")
  private Long id;

  @Basic @CreatedDate private Long createdAt;

  @Column(length = 100)
  @CreatedBy
  private String createdBy;

  @Basic @LastModifiedDate private Long updatedAt;

  @Column(length = 100)
  @LastModifiedBy
  private String updatedBy;

  @Basic private Long version = 1L;

  @Basic private Long deletedAt;

  @Column(length = 100)
  private String deletedBy;

  @PrePersist
  public void onCreate() {
    this.createdAt = System.currentTimeMillis();
    this.createdBy = getCurrentUser();
    this.version = 1L;
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = System.currentTimeMillis();
    this.updatedBy = getCurrentUser();
    this.version++;
  }

  private String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (!(authentication instanceof AnonymousAuthenticationToken))
        ? authentication.getName()
        : null;
  }

  public void delete() {
    this.deletedAt = System.currentTimeMillis();
    this.deletedBy = getCurrentUser();
  }
}
