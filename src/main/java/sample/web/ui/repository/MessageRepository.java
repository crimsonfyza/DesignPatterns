package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.MessageObject;

public interface MessageRepository extends CrudRepository<MessageObject, Long> {}
