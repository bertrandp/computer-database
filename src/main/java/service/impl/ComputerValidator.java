package service.impl;


import dao.DAOFactory;
import dao.IComputerDAO;
import model.Company;
import model.Computer;
import service.ICompanyService;
import service.IComputerValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ebiz on 16/02/17.
 */
public class ComputerValidator implements IComputerValidator {

    private static final int MAX_LENGTH = 255;

    @Override
    public void validateName(String name, Computer computer) throws ComputerValidationException {
        if(name == null || name.trim().isEmpty()) {
            throw new ComputerValidationException("Name is empty");
        } else if(name.length() >= MAX_LENGTH) {
            throw new ComputerValidationException("Name is too long");
        }
        computer.setName(name);
    }


    @Override
    public void validateIntroducedDate(LocalDate introduced, Computer computer) {
        computer.setIntroduced(introduced);
    }

    @Override
    public void validateDiscontinuedDate(LocalDate discontinued, Computer computer) throws ComputerValidationException {
        if(discontinued != null) {
            if(discontinued.isBefore(computer.getIntroduced())){
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
            } else if (company.getName().length() >= MAX_LENGTH){
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
