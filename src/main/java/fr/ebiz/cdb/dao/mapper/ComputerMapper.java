package fr.ebiz.cdb.dao.mapper;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_FORMAT;

/**
 * Created by bpestre on 23/02/17.
 */
public class ComputerMapper {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_NAME = "company_name";
    public static final int DEFAULT_INT = 0;

    /**
     * Map the result set to list of computerDTO.
     *
     * @param resultSet the result set to map
     * @return the list of computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static List<ComputerDTO> mapToComputerDTOList(ResultSet resultSet) throws SQLException {
        List<ComputerDTO> list = new ArrayList<>();
        while (resultSet.next()) {
            ComputerDTO computer = new ComputerDTO();
            computer.setId(resultSet.getInt(ID));
            computer.setName(resultSet.getString(NAME));
            computer.setIntroduced(resultSet.getString(INTRODUCED));
            computer.setDiscontinued(resultSet.getString(DISCONTINUED));
            computer.setCompanyName(resultSet.getString(COMPANY_NAME));
            list.add(computer);
        }
        return list;
    }


    /**
     * Map the result set to a computer.
     *
     * @param resultSet the result set to map
     * @return the computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static Computer mapToComputer(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();
        if (resultSet.next()) {
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
        return null;
    }

    /**
     * Map the result set to a computerDTO.
     *
     * @param resultSet the result set to map
     * @return the computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static ComputerDTO mapToComputerDTO(ResultSet resultSet) throws SQLException {
        ComputerDTO computer = new ComputerDTO();
        if (resultSet.next()) {
            computer.setId(resultSet.getInt(ID));
            computer.setName(resultSet.getString(NAME));
            computer.setIntroduced(resultSet.getString(INTRODUCED));
            computer.setDiscontinued(resultSet.getString(DISCONTINUED));
            computer.setCompanyName(resultSet.getString(COMPANY_NAME));
            computer.setCompanyId(resultSet.getInt(COMPANY_ID));
            return computer;
        }
        return null;
    }

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
}
