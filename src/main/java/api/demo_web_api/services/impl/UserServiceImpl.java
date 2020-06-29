package api.demo_web_api.services.impl;


import api.demo_web_api.models.entities.Role;
import api.demo_web_api.models.entities.User;
import api.demo_web_api.models.service.RoleServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.models.view.UserProfileViewModel;
import api.demo_web_api.repositories.UserRepository;
import api.demo_web_api.services.RoleService;
import api.demo_web_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;


    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        RoleServiceModel roleService = this.roleService.findByAuthority(this.userRepository.count() == 0 ? "ADMIN" : "USER");
        userServiceModel.setRole(roleService);

        User user = this.modelMapper.map(userServiceModel, User.class);

        return this.modelMapper.map(userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel finByName(String username) {

        return this.userRepository.findFirstByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public UserServiceModel findUniqueUser(String username, String email, String git) {
        UserServiceModel userServiceModel = null;
        if (userRepository.findFirstByUsername(username).orElse(null) != null) {
            userServiceModel = this.modelMapper.map(userRepository.findFirstByUsername(username).orElse(null), UserServiceModel.class);
        }
        if (userRepository.findFirstByEmail(email).orElse(null) != null) {

            User user = userRepository.findFirstByEmail(email).orElse(null);
            System.out.println();
            userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        }
        if (userRepository.findFirstByGit(git).orElse(null) != null) {
            userServiceModel = this.modelMapper.map(userRepository.findFirstByGit(git).orElse(null), UserServiceModel.class);
        }

        return userServiceModel;
    }

    @Override
    public List<String> getAllUserNames(String name) {
        List<String> usernames =
                this.userRepository
                        .findAll()
                        .stream()
                        .filter(user -> !user.getUsername().equals(name))
                        .map(User::getUsername)
                        .collect(Collectors.toList());
        return usernames;
    }

    @Override
    public void changeRole(String username, String role) {

        User user = this.userRepository.findFirstByUsername(username).orElse(null);
        Role newRole = this.modelMapper.map(this.roleService.findByAuthority(role), Role.class);

        if (!user.getRole().getName().equals(role)) {
            user.setRole(newRole);
            this.userRepository.saveAndFlush(user);
        }

    }

    @Override
    public UserProfileViewModel findById(String id) {

        return this.userRepository.findById(id).map(user -> modelMapper.map(user, UserProfileViewModel.class)).orElse(null);
    }

    @Override
    public long findAllUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> getTopThreeStudents() {
        return this.userRepository.
                findAll()
                .stream()
                .sorted((a,b) -> b.getHomeworks().size()-a.getHomeworks().size())
                .map(user -> user.getUsername())
                .limit(3)
                .collect(Collectors.toList());

    }
}
