package repositoryTest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.TODO.repository.MongoTodoRepository;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class MongoTodoRepositoryTest {

    @Mock
    private MongoCollection<Document> collection;

    private MongoTodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoRepository = new MongoTodoRepository(collection);
    }

    @Test
    void createTest() {
        User user = new User("John Doe");
        Todo todo = new Todo("Test Todo", user);

        Todo newTodo = todoRepository.create(todo);
        verify(collection).insertOne(todo.convertToDocument());
        assertEquals(todo, newTodo);
    }

    @Test
    void findByUserIdTest() {
        User user1 = new User("Brent");
        User user2 = new User("Burt");
        Document doc1 = new Todo("Todo 1", user1).convertToDocument();
        Document doc2 = new Todo("Todo 2", user1).convertToDocument();

        String userIdForQuery = user1.getId();
        Document query = new Document("Assigned User ID", userIdForQuery);

        FindIterable<Document> iterable = mock(FindIterable.class);
        MongoCursor<Document> cursor = mock(MongoCursor.class);

        when(collection.find(query)).thenReturn(iterable);
        when(iterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(doc1, doc2);

        List<Todo> todos = todoRepository.findByUserId(userIdForQuery);

        assertEquals(2, todos.size());
        assertEquals("Todo 1", todos.get(0).getDescription());
        assertEquals("Todo 2", todos.get(1).getDescription());
        assertEquals(userIdForQuery, todos.get(0).getUserId());
        assertEquals(userIdForQuery, todos.get(1).getUserId());
    }


    @Test
    void findAllTest() {
        Document doc1 = new Todo("Wash", new User("Brent")).convertToDocument();
        Document doc2 = new Todo("Run", new User("Burt")).convertToDocument();

        FindIterable<Document> findIterable = mock(FindIterable.class);
        MongoCursor<Document> cursor = mock(MongoCursor.class);

        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(doc1, doc2);

        List<Todo> todos = todoRepository.findAll();

        assertEquals(2, todos.size());
        assertEquals("Wash", todos.get(0).getDescription());
        assertEquals("Run", todos.get(1).getDescription());
    }


    @Test
    void updateTest() {
        Todo todo = new Todo("Original todo", new User("Brock"));
        Todo updatedTodo = new Todo("Updated todo", new User(""));

        updatedTodo = todoRepository.update(updatedTodo, todo.getTodoId());

        assertNotEquals(todo.getTodoId(), updatedTodo.getTodoId());
        verify(collection).replaceOne(new Document("TODO ID", todo.getTodoId()), new Document("$set", updatedTodo.convertToDocument()));
    }

    @Test
    void deleteTest() {
        String todoId = "abc123";

        Document query = new Document("TODO ID", todoId);

        todoRepository.delete(todoId);

        verify(collection).deleteOne(query);
    }
}
