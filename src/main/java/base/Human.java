package base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by azhilin on 02.02.2017.
 */
@NamedQueries(value = @NamedQuery(name = "myNameQuery", query = "SELECT p FROM base.Human p"))
@Entity
@Table(name = "humans")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Human {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    int id;
    @Column(name = "name", unique = true)
    String name;
    @Column(name = "surname")
    String surname;
    @ManyToOne
    Bank bank;

    public Human() {
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
