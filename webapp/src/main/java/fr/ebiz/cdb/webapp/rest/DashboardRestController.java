package fr.ebiz.cdb.webapp.rest;

import fr.ebiz.cdb.binding.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.webapp.rest.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
@RestController
@RequestMapping("api/dashboard")
public class DashboardRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardRestController.class);

    @Autowired
    private IComputerService computerService;

    /**
     * Get dashboard.
     *
     * @param computerPagerDTO computerPagerDTO
     * @param result           result
     * @return the page
     */
    @GetMapping
    public ComputerPagerDTO getDashboard(@Valid ComputerPagerDTO computerPagerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            throw new InvalidParameterException(result.getAllErrors());
        }

        LOGGER.debug("validate " + computerPagerDTO);

        return computerService.fetchComputerList(computerPagerDTO);
    }
}
