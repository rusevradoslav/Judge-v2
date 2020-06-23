package api.demo_web_api.models.service;

import api.demo_web_api.models.entities.UserServiceModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class HomeworkServiceModel extends BaseServiceModel{

    private LocalDateTime addedOn;
    private String gitAddress;
    private UserServiceModel author;
    private ExerciseServiceModel exercise;

    public HomeworkServiceModel() {
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }
    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+" ,message = "Enter git address following this pattern")
    public String getGitAddress() {
        return gitAddress;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public ExerciseServiceModel getExercise() {
        return exercise;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public void setExercise(ExerciseServiceModel exercise) {
        this.exercise = exercise;
    }
}
