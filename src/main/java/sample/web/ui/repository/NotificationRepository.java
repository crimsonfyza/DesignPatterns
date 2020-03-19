package sample.web.ui.repository;


import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.NotificationObject;
import sample.web.ui.domain.UserAccount;

public interface NotificationRepository extends CrudRepository<NotificationObject, Long> {
}
