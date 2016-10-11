package com.n11.service;

import com.n11.model.Conference;
import com.n11.repository.ConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by serhat on 10/4/16.
 */
@Service
@Transactional
public class ConferenceService {

    @Autowired
    ConferenceRepository conferenceRepository;

    private static final Logger log = LoggerFactory.getLogger(ConferenceService.class);


    public void save(Conference conference) {
        conferenceRepository.save(conference);
        log.debug("Created conference with : {}", conference);
    }

    @Transactional(readOnly = true)
    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public void delete(String id) {
        conferenceRepository.findById(id).ifPresent(
                o -> {
                    conferenceRepository.delete(id);
                    log.debug("Deleted conference with id: {}", id);
                }
        );
    }

    public void deleteAll() {
        conferenceRepository.deleteAll();
    }
}
