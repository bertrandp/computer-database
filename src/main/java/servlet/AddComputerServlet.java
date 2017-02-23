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

    public static final String COMPUTER_NAME = "computerName";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "companyId";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String DASHBOARD = "/dashboard";
    public static final String JSP_403 = "jsp/403.jsp";
    public static final String ADD_COMPUTER_JSP = "jsp/addComputer.jsp";
    public static final String COMPANY_LIST = "companyList";

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter(COMPUTER_NAME);
        String introduced = req.getParameter(INTRODUCED);
        String discontinued = req.getParameter(DISCONTINUED);
        String companyId = req.getParameter(COMPANY_ID);

        LOGGER.debug("AddComputerServlet.doPost() : name:" + name + ", introduced:" + introduced + ", discontinued:" + discontinued + ", companyId:" + companyId);

        IComputerService computerService = new ComputerService();
        try {
            computerService.addWithCompanyId(name, introduced, discontinued, companyId);
        } catch (ComputerValidationException e) {
            LOGGER.error(e.getMessage());
            req.setAttribute(ERROR_MESSAGE, e.getMessage());
            resp.setStatus(403);
            RequestDispatcher view = req.getRequestDispatcher(JSP_403);
            view.forward(req, resp);
        }

        resp.sendRedirect(DASHBOARD);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ICompanyService companyService = new CompanyService();
        List<Company> companyList = companyService.fetchAll();

        request.setAttribute(COMPANY_LIST, companyList);

        RequestDispatcher view = request.getRequestDispatcher(ADD_COMPUTER_JSP);
        view.forward(request, response);
    }
}
