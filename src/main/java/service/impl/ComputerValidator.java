package main.java.service.impl;

import main.java.dao.DAOFactory;
import main.java.dao.IComputerDAO;
import main.java.model.Company;
import main.java.model.Computer;
import main.java.service.ICompanyService;
import main.java.service.IComputerService;
import main.java.service.IComputerValidator;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidator implements IComputerValidator {

    @Override
    public void validateName(String name, Computer computer) throws ComputerValidationException {
        if(name == null || name.trim().isEmpty()) {
            throw new ComputerValidationException("Name is empty");
        } else if(name.length() >= 255) {
            throw new ComputerValidationException("Name is too long");
        }
        computer.setName(name);
    }

    @Override
    public void validateIntroducedDate(Date introduced, Computer computer) {
        computer.setIntroduced(introduced);
    }

    @Override
    public void validateDiscontinuedDate(Date discontinued, Computer computer) throws ComputerValidationException {
        if(discontinued != null) {
            if(discontinued.before(computer.getIntroduced())){
                throw new ComputerValidationException("Discontinued date is before introduced date");
            }
        }
        computer.setDiscontinued(discontinued);
    }

    @Override
    public void validateCompanyName(Company company, Computer computer) throws ComputerValidationException {
        if(company != null) {
            if(company.getName() == null || company.getName().trim().isEmpty()) {
                company = null;
            } else if (company.getName().length() >= 255){
                throw new ComputerValidationException("Company name is too long");
            } else {
                ICompanyService companyService = new CompanyService();
                if(!companyService.alreadyExists(company.getName())) {
                    throw new ComputerValidationException("Company name doesn't exists");
                }
            }
        }
        computer.setCompany(company);
    }

    @Override
    public void validateId(String id) throws ComputerValidationException {
        if(id == null || id.trim().isEmpty()) {
            throw new ComputerValidationException("Id is empty");
        } else {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(id);
            if(m.find()) {
                throw new ComputerValidationException("Id is not a valid number");
            }
            DAOFactory daoFactory = DAOFactory.getInstance();
            IComputerDAO computerDAO = daoFactory.ComputerDAO();

            if(computerDAO.fetchById(Integer.valueOf(id)) == null) {
                throw new ComputerValidationException("Id " + id + " does not exist");
            }
        }
    }


}
