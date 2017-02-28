package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.CompanyException;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.CompanyService;
import fr.ebiz.cdb.service.impl.ComputerService;
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

import static fr.ebiz.cdb.servlet.AddComputerServlet.COMPANY_ID;
import static fr.ebiz.cdb.servlet.AddComputerServlet.COMPANY_LIST;
import static fr.ebiz.cdb.servlet.AddComputerServlet.COMPUTER_NAME;
import static fr.ebiz.cdb.servlet.AddComputerServlet.DASHBOARD;
import static fr.ebiz.cdb.servlet.AddComputerServlet.DISCONTINUED;
import static fr.ebiz.cdb.servlet.AddComputerServlet.INTRODUCED;
import static fr.ebiz.cdb.servlet.AddComputerServlet.JSP_403;
import static fr.ebiz.cdb.servlet.DashboardServlet.ERROR_MESSAGE;

/**
 * Created by bpestre on 24/02/17.
 */
@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter(COMPUTER_NAME);
        String introduced = req.getParameter(INTRODUCED);
        String discontinued = req.getParameter(DISCONTINUED);
        String companyId = req.getParameter(COMPANY_ID);

        LOGGER.debug("EditComputerServlet.doPost() : id:" + id + ", name:" + name + ", introduced:" + introduced + ", discontinued:" + discontinued + ", companyId:" + companyId);

        IComputerService computerService = ComputerService.INSTANCE;
        try {
            computerService.update(id, name, introduced, discontinued, "0".equals(companyId) ? null : companyId);
        } catch (ComputerException | CompanyException | InputValidationException e) {
            LOGGER.error(e.getMessage());
            req.setAttribute(ERROR_MESSAGE, e.getMessage());
            resp.setStatus(403);
            RequestDispatcher view = req.getRequestDispatcher(JSP_403);
            view.forward(req, resp);
        }

        resp.sendRedirect(DASHBOARD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        IComputerService computerService = ComputerService.INSTANCE;
        try {
            Computer computer = computerService.get(id);
            req.setAttribute("computer", computer);

        } catch (ComputerException | InputValidationException e) {
            e.printStackTrace();
        }

        ICompanyService companyService = CompanyService.INSTANCE;
        List<Company> companyList = companyService.fetchAll();

        req.setAttribute(COMPANY_LIST, companyList);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/editComputer.jsp");
        view.forward(req, resp);
    }
}
