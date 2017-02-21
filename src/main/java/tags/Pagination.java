package tags;

import model.Pager;
import service.IComputerService;
import service.impl.ComputerService;
import service.impl.ComputerValidationException;

import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * Created by bpestre on 20/02/17.
 */
public class Pagination extends BodyTagSupport {

    int limit = 40;
    int page = 1;

    /**
     * Set the limit.
     *
     * @param limit the number of item to display
     */
    public void setLimit(int limit) {
        if (limit < 1) {
            this.limit = 1;
        } else if (limit > 100) {
            this.limit = 100;
        } else {
            this.limit = limit;
        }
    }

    /**
     * Set the page index.
     *
     * @param page the page index
     */
    public void setPage(int page) {
        IComputerService computerService = new ComputerService();
        int count = computerService.count();
        if (page < 1) {
            this.page = 1;
        } else if (page > count / limit + 1) {
            this.page = count / limit + 1;
        } else {
            this.page = page;
        }
    }

    /**
     * Fetch the page of computer.
     *
     * @return status
     */
    public int doEndTag() {

        IComputerService computerService = new ComputerService();
        Pager pager = null;

        try {
            pager = computerService.getPagedComputerList(page, limit);
        } catch (ComputerValidationException e) {
            pageContext.setAttribute("errorMessage", e.getMessage());
        }

        pageContext.setAttribute("computerList", pager.getList());
        pageContext.setAttribute("page", page);
        pageContext.setAttribute("limit", limit);

        return EVAL_PAGE;
    }
}
