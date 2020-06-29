package api.demo_web_api.web.controllers;


import api.demo_web_api.models.binding.ExerciseAddBindingModel;
import api.demo_web_api.models.service.ExerciseServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.services.ExerciseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/exercises")
@AllArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;





    @GetMapping("/add")
    public ModelAndView add(
            @Valid @ModelAttribute("exerciseAddBindingModel") ExerciseAddBindingModel exerciseAddBindingModel,
            ModelAndView modelAndView,
            HttpSession httpSession) {

      /*List<ExerciseServiceModel> serviceModels =this.exerciseService.getAllExercise();*/


        UserServiceModel user = (UserServiceModel) httpSession.getAttribute("user");
        if (user.getRole().getName().equals("ADMIN")) {
            modelAndView.addObject("exerciseAddBindingModel",exerciseAddBindingModel);
            modelAndView.setViewName("exercise-add");
        }else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }


    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid @ModelAttribute("exerciseAddBindingModel") ExerciseAddBindingModel exerciseAddBindingModel,
                                   BindingResult bindingResult,
                                   ModelMapper modelMapper,
                                   RedirectAttributes redirectAttributes,
                                   ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            System.out.println();
            redirectAttributes.addFlashAttribute("exerciseBindingAddModel", exerciseAddBindingModel);
            modelAndView.setViewName("exercise-add");
            System.out.println();
        } else {
            ExerciseServiceModel exerciseServiceModel = this.exerciseService
                    .addExercise(modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class));

            if (exerciseServiceModel == null) {
                System.out.println();
                redirectAttributes.addFlashAttribute("isFound", true);
                redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
                modelAndView.setViewName("redirect:/exercises/add");
            } else {
                modelAndView.setViewName("redirect:/");

            }
        }

        return modelAndView;

    }
}
