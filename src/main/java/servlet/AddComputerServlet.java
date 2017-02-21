package servlet;

import model.Company;
import service.ICompanyService;
import service.impl.CompanyService;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String name = req.getParameter("computerName");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyName = req.getParameter("introduced");

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //logger.debug("DashboardServlet.doGet()");

        ICompanyService companyService = new CompanyService();
        List<Company> companyList = companyService.fetchAll();


        request.setAttribute("companyList", companyList);
        RequestDispatcher view = request.getRequestDispatcher("jsp/addComputer.jsp");
        view.forward(request, response);
    }
}
