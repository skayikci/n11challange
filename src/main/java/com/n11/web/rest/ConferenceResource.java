package com.n11.web.rest;

import com.n11.model.Conference;
import com.n11.service.ConferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by serhat on 10/4/16.
 */
@RestController
@RequestMapping(ConferenceResource.URL)
public class ConferenceResource {
    static final String URL = "/api/conference";
    private static final Logger log = LoggerFactory.getLogger(ConferenceResource.class);

    ConferenceService conferenceService;


    @Autowired
    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    /**
     * POST /create  --> Create a new conference and save it in the database.
     */
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void create(@RequestBody Conference conference) {
        log.info("Request to create a conference with param: {}", conference);
        conferenceService.save(conference);
    }

    /**
     * GET /find-all  --> Get all conferences
     */
    @RequestMapping(
            value = "/find-all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Conference> findAll() {
        log.info("Request to get all conferences");
        return conferenceService.findAll();
    }

    /**
     * DELETE /delete  --> Delete a conference.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void delete(@PathVariable String id) {
        log.info("Request to delete a conference with param: {}", id);
        conferenceService.delete(id);
    }

    /**
     * DELETE /delete  --> Delete all conferences.
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void deleteAll() {
        log.info("Request to delete all conferences");
        conferenceService.deleteAll();
    }


}
