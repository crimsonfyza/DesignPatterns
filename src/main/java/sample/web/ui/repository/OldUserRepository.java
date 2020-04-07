package sample.web.ui.repository;


import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.UserAccount;

public interface OldUserRepository extends CrudRepository<UserAccount, Long> {
}
