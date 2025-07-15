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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.subho.lunchbooking.entities.BaseEntity;

@Entity
@Table(
    name = "users_login",
    indexes = {@Index(columnList = "username", name = "username", unique = true)})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@NaturalIdCache
public class UserLogin extends BaseEntity implements UserDetails, Serializable {

  @Serial private static final long serialVersionUID = -1484069631072335374L;

  // User Name is the Email ID
  @Column(name = "username", nullable = false, unique = true, updatable = false, length = 128)
  @NaturalId
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "salt", nullable = false, length = 256)
  private String salt;

  private Long expiredAt;

  private Long lockedAt;

  private Long credentialExpiredAt;

  private Long enabledAt;

  private Long securedAt;

  @Basic private Long currentLogin;

  @Basic private Long lastLogin;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Roles> roles = new HashSet<>(4, 1);

  public void expireAccount() {
    expiredAt = System.currentTimeMillis();
  }

  public void unExpireAccount() {
    expiredAt = null;
  }

  public void lock() {
    lockedAt = System.currentTimeMillis();
  }

  public void unlock() {
    lockedAt = null;
  }

  public void expireCredentials() {
    credentialExpiredAt = System.currentTimeMillis();
  }

  public void unExpireCredentials() {
    credentialExpiredAt = null;
  }

  public void enable() {
    enabledAt = System.currentTimeMillis();
  }

  public void disable() {
    enabledAt = null;
  }

  public void secure() {
    securedAt = System.currentTimeMillis();
  }

  public void unsecure() {
    securedAt = null;
  }

  public long makeNewLogin() {

    if (Objects.isNull(currentLogin)) {
      currentLogin = System.currentTimeMillis();
    } else {
      lastLogin = currentLogin;
      currentLogin = System.currentTimeMillis();
    }
    return Objects.isNull(lastLogin) ? 0L : lastLogin;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getRole()))
        .collect(Collectors.toSet());
  }

  @Override
  public boolean isAccountNonExpired() {
    return Objects.isNull(expiredAt);
  }

  @Override
  public boolean isAccountNonLocked() {
    return Objects.isNull(lockedAt);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return Objects.isNull(credentialExpiredAt);
  }

  @Override
  public boolean isEnabled() {
    return Objects.nonNull(enabledAt);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UserLogin userLogin = (UserLogin) o;
    return getId() != null && Objects.equals(getId(), userLogin.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
