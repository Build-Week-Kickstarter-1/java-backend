package com.lambdaschool.usermodel;

import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.services.RoleService;
import com.lambdaschool.usermodel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
            Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");


        r1 = roleService.save(r1);
        r2 = roleService.save(r2);


        // admin, data, user
        User u1 = new User("admin",
                           "password");
        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1.getRoles()
                .add(new UserRoles(u1, r2));


        u1.getCampaigns()
                .add(new Campaign("Student Operated Fabrication Lab","Test", "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",5000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30),true, u1));
        u1.getCampaigns()
                .add(new Campaign("Strawberry Fields Café and Patisserie", "Test", "more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 50000, "CA", new Date(1989, 12, 06), new Date(1989, 12, 30),false, u1));
        u1.getCampaigns()
                .add(new Campaign("Operación Douve", "Test", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." ,100, "PT", new Date(1989, 12, 06), new Date(1989, 12, 30), true, u1));
        userService.save(u1);

        // data, user
        User u2 = new User("hungtruong",
                           "password");

        u2.getRoles()
                .add(new UserRoles(u2, r2));


        u2.getCampaigns()
                .add(new Campaign("Crap Amidst Hilarity, vol. 1","Games", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout." , 7500, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));
        u2.getCampaigns()
                .add(new Campaign("Kaleidoscope Man", "Film & Video", "Blurb with words go here", 60000, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false));
        u2.getCampaigns()
                .add(new Campaign("Operation: Make Stuff", "Games", "Blurb with more words go here to explain",200, "PT", new Date(1989, 12, 06), new Date(1989, 12, 30),true));


        userService.save(u2);

        // user
        User u3 = new User("tristanbrown",
                           "password");

        u3.getRoles()
                .add(new UserRoles(u3, r2));

        u3.getCampaigns()
                .add(new Campaign("A photobook of life in Japan","Photography", "My adventures in Japan",7500, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));
        u3.getCampaigns()
                .add(new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false));
        u3.getCampaigns()
                .add(new Campaign("Loony Tunes the Game", "Games", "The movie as a game", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));

        userService.save(u3);

        User u4 = new User("kalvinzhao",
                           "password");
        u4.getRoles()
                .add(new UserRoles(u4, r2));

        u4.getCampaigns()
                .add(new Campaign("Loony Tunes the Game", "Games", "The movie as a game", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));
        u4.getCampaigns()
                .add(new Campaign("This is a Test Name", "Games", "blurb goes here",25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));
        u4.getCampaigns()
                 .add(new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false));

        userService.save(u4);

        User u5 = new User("brandononeal",
                           "password");
        u5.getRoles()
                .add(new UserRoles(u5, r2));

        u5.getCampaigns()
                .add(new Campaign("Kickstarter Success Test", "Games", "Test blurb is going on here", 25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30),true));
        u5.getCampaigns()
                .add(new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30), false));

        userService.save(u5);

        User u6 = new User("ajwpdx",
                "password");
        u6.getRoles()
                .add(new UserRoles(u6, r2));

        u6.getCampaigns()
                .add(new Campaign("Triumph: The Card Game", "Games", "A card arranging strategy game",25000, "US", new Date(1989, 12, 06), new Date(1989, 12, 30), true));
        u6.getCampaigns()
                .add(new Campaign("Spirits Asunder", "Film & Video", "A movie about Spirits",600, "GB", new Date(1989, 12, 06), new Date(1989, 12, 30),false));

        userService.save(u6);


    }
}