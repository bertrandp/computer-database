package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.service.IComputerService;
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

    @Autowired
    private InputUtils inputUtils;

    /**
     * Create the name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    String writeName(ComputerDTO newComputer) {
        System.out.println("* Name : ");
        return inputUtils.inputName(newComputer);
    }

    /**
     * Create the introduced date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    String writeIntroduced(ComputerDTO newComputer) {
        System.out.println("* Introduced Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return inputUtils.inputIntroducedDate(newComputer);
    }

    /**
     * Create the discontinued date of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    String writeDiscontinued(ComputerDTO newComputer) {
        System.out.println("* Discontinued Date (" + DATE_FORMAT + ") : ");
        System.out.println("* (optional) ");
        return inputUtils.inputDiscontinuedDate(newComputer);
    }

    /**
     * Create the company name of the computer.
     *
     * @param newComputer computer to create
     * @return the input
     */
    String writeCompany(ComputerDTO newComputer) {
        System.out.println("* Company : ");
        System.out.println("* (optional) ");
        return inputUtils.inputCompanyId(newComputer);
    }

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


        ComputerDTO computerDTO = new ComputerDTO.Builder()
                .name(name)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(Integer.valueOf(companyId))
                .build();

        ComputerValidator.validate(computerDTO);

        Computer computer = ComputerMapper.mapToComputer(computerDTO);

        computerService.add(computer);

        menuPage.display();
    }
}
