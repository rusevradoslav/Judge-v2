package api.demo_web_api.repositories;

import api.demo_web_api.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,String> {
}
