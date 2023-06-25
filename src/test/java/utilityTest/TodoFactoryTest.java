package utilityTest;

import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.TODO.utility.TodoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoFactoryTest {

    private TodoFactory todoFactory;
    private User user;
    private final String userName = "Oskar Hagman";

    @BeforeEach
    public void setUp() {
        todoFactory = new TodoFactory();
        user = new User(userName);
    }

    @Test
    public void createTodoTest() {
        String description = "Wash Dishes";
        Todo todo = todoFactory.createTodo(description, user);
        assertEquals(description, todo.getDescription());
        assertEquals(user.getId(), todo.getUserId());
        assertEquals(userName, todo.getUserName());
    }
}
