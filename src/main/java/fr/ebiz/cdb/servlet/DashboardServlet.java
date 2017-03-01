package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import fr.ebiz.cdb.service.validation.ComputerValidator;
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

    public static final String LIMIT = "limit";
    public static final String PAGE = "page";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String DASHBOARD_JSP = "/WEB-INF/jsp/dashboard.jsp";
    public static final String SEARCH = "search";
    public static final String SELECTION = "selection";
    public static final String ORDER = "order";
    public static final String COLUMN = "column";
    private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("DashboardServlet.doGet()");

        ComputerPagerDTO page = parseRequest(request);
        ComputerPagerDTO pageValid = ComputerValidator.validate(page);

        IComputerService computerService = ComputerService.INSTANCE;
        ComputerPagerDTO pageToSend = computerService.fetchComputerList(pageValid);

        request.setAttribute(PAGE, pageToSend);

        RequestDispatcher view = request.getRequestDispatcher(DASHBOARD_JSP);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter(SELECTION);
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
     * Parse the parameters of the request.
     *
     * @param request the http request
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
