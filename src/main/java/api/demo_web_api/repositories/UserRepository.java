package api.demo_web_api.repositories;

import api.demo_web_api.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findFirstByUsername(String username);
    Optional<User> findFirstByUsernameAndEmailAndGit(String username, String email, String git);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByGit(String git);




}
