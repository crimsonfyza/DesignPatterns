package sample.web.ui.repository;


import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.ExamObject;
import sample.web.ui.domain.NotificationObject;

public interface ExamRepository extends CrudRepository<ExamObject, Long> {
}
