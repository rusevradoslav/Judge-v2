package api.demo_web_api.web.controllers;


import api.demo_web_api.models.binding.CommentAddBindingModel;
import api.demo_web_api.models.binding.HomeworkAddBindingModel;
import api.demo_web_api.models.service.CommentServiceModel;
import api.demo_web_api.models.service.ExerciseServiceModel;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.models.view.HomeworkViewModel;
import api.demo_web_api.services.CommentService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;
    private final CommentService commentService;


    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService, CommentService commentService) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
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
        return "redirect:/";


    }

    @GetMapping("/check")
    public String check(Model model, HttpSession httpSession, ModelMapper modelMapper) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("commentAddBindingModel")) {

            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }
        UserServiceModel user = (UserServiceModel) httpSession.getAttribute("user");
        HomeworkViewModel homework = modelMapper.map(this.homeworkService.findFirstMinComments(user.getId()), HomeworkViewModel.class);
        System.out.println();
        model.addAttribute("homework", homework);
        return "homework-check";
    }


    @PostMapping("/check")
    public String checkConfirm(@Valid @ModelAttribute("commentAddBindingModel") CommentAddBindingModel commentAddBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               ModelMapper modelMapper,
                               HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
            return "redirect:/homeworks/check";
        }

        CommentServiceModel commentServiceModel = modelMapper.map(commentAddBindingModel, CommentServiceModel.class);
        commentServiceModel.setHomework(this.homeworkService.findById(commentAddBindingModel.getHomeworkId()));
        commentServiceModel.setAuthor((UserServiceModel) httpSession.getAttribute("user"));
        commentService.addComment(commentServiceModel);
        return "redirect:/";
    }
}
