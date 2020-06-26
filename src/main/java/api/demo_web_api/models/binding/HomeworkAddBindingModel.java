package api.demo_web_api.models.binding;

import api.demo_web_api.models.service.ExerciseServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@NoArgsConstructor
public class HomeworkAddBindingModel {

    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+" ,message = "Enter git address following this pattern")
    private String gitAddress;
    private String exercise;









}
