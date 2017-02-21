package model;

import dao.DAOFactory;
import dao.IComputerDAO;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerPager implements Pager {

    private List<Computer> list;
    private int limit = 40;
    private int offset = 0;
    private int index = 1;
    private int rowTotal;

    /**
     * ComputerPager constructor. Fill the list with the first 40 computers.
     */
    public ComputerPager() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        this.list = computerDAO.fetch(limit, offset);
        this.rowTotal = computerDAO.count();
    }

    public ComputerPager(int page, int itemPerPage) {

        this.index = page;
        this.limit = itemPerPage;
        this.offset = (page - 1) * itemPerPage;
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        this.list = computerDAO.fetch(limit, offset);
        this.rowTotal = computerDAO.count();
    }

    @Override
    public List<Computer> getList() {
        return list;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int countPages() {
        return rowTotal / limit + 1;
    }

    @Override
    public boolean hasNext() {
        if (offset < rowTotal - limit) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrevious() {
        if (offset >= limit) {
            return true;
        }
        return false;
    }

    @Override
    public void next() {
        if (hasNext()) {
            offset += limit;
            index++;
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.getComputerDAO();
            list = computerDAO.fetch(limit, offset);
        }
    }

    @Override
    public void previous() {
        if (hasPrevious()) {
            offset -= limit;
            index--;
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.getComputerDAO();
            list = computerDAO.fetch(limit, offset);
        }
    }
}
