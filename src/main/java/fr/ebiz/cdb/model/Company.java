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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;

        if (id != company.id) {
            return false;
        }
        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
