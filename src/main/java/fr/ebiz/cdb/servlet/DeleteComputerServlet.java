package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.persistence.utils.DAOException;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fr.ebiz.cdb.servlet.utils.ServletHelper.DASHBOARD;

/**
 * Created by bpestre on 08/03/17.
 */
@Controller
@WebServlet("/delete-computer")
public class DeleteComputerServlet extends HttpServlet {

    public static final String SELECTION = "selection";
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputerServlet.class);

    @Autowired
    private IComputerService computerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Integer> idList = parseIdList(req);
        LOGGER.debug(" IDs of computers to delete : " + idList);

        try {
            computerService.delete(idList);
        } catch (DAOException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(DASHBOARD);
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
}
