package fr.ebiz.cdb.binding.mapper;


import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Computer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static fr.ebiz.cdb.binding.utils.DatesValidator.DATE_FORMAT;

/**
 * Created by bpestre on 23/02/17.
 */
public class ComputerMapper {

    private static final int DEFAULT_INT = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

    /**
     * Map the ComputerDTO to a Computer.
     *
     * @param computerDTO the computerDTO to map
     * @return the computer
     */
    public static Computer mapToComputer(ComputerDTO computerDTO) {
        Computer computer = new Computer();
        if (computerDTO.getId() != null) {
            computer.setId(computerDTO.getId());
        }
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
        if (computerDTO.getCompanyId() != null && computerDTO.getCompanyId() != DEFAULT_INT) {
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

        for (int i = 0; i < computers.size(); i++) {
            Object o = computers.get(i);
            if (o.getClass().isArray()) {
                Object[] array = (Object[]) o;
                computerDTOS.add(mapToComputerDTO((Computer) array[0]));
            }
        }

        return computerDTOS;
    }

}
