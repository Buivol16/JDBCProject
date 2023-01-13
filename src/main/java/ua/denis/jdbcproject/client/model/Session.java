package ua.denis.jdbcproject.client.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Session {
    private Long id;
    private UUID sessionKey;
    private Long userId;
    private Timestamp expiredAt;


}
