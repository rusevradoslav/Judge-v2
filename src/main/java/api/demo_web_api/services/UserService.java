package api.demo_web_api.services;


import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.models.view.UserProfileViewModel;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel finByName(String username);

    UserServiceModel findUniqueUser(String username, String email, String git);


    List<String> getAllUserNames(String name);

    void changeRole(String username, String role);

    UserProfileViewModel findById(String id);

    long findAllUsersCount();

    List<String> getTopThreeStudents();
}
