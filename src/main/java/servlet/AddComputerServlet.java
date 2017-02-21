package servlet;

import model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ICompanyService;
import service.IComputerService;
import service.impl.CompanyService;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by bpestre on 21/02/17.
 */
@WebServlet("/add-computer")
public class AddComputerServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(AddComputerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("computerName");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyId = req.getParameter("companyId");

        logger.debug("AddComputerServlet.doPost() : name:" + name + ", introduced:" + introduced + ", discontinued:" + discontinued + ", companyId:" + companyId);

        IComputerService computerService = new ComputerService();
        try {
            computerService.add(name, introduced, discontinued, companyId);
        } catch (ComputerValidationException e) {
            logger.error(e.getMessage()); // TODO
            req.setAttribute("errorMessage", e.getMessage());
        }

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ICompanyService companyService = new CompanyService();
        List<Company> companyList = companyService.fetchAll();

        request.setAttribute("companyList", companyList);

        RequestDispatcher view = request.getRequestDispatcher("jsp/addComputer.jsp");
        view.forward(request, response);
    }
}
