package domainTest;

import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TodoTest {

    private User user;
    private Todo todo;
    private final String description = "Wash Dishes";
    private final String userName = "Oskar Hagman";

    @BeforeEach
    public void setUp(){
        user = new User(userName);
        todo = new Todo(description, user);
    }
    @Test
    public void getTodoIdTest() {
        User user = mock(User.class);
        Todo todo = new Todo("Some description", user);
        String expectedId = "1234567890";
        todo.setTodoId(expectedId);
        String actualId = todo.getTodoId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void getTodoDescriptionTest(){
        assertEquals(description, todo.getDescription());
    }
    @Test
    public void getTodoUserIdTest(){
        assertEquals(user.getId(), todo.getUserId());
    }
    @Test
    public void getTodoUserNameTest(){
        assertEquals(userName, todo.getUserName());
    }
    @Test
    public void isDoneTest(){
        assertFalse(todo.isDone());
    }
    @Test
    public void setDoneTest(){
        todo.setDone(true);
        assertTrue(todo.isDone());
    }
    @Test
    public void setDescriptionTest(){
        String newDescription = "Change oil";
        todo.setDescription(newDescription);
        assertEquals(newDescription, todo.getDescription());
    }
    @Test
    public void setUserTest(){
        User newUser = new User("Ben Weinman");
        todo.setUser(newUser);
        assertEquals(newUser.getId(), todo.getUserId());
        assertEquals(newUser.getName(), todo.getUserName());
    }
    @Test
    public void convertToDocumentTest(){
        Document doc = todo.convertToDocument();
        assertEquals(todo.getTodoId(), doc.get("TODO ID"));
        assertEquals(description, doc.get("Description"));
        assertEquals(false, doc.get("Status"));
        assertEquals(user.getId(), doc.get("Assigned User ID"));
        assertEquals(userName, doc.get("Assigned User"));

    }
}
