package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.servlet.utils.ServletHelper;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static fr.ebiz.cdb.servlet.utils.ServletHelper.COMPANY_LIST;
import static fr.ebiz.cdb.servlet.utils.ServletHelper.DASHBOARD;
import static fr.ebiz.cdb.servlet.utils.ServletHelper.ID;


/**
 * Created by bpestre on 24/02/17.
 */

@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputerServlet.class);
    private static final String COMPUTER = "computer";
    private static final String COMPUTER_JSP = "/WEB-INF/jsp/editComputer.jsp";

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ICompanyService companyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerDTO computerDTO = ServletHelper.parseRequest(req);
        LOGGER.debug("EditComputerServlet doPost parameters : " + computerDTO);

        List<String> errors = ComputerValidator.validate(computerDTO);
        if (errors.isEmpty()) {
            Computer computer = ComputerMapper.mapToComputer(computerDTO);

            if (!computerService.update(computer)) {
                throw new ServletException("Failed to update computer");
            }
        } else {
            throw new ServletException(errors.toString());
        }

        resp.sendRedirect(DASHBOARD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter(ID);

        try {
            ComputerDTO computer = computerService.getDTO(Integer.valueOf(id));
            req.setAttribute(COMPUTER, computer);

            List<Company> companyList = companyService.fetchAll();

            req.setAttribute(COMPANY_LIST, companyList);
            RequestDispatcher view = req.getRequestDispatcher(COMPUTER_JSP);
            view.forward(req, resp);

        } catch (DAOException e) {
            throw new ServletException(e);
        }

    }
}
