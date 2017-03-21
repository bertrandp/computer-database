package fr.ebiz.cdb.controller;

import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Bertrand Pestre on 21/03/17.
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    private static final String LIMIT = "limit";
    private static final String PAGE = "page";
    private static final String SEARCH = "search";
    private static final String ORDER = "order";
    private static final String COLUMN = "column";
    private static final String PAGER = "pager";
    private static final String DASHBOARD = "dashboard";

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private IComputerService computerService;

    /**
     * Get dashboard Page.
     *
     * @param model       model
     * @param limit       limit
     * @param currentPage currentPage
     * @param search      search
     * @param order       order
     * @param column      column
     * @return dashboard
     */
    @GetMapping
    public String get(ModelMap model,
                      @RequestParam(name = LIMIT, required = false) Integer limit,
                      @RequestParam(name = PAGE, required = false) Integer currentPage,
                      @RequestParam(name = SEARCH, required = false) String search,
                      @RequestParam(name = ORDER, required = false) String order,
                      @RequestParam(name = COLUMN, required = false) String column) {

        ComputerPagerDTO page = new ComputerPagerDTO.Builder()
                .limit(limit)
                .currentPage(currentPage)
                .search(search)
                .order(order != null ? ComputerPagerDTO.ORDER.valueOf(order.toUpperCase()) : null)
                .column(column != null ? ComputerPagerDTO.COLUMN.valueOf(column.toUpperCase()) : null).build();

        LOGGER.debug("parseRequest(request) " + page);
        ComputerPagerDTO pageValid = ComputerValidator.validate(page);
        LOGGER.debug("validate(page) " + pageValid);

        model.addAttribute(PAGER, computerService.fetchComputerList(pageValid));
        return DASHBOARD;
    }

}
