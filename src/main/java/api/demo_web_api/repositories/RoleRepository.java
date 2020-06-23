package api.demo_web_api.repositories;

import api.demo_web_api.models.entities.Role;
import api.demo_web_api.models.service.RoleServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findFirstByName (String authority);
}
