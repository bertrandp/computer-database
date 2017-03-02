package fr.ebiz.cdb.servlet.utils;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import fr.ebiz.cdb.service.validation.InputValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bpestre on 01/03/17.
 */
public class ServletHelper {

    public static final String COMPUTER_NAME = "computerName";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "companyId";
    public static final String ID = "id";
    public static final String JSP_403 = "jsp/403.jsp";
    public static final String DASHBOARD = "/dashboard";

    public static final String COMPANY_LIST = "companyList";

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    public static ComputerDTO parseRequest(HttpServletRequest req) {

        String id = req.getParameter(ID);
        String name = req.getParameter(COMPUTER_NAME);
        String introduced = req.getParameter(INTRODUCED);
        String discontinued = req.getParameter(DISCONTINUED);
        String companyId = req.getParameter(COMPANY_ID);

        ComputerDTO.Builder computerDTOBuilder = new ComputerDTO.Builder();

        if (id != null) {
            computerDTOBuilder.id(Integer.valueOf(id));
        }
        computerDTOBuilder.name(name);
        computerDTOBuilder.introduced(introduced);
        computerDTOBuilder.discontinued(discontinued);
        if (companyId != null) {
            computerDTOBuilder.companyId(Integer.valueOf(companyId));
        }

        return computerDTOBuilder.build();
    }

    public static void validateInput(ComputerDTO computerDTO, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ComputerValidator.validate(computerDTO);
        } catch (InputValidationException e) {
            LOGGER.error(e.getMessage());
            resp.setStatus(403);
            RequestDispatcher view = req.getRequestDispatcher(JSP_403);
            view.forward(req, resp);
        }
    }
}
