package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.repository.CampaignRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampaignServiceImplTest
{

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {

        MockitoAnnotations.initMocks(this);

        List<Campaign> myList = campaignService.findAll();
        for (Campaign c : myList)
        {
            System.out.println(c.getCampaignid() + " " + c.getName());
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(16, campaignService.findAll().size());
    }

    @Test
    public void b_findCampaignById()
    {
        assertEquals("Kaleidoscope Man", campaignService.findCampaignById(9).getName());
    }

    @Test
    public void c_delete()
    {
        assertEquals("A photobook of life in Japan", campaignService.findCampaignById(12).getName());
        campaignService.delete(12);
        assertEquals(15, campaignService.findAll().size());
    }


    @Test
    public void d_save()
    {
        User admin = userService.findUserById(3);

        String campaignTitle = "new thing";
        Campaign newCampaign = new Campaign(campaignTitle,"Technology", "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",5000, "US", new Date(), new Date(),true, admin);

        Campaign savedCampaign = campaignService.save(newCampaign);
        assertNotNull(newCampaign);
        assertEquals(campaignTitle, savedCampaign.getName());

    }

    @Test
    public void e_update()
    {
        Campaign c9 = campaignService.findCampaignById(9);
        c9.setGoal(123456);

        assertEquals(60000, campaignService.findCampaignById(9).getGoal());

        campaignService.update(c9, 9);

        assertEquals(123456, campaignService.findCampaignById(9).getGoal());
    }

    @Test
    public void f_deleteAll()
    {
    }
}