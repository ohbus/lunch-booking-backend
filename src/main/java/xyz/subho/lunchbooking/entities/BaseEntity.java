package xyz.subho.lunchbooking.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

  @Basic private Boolean deleted;

  @Basic private Long createdAt;

  @Basic private String createdBy;

  @Basic private Long updatedAt;

  @Basic private String updatedBy;

  @PrePersist
  public void onCreate() {
    this.createdAt = System.currentTimeMillis();
    this.createdBy = this.getCurrentUser();
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = System.currentTimeMillis();
    this.updatedBy = this.getCurrentUser();
  }

  private String getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (!(authentication instanceof AnonymousAuthenticationToken))
        ? authentication.getName()
        : null;
  }

  public LocalDateTime getCreatedTime() {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.createdAt), ZoneId.systemDefault());
  }

  public LocalDateTime getUpdatedTime() {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.updatedAt), ZoneId.systemDefault());
  }
}
