package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Exercise;
import api.demo_web_api.models.service.ExerciseServiceModel;
import api.demo_web_api.repositories.ExerciseRepository;
import api.demo_web_api.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;



    @Override
    public ExerciseServiceModel findExByName(String name) {

        return modelMapper.map(this.exerciseRepository.findFirstByName(name), ExerciseServiceModel.class);

    }

    @Override
    public ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel) {
        System.out.println();
        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);

        Exercise exercise1 = this.exerciseRepository.findFirstByName(exercise.getName());

        if (exercise1 != null) {
            return null;
        }
        System.out.println();
        return modelMapper.map(this.exerciseRepository.saveAndFlush(exercise), ExerciseServiceModel.class);

    }


    public List<String> getAllActiveExercise() {


        List<String> activeExerciseNames = new ArrayList<>();
         for (Exercise exercise : this.exerciseRepository.findAll()) {
             LocalDateTime localDateTime = LocalDateTime.now();
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
             LocalDateTime formatDateTime = LocalDateTime.parse(localDateTime.format(formatter));
            if ((formatDateTime.compareTo(exercise.getDueDate())) != 1) {
               ExerciseServiceModel exerciseServiceModel = this.modelMapper.map(exercise,ExerciseServiceModel.class);
                activeExerciseNames.add(exerciseServiceModel.getName());
            }
        }


        return activeExerciseNames    ;
    }

/*
    @Override
    public List<String> getAllInactiveExercise() {

        List<String> inactiveExerciseNames = new ArrayList<>();
        this.exerciseRepository.findAll().forEach(exercise -> {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime formatDateTime = LocalDateTime.parse(localDateTime.format(formatter));
            if ((formatDateTime.compareTo(exercise.getDueDate())) == 1) {
                ExerciseServiceModel exerciseServiceModel = this.modelMapper.map(exercise, ExerciseServiceModel.class);
                inactiveExerciseNames.add(exerciseServiceModel.getName());
            }
        });


        return inactiveExerciseNames    ;
    }
*/


    @Override
    public List<String> getAllExercises() {
        return this.exerciseRepository.findAll().stream().map(Exercise::getName).collect(Collectors.toList());
    }
}
