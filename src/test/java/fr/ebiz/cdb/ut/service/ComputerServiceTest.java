package fr.ebiz.cdb.ut.service;

import fr.ebiz.cdb.dao.IComputerDAO;
import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.exception.ComputerException;
import fr.ebiz.cdb.service.exception.InputValidationException;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


/**
 * Created by bpestre on 22/02/17.
 */
@RunWith(PowerMockRunner.class)
public class ComputerServiceTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    private IComputerService computerService;
    @Mock
    private IComputerDAO mockComputerDAO;

    @Before
    public void setUp() {
        computerService = new ComputerService();
        computerService.setComputerDAO(mockComputerDAO);
    }

    @Test
    public void testCount() {
        PowerMockito.when(mockComputerDAO.count()).thenReturn(10);
        assertEquals(10, computerService.count());
    }

    @Test
    public void testGet() throws ComputerException, InputValidationException {
        PowerMockito.when(mockComputerDAO.fetchById(8888)).thenReturn(new Computer("tutu", LocalDate.of(2000, 2, 2), LocalDate.of(2000, 3, 3), new Company("ibm")));
        assertEquals(new Computer("tutu", LocalDate.of(2000, 2, 2), LocalDate.of(2000, 3, 3), new Company("ibm")), computerService.get("8888"));
    }

    @Test
    public void throwExceptionWhenIdDoesNotExists() throws ComputerException, InputValidationException {
        thrown.expect(ComputerException.class);
        PowerMockito.when(mockComputerDAO.fetchById(0)).thenReturn(null);
        computerService.get("0");
    }

    @Test
    public void testGetDTO() throws ComputerException, InputValidationException {
        PowerMockito.when(mockComputerDAO.fetchDTOById(8888)).thenReturn(new ComputerDTO(12, "tutu", "11/12/1234", "11/12/1234", "ibm"));
        assertEquals(new ComputerDTO(12, "tutu", "11/12/1234", "11/12/1234", "ibm"), computerService.getDTO("8888"));
    }


    /*@Rule
    public final ExpectedException thrown = ExpectedException.none();
    private IComputerService computerService;

    @Before
    public void init() {
        computerService = new ComputerService();
    }


    @Test
    public void getComputerWithValidId() throws InputValidationException {
        assertNotNull(computerService.get("1"));
    }

    @Test
    public void throwExceptionWhenAddingComputerWithInvalidDate() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(DATE_FORMAT_IS_INVALID);
        computerService.add("test", "invalid date", "", "");
    }

    @Test
    public void throwExceptionWhenAddingComputerWithInvalidCompanyId() throws InputValidationException {
        thrown.expect(InputValidationException.class);
        thrown.expectMessage(ID_IS_NOT_A_VALID_NUMBER);
        computerService.add("test", "", "", "invalid id");
    }*/

    /*@Test
    public void addComputerWithValidData() throws InputValidationException {
        assertNotNull(computerService.add("test", "01/01/2000", "01/01/2001", "1"));
    }*/

}
