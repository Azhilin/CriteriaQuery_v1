package base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azhilin on 31.01.2017.
 */
@NamedNativeQueries(value = @NamedNativeQuery(name = "myQuery", query = "SELECT * FROM banks", resultSetMapping = "myMapping"))
@SqlResultSetMapping(name = "myMapping", entities = @EntityResult(entityClass = Bank.class))
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    int id;
    @Column(name = "name", unique = true)
    String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "bank")
    List<Human> humans = new ArrayList<Human>();

    public Bank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Human> getHumans() {
        return humans;
    }

    public void setHumans(List<Human> humans) {
        this.humans = humans;
    }
}
