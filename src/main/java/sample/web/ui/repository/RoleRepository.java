package sample.web.ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.web.ui.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
