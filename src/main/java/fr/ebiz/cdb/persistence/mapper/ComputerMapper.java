package fr.ebiz.cdb.persistence.mapper;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static fr.ebiz.cdb.validation.ComputerValidator.DATE_FORMAT;

/**
 * Created by bpestre on 23/02/17.
 */
public class ComputerMapper implements RowMapper<Computer> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "company_id";
    private static final String COMPANY_NAME = "company_name";
    private static final int DEFAULT_INT = 0;

    /**
     * Map the ComputerDTO to a Computer.
     *
     * @param computerDTO the computerDTO to map
     * @return the computer
     */
    public static Computer mapToComputer(ComputerDTO computerDTO) {
        Computer computer = new Computer();
        computer.setId(computerDTO.getId());
        computer.setName(computerDTO.getName());
        if (computerDTO.getIntroduced() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate introducedLD = LocalDate.parse(computerDTO.getIntroduced(), formatter);
            computer.setIntroduced(introducedLD);
        }
        if (computerDTO.getDiscontinued() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate discontinuedLD = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
            computer.setDiscontinued(discontinuedLD);
        }
        if (computerDTO.getCompanyId() != DEFAULT_INT) {
            computer.setCompany(new Company(computerDTO.getCompanyId()));
        }
        return computer;
    }

    /**
     * Map the ComputerDTO to a Computer.
     *
     * @param computer the computerDTO to map
     * @return the computer
     */
    public static ComputerDTO mapToComputerDTO(Computer computer) {
        return new ComputerDTO.Builder()
                .id(computer.getId())
                .name(computer.getName())
                .introduced(computer.getIntroduced() != null ? computer.getIntroduced().toString() : null)
                .discontinued(computer.getDiscontinued() != null ? computer.getDiscontinued().toString() : null)
                .companyId(computer.getCompany() != null ? computer.getCompany().getId() : null)
                .companyName(computer.getCompany() != null ? computer.getCompany().getName() : null)
                .build();
    }

    /**
     * Map a computer list to a ComputerDTO list.
     *
     * @param computers the computer list
     * @return the computerDTO list
     */
    public static List<ComputerDTO> mapToComputerDTOList(List<Computer> computers) {
        List<ComputerDTO> computerDTOS = new ArrayList<>();
        for (Computer computer : computers) {
            computerDTOS.add(mapToComputerDTO(computer));
        }
        return computerDTOS;
    }

    @Override
    public Computer mapRow(ResultSet resultSet, int i) throws SQLException {
        Computer computer = new Computer();
        computer.setId(resultSet.getInt(ID));
        computer.setName(resultSet.getString(NAME));
        Date inputIntroduced = resultSet.getDate(INTRODUCED);
        if (inputIntroduced != null) {
            computer.setIntroduced(inputIntroduced.toLocalDate());
        } else {
            computer.setIntroduced(null);
        }
        Date inputDiscontinued = resultSet.getDate(DISCONTINUED);
        if (inputDiscontinued != null) {
            computer.setDiscontinued(inputDiscontinued.toLocalDate());
        } else {
            computer.setDiscontinued(null);
        }
        computer.setCompany(new Company(resultSet.getInt(COMPANY_ID), resultSet.getString(COMPANY_NAME)));
        return computer;
    }

}
