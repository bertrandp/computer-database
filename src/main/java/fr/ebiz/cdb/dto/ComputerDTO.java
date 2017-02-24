package fr.ebiz.cdb.dto;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerDTO {

    private int id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyName;

    /**
     * Empty ComputerDTO constructor.
     */
    public ComputerDTO() {
    }

    /**
     * ComputerDTO constructor.
     *
     * @param id           the id of the computer
     * @param name         the name of the computer
     * @param introduced   the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyName  the company name of the computer
     */
    public ComputerDTO(int id, String name, String introduced, String discontinued, String companyName) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyName = companyName;
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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComputerDTO that = (ComputerDTO) o;

        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (introduced != null ? !introduced.equals(that.introduced) : that.introduced != null) {
            return false;
        }
        if (discontinued != null ? !discontinued.equals(that.discontinued) : that.discontinued != null) {
            return false;
        }
        return companyName != null ? companyName.equals(that.companyName) : that.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }
}
