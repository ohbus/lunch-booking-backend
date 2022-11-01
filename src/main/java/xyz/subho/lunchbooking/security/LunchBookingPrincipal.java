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
