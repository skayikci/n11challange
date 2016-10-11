package com.n11.web.rest;

import com.n11.model.Track;
import com.n11.service.AgendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by serhat on 10/7/16.
 */
@RestController
@RequestMapping(AgendaResource.URL)
public class AgendaResource {
    static final String URL = "/api/agenda";

    private static final Logger log = LoggerFactory.getLogger(AgendaResource.class);

    AgendaService agendaService;

    @Autowired
    public void setAgendaService(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    /**
     * GET /  --> Get Track
     */
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<Track> getAgenda() throws CloneNotSupportedException {
        log.info("Request to get agenda");
        return agendaService.getAgenda();
    }
}
