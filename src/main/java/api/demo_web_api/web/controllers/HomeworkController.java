package api.demo_web_api.web.controllers;


import api.demo_web_api.models.binding.HomeworkAddBindingModel;
import api.demo_web_api.models.service.ExerciseServiceModel;
import api.demo_web_api.services.ExerciseService;
import api.demo_web_api.services.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;


    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/add")
    public ModelAndView add(
            @Valid @ModelAttribute("homeworkAddBindingModel") HomeworkAddBindingModel homeworkAddBindingModel,
            ModelAndView modelAndView,
            HttpSession httpSession){
        List<String> activeExercises =this.exerciseService.getAllActiveExercise();

        List<String> inactiveExercises =this.exerciseService.getAllInactiveExercise();
        modelAndView.addObject("homeworkAddBindingModel",homeworkAddBindingModel);
        modelAndView.addObject("activeExercises",activeExercises);
        modelAndView.addObject("inactiveExercises",inactiveExercises);
        modelAndView.setViewName("homework-add");
        System.out.println();
        return modelAndView;
    }

}
