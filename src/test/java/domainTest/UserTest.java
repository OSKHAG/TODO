package domainTest;

import org.TODO.domain.User;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;
    private final String name = "Oskar Hagman";

    @BeforeEach
    public void setUp() {
        user = new User(name);
    }

    @Test
    public void getIdTest() {
        String expectedId = "1234567890";
        user.setId(expectedId);
        assertEquals(expectedId, user.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals(name, user.getName());
    }

    @Test
    public void setNameTest() {
        String newName = "Bart Simpson";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }

    @Test
    public void convertToDocumentTest() {
        Document doc = user.convertToDocument();
        assertEquals(name, doc.get("User Name"));
        assertEquals(user.getId(), doc.get("User ID"));
    }
}
