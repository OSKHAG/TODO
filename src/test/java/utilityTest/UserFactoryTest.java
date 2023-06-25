package utilityTest;

import org.TODO.domain.User;
import org.TODO.utility.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFactoryTest {

    private UserFactory userFactory;
    private User user;
    private final String userName = "Oskar Hagman";

    @BeforeEach
    public void setUp() {
        userFactory = new UserFactory();
        user = new User(userName);
    }
    @Test
    public void createTodoTest() {
        user = userFactory.createUser(userName);
        assertEquals(userName, user.getName());
    }
}
