package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.service.ICompanyService;
import fr.ebiz.cdb.service.impl.CompanyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static fr.ebiz.cdb.servlet.AddComputerServlet.COMPANY_LIST;

/**
 * Created by bpestre on 24/02/17.
 */
@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyName = req.getParameter("companyName");

        req.setAttribute("id", id);
        req.setAttribute("name", name);
        if (introduced != null) {
            req.setAttribute("introduced", introduced);
        }
        if (discontinued != null) {
            req.setAttribute("discontinued", discontinued);
        }

        ICompanyService companyService = new CompanyService();
        List<Company> companyList = companyService.fetchAll();

        req.setAttribute(COMPANY_LIST, companyList);
        RequestDispatcher view = req.getRequestDispatcher("jsp/editComputer.jsp");
        view.forward(req, resp);
    }
}
