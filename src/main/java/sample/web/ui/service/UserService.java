package sample.web.ui.service;

import sample.web.ui.domain.User;

/**
 *
 * @author  Mark van Dalen
 *
 */

public interface UserService {
    void save(User user);

    User findByUsername(String username);

}

