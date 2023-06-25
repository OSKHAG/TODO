package utilityTest;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.TODO.utility.InputHandler;
import org.TODO.utility.MongoDbConnection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.bson.assertions.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import static com.mongodb.assertions.Assertions.assertFalse;

public class MongoDbConnectionTest {

    private MongoDbConnection connection;
    private InputHandler input;


    @BeforeEach
    public void setUp(){
        input = mock(InputHandler.class);
        connection = new MongoDbConnection(input);
    }

    @Test
    public void loginCorrectPasswordTest(){
        when(input.getStringInput()).thenReturn("user1");
        connection.login();
        assertTrue(connection.testConnection(5));
    }
    @Test
    public void loginIncorrectPasswordTest(){
        when(input.getStringInput())
                .thenReturn("1")
                .thenReturn("user1");

        connection.login();
        assertTrue(connection.testConnection(5));
    }

    @Test
    public void testConnectionTest(){
        assertFalse(connection.testConnection(5));
    }
    @Test
    public void getDatabaseTest(){
        when(input.getStringInput()).thenReturn("user1");
        connection.login();
        MongoDatabase database = connection.getDatabase();
        MongoIterable<String> collectionNames = database.listCollectionNames();
        assertNotNull(collectionNames.iterator().hasNext());
    }
    @ParameterizedTest
    @ValueSource(strings = {"TODO" , "DONE", "USER"})
    public void getTodoCollectionTest(String type){
        when(input.getStringInput()).thenReturn("user1");
        connection.login();
        List<Document> documents = connection.getCollection(type);
        assertNotNull(documents);
    }

}
