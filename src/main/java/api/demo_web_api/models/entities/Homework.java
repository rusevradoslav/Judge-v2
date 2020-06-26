package api.demo_web_api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "homeworks")
public class Homework extends BaseEntity {
    private LocalDateTime addedOn;
    private String gitAddress;
    private User author;
    private Exercise exercise;

    public Homework() {
    }

    @Column(nullable = false, unique = true)
    public String getGitAddress() {
        return gitAddress;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    @ManyToOne
    public Exercise getExercise() {
        return exercise;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
