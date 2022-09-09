package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(
    name = "users",
    indexes = {@Index(columnList = "username")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@EqualsAndHashCode(callSuper = true)
public class UserLogin extends BaseEntity implements UserDetails, Serializable {

  private static final long serialVersionUID = -1484069631072335374L;

// username is the email ID
  @Column(name = "username", nullable = false, unique = true, updatable = false, length = 100)
  private String username;

  @Column(name = "password", nullable = false, length = 130)
  private String password;
  
  @Column(name = "salt", nullable = false)
  private String salt;

  @Column(name = "locked", columnDefinition = "boolean default false", nullable = false)
  private boolean locked = false;
  
  @Column(name = "secured")
  private Boolean secured = false;

  @Basic private Long currentLoginDt;
  
  @Basic private Long lastLoginDt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Roles> roles = new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
