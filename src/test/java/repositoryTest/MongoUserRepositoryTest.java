package repositoryTest;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.TODO.domain.User;
import org.TODO.repository.MongoUserRepository;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MongoUserRepositoryTest {


    @Mock
    private MongoCollection<Document> collection;

    private MongoUserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRepository = new MongoUserRepository(collection);
    }

    @Test
    public void createTest() {
        String userName = "Oskar";
        User user = new User(userName);

        User createdUser = userRepository.create(user);

        verify(collection).insertOne(user.convertToDocument());
        assertEquals(user, createdUser);
    }

    @Test
    public void findAllTest() {
        String username1 = "Oskar";
        String userName2 = "Fred";
        Document doc1 = new User(username1).convertToDocument();
        Document doc2 = new User(userName2).convertToDocument();

        FindIterable<Document> findIterable = mock(FindIterable.class);
        MongoCursor<Document> cursor = mock(MongoCursor.class);

        when(collection.find()).thenReturn(findIterable);
        when(findIterable.iterator()).thenReturn(cursor);
        when(cursor.hasNext()).thenReturn(true, true, false);
        when(cursor.next()).thenReturn(doc1, doc2);

        List<User> users = userRepository.findAll();

        assertEquals(2, users.size());
        assertEquals(username1, users.get(0).getName());
        assertEquals(userName2, users.get(1).getName());

    }

    @Test
    public void updateTest() {
        String originalName = "Oskar";
        String updatedName = "Fred";

        User originalUser = new User(originalName);
        User updatedUser = new User(updatedName);

        updatedUser = userRepository.update(updatedUser, originalUser.getId());

        assertNotEquals(originalUser.getId(), updatedUser.getId());
        verify(collection).replaceOne(new Document("User ID", originalUser.getId()), new Document(updatedUser.convertToDocument()));
    }

    @Test
    public void deleteTest(){
        String userId = "abc123";

        Document query = new Document("User ID", userId);

        userRepository.delete(userId);

        verify(collection).deleteOne(query);
    }

}
