package fr.ebiz.cdb.dto;

import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dao.impl.ComputerDAO;

import java.util.List;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerPagerDTO {

    private List<ComputerDTO> list;
    private int count;
    private int currentPage;
    private int limit;

    /**
     * ComputerPagerDTO constructor. Fetch list of computerDTO.
     *
     * @param count the total number of entries
     * @param page  the currentPage index
     * @param limit the limit
     */
    public ComputerPagerDTO(int count, int page, int limit) {
        this.currentPage = page;
        this.limit = limit;
        this.count = count;
        int offset = (page - 1) * limit;
        IComputerDAO computerDAO = ComputerDAO.INSTANCE;
        this.list = computerDAO.fetchPageDTO(limit, offset);
    }

    public List getList() {
        return list;
    }

    public int getCount() {
        return count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLimit() {
        return limit;
    }
}
