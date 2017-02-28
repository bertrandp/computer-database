package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
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

/**
 * Created by bpestre on 20/02/17.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    public static final String COUNT = "count";
    public static final String LIMIT = "limit";
    public static final String PAGE = "page";
    public static final String COMPUTER_LIST = "computerList";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String DASHBOARD_JSP = "/WEB-INF/jsp/dashboard.jsp";
    private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("DashboardServlet.doGet()");
        setAttributes(request);
        RequestDispatcher view = request.getRequestDispatcher(DASHBOARD_JSP);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter("selection");
        logger.debug(" IDs of computers to delete : " + ids);

        String[] idList = ids.split(",");

        IComputerService computerService = ComputerService.INSTANCE;
        try {
            computerService.delete(idList);
        } catch (InputValidationException | ComputerException e) {
            e.printStackTrace();
        }

        doGet(req, resp);
    }

    /**
     * Set request attributes related to pagination.
     *
     * @param request the http request
     */
    private void setAttributes(HttpServletRequest request) {

        String limit = request.getParameter(LIMIT);
        String currentPage = request.getParameter(PAGE);
        String search = request.getParameter("search");

        try {
            IComputerService computerService = ComputerService.INSTANCE;
            ComputerPagerDTO page = computerService.getPagedComputerDTOList(currentPage, limit, search);
            request.setAttribute(PAGE, page);
        } catch (InputValidationException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
        }

    }

}
