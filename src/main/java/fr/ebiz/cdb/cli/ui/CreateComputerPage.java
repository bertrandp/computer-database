package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.dao.mapper.ComputerMapper;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static fr.ebiz.cdb.service.validation.ComputerValidator.DATE_FORMAT;

/**
 * Created by ebiz on 16/02/17.
 */
public class CreateComputerPage {

    private static Logger logger = LoggerFactory.getLogger(CreateComputerPage.class);

    /**
     * Display the page to create a computer.
     */
    static void display() {

        ComputerDTO newComputer = new ComputerDTO();
        System.out.println("* Create a computer ");
        String name = writeName(newComputer);
        String introduced = writeIntroduced(newComputer);
        String discontinued = writeDiscontinued(newComputer);
        String companyId = writeCompany(newComputer);

        try {
            ComputerDTO computerDTO = new ComputerDTO.Builder()
                    .name(name)
                    .introduced(introduced)
                    .discontinued(discontinued)
                    .companyId(Integer.valueOf(companyId))
                    .build();

            ComputerValidator.validate(computerDTO);

            IComputerService computerService = ComputerService.INSTANCE;
            Computer computer = ComputerMapper.mapToComputer(computerDTO);

            computerService.add(computer);

        } catch (InputValidationException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        MenuPage.display();
    }

    /**
     * Create the name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeName(ComputerDTO newComputer) {
        System.out.println("* Name : ");
        return InputUtils.inputName(newComputer);
    }

    /**
     * Create the introduced date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeIntroduced(ComputerDTO newComputer) {
        System.out.println("* Introduced Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return InputUtils.inputIntroducedDate(newComputer);
    }

    /**
     * Create the discontinued date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeDiscontinued(ComputerDTO newComputer) {
        System.out.println("* Discontinued Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return InputUtils.inputDiscontinuedDate(newComputer);
    }

    /**
     * Create the company name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    static String writeCompany(ComputerDTO newComputer) {
        System.out.println("* Company : ");
        System.out.println("* (optional) ");
        return InputUtils.inputCompanyId(newComputer);
    }
}
