package dao.mapper;

import dto.ComputerDTO;
import model.Company;
import model.Computer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Map the result set to list of computer.
     *
     * @param resultSet the result set to map
     * @return the list of computer
     * @throws SQLException exception raised if the there is an issue with the database
     */
    public static List<Computer> mapToComputerList(ResultSet resultSet) throws SQLException {
        List<Computer> list = new ArrayList<>();
        while (resultSet.next()) {
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
            list.add(computer);
        }
        return list;
    }

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


    public static ComputerDTO mapToComputerDTO(ResultSet resultSet) throws SQLException {
        ComputerDTO computer = new ComputerDTO();
        if (resultSet.next()) {
            computer.setId(resultSet.getInt(ID));
            computer.setName(resultSet.getString(NAME));
            computer.setIntroduced(resultSet.getString(INTRODUCED));
            computer.setDiscontinued(resultSet.getString(DISCONTINUED));
            computer.setCompanyName(resultSet.getString(COMPANY_NAME));
            return computer;
        }
        return null;
    }
}
