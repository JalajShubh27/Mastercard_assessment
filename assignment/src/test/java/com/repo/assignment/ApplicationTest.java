package com.repo.assignment;
import com.repo.assignment.resourceloader.DataService;
import com.repo.assignment.util.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
public class ApplicationTest extends AssignmentApplicationTests{

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void connectedCitiesTest() throws Exception {
        String origin="Albany";
        String destination = "New York";
        String origin1="Philadelphia";
        String destination1 = "Boston";
        DataService dataService = new DataService();

        DataService dataService1 = new DataService();

        MvcResult response = mockMvc.perform(get("/connected")
                .param("origin","Philadelphia").param("destination","New York"))
                .andReturn();

        Assert.assertEquals(HttpStatus.OK.value(),response.getResponse().getStatus());
        Assert.assertEquals("No",dataService.showPath(origin,destination));
        Assert.assertEquals("Yes",dataService1.showPath(origin1,destination1));
    }
}