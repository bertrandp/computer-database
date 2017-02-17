package model;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public interface Pager<T> {

    List<T> getList();
    int getIndex();
    int countPages();
    boolean hasNext();
    boolean hasPrevious();
    void next();
    void previous();

}
