package sample.web.ui.service;

import sample.web.ui.domain.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

