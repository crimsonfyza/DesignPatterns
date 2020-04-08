package sample.web.ui.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sample.web.ui.domain.Classroom;
import sample.web.ui.domain.ClassroomObject;
import sample.web.ui.repository.ClassroomRepository;
import sample.web.ui.service.ClassroomService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/classrooms")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("!isAuthenticated()")
        public class ClassroomRESTController {
            private final ClassroomService classroomService;

            @GetMapping
            public ResponseEntity<List<ClassroomObject>> findAll() {
                return ResponseEntity.ok(classroomService.findAll());
            }

            @PostMapping
            public ResponseEntity create(@Valid @RequestBody ClassroomObject classroomObject) {
                return ResponseEntity.ok(classroomService.save(classroomObject));
            }

            @GetMapping("/{id}")
            public ResponseEntity<ClassroomObject> findById(@PathVariable Long id) {
                Optional<ClassroomObject> stock = classroomService.findById(id);
                if (!stock.isPresent()) {
                    log.error("Id " + id + " is not existed");
                    ResponseEntity.badRequest().build();
                }

                return ResponseEntity.ok(stock.get());
            }

            @PutMapping("/{id}")
            public ResponseEntity<ClassroomObject> update(@PathVariable Long id, @Valid @RequestBody ClassroomObject classroomObject) {
                if (!classroomService.findById(id).isPresent()) {
                    log.error("Id " + id + " is not existed");
                    ResponseEntity.badRequest().build();
                }

                return ResponseEntity.ok(classroomService.save(classroomObject));
            }

            @DeleteMapping("/{id}")
            public ResponseEntity delete(@PathVariable Long id) {
                if (!classroomService.findById(id).isPresent()) {
                    log.error("Id " + id + " is not existed");
                    ResponseEntity.badRequest().build();
                }

                classroomService.deleteById(id);

                return ResponseEntity.ok().build();
            }
        }
