package api.demo_web_api.models.service;

import api.demo_web_api.models.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkServiceModel extends BaseServiceModel {


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime addedOn;
    @NotNull
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+\\/.+", message = "Enter git address following this pattern")
    private String gitAddress;
    private UserServiceModel author;
    private ExerciseServiceModel exercise;


}
