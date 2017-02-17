package model;

import dao.DAOFactory;
import dao.IComputerDAO;

import java.util.List;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerPager implements Pager {

    private List<Computer> list;
    private int rowPerPage = 40;
    private int limit = rowPerPage ;
    private int offset = 0;
    private int index = 1;
    private int rowTotal;

    public ComputerPager() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.ComputerDAO();
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
        return rowTotal/rowPerPage + 1;
    }

    @Override
    public boolean hasNext() {
        if(offset < rowTotal - rowPerPage) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrevious() {
        if(offset >= rowPerPage ) {
            return true;
        }
        return false;
    }

    @Override
    public void next() {
        if( hasNext() ) {
            offset += rowPerPage;
            index ++;
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.ComputerDAO();
            list = computerDAO.fetch(limit, offset);
        }
    }

    @Override
    public void previous() {
        if( hasPrevious() ) {
            offset -= rowPerPage;
            index --;
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.ComputerDAO();
            list = computerDAO.fetch(limit, offset);
        }
    }
}
