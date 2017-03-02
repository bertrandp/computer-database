package fr.ebiz.cdb.model;

/**
 * Created by bpestre on 14/02/17.
 */
public class Company {

    private int id;
    private String name;

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
