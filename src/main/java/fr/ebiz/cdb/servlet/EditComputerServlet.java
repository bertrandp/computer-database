package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dao.mapper.ComputerMapper;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.CompanyService;
import fr.ebiz.cdb.service.impl.ComputerService;
import fr.ebiz.cdb.service.validation.ComputerValidator;
import fr.ebiz.cdb.servlet.utils.ServletHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ComputerDTO computerDTO = ServletHelper.parseRequest(req);
        LOGGER.debug("EditComputerServlet doPost parameters : " + computerDTO);

        ServletHelper.validateInput(computerDTO, req, resp);

        IComputerService computerService = ComputerService.INSTANCE;
        Computer computer = ComputerMapper.mapToComputer(computerDTO);

        computerService.update(computer);

        resp.sendRedirect(DASHBOARD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter(ID);

        IComputerService computerService = ComputerService.INSTANCE;
        try {
            ComputerDTO computer = computerService.getDTO(Integer.valueOf(id));
            req.setAttribute(COMPUTER, computer);

        } catch (ComputerException | InputValidationException e) {
            e.printStackTrace();
        }

        ICompanyService companyService = CompanyService.INSTANCE;
        List<Company> companyList = companyService.fetchAll();

        req.setAttribute(COMPANY_LIST, companyList);
        RequestDispatcher view = req.getRequestDispatcher(COMPUTER_JSP);
        view.forward(req, resp);
    }
}
