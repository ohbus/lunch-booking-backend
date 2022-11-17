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

package xyz.subho.lunchbooking.security;

import java.security.Principal;
import java.util.Set;
import javax.security.auth.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LunchBookingPrincipal implements Principal {

  private Long id;
  private String username;
  private Set<? extends GrantedAuthority> authorities;

  @Override
  public boolean equals(Object another) {
    if (another instanceof LunchBookingPrincipal principal) {
      return this.id.equals(principal.id);
    }
    return false;
  }

  @Override
  public String toString() {
    return "LunchBookingPrincipal{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", authorities="
        + authorities
        + '}';
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String getName() {
    return id.toString();
  }

  @Override
  public boolean implies(Subject subject) {
    return Principal.super.implies(subject);
  }
}
