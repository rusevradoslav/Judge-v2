package api.demo_web_api.web.controllers;

import api.demo_web_api.models.binding.UserLoginBindingModel;
import api.demo_web_api.models.binding.UserRegisterBindingModel;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.models.view.UserProfileViewModel;
import api.demo_web_api.services.HomeworkService;
import api.demo_web_api.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final ModelMapper modelMapper;


    @GetMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
        modelAndView.setViewName("/user/login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView,
                                     HttpSession httpSession,
                                     RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            modelAndView.setViewName("redirect:/users/login");
        } else {
            UserServiceModel userServiceModel = this.userService
                    .finByName(userLoginBindingModel.getUsername());


            if (userServiceModel == null || !userServiceModel.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("notFound", true);
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                modelAndView.setViewName("redirect:/users/login");
            } else {
                httpSession.setAttribute("user", userServiceModel);
                httpSession.setAttribute("username", userServiceModel.getUsername());
                httpSession.setAttribute("id", userServiceModel.getId());
                httpSession.setAttribute("role", userServiceModel.getRole().getName());
                modelAndView.setViewName("redirect:/");
            }
        }

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView) {

        modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
        modelAndView.setViewName("/user/register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            modelAndView.setViewName("redirect:/users/register");
            System.out.println();
        } else {
            UserServiceModel user = this.userService.findUniqueUser(userRegisterBindingModel.getUsername()
                    , userRegisterBindingModel.getEmail()
                    , userRegisterBindingModel.getGit());
            if (user == null) {
                UserServiceModel userServiceModel =
                        this.userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
                modelAndView.setViewName("redirect:/users/login");
            } else {
                System.out.println();
                redirectAttributes.addFlashAttribute("isFound", true);
                redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
                modelAndView.setViewName("redirect:/users/register");
            }


        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession httpSession) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");

        return modelAndView;

    }


    @GetMapping("/profile")  /*@GetMapping("/profile/{id}") @PathVariable("id") String id,*/
    public ModelAndView profile(@RequestParam("id") String id, ModelAndView modelAndView) {

        UserProfileViewModel userProfile = this.userService.findById(id);
        String homeworksNames = this.homeworkService.findAllHomeWorksByUserId(id);
        System.out.println();
        modelAndView.addObject("userProfile", userProfile);
        modelAndView.addObject("userHomeworks", homeworksNames);
        modelAndView.setViewName("profile");
        return modelAndView;

    }


}
