package api.demo_web_api.web.controllers;


import api.demo_web_api.services.CommentService;
import api.demo_web_api.services.ExerciseService;
import api.demo_web_api.services.HomeworkService;
import api.demo_web_api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
public class HomeController {
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final ExerciseService exerciseService;


    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("totalUsersCount", this.userService.findAllUsersCount());
        model.addAttribute("avgGrade", this.commentService.getAvgScoreOfGrade());
        model.addAttribute("scoreMap", this.commentService.getScoreMap());
        model.addAttribute("topThreeStudents",userService.getTopThreeStudents());
        model.addAttribute("activeExercise", exerciseService.getAllActiveExercise());
        return "home";
    }


}
