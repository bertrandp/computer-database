package servlet;

import dto.ComputerPagerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.IComputerService;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

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
        request.setAttribute("count", count);

        String limitParam = request.getParameter("limit");
        int limit = 50;
        if (limitParam != null) {
            limit = Integer.valueOf(limitParam);
            if (limit < 1) {
                limit = 1;
            } else if (limit > 100) {
                limit = 100;
            }
        }
        request.setAttribute("limit", limit);

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            page = Integer.valueOf(pageParam);
            if (page < 1) {
                page = 1;
            } else if (page > count / limit + 1) {
                page = count / limit + 1;
            }
        }
        request.setAttribute("page", page);

        try {
            ComputerPagerDTO pager = computerService.getPagedComputerDTOList(page, limit);
            request.setAttribute("computerList", pager.getList());
        } catch (ComputerValidationException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }


    }

}
