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
    private String search;

    /**
     * ComputerPagerDTO constructor. Fetch list of computerDTO.
     */
    public ComputerPagerDTO() {

    }

    public ComputerPagerDTO(List<ComputerDTO> list, int count, int currentPage, int limit, String search) {
        this.list = list;
        this.count = count;
        this.currentPage = currentPage;
        this.limit = limit;
        this.search = search;
    }

    private ComputerPagerDTO(Builder builder) {
        setList(builder.list);
        setCount(builder.count);
        setCurrentPage(builder.currentPage);
        setLimit(builder.limit);
        setSearch(builder.search);
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public static final class Builder {
        private List<ComputerDTO> list;
        private int count;
        private int currentPage;
        private int limit;
        private String search;

        public Builder() {
        }

        public Builder list(List<ComputerDTO> val) {
            list = val;
            return this;
        }

        public Builder count(int val) {
            count = val;
            return this;
        }

        public Builder currentPage(int val) {
            currentPage = val;
            return this;
        }

        public Builder limit(int val) {
            limit = val;
            return this;
        }

        public Builder search(String val) {
            search = val;
            return this;
        }

        public ComputerPagerDTO build() {
            return new ComputerPagerDTO(this);
        }
    }
}
