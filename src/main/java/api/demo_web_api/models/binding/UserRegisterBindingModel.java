package api.demo_web_api.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String git;

    public UserRegisterBindingModel() {
    }


    @Length(min = 2, message = "Username length must be minimum two characters!")
    public String getUsername() {
        return username;
    }


    @Length(min = 3, max = 10, message = "Password must be between 3 and 10 characters")
    public String getPassword() {
        return password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }



    @Email(message = "Enter valid email address")
    public String getEmail() {
        return email;
    }


    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+" ,message = "Enter git address following this pattern: " +
            "https://github.com/{usrname}}/{repoName}")
    public String getGit() {
        return git;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGit(String git) {
        this.git = git;
    }
}