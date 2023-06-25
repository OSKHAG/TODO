package org.TODO.utility;

import org.TODO.domain.User;


public class UserFactory {
    public User createUser(String name) {
        return new User(name);
    }
}
