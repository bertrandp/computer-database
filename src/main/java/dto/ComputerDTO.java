package dto;

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
     * @param id the id of the computer
     * @param name the name of the computer
     * @param introduced the introduced date of the computer
     * @param discontinued the discontinued date of the computer
     * @param companyName the company name of the computer
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
}
