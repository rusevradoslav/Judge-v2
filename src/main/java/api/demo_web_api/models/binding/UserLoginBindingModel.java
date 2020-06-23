package api.demo_web_api.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Length(min = 2, message = "Username length must be more than 2 characters!")
    public String getUsername() {
        return username;
    }

    @Length(min = 3, max = 10, message = "Password length must be between 3 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
