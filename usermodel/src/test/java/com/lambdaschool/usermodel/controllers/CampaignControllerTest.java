package com.lambdaschool.usermodel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.services.CampaignService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
public class CampaignControllerTest
{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CampaignService campaignService;

    List<Campaign> campaignList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {

        Date test = new Date();
        System.out.println(test);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1.setRoleid(1);
        r2.setRoleid(2);

        User u1 = new User("admin", "password");
        User u2 = new User("hungtroung", "password");
        User u3 = new User("tristanbrown", "password");
        User u4 = new User("kalvinzhao", "password");
        User u5 = new User("brandononeal", "password");
        User u6 = new User("ajwpdx", "password");

        u1.setUserid(20);

        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1.getRoles()
                .add(new UserRoles(u1, r2));


        Campaign c1 = new Campaign("Student Operated Fabrication Lab","Test", "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",5000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30),true, u1);
        c1.setCampaignid(41);
        Campaign c2 = new Campaign("Strawberry Fields Café and Patisserie", "Test", "more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 50000, "CA", new Date(1989, 12, 06), new Date(1989, 12, 30),false, u1);
        c2.setCampaignid(42);
        Campaign c3 = new Campaign("Operación Douve", "Test", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." ,100, "PT", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u1);
        c3.setCampaignid(43);

        u2.setUserid(21);

        u2.getRoles()
                .add(new UserRoles(u2, r2));

        Campaign c4 = new Campaign("Crap Amidst Hilarity, vol. 1","Games", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." , 7500, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u2);
        Campaign c5 = new Campaign("Kaleidoscope Man", "Film & Video", "Blurb with words go here", 60000, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false, u2);
        Campaign c6 = new Campaign("Operation: Make Stuff", "Games", "Blurb with more words go here to explain",200, "PT", new Date(1989, 12, 06), new Date(1989, 12, 30),true, u2);

        u3.setUserid(22);

        u3.getRoles()
                .add(new UserRoles(u3, r2));

        Campaign c7 = new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false, u3);
        Campaign c8 = new Campaign("A photobook of life in Japan","Photography", "My adventures in Japan",7500, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u3);
        Campaign c9 = new Campaign("Loony Tunes the Game", "Games", "The movie as a game", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u3);


        u4.setUserid(23);

        u4.getRoles()
                .add(new UserRoles(u4, r2));

        Campaign c10 = new Campaign("Loony Tunes the Game", "Games", "The movie as a game", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u4);
        Campaign c11 = new Campaign("This is a Test Name", "Games", "blurb goes here",25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u4);
        Campaign c12 = new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false, u4);


        u5.setUserid(24);

        u5.getRoles()
                .add(new UserRoles(u5, r2));

        Campaign c13 = new Campaign("Kickstarter Success Test", "Games", "Test blurb is going on here", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30),true, u5);
        Campaign c14 = new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false, u5);


        u6.setUserid(25);

        u6.getRoles()
                .add(new UserRoles(u6, r2));

        Campaign c15 = new Campaign("Triumph: The Card Game", "Games", "A card arranging strategy game",25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u6);
        Campaign c16 = new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false, u6);


        campaignList.add(c1);
        campaignList.add(c2);
        campaignList.add(c3);
        campaignList.add(c4);
        campaignList.add(c5);
        campaignList.add(c6);
        campaignList.add(c7);
        campaignList.add(c8);
        campaignList.add(c9);
        campaignList.add(c10);
        campaignList.add(c11);
        campaignList.add(c12);
        campaignList.add(c13);
        campaignList.add(c14);
        campaignList.add(c15);
        campaignList.add(c16);

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllCampaigns() throws Exception
    {

        String apiUrl = "/campaigns/all";
        Mockito.when(campaignService.findAll()).thenReturn(campaignList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(campaignList);
        System.out.println(er);
        assertEquals(er, tr);
    }

    @Test
    public void getCampaignById()
    {
    }

    @Test
    public void deleteCampaignBy()
    {
    }

    @Test
    public void addNewCampaign()
    {
    }

    @Test
    public void updateFullCampaign()
    {
    }

    @Test
    public void updatePartCampaign()
    {
    }
}