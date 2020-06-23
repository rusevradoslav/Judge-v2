package api.demo_web_api.models.entities;

import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserServiceModel extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;

    public UserServiceModel() {

    }


    @Column(nullable = false, unique = true)
    @Length(min = 2, message = "Username length must be minimum two characters!")
    public String getUsername() {
        return username;
    }


    @Column(nullable = false)
    @Length(min = 3, message = "Username length must be minimum three characters!")
    public String getPassword() {
        return password;
    }

    @Column(nullable = false, unique = true)
    @Length(min = 2, message = "Username length must be minimum two characters!")
    public String getEmail() {
        return email;
    }

    @Column(name = "github_address", nullable = false, unique = true)
    public String getGit() {
        return git;
    }


    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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


//    @Override
//    @Transient
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    @Transient
//    public boolean isEnabled() {
//        return true;
//    }
}
