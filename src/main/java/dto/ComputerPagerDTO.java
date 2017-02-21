package dto;

import dao.DAOFactory;
import dao.IComputerDAO;

import java.util.List;

/**
 * Created by bpestre on 21/02/17.
 */
public class ComputerPagerDTO {

    private List<ComputerDTO> list;

    /**
     * ComputerPagerDTO constructor. Fetch list of computerDTO.
     * @param page the page index
     * @param limit the limit
     */
    public ComputerPagerDTO(int page, int limit) {
        int offset = (page - 1) * limit;
        DAOFactory daoFactory = DAOFactory.getInstance();
        IComputerDAO computerDAO = daoFactory.getComputerDAO();
        this.list = computerDAO.fetchDTO(limit, offset);
    }

    public List getList() {
        return list;
    }

}
