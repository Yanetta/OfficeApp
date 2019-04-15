package app.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import app.dao.OfficeDaoImpl;
import app.model.Offices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {
    @Spy
    @InjectMocks
    private OfficeService officeService = new OfficeServiceImpl();
    @Mock
    private OfficeDaoImpl officeDaoImpl;

    private static final Offices EXISTING_OFFICE = new Offices(BigDecimal.valueOf(555));
    private static final Offices NOT_EXISTING_OFFICE = new Offices(BigDecimal.valueOf(222));

    @Test
    public void testGetAllOffices() {
        Set<Offices> officesSet = new HashSet<Offices>();
        officesSet.add(EXISTING_OFFICE);
        officesSet.add(NOT_EXISTING_OFFICE);
        doReturn(officesSet).when(officeDaoImpl).getAllOffices();
        Set<Offices> result = officeService.getAllOffices();
        assertTrue(officesSet.containsAll(result) && result.containsAll(officesSet));
    }

    @Test
    public void testFindOrderById() {
        doReturn(EXISTING_OFFICE).when(officeDaoImpl).findOfficeById(any());
        Offices result = officeService.findOfficeById(BigDecimal.valueOf(555));
        assertEquals(EXISTING_OFFICE, result);
    }

    @Test
    public void testInsertOffice() {
        doReturn(true).when(officeDaoImpl).insertOffice(NOT_EXISTING_OFFICE);
        officeService.insertOffice(NOT_EXISTING_OFFICE);
        verify(officeDaoImpl, times(1)).insertOffice(NOT_EXISTING_OFFICE);
    }

    @Test
    public void testUpdateOffice() {
        doReturn(true).when(officeDaoImpl).updateOffice(NOT_EXISTING_OFFICE);
        officeService.updateOffice(EXISTING_OFFICE);
        verify(officeDaoImpl, times(1)).updateOffice(EXISTING_OFFICE);
    }

    @Test
    public void testDeleteOrder() {
        doReturn(true).when(officeDaoImpl).deleteOffice(EXISTING_OFFICE.getOffice());
        officeService.deleteOffice(EXISTING_OFFICE.getOffice());
        verify(officeDaoImpl, times(1)).deleteOffice(EXISTING_OFFICE.getOffice());
    }
}