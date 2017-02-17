package model;

/**
 * Created by bpestre on 14/02/17.
 */
public class Company {

    private Integer id;
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public Company(int id, String name) {
        this(name);
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
