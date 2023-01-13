import org.junit.jupiter.api.Test;
import ua.denis.jdbcproject.server.db.model.User;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IntegrationTest {
    private String username = "RootUser";
    private String password = "admin";
    @Inject
    private UserRepository userRepository;
    @Test
    void shouldCreateAndFindUser(){
        User user = User.builder().username(username).password(password).build();
        userRepository.saveEntity(user);
        user = userRepository.findByUsername(username);
        assert(user != null);
        userRepository.deleteEntity(user);
    }
}
