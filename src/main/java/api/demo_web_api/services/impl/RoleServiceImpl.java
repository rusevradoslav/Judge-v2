package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Role;
import api.demo_web_api.models.service.RoleServiceModel;
import api.demo_web_api.repositories.RoleRepository;
import api.demo_web_api.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostConstruct
    public void seedRoleInDB() {

        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setName("ADMIN");
            Role user = new Role();
            user.setName("USER");
            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(user);
        }


    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.roleRepository
                .findFirstByName(authority)
                .map(role -> this.modelMapper.map(role, RoleServiceModel.class)).orElse(null);
    }
}
