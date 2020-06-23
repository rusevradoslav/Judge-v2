package api.demo_web_api.models.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity  {
   private String name;

    public Role() {
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String authority) {
        this.name = authority;
    }
}
