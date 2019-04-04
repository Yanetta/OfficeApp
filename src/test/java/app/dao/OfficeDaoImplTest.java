package app.dao;

import app.dao.OfficeDaoImpl;
import app.model.Offices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:test.properties")
public class OfficeDaoImplTest {

    private static final Offices EXISTING_OFFICE = new Offices(BigDecimal.valueOf(555));
    private static final Offices NOT_EXISTING_OFFICE = new Offices(BigDecimal.valueOf(222));

    @Autowired
    private OfficeDaoImpl officeDao;
    @Autowired
    private DataSource dataSource;

    @Before
    public void initDb() {
        Resource initSchema = new ClassPathResource("schema.sql");
        Resource data = new ClassPathResource("data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    @Test
    public void testGetAllOffices() {
        Set<Offices> officesSet = officeDao.getAllOffices();
        assertTrue(officesSet.size() >= 2);
    }

    @Test
    public void testInsertOffice() {
        officeDao.insertOffice(NOT_EXISTING_OFFICE);
    }

    @Test
    public void testUpdateOffice(){
        EXISTING_OFFICE.setCity("Kyiv");
        EXISTING_OFFICE.setTarget(BigDecimal.valueOf(800));
        officeDao.updateOffice(EXISTING_OFFICE);
    }

    @Test
    public void testDeleteOffice(){
        officeDao.deleteOffice(EXISTING_OFFICE.getOffice());
    }

    @Test
    public void testFindOfficeById(){
        Offices offices = officeDao.findOfficeById(EXISTING_OFFICE.getOffice());
        assertNotNull(offices);
    }

    @Test
    public void testFindOfficeByIdNotExist(){
        Offices offices = officeDao.findOfficeById(NOT_EXISTING_OFFICE.getOffice());
        assertNull(offices);
    }
}
