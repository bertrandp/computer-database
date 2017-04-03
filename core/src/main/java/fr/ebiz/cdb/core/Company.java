package fr.ebiz.cdb.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by bpestre on 14/02/17.
 */
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column
    private String name;

    /**
     * Empty constructor.
     */
    public Company() {
    }

    /**
     * Company constructor.
     *
     * @param name the name of the company
     */
    public Company(String name) {
        this.name = name;
    }

    /**
     * Company constructor.
     *
     * @param id   the id of the company
     * @param name the name of the company
     */
    public Company(int id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Company constructor.
     *
     * @param id the id of the company
     */
    public Company(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
