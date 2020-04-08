package sample.web.ui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.web.ui.domain.Classroom;
import sample.web.ui.domain.ClassroomObject;
import sample.web.ui.repository.ClassroomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassroomService {


        private final ClassroomRepository classroomRepository;

        public List<ClassroomObject> findAll() {
            return (List<ClassroomObject>) classroomRepository.findAll();
        }

        public Optional<ClassroomObject> findById(Long id) {
            return classroomRepository.findById(id);
        }

        public ClassroomObject save(ClassroomObject classroom) {
            return classroomRepository.save(classroom);
        }

        public void deleteById(Long id) {
            classroomRepository.deleteById(id);
        }

}
