package fr.ebiz.cdb.cli.ui;


import fr.ebiz.cdb.cli.validation.ComputerValidator;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by ebiz on 16/02/17.
 */
@Component
public class UpdateComputerPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateComputerPage.class);

    @Autowired
    private IComputerService computerService;

    @Autowired
    private MenuPage menuPage;

    @Autowired
    private InputUtils inputUtils;

    /**
     * Display the current company name of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private String updateCompany(Computer computer) {
        if (computer.getCompany().getName() != null) {
            System.out.println("* Company Name : " + computer.getCompany().getName());
        } else {
            System.out.println("* Company Name : undefined");
        }
        System.out.println("* Specify the new id of the company company or press Enter ");
        return inputUtils.inputCompanyId(computer);
    }

    /**
     * Display the current discontinued date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private String updateDiscontinuedDate(Computer computer) {
        System.out.println("* Discontinued Date : " + computer.getDiscontinued());
        System.out.println("* Specify the new discontinued date or press Enter ");
        return inputUtils.inputDiscontinuedDate(computer);
    }

    /**
     * Display the current introduced date of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private String updateIntroducedDate(Computer computer) {
        System.out.println("* Introduced Date : " + computer.getIntroduced());
        System.out.println("* Specify the new introduced date or press Enter ");
        return inputUtils.inputIntroducedDate(computer);
    }

    /**
     * Display the current name of the given computer and ask for an update.
     *
     * @param computer the computer to update
     * @return the input
     */
    private String updateName(Computer computer) {
        System.out.println("* Name : " + computer.getName());
        System.out.println("* Specify the new name or press Enter to keep this name ");
        return inputUtils.inputName(computer);
    }

    /**
     * Display the page to update a computer.
     */
    void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Specify the computer's id :");
        String id = sc.nextLine();

        if (!id.trim().isEmpty()) {

            Computer computer;


            computer = computerService.get(Integer.valueOf(id));

            System.out.println("* Update computer : " + computer.getName());
            String name = updateName(computer);
            String introduced = updateIntroducedDate(computer);
            String discontinued = updateDiscontinuedDate(computer);
            String companyId = updateCompany(computer);
            ComputerDTO computerDTO = new ComputerDTO.Builder()
                    .id(Integer.valueOf(id))
                    .name(name)
                    .introduced(introduced)
                    .discontinued(discontinued)
                    .companyId(Integer.valueOf(companyId))
                    .build();

            ComputerValidator.validate(computerDTO);

            Computer computerToAdd = ComputerMapper.mapToComputer(computerDTO);

            computerService.update(computerToAdd);

            menuPage.display();


        } else {
            LOGGER.error(" *** Error : Invalid id");
            display();
        }

    }

}
