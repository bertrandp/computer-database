package fr.ebiz.cdb.controller;

import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * Created by Bertrand Pestre on 21/03/17.
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    private static final String PAGER = "pager";
    private static final String DASHBOARD = "dashboard";

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private IComputerService computerService;

    /**
     * Get dashboard Page.
     *
     * @param model            model
     * @param computerPagerDTO computerPagerDTO
     * @param result           result
     * @return the dashboard
     */
    @GetMapping
    public String getDashboard(Model model, @Valid ComputerPagerDTO computerPagerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            return "errorpages/500";
        }

        LOGGER.debug("validate " + computerPagerDTO);

        model.addAttribute(PAGER, computerService.fetchComputerList(computerPagerDTO));
        return DASHBOARD;
    }

}
