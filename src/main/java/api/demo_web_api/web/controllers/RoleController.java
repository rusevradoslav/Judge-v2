package api.demo_web_api.web.controllers;

import api.demo_web_api.models.binding.RoleAddBindingModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.services.RoleService;
import api.demo_web_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

   private final RoleService roleService;
   private final ModelMapper modelMapper;
   private final UserService userService;

    public RoleController(RoleService roleService, ModelMapper modelMapper, UserService userService) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView addRole(ModelAndView modelAndView,HttpSession httpSession){

        UserServiceModel user = (UserServiceModel) httpSession.getAttribute("user");
        System.out.println();
        if (user.getRole().getName().equals("ADMIN")) {
            String name = user.getUsername();
            List<String> usernamesCollection = this.userService.getAllUserNames(name);
            System.out.println();
            modelAndView.addObject("usernamesCollection", usernamesCollection);
            modelAndView.setViewName("role-add");
        }else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
    @PostMapping("/add")
    public ModelAndView addRoleConfirm(@Valid @ModelAttribute("roleAddBindingModel") RoleAddBindingModel roleAddBindingModel,
                                ModelAndView modelAndView,HttpSession httpSession){

        UserServiceModel user = (UserServiceModel) httpSession.getAttribute("user");
        UserServiceModel userServiceModel = this.userService.finByName(user.getUsername());
        String userRole = userServiceModel.getRole().getName();
        System.out.println();
        if (userRole.equals("ADMIN")) {
            this.userService.changeRole(roleAddBindingModel.getUsername(), roleAddBindingModel.getRole());
            System.out.println();
            modelAndView.setViewName("redirect:/");
        }else {
            modelAndView.setViewName("redirect:/roles/add");
        }
        return modelAndView;
    }



}
