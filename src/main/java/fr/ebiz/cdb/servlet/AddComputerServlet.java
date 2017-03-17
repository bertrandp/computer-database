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

/**
 * Created by bpestre on 21/02/17.
 */
@WebServlet("/add-computer")
public class AddComputerServlet extends HttpServlet {

    private static final String ADD_COMPUTER_JSP = "/WEB-INF/jsp/addComputer.jsp";
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);


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
        LOGGER.debug("AddComputerServlet doPost parameters : " + computerDTO);

        try {

            List<String> errors = ComputerValidator.validate(computerDTO);
            if (errors.isEmpty()) {
                Computer computer = ComputerMapper.mapToComputer(computerDTO);

                computerService.add(computer);
            } else {
                throw new ServletException(errors.toString());
            }

        } catch (DAOException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(DASHBOARD);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Company> companyList;

        try {
            companyList = companyService.fetchAll();
        } catch (DAOException e) {
            throw new ServletException(e);
        }

        request.setAttribute(COMPANY_LIST, companyList);

        RequestDispatcher view = request.getRequestDispatcher(ADD_COMPUTER_JSP);
        view.forward(request, response);
    }
}
