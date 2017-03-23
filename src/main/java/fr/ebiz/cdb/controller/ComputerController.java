package fr.ebiz.cdb.controller;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Bertrand Pestre on 21/03/17.
 */
@Controller
@RequestMapping("computer")
public class ComputerController {

    private static final String COMPANY_LIST = "companyList";
    private static final String ID = "id";
    private static final String SELECTION = "selection";
    private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    private static final String COMPUTER = "computer";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
    private static final String ERROR_500 = "errorpages/500";

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ICompanyService companyService;

    /**
     * Get add computer page.
     *
     * @param model model
     * @return add computer page
     */
    @GetMapping("add")
    public String getAddComputer(ModelMap model) {

        model.addAttribute(COMPANY_LIST, companyService.fetchAll());
        return "addComputer";
    }

    /**
     * Add a computer.
     *
     * @param computerDTO computerDTO
     * @param result      result
     * @return to dashboard
     */
    @PostMapping("add")
    public String addComputer(@Valid ComputerDTO computerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            return "errorpages/500";
        }

        LOGGER.debug("addComputer parameters : " + computerDTO);
        Computer computer = ComputerMapper.mapToComputer(computerDTO);
        if (!computerService.add(computer)) {
            return "errorpages/500";
        }

        return REDIRECT_DASHBOARD;
    }

    /**
     * Get edit computer page.
     *
     * @param model model
     * @param id    id
     * @return edit computer page
     */
    @GetMapping("edit")
    public String getEditComputer(ModelMap model, @RequestParam(ID) int id) {

        model.addAttribute(COMPUTER, ComputerMapper.mapToComputerDTO(computerService.get(id)));
        model.addAttribute(COMPANY_LIST, companyService.fetchAll());
        return "editComputer";
    }

    /**
     * Edit computer request handler.
     *
     * @param computerDTO computerDTO
     * @param result      result
     * @return to dashboard
     */
    @PostMapping("edit")
    public String editComputer(@Valid ComputerDTO computerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            return "errorpages/500";
        }

        LOGGER.debug("editComputer parameters : " + computerDTO);
        Computer computer = ComputerMapper.mapToComputer(computerDTO);
        if (!computerService.update(computer)) {
            return "errorpages/500";
        }

        return REDIRECT_DASHBOARD;
    }

    /**
     * Delete computers request handler.
     *
     * @param idList idList
     * @return to dashboard
     */
    @PostMapping("delete")
    public String deleteComputers(@RequestParam(SELECTION) List<Integer> idList) {

        LOGGER.debug(" IDs of computers to delete : " + idList);
        if (!computerService.delete(idList)) {
            return ERROR_500;
        }
        return REDIRECT_DASHBOARD;
    }

}