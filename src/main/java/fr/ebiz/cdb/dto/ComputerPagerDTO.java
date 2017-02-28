package fr.ebiz.cdb.dto;

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
     */
    public ComputerPagerDTO() {

    }

    public List getList() {
        return list;
    }

    public void setList(List<ComputerDTO> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
