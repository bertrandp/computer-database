package fr.ebiz.cdb.service.impl;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.dto.ComputerDTO;
import fr.ebiz.cdb.model.dto.ComputerPagerDTO;
import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.validation.ComputerValidator;
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
public class ComputerService implements IComputerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public ComputerDTO getDTO(Integer id) {
        return ComputerMapper.mapToComputerDTO(computerDAO.fetchById(id));
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
    @Transactional
    public ComputerPagerDTO fetchComputerList(ComputerPagerDTO page) {

        page.setCount(computerDAO.count(page.getSearch()));

        int pageToValidate = page.getCurrentPage();
        page.setCurrentPage(ComputerValidator.validateCurrentPageMax(page.getCount(), page.getLimit(), pageToValidate));

        int offset = (page.getCurrentPage() - 1) * page.getLimit();
        page.setList(ComputerMapper.mapToComputerDTOList(computerDAO.fetchPage(page.getLimit(), offset, page.getSearch(), page.getOrder(), page.getColumn())));

        return page;
    }

}
