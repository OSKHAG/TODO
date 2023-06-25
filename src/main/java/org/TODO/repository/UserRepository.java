package org.TODO.repository;


import org.TODO.domain.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    List<User> findAll();
    User update(User user, String queryID);
    void delete(String userId);
}
