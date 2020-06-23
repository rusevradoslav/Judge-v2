package api.demo_web_api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercise")
public class Exercise extends BaseEntity {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public Exercise() {
    }

    @Column(nullable = false,unique = true)
    public String getName() {
        return name;
    }
    @Column(nullable = false)
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    @Column(nullable = false)
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
