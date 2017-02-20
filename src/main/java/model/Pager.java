package model;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public interface Pager<T> {

    /**
     * Retrieve the list of the page's items.
     *
     * @return the list of the page's items
     */
    List<T> getList();

    /**
     * Retrieve the index of the page.
     *
     * @return the index of the page
     */
    int getIndex();

    /**
     * Retrieve the total number of pages.
     *
     * @return the total number of pages
     */
    int countPages();

    /**
     * Check if there is a next page.
     *
     * @return true if there is a next page
     */
    boolean hasNext();

    /**
     * Check if there is a previous page.
     *
     * @return true if there is a previous page
     */
    boolean hasPrevious();

    /**
     * Switch the page to the next page and update the item list.
     */
    void next();

    /**
     * Switch the page to the previous page and update the item list.
     */
    void previous();

}
