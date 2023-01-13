import org.junit.jupiter.api.Test;
import ua.denis.jdbcproject.server.db.model.User;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

public class IntegrationTest {
    private String username = "RootUser";
    private String password = "admin";
    private UserRepository userRepository = UserRepository.getInstance();
    @Test
    void shouldCreateAndFindUser(){
        var result = false;
        User user = User.builder().username(username).password(password).build();
        userRepository.saveEntity(user);
        user = userRepository.findByUsername(username);
        if (user != null) result = true;
        userRepository.deleteEntity(user);
        assert(result);
    }
}
