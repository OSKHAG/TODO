package serviceTest;

import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.TODO.repository.TodoRepository;
import org.TODO.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        todoService = new TodoService(todoRepository);
    }

    @Test
    public void createTodoTest() {
        String userName = "Oskar";
        String description = "Wash";
        User user = new User(userName);
        Todo todo = new Todo(description, user);

        todoService.createTodo(todo);
        verify(todoRepository).create(todo);
    }

    @Test
    public void getTodosByUserIdTest() {
        String userName = "Oskar";
        String description = "Wash";
        User user = new User(userName);
        Todo todo = new Todo(description, user);

        todoService.getTodosByUserId(todo.getUserId());

        verify(todoRepository).findByUserId(todo.getUserId());
    }

    @Test
    public void getAllTodosTest() {
        todoService.getAllTodos();
        verify(todoRepository).findAll();
    }

    @Test
    public void updateTodoTest() {
        String userName = "Oskar";
        String description = "Wash";
        String newDescription = "Run";
        User user = new User(userName);
        Todo todo = new Todo(description, user);
        Todo updatedTodo = new Todo(newDescription, user);

        todoService.updateTodo(updatedTodo, todo.getTodoId());

        verify(todoRepository).update(updatedTodo, todo.getTodoId());
    }

    @Test
    public void deleteTodoByIdTest() {
        String todoID = "abc123";

        todoService.deleteTodoById(todoID);

        verify(todoRepository).delete(todoID);
    }
}
