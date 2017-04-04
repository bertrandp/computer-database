package fr.ebiz.cdb.webapp;


import fr.ebiz.cdb.binding.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;


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
     * @param error            error
     * @return the dashboard
     */
    @GetMapping
    public String getDashboard(Model model, @Valid ComputerPagerDTO computerPagerDTO, BindingResult result,
                               @RequestParam("error") Optional<String> error) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            return "errorpages/500";
        }

        model.addAttribute("error", error.orElse(null));

        LOGGER.debug("validate " + computerPagerDTO);

        model.addAttribute(PAGER, computerService.fetchComputerList(computerPagerDTO));
        return DASHBOARD;
    }

}
