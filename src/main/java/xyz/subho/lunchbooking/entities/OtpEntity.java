package xyz.subho.lunchbooking.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity implements Serializable {

  @Id @GeneratedValue Long id;

  @Column(updatable = false, nullable = false)
  Integer otp;

  @Column(updatable = false, nullable = false)
  Long userId;

  LocalDateTime sentAt;

  @Column(nullable = false, updatable = false)
  LocalDateTime issuesAt = LocalDateTime.now();

  LocalDateTime verifiedAt;

  @Column(nullable = false, updatable = false)
  LocalDateTime expiresAt;

  public OtpEntity(@NonNull Long userId) {
    this.userId = userId;
  }

  public boolean isSent() {
    return Objects.nonNull(sentAt);
  }

  public boolean isIssued() {
    return Objects.nonNull(issuesAt);
  }

  public boolean isVerified() {
    return Objects.nonNull(verifiedAt);
  }

  public boolean isExpired() {
    return LocalDateTime.now().isBefore(expiresAt);
  }

  public void send() {
    sentAt = LocalDateTime.now();
  }

  public void issue() {
    issuesAt = LocalDateTime.now();
  }

  public void verify() {
    verifiedAt = LocalDateTime.now();
  }
}
