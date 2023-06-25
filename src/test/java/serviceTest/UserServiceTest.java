package serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.TODO.domain.User;
import org.TODO.repository.UserRepository;
import org.TODO.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void createUserTest() {
        User user = new User("Oskar");

        when(userRepository.create(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        verify(userRepository).create(user);
        assertEquals(user, createdUser);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(new User("Oskar"));
        users.add(new User("Fred"));

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        verify(userRepository).findAll();
        assertEquals(users, allUsers);
    }

    @Test
    public void updateUserTest() {
        String userName = "Oskar";
        String newUserName = "Fred";
        User user = new User(userName);
        User newUser = new User(newUserName);

        userService.updateUser(newUser, user.getId());
        verify(userRepository).update(newUser, user.getId());
    }

    @Test
    public void deleteUserByIdTest() {
        String id = "abc123";

        userService.deleteUserById(id);

        verify(userRepository).delete(id);
    }

}
