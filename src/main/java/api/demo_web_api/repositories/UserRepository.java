package api.demo_web_api.repositories;

import api.demo_web_api.models.entities.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserServiceModel,String> {
    Optional<UserServiceModel> findFirstByUsername(String username);
    Optional<UserServiceModel> findFirstByUsernameAndEmailAndGit(String username, String email, String git);
    Optional<UserServiceModel> findFirstByEmail(String email);
    Optional<UserServiceModel> findFirstByGit(String git);
}
