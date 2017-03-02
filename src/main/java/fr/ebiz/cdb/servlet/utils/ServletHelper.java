package fr.ebiz.cdb.servlet.utils;

import fr.ebiz.cdb.dto.ComputerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bpestre on 01/03/17.
 */
public class ServletHelper {

    public static final String COMPUTER_NAME = "computerName";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "companyId";
    public static final String ID = "id";
    public static final String DASHBOARD = "/dashboard";

    public static final String COMPANY_LIST = "companyList";

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    /**
     * Parse the request and retrieve the ComputerDTO.
     *
     * @param req the http request
     * @return the computerDTO
     */
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

}
