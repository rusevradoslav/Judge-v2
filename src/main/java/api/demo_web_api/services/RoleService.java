package api.demo_web_api.services;

import api.demo_web_api.models.entities.Role;
import api.demo_web_api.models.service.RoleServiceModel;

public interface RoleService {

    void seedRoleInDB();

    RoleServiceModel findByAuthority(String role);
}
