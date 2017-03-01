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
    private ORDER order;
    private COLUMN column;

    private ComputerPagerDTO(Builder builder) {
        setList(builder.list);
        setCount(builder.count);
        setCurrentPage(builder.currentPage);
        setLimit(builder.limit);
        setSearch(builder.search);
        setOrder(builder.order);
        setColumn(builder.column);
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

    public ORDER getOrder() {
        return order;
    }

    public void setOrder(ORDER order) {
        this.order = order;
    }

    public COLUMN getColumn() {
        return column;
    }

    public void setColumn(COLUMN column) {
        this.column = column;
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
        private ORDER order;
        private COLUMN column;

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

        public Builder order(ORDER val) {
            order = val;
            return this;
        }

        public Builder column(COLUMN val) {
            column = val;
            return this;
        }

        public ComputerPagerDTO build() {
            return new ComputerPagerDTO(this);
        }
    }

    public enum ORDER {
        ASC,DESC
    }

    public enum COLUMN {
        NAME, INTRODUCED, DISCONTINUED, COMPANY_NAME
    }

    @Override
    public String toString() {
        return "ComputerPagerDTO{" +
                "currentPage=" + currentPage +
                ", limit=" + limit +
                ", search='" + search + '\'' +
                ", order=" + order +
                ", column=" + column +
                '}';
    }
}
