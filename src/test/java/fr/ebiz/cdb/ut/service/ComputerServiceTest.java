package fr.ebiz.cdb.ut.service;

import fr.ebiz.cdb.persistence.IComputerDAO;
import fr.ebiz.cdb.service.IComputerService;
import fr.ebiz.cdb.service.impl.ComputerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;


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
        computerService = ComputerService.INSTANCE;
        computerService.setComputerDAO(mockComputerDAO);
    }

    // TODO fix this test, mock connection
    /*@Test
    public void testGet() throws ComputerException, InputValidationException {
        PowerMockito.when(mockComputerDAO.fetchById(8888)).thenReturn(new Computer("tutu", LocalDate.of(2000, 2, 2), LocalDate.of(2000, 3, 3), new Company("ibm")));
        assertEquals(new Computer("tutu", LocalDate.of(2000, 2, 2), LocalDate.of(2000, 3, 3), new Company("ibm")), computerService.get("8888"));
    }*/

    /*@Test
    public void throwExceptionWhenIdDoesNotExists() throws ComputerException, InputValidationException {
        thrown.expect(ComputerException.class);
        PowerMockito.when(mockComputerDAO.fetchById(0)).thenReturn(null);
        computerService.get("0");
    }*/

    /*@Test
    public void testGetDTO() throws ComputerException, InputValidationException {
        PowerMockito.when(mockComputerDAO.fetchDTOById(8888)).thenReturn(new ComputerDTO(12, "tutu", "11/12/1234", "11/12/1234", "ibm"));
        assertEquals(new ComputerDTO(12, "tutu", "11/12/1234", "11/12/1234", "ibm"), computerService.getDTO("8888"));
    }*/

}
