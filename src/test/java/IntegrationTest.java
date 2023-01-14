import org.junit.jupiter.api.Test;
import ua.denis.jdbcproject.server.db.model.User;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

public class IntegrationTest {
    private User testUser = User.builder().password("admin").username("RootUser").build();
    private UserRepository userRepository = UserRepository.getInstance();
    @Test
    void shouldCreateAndFindUser(){
        var result = false;
        userRepository.saveEntity(testUser);
        testUser = userRepository.findById(testUser);
        if (testUser != null) result = true;
        userRepository.deleteEntity(testUser);
        assert(result);
    }

}
