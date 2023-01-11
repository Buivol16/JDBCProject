package ua.denis.jdbcproject.loginapp.session.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import static ua.denis.jdbcproject.loginapp.utils.DatabaseConstUtils.SessionColumnConsts.MINUTES_PLUS;
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Session {
  @Id
  @Column(name = "id")
  private Long id;
  @Column(name = "session_key")
  private UUID sessionKey;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "expired_at")
  private Timestamp expiredAt;

  public Session(Long userId) {
    this.sessionKey = UUID.randomUUID();
    this.userId = userId;
    this.expiredAt = Timestamp.from(
        LocalDateTime.now().plusMinutes(MINUTES_PLUS).toInstant(ZoneOffset.UTC));
  }

}
