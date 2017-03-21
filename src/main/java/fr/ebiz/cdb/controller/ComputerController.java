package fr.ebiz.cdb.controller;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import java.util.List;

/**
 * Created by Bertrand Pestre on 21/03/17.
 */
@Controller
@RequestMapping("computer")
public class ComputerController {

    private static final String COMPANY_LIST = "companyList";
    private static final String COMPUTER_NAME = "computerName";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "companyId";
    private static final String ID = "id";
    private static final String SELECTION = "selection";
    private static final String REDIRECT_DASHBOARD = "redirect:/dashboard";
    private static final String COMPUTER = "computer";
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

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
     * Add computer request handler.
     *
     * @param name         name
     * @param introduced   introduced
     * @param discontinued discontinued
     * @param companyId    companyId
     * @return to dashboard
     * @throws ServletException ServletException
     */
    @PostMapping("add")
    public String addComputer(@RequestParam(COMPUTER_NAME) String name,
                              @RequestParam(INTRODUCED) String introduced,
                              @RequestParam(DISCONTINUED) String discontinued,
                              @RequestParam(COMPANY_ID) Integer companyId) throws ServletException {

        ComputerDTO computerDTO = new ComputerDTO.Builder()
                .name(name)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(companyId).build();

        LOGGER.debug("AddComputerServlet doPost parameters : " + computerDTO);

        List<String> errors = ComputerValidator.validate(computerDTO);
        if (errors.isEmpty()) {
            Computer computer = ComputerMapper.mapToComputer(computerDTO);
            if (!computerService.add(computer)) {
                throw new ServletException("Failed to add computer");
            }
        } else {
            throw new ServletException(errors.toString());
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

        model.addAttribute(COMPUTER, computerService.getDTO(id));
        model.addAttribute(COMPANY_LIST, companyService.fetchAll());
        return "editComputer";
    }

    /**
     * Edit computer request handler.
     *
     * @param id           id
     * @param name         name
     * @param introduced   introduced
     * @param discontinued discontinued
     * @param companyId    companyId
     * @return to dashboard
     * @throws ServletException ServletException
     */
    @PostMapping("edit")
    public String editComputer(@RequestParam(ID) Integer id,
                               @RequestParam(COMPUTER_NAME) String name,
                               @RequestParam(INTRODUCED) String introduced,
                               @RequestParam(DISCONTINUED) String discontinued,
                               @RequestParam(COMPANY_ID) Integer companyId) throws ServletException {

        ComputerDTO computerDTO = new ComputerDTO.Builder()
                .id(id)
                .name(name)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(companyId).build();

        List<String> errors = ComputerValidator.validate(computerDTO);
        if (errors.isEmpty()) {
            Computer computer = ComputerMapper.mapToComputer(computerDTO);

            if (!computerService.update(computer)) {
                throw new ServletException("Failed to update computer");
            }
        } else {
            throw new ServletException(errors.toString());
        }
        return REDIRECT_DASHBOARD;
    }

    /**
     * Delete computers request handler.
     *
     * @param idList idList
     * @return to dashboard
     * @throws ServletException ServletException
     */
    @PostMapping("delete")
    public String deleteComputers(@RequestParam(SELECTION) List<Integer> idList) throws ServletException {

        LOGGER.debug(" IDs of computers to delete : " + idList);
        if (!computerService.delete(idList)) {
            throw new ServletException("Failed to delete computer(s)");
        }
        return REDIRECT_DASHBOARD;
    }

}