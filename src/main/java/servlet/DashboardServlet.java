package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bpestre on 20/02/17.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("DashboardServlet.doGet()");

        setAttributes(request);

        RequestDispatcher view = request.getRequestDispatcher("jsp/dashboard.jsp");
        view.forward(request, response);
    }

    /**
     * Set request attributes related to pagination.
     *
     * @param request the http request
     */
    private void setAttributes(HttpServletRequest request) {
        IComputerService computerService = new ComputerService();
        int count = computerService.count();
        logger.debug("Count computers : " + count);
        request.setAttribute("countComputer", count);

        String limitParam = request.getParameter("limit");
        int limit = 50;
        if (limitParam != null) {
            limit = Integer.valueOf(limitParam);
        }
        request.setAttribute("limit", limit);
        request.setAttribute("lastPage", count / limit + 1);

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            page = Integer.valueOf(pageParam);
        }

        request.setAttribute("page", page);
    }

}
