package dto;

import dao.DAOFactory;
import dao.IComputerDAO;

import java.util.List;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerPagerDTO {

    private List<ComputerDTO> list;
    private int count;
    private int page;
    private int limit;

    /**
     * ComputerPagerDTO constructor. Fetch list of computerDTO.
     *
     * @param count the total number of entries
     * @param page  the page index
     * @param limit the limit
     */
    public ComputerPagerDTO(int count, int page, int limit) {
        this.page = page;
        this.limit = limit;
        this.count = count;
        int offset = (page - 1) * limit;
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        this.list = computerDAO.fetchPageDTO(limit, offset);
    }

    public List getList() {
        return list;
    }

    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }
}
