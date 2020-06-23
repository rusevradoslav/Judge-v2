package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Role;
import api.demo_web_api.models.entities.UserServiceModel;
import api.demo_web_api.models.service.RoleServiceModel;
import api.demo_web_api.repositories.UserRepository;
import api.demo_web_api.services.RoleService;
import api.demo_web_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public api.demo_web_api.models.service.UserServiceModel registerUser(api.demo_web_api.models.service.UserServiceModel userServiceModel) {
        RoleServiceModel roleService = this.roleService.findByAuthority(this.userRepository.count() == 0 ? "ADMIN" : "USER");
        userServiceModel.setRole(roleService);

        UserServiceModel user = this.modelMapper.map(userServiceModel, UserServiceModel.class);

        return modelMapper.map(this.userRepository.saveAndFlush(user), api.demo_web_api.models.service.UserServiceModel.class);
    }

    @Override
    public api.demo_web_api.models.service.UserServiceModel finByName(String username) {

        return this.userRepository.findFirstByUsername(username)
                .map(user -> this.modelMapper.map(user, api.demo_web_api.models.service.UserServiceModel.class)).orElse(null);
    }

    @Override
    public api.demo_web_api.models.service.UserServiceModel findUniqueUser(String username, String email, String git) {
        api.demo_web_api.models.service.UserServiceModel userServiceModel = null;
        if (userRepository.findFirstByUsername(username).orElse(null) != null) {
            userServiceModel = this.modelMapper.map(userRepository.findFirstByUsername(username).orElse(null), api.demo_web_api.models.service.UserServiceModel.class);
        }
        if (userRepository.findFirstByEmail(email).orElse(null) != null) {

            UserServiceModel user = userRepository.findFirstByEmail(email).orElse(null);
            System.out.println();
            userServiceModel = this.modelMapper.map(user, api.demo_web_api.models.service.UserServiceModel.class);
        }
        if (userRepository.findFirstByGit(git).orElse(null) != null) {
            userServiceModel = this.modelMapper.map(userRepository.findFirstByGit(git).orElse(null), api.demo_web_api.models.service.UserServiceModel.class);
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
                        .map(UserServiceModel::getUsername)
                        .collect(Collectors.toList());
        return usernames;
    }

    @Override
    public void changeRole(String username, String role) {

        UserServiceModel user = this.userRepository.findFirstByUsername(username).orElse(null);
        Role newRole = this.modelMapper.map(this.roleService.findByAuthority(role), Role.class);

        if (!user.getRole().getName().equals(role)) {
            user.setRole(newRole);
            this.userRepository.saveAndFlush(user);
        }

    }
}
