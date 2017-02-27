package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
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


    /**
     * Set request attributes related to pagination.
     *
     * @param request the http request
     */
    private void setAttributes(HttpServletRequest request) {

        String limit = request.getParameter(LIMIT);
        String page = request.getParameter(PAGE);

        try {
            IComputerService computerService = ComputerService.INSTANCE;
            ComputerPagerDTO pager = computerService.getPagedComputerDTOList(page, limit);
            request.setAttribute(COUNT, pager.getCount());
            request.setAttribute(LIMIT, pager.getLimit());
            request.setAttribute(PAGE, pager.getPage());
            request.setAttribute(COMPUTER_LIST, pager.getList());
        } catch (InputValidationException e) {
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
        }

    }

}
