package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Campaign;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.services.CampaignService;
import com.lambdaschool.usermodel.services.HelperFunctions;
import com.lambdaschool.usermodel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController
{

    @Autowired
    CampaignService campaignService;

    @Autowired
    UserService userService;

    @Autowired
    HelperFunctions helperFunctions;

    //GET http://localhost:2019/campaigns/all
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/all",
            produces = {"application/json"})
    public ResponseEntity<?> listAllCampaigns()
    {
        List<Campaign> allCampaigns = campaignService.findAll();
        return new ResponseEntity<>(allCampaigns, HttpStatus.OK);
    }

    // GET http://localhost:2019/campaigns/campaign/17
    @GetMapping(value = "/campaign/{campaignid}",
            produces = {"application/json"})
    public ResponseEntity<?> getCampaignById(
            HttpServletRequest request,
            @PathVariable
                    Long campaignid)
    {
        Campaign c = campaignService.findCampaignById(campaignid);
        return new ResponseEntity<>(c,
                HttpStatus.OK);
    }

    // DELETE http://localhost:2019/campaigns/campaign/17
    @DeleteMapping(value = "/campaign/{campaignid}")
    public ResponseEntity<?> deleteCampaignBy(
            @PathVariable
                    long campaignid)
    {
        campaignService.delete(campaignid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST http://localhost:2019/campaigns/campaign
    @PostMapping(value = "/campaign", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewCampaign(@Valid @RequestBody Campaign newcampaign)
    {
        newcampaign.setCampaignid(0);
        newcampaign.setUser(helperFunctions.getCurrentUser());
        Campaign returnCampaign = campaignService.save(newcampaign);


        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCampaignURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{campaignid}")
                .buildAndExpand(newcampaign.getCampaignid())
                .toUri();
        responseHeaders.setLocation(newCampaignURI);

        return new ResponseEntity<>(returnCampaign,
                responseHeaders,
                HttpStatus.CREATED);

    }

    // PUT http://localhost:2019/campaigns/campaign/17
    @PutMapping(value = "campaign/{campaignid}", consumes ="application/json", produces = "application/json")
    public ResponseEntity<?> updateFullCampaign(@Valid @RequestBody Campaign updatedcampaign, @PathVariable long campaignid)
    {
        updatedcampaign.setUser(helperFunctions.getCurrentUser());
        updatedcampaign = campaignService.update(updatedcampaign, campaignid);
        return new ResponseEntity<>(updatedcampaign, HttpStatus.OK);
    }

    // PUT http://localhost:2019/campaigns/campaign/17
    @PatchMapping(value = "campaign/{campaignid}", consumes ="application/json", produces = "application/json")
    public ResponseEntity<?> updatePartCampaign(@RequestBody Campaign updatedcampaign, @PathVariable long campaignid)
    {
        updatedcampaign.setUser(helperFunctions.getCurrentUser());
        updatedcampaign = campaignService.update(updatedcampaign, campaignid);
        return new ResponseEntity<>(updatedcampaign, HttpStatus.OK);
    }


}


