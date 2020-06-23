package api.demo_web_api.models.binding;

import api.demo_web_api.models.service.ExerciseServiceModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HomeworkAddBindingModel {


    private String gitAddress;
    private ExerciseServiceModel exercise;

    public HomeworkAddBindingModel() {
    }

    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+" ,message = "Enter git address following this pattern")
    public String getGitAddress() {
        return gitAddress;
    }

    public ExerciseServiceModel getExercise() {
        return exercise;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public void setExercise(ExerciseServiceModel exercise) {
        this.exercise = exercise;
    }
}
