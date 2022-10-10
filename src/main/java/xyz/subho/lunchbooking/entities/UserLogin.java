package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
    name = "users_login",
    indexes = {@Index(columnList = "username")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class UserLogin extends BaseEntity implements UserDetails, Serializable {

  private static final long serialVersionUID = -1484069631072335374L;

  // User Name is the Email ID
  @Column(name = "username", nullable = false, unique = true, updatable = false, length = 128)
  private String username;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private UserMetadata user;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "salt", nullable = false, length = 256)
  private String salt;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean expired = false;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean locked = false;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private boolean credentialExpired = false;

  @Column(columnDefinition = "boolean default true", nullable = false)
  private boolean enabled = true;

  @Column(columnDefinition = "boolean default false", nullable = false)
  private Boolean secured = false;

  @Basic private Long currentLogin;

  @Basic private Long lastLogin;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Roles> roles = new HashSet<>();

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
    Set<Permissions> permission = new HashSet<>();
    this.roles.forEach(role -> permission.addAll(role.getPermissions()));
    return permission;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !expired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !credentialExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
