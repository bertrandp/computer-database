package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.impl.ComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static fr.ebiz.cdb.validation.ComputerValidator.DATE_FORMAT;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class CreateComputerPage {

    private static Logger logger = LoggerFactory.getLogger(CreateComputerPage.class);

    @Autowired
    private IComputerService computerService;

    @Autowired
    private MenuPage menuPage;

    /**
     * Display the page to create a computer.
     */
    void display() {

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

            Computer computer = ComputerMapper.mapToComputer(computerDTO);

            computerService.add(computer);

        } catch (DAOException e) {
            logger.error("*** Error : " + e.getMessage());
            display();
        }

        menuPage.display();
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
