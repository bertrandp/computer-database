package fr.ebiz.cdb.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by ebiz on 14/02/17.
 */
public class Computer implements Serializable {

    private int id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /**
     * Computer empty constructor.
     */
    public Computer() {
    }

    /**
     * Computer constructor.
     *
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyId    the id of the company of the computer
     */
    public Computer(String name, LocalDate introduced, LocalDate discontinued, Company companyId) {
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = companyId;
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

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Computer computer = (Computer) o;

        if (id != computer.id) {
            return false;
        }
        if (!name.equals(computer.name)) {
            return false;
        }
        if (introduced != null ? !introduced.equals(computer.introduced) : computer.introduced != null) {
            return false;
        }
        if (discontinued != null ? !discontinued.equals(computer.discontinued) : computer.discontinued != null) {
            return false;
        }
        return company != null ? company.equals(computer.company) : computer.company == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced=" + introduced +
                ", discontinued=" + discontinued +
                ", company=" + company +
                '}';
    }

    /**
     * Compare the given LocalDate to introduced date.
     *
     * @param date the date to compare
     * @return true if the date is greater the introduced date
     */
    public boolean isGreaterThanIntroduced(LocalDate date) {
        return introduced == null || date.isAfter(introduced);
    }
}
