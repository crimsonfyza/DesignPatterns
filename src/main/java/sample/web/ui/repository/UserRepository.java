package sample.web.ui.repository;


import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.User;
import sample.web.ui.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author  Mark van Dalen
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
