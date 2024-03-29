package ua.denis.jdbcproject.loginapp.session.model;

import ua.denis.jdbcproject.loginapp.utils.ConstUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import static ua.denis.jdbcproject.loginapp.utils.DatabaseConstUtils.SessionColumnConsts.*;

public class Session {
  private UUID sessionKey;
  private Long userId;
  private String expiredAt;

  public Session(Long userId) {
    this.sessionKey = UUID.randomUUID();
    this.userId = userId;
    this.expiredAt =
        LocalDateTime.now()
            .plusMinutes(MINUTES_PLUS)
            .format(DateTimeFormatter.ofPattern(ConstUtils.TIME_PATTERN));
  }

  public String getSessionKey() {
    return sessionKey.toString();
  }

  public Long getUserId() {
    return userId;
  }

  public String getExpiredAt() {
    return expiredAt;
  }
}
