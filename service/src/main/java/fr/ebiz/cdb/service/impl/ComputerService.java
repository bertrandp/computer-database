package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.binding.ComputerPagerDTO;
import fr.ebiz.cdb.binding.mapper.ComputerMapper;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ebiz on 14/02/17.
 */
@Service
@Transactional
public class ComputerService implements IComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private IComputerDAO computerDAO;

    /**
     * Validate the given page number and return it, return a default value in case the input is not valid.
     *
     * @param count       the total number of entries
     * @param limit       the limit value
     * @param currentPage the page number to validate
     * @return the validated page number
     */
    private static int validateCurrentPageMax(int count, int limit, int currentPage) {
        int maximumPage = count / limit + 1;
        if (currentPage > maximumPage) {
            currentPage = maximumPage;
        }
        return currentPage;
    }

    @Override
    public Computer get(Integer id) {
        return computerDAO.fetchById(id);
    }

    @Override
    public boolean add(Computer computer) {
        return computerDAO.add(computer);
    }

    @Override
    public boolean update(Computer computer) {
        return computerDAO.update(computer);
    }

    @Override
    public boolean delete(List<Integer> idList) {
        for (Integer id : idList) {
            computerDAO.delete(id);
        }
        return true;
    }

    @Override
    public ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) {

        page.setCount(computerDAO.count(page.getSearch()));

        int pageToValidate = page.getPage();
        page.setPage(validateCurrentPageMax(page.getCount(), page.getLimit(), pageToValidate));

        int offset = (page.getPage() - 1) * page.getLimit();
        page.setList(ComputerMapper.mapToComputerDTOList(computerDAO.fetchPage(page.getLimit(), offset, page.getSearch(), page.getOrder(), page.getColumn())));

        return page;
    }


}
