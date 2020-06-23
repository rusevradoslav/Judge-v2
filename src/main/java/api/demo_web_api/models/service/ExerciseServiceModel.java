package api.demo_web_api.models.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class ExerciseServiceModel extends BaseServiceModel {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public ExerciseServiceModel() {
    }

    @Length(min = 2, message = "Exercise name length must be more than 2 characters")
    public String getName() {
        return name;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date must be in the past!")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date must be in the future!")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
