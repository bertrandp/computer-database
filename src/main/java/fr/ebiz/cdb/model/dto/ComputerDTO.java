package fr.ebiz.cdb.model.dto;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerDTO {

    private Integer id;
    private String name;
    private String introduced;
    private String discontinued;
    private Integer companyId;
    private String companyName;

    /**
     * Empty ComputerDTO constructor.
     */
    public ComputerDTO() {
    }

    /**
     * ComputerDTO constructor.
     *
     * @param builder the computerDTO builder
     */
    private ComputerDTO(Builder builder) {
        id = builder.id;
        name = builder.name;
        introduced = builder.introduced;
        discontinued = builder.discontinued;
        companyId = builder.companyId;
        companyName = builder.companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String introduced;
        private String discontinued;
        private Integer companyId;
        private String companyName;

        /**
         *
         */
        public Builder() {
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder id(Integer val) {
            id = val;
            return this;
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder name(String val) {
            name = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder introduced(String val) {
            introduced = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder discontinued(String val) {
            discontinued = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder companyId(Integer val) {
            companyId = val;
            return this;
        }

        /**
         * @param val val
         * @return the builder
         */
        public Builder companyName(String val) {
            companyName = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         * @return the computerDTO
         */
        public ComputerDTO build() {
            return new ComputerDTO(this);
        }
    }
}
