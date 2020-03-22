package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
}
