package app.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import javax.sql.DataSource;
import app.model.Offices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class OfficeControllerTest {
    private static final int STATUS_OK = 200;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        Resource initSchema = new ClassPathResource("schema.sql");
        Resource data = new ClassPathResource("data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllOffices() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/office")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

    @Test
    public void testGetExistOfficeById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/office/{id}", "555")).andDo(print()).andReturn();
        Offices offices = mapper.readValue(mvcResult.getResponse().getContentAsString(), Offices.class);
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertNotNull(offices);
    }

    @Test
    public void testGetNotExistOfficeById() throws Exception {
        assertNotEquals(422, mockMvc.perform(get("/office/{id}", "9999")).andDo(print()).andReturn().getResponse().getStatus());
        assertTrue(mockMvc.perform(get("/customer/{id}", "9999")).andDo(print()).andReturn().getResponse().getContentAsString().length() == 0);
    }

    @Test
    public void testInsertOffice() throws Exception {
        String json = mapper.writeValueAsString(new Offices());
        MvcResult mvcResult = mockMvc.perform(post("/office").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteOffice() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/office/{id}", "777")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateOfficeById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/office/{id}", "5555").param("target", "400")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }
}

