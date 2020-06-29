package api.demo_web_api.services;

import api.demo_web_api.models.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    ExerciseServiceModel findExByName(String name);

    ExerciseServiceModel addExercise(ExerciseServiceModel map);
   List<String> getAllActiveExercise();

  /*  List<String> getAllInactiveExercise();*/

    List<String> getAllExercises();

}
