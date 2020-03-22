package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long> {
}
