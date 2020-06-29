package api.demo_web_api.models.binding;

import api.demo_web_api.models.entities.Homework;
import api.demo_web_api.models.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class CommentAddBindingModel {


    @Min(value = 2,message = "Score must be between 2 and 6 inclusive")
    @Max(value = 6,message = "Score must be between 2 and 6 inclusive")
    private int score;
    @Length(min = 3,message = "Comment text content must be more than 3 characters!")
    private String textContent;

    private String homeworkId;
}
