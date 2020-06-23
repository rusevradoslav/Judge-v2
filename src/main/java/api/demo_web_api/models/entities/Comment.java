package api.demo_web_api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    private int score;
    private String textContent;
    private UserServiceModel author;
    private Homework homework;

    public Comment() {
    }

    @Column(nullable = false)
    public int getScore() {
        return score;
    }

    @Column(columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    @ManyToOne
    public UserServiceModel getAuthor() {
        return author;
    }

    @ManyToOne
    public Homework getHomework() {
        return homework;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
