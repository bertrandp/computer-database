package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

    public static final String LIMIT = "limit";
    public static final String PAGE = "page";
    public static final String PAGER = "pager";
    public static final String DASHBOARD_JSP = "/WEB-INF/jsp/dashboard.jsp";
    public static final String SEARCH = "search";

    public static final String ORDER = "order";
    public static final String COLUMN = "column";
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class);

    @Autowired
    private IComputerService computerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ComputerPagerDTO page = parseRequest(request);
        LOGGER.debug("parseRequest(request) " + page);
        ComputerPagerDTO pageValid = ComputerValidator.validate(page);
        LOGGER.debug("validate(page) " + pageValid);

        ComputerPagerDTO pageToSend = computerService.fetchComputerList(pageValid);

        request.setAttribute(PAGER, pageToSend);

        RequestDispatcher view = request.getRequestDispatcher(DASHBOARD_JSP);
        view.forward(request, response);
    }

    /**
     * Parse the parameters of the request and build the computerPagerDTO.
     *
     * @param request the http request
     * @return the computerPagerDTO
     */
    private ComputerPagerDTO parseRequest(HttpServletRequest request) {

        String limit = request.getParameter(LIMIT);
        String currentPage = request.getParameter(PAGE);
        String search = request.getParameter(SEARCH);
        String order = request.getParameter(ORDER);
        String column = request.getParameter(COLUMN);

        ComputerPagerDTO.Builder pageBuilder = new ComputerPagerDTO.Builder();
        if (limit != null) {
            pageBuilder.limit(Integer.valueOf(limit));
        }
        if (currentPage != null) {
            pageBuilder.currentPage(Integer.valueOf(currentPage));
        }
        if (search != null) {
            pageBuilder.search(search);
        }
        if (order != null) {
            pageBuilder.order(ComputerPagerDTO.ORDER.valueOf(order.toUpperCase()));
        }
        if (column != null) {
            pageBuilder.column(ComputerPagerDTO.COLUMN.valueOf(column.toUpperCase()));
        }

        return pageBuilder.build();
    }

}
