package api.demo_web_api.web.controllers;


import api.demo_web_api.models.binding.HomeworkAddBindingModel;
import api.demo_web_api.models.service.ExerciseServiceModel;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.services.ExerciseService;
import api.demo_web_api.services.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
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
    public String add(Model model, HttpSession httpSession) {


        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("homeworkAddBindingModel")) {

            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
        }
        List<String> exercises = this.exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("homeworkAddBindingModel") HomeworkAddBindingModel homeworkAddBindingModel
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , ModelMapper modelMapper, HttpSession httpSession) {

        ExerciseServiceModel exerciseServiceModel = this.exerciseService.findExByName(homeworkAddBindingModel.getExercise());
        boolean notActive = exerciseServiceModel.getDueDate().isBefore(LocalDateTime.now());
        if (bindingResult.hasErrors() || notActive) {
            redirectAttributes.addFlashAttribute("notActive", true);
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
            return "redirect:/homeworks/add";
        }

        HomeworkServiceModel homeworkServiceModel = new HomeworkServiceModel();
        homeworkServiceModel.setExercise(exerciseServiceModel);
        homeworkServiceModel.setGitAddress(homeworkAddBindingModel.getGitAddress());
        homeworkServiceModel.setAddedOn(LocalDateTime.now());
        homeworkServiceModel.setAuthor((UserServiceModel) httpSession.getAttribute("user"));

        homeworkService.addHomework(homeworkServiceModel);

        return"redirect:/";
}

}
