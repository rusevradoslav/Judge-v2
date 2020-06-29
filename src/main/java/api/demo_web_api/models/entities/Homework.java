package api.demo_web_api.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "homeworks")
public class Homework extends BaseEntity {
    private LocalDateTime addedOn;
    private String gitAddress;
    private User author;
    private Exercise exercise;
    private List<Comment> comments;

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

    @OneToMany(mappedBy = "homework")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
