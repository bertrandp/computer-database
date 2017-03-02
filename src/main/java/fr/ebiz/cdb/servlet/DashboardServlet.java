package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dao.utils.DAOException;
import fr.ebiz.cdb.dto.ComputerPagerDTO;
import fr.ebiz.cdb.service.IComputerService;
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
import java.util.ArrayList;
import java.util.List;

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
    public static final String SELECTION = "selection";
    public static final String ORDER = "order";
    public static final String COLUMN = "column";
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ComputerPagerDTO page = parseRequest(request);
        LOGGER.debug("parseRequest(request) " + page);
        ComputerPagerDTO pageValid = ComputerValidator.validate(page);
        LOGGER.debug("validate(page) " + pageValid);
        IComputerService computerService = ComputerService.INSTANCE;
        ComputerPagerDTO pageToSend;
        try {
            pageToSend = computerService.fetchComputerList(pageValid);
        } catch (DAOException e) {
            throw new ServletException(e);
        }

        request.setAttribute(PAGER, pageToSend);

        RequestDispatcher view = request.getRequestDispatcher(DASHBOARD_JSP);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> idList = parseIdList(req);
        LOGGER.debug(" IDs of computers to delete : " + idList);
        IComputerService computerService = ComputerService.INSTANCE;
        try {
            computerService.delete(idList);
        } catch (DAOException e) {
            throw new ServletException(e);
        }

        doGet(req, resp);
    }

    /**
     * Parse the request and retrieve the list of computer id.
     *
     * @param req the http request
     * @return the list of computer id
     */
    private List<Integer> parseIdList(HttpServletRequest req) {
        String ids = req.getParameter(SELECTION);
        String[] stringList = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String stringId : stringList) {
            idList.add(Integer.valueOf(stringId));
        }
        return idList;
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
