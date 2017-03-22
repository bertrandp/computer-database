package fr.ebiz.cdb.model.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

import static fr.ebiz.cdb.validation.ComputerValidator.DEFAULT_LIMIT;
import static fr.ebiz.cdb.validation.ComputerValidator.MIN_PAGE;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerPagerDTO {

    private List<ComputerDTO> list;
    private Integer count;

    @Min(1)
    private Integer currentPage;

    @Min(10) @Max(100)
    private Integer limit;

    @Size(max=500)
    private String search;


    private ORDER order;
    private COLUMN column;

    /**
     * ComputerPagerDTO constructor.
     *
     * @param builder the builder
     */
    private ComputerPagerDTO(Builder builder) {
        list = builder.list;
        count = builder.count;
        currentPage = builder.currentPage;
        limit = builder.limit;
        search = builder.search;
        order = builder.order;
        column = builder.column;
    }

    public List<ComputerDTO> getList() {
        return list;
    }

    public void setList(List<ComputerDTO> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    public enum ORDER {
        ASC, DESC
    }

    public enum COLUMN {
        NAME, INTRODUCED, DISCONTINUED, COMPANY_NAME
    }

    public static final class Builder {
        private List<ComputerDTO> list;
        private Integer count;
        private Integer currentPage;
        private Integer limit;
        private String search;
        private ORDER order;
        private COLUMN column;

        /**
         *
         */
        public Builder() {
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder list(List<ComputerDTO> val) {
            list = val;
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder count(Integer val) {
            count = val;
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder currentPage(Integer val) {
            currentPage = val == null ? MIN_PAGE : val;
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder limit(Integer val) {
            limit = val == null ? DEFAULT_LIMIT : val;
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder search(String val) {
            search = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder order(ORDER val) {
            order = val;
            return this;
        }

        /**
         *
         * @param val val
         * @return builder
         */
        public Builder column(COLUMN val) {
            column = val;
            return this;
        }

        /**
         *
         * @return builder
         */
        public ComputerPagerDTO build() {
            return new ComputerPagerDTO(this);
        }
    }
}
