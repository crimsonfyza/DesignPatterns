package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.ClassroomObject;

public interface ClassroomRepository extends CrudRepository<ClassroomObject, Long> {}
