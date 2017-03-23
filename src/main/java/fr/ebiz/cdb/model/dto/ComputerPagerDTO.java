package fr.ebiz.cdb.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerPagerDTO {

    private static final int MAX_LIMIT = 100;
    private static final int MIN_LIMIT = 10;
    private static final int DEFAULT_LIMIT = 50;
    private static final int MIN_PAGE = 1;

    private List<ComputerDTO> list;
    private int count;

    @Min(MIN_PAGE)
    private int page = MIN_PAGE;

    @Min(MIN_LIMIT)
    @Max(MAX_LIMIT)
    private int limit = DEFAULT_LIMIT;

    @Size(max = 100)
    private String search;

    private ORDER order = ORDER.ASC;
    private COLUMN column = COLUMN.NAME;

    /**
     * Empty constructor.
     */
    public ComputerPagerDTO() {
    }

    /**
     * ComputerPagerDTO constructor.
     *
     * @param builder the builder
     */
    private ComputerPagerDTO(Builder builder) {
        list = builder.list;
        count = builder.count;
        page = builder.page;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
                "page=" + page +
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
        private int count;
        private int page;
        private int limit;
        private String search;
        private ORDER order;
        private COLUMN column;

        /**
         *
         */
        public Builder() {
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder list(List<ComputerDTO> val) {
            list = val;
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder count(int val) {
            count = val;
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder page(int val) {
            page = val;
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder limit(int val) {
            limit = val;
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder search(String val) {
            search = val == null || val.trim().isEmpty() ? null : val.trim();
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder order(ORDER val) {
            order = val;
            return this;
        }

        /**
         * @param val val
         * @return builder
         */
        public Builder column(COLUMN val) {
            column = val;
            return this;
        }

        /**
         * @return builder
         */
        public ComputerPagerDTO build() {
            return new ComputerPagerDTO(this);
        }
    }
}
