package api.demo_web_api.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {
    private String username;
    private String password;
    private String email;
    private String git;
    private RoleServiceModel role;


    public UserServiceModel() {
    }

    @NotNull
    @Length(min = 2, message = "Username length must be minimum two characters!")
    public String getUsername() {
        return username;
    }

    @NotNull
    @Length(min = 3, max = 10, message = "Password must be between 3 and 10 characters")
    public String getPassword() {
        return password;
    }

    @NotNull
    @Email(message = "Enter valid email address")
    public String getEmail() {
        return email;
    }

    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+" ,message = "Enter git address following this pattern")
    public String getGit() {
        return git;
    }


    public RoleServiceModel getRole() {
        return role;
    }

    public void setRole(RoleServiceModel role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGit(String git) {
        this.git = git;
    }


}
