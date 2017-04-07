package fr.ebiz.cdb.webapp.rest;

import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.mapper.ComputerMapper;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.webapp.rest.exception.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
@RestController
@RequestMapping("api/computer")
public class ComputerRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerRestController.class);

    @Autowired
    private IComputerService computerService;

    /**
     * Add computer.
     *
     * @param computerDTO computerDTO
     * @param result      result
     */
    @PostMapping
    public void addComputer(@RequestBody @Valid ComputerDTO computerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            throw new InvalidParameterException(result.getAllErrors());
        }

        LOGGER.debug("addComputer parameters : " + computerDTO);
        Computer computer = ComputerMapper.mapToComputer(computerDTO);
        if (!computerService.add(computer)) {
            throw new PersistenceException("Failed to add computer : " + computerDTO);
        }
    }

    /**
     * Get computer by id.
     *
     * @param id id
     * @return computer
     */
    @GetMapping("{id}")
    public ComputerDTO getComputer(@PathVariable("id") int id) {
        Computer computer = computerService.get(id);
        if (computer == null) {
            throw new NoSuchElementException("Computer not found. (id=" + id + ")");
        }
        return ComputerMapper.mapToComputerDTO(computer);
    }

    /**
     * Edit computer by id.
     *
     * @param id          id
     * @param computerDTO computerDTO
     * @param result      result
     */
    @PutMapping("{id}")
    public void editComputer(@PathVariable("id") int id, @RequestBody @Valid ComputerDTO computerDTO, BindingResult result) {

        if (result.hasErrors()) {
            LOGGER.error(result.getAllErrors().toString());
            throw new InvalidParameterException(result.getAllErrors());
        }

        computerDTO.setId(id);

        LOGGER.debug("editComputer parameters : " + computerDTO);
        Computer computer = ComputerMapper.mapToComputer(computerDTO);
        if (!computerService.update(computer)) {
            throw new PersistenceException("Failed to update computer : " + computerDTO);
        }
    }

    /**
     * Delete computer by id.
     *
     * @param id id
     */
    @DeleteMapping("{id}")
    public void deleteComputers(@PathVariable("id") int id) {

        LOGGER.debug(" Computer to delete : " + id);
        if (!computerService.delete(new ArrayList<>(Arrays.asList(id)))) {
            throw new NoSuchElementException("Computer not found. (id=" + id + ")");
        }
    }
}
