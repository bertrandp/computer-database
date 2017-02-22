package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import service.impl.ComputerService;
import service.utils.ComputerValidationException;

import static org.junit.Assert.assertNotNull;
import static service.utils.ComputerValidator.COMPANY_ID_IS_NOT_A_VALID_NUMBER;
import static service.utils.ComputerValidator.DATE_FORMAT_IS_INVALID;
import static service.utils.ComputerValidator.DISCONTINUED_DATE_IS_BEFORE_INTRODUCED_DATE;
import static service.utils.ComputerValidator.ID_IS_EMPTY;
import static service.utils.ComputerValidator.ID_IS_NOT_A_VALID_NUMBER;
import static service.utils.ComputerValidator.NAME_IS_EMPTY;

/**
 * Created by bpestre on 22/02/17.
 */
public class ComputerServiceTest {

    private IComputerService computerService;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        computerService = new ComputerService();
    }

    @Test
    public void throwExceptionWhenGetComputerWithEmptyId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_EMPTY);
        computerService.get("  ");
    }

    @Test
    public void throwExceptionWhenGettingComputerWithInvalidId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(ID_IS_NOT_A_VALID_NUMBER);
        computerService.get("invalid input");
    }

    @Test
    public void getComputerWithValidId() throws ComputerValidationException {
        assertNotNull(computerService.get("1"));
    }

    @Test
    public void throwExceptionWhenAddingComputerWithInvalidDate() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(DATE_FORMAT_IS_INVALID);
        computerService.add("test","invalid date","","");
    }

    @Test
    public void throwExceptionWhenAddingComputerWithInvalidCompanyId() throws ComputerValidationException {
        thrown.expect(ComputerValidationException.class);
        thrown.expectMessage(COMPANY_ID_IS_NOT_A_VALID_NUMBER);
        computerService.add("test","","","invalid id");
    }

    @Test
    public void addComputerWithValidData() throws ComputerValidationException {
        assertNotNull(computerService.add("test","01/01/2000","01/01/2001","1"));
    }

}
