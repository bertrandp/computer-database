package main.java.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by ebiz on 14/02/17.
 */
public class Computer {

    private int id;
    private String name;
    private Timestamp introduced;
    private Timestamp discontinued;
    private int companyId;

    public Computer() {
    }

    public Computer(String name, Timestamp introduced, Timestamp discontinued, int companyId) {
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
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

    public Timestamp getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Timestamp introduced) {
        this.introduced = introduced;
    }

    public Timestamp getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Timestamp discontinued) {
        this.discontinued = discontinued;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced=" + introduced +
                ", discontinued=" + discontinued +
                ", companyId=" + companyId +
                '}';
    }
}
