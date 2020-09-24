package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "campaigns")
@JsonIgnoreProperties(value = "hasgoal")
public class Campaign
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long campaignid;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String blurb;

    @Column
    @Transient
    public boolean hasgoal = false;
    private long goal;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private Date launchdate;

    @Column(nullable = true)
    private Date deadline;

    @Column(nullable = true)
    private boolean successprediction;

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "campaigns", allowSetters = true)
    private User user;

    //default constructor
    public Campaign()
    {
    }

    public Campaign(String name, String category, String blurb, long goal, String country, Date launchdate, Date deadline, boolean successprediction, User user)
    {
        this.name = name;
        this.category = category;
        this.blurb = blurb;
        this.goal = goal;
        this.country = country;
        this.launchdate = launchdate;
        this.deadline = deadline;
        this.successprediction = successprediction;
        this.user = user;
    }

    public Campaign(String name, String category, String blurb, long goal, String country, Date launchdate, Date deadline, boolean successprediction)
    {
        this.name = name;
        this.category = category;
        this.blurb = blurb;
        this.goal = goal;
        this.country = country;
        this.launchdate = launchdate;
        this.deadline = deadline;
        this.successprediction = successprediction;

    }

    public long getCampaignid()
    {
        return campaignid;
    }

    public void setCampaignid(long campaignid)
    {
        this.campaignid = campaignid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public long getGoal()
    {
        return goal;
    }

    public void setGoal(long goal)
    {
        hasgoal = true;
        this.goal = goal;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String currency)
    {
        this.country = currency;
    }

    public Date getLaunchdate()
    {
        return launchdate;
    }

    public void setLaunchdate(Date launchdate)
    {
        this.launchdate = launchdate;
    }

    public boolean isSuccessprediction()
    {
        return successprediction;
    }

    public void setSuccessprediction(boolean successprediction)
    {
        this.successprediction = successprediction;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public String getBlurb()
    {
        return blurb;
    }

    public void setBlurb(String blurb)
    {
        this.blurb = blurb;
    }
}
