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
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @Column(name = "id")
  private Long id;

  @Basic
  @CreatedDate
  private Long createdAt;

  @Basic
  @CreatedBy
  private String createdBy;

  @Basic
  @LastModifiedDate
  private Long updatedAt;

  @Basic
  @LastModifiedBy
  private String updatedBy;
  
  @Basic private Long version = 1L;
  
  @Basic private Long deletedAt;
  
  @Basic private String deletedBy;

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
