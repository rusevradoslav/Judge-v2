package api.demo_web_api.repositories;

import api.demo_web_api.models.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {
  Exercise findFirstByName(String name);
}
