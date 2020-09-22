package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.repository.CampaignRepository;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "campaignService")
public class CampaignServiceImpl implements CampaignService
{
    //Autowires
    @Autowired
    CampaignRepository campaignrepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    UserService userService;

    @Autowired
    HelperFunctions helperFunctions;



    @Override
    public List<Campaign> findAll()
    {
        List<Campaign> list = new ArrayList<>();
        campaignrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Campaign findCampaignById(long id)
    {
        return campaignrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign with id " + id + " not found!"));
    }

    @Override
    public void delete(long id)
    {
        if (campaignrepos.findById(id)
                .isPresent())
        {
            campaignrepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException("Campaign with id " + id + " not found!");
        }
    }

    @Transactional
    @Override
    public Campaign save(Campaign campaign)
    {
        {
            Campaign newCampaign = new Campaign();

            if (campaign.getCampaignid() != 0)
            {
                Campaign finalCampaign = campaign;
                campaign = campaignrepos.findById(campaign.getCampaignid())
                        .orElseThrow(() -> new ResourceNotFoundException("Campaign id " + finalCampaign.getCampaignid() + " not found!"));

            }

            newCampaign.setName(campaign.getName());
            newCampaign.setCategory(campaign.getCategory());
            newCampaign.setGoal(campaign.getGoal());
            newCampaign.setCurrency(campaign.getCurrency());
            newCampaign.setLaunchdate(campaign.getLaunchdate());
            newCampaign.setSuccessprediction(campaign.isSuccessprediction());
            newCampaign.setUser(campaign.getUser());

            return campaignrepos.save(newCampaign);
        }
    }

    @Transactional
    @Override
    public Campaign update(Campaign campaign, long campaignid)
    {
        Campaign currentCampaign = campaignrepos.findById(campaignid)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign id " + campaignid + " not found!"));

        if (campaign.getName() != null)
        {
            currentCampaign.setName(campaign.getName());
        }

        if (campaign.getCategory() != null)
        {
            currentCampaign.setCategory(campaign.getCategory());
        }

        if (campaign.getCurrency() !=null)
        {
            currentCampaign.setCurrency(campaign.getCurrency());
        }

        if (campaign.getLaunchdate() != null)
        {
            currentCampaign.setSuccessprediction(campaign.isSuccessprediction());
        }

        if (campaign.hasgoal)
        {
            currentCampaign.setGoal(campaign.getGoal());
        }

        currentCampaign.setUser(campaign.getUser());
        currentCampaign.setCampaignid(campaignid);



        return currentCampaign;
    }


    @Transactional
    @Override
    public void deleteAll()
    {
        campaignrepos.deleteAll();
    }
}


