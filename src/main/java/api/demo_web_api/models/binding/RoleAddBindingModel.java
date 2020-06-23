package api.demo_web_api.models.binding;

public class RoleAddBindingModel {
    private String username;
    private String role;

    public RoleAddBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
