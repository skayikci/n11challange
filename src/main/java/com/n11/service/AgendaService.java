package com.n11.service;

import com.n11.model.AgendaSlot;
import com.n11.model.Conference;
import com.n11.model.Track;
import com.n11.util.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by serhat on 10/7/16.
 */
@Service
@Transactional
public class AgendaService {

    private static final Logger log = LoggerFactory.getLogger(AgendaService.class);
    private static HashMap<String, List<Conference>> sumMap = new HashMap<>();

    @Autowired
    ConferenceService conferenceService;

    public List<Track> getAgenda() throws CloneNotSupportedException {
        List<Track> trackList = new ArrayList<>();

        //get list of conferences
        List<Conference> conferenceList = conferenceService.findAll();
        conferenceList.sort((c1, c2) -> c2.getDuration().compareTo(c1.getDuration()));
        log.info("Sorted conference list: {}", conferenceList);
        int sum = conferenceList.stream().mapToInt(o -> o.getDuration()).sum();
        int numberOfTracks = sum / ServiceConstants.NINE_HOURS_IN_MINUTES;
        if (numberOfTracks == 0) {
            numberOfTracks = (sum + 60) / ServiceConstants.NINE_HOURS_IN_MINUTES;
        }
        //create a track
        if (numberOfTracks > 0) {
            Track track;
            for (int i = 0; i < numberOfTracks; i++) {
                track = new Track();
                track.setName("Track" + (i + 1));
                track.setRemainingTime(ServiceConstants.NINE_HOURS_IN_MINUTES);
                track.setSlots(new ArrayList<>());
                //each of the slots has duration, start and end times
                //the initial slots start at 9AM and ends at 18PM...
                //the lunch slot is at 12PM...
                fillSlot(track, 180, conferenceList);
                AgendaSlot lunchSlot = new AgendaSlot();
                lunchSlot.setConferenceName(ServiceConstants.LUNCH);
                lunchSlot.setStart(12);
                lunchSlot.setEnd(13);
                lunchSlot.setDuration(60);
                allocateSlot(lunchSlot, track);
                fillSlot(track, 300, conferenceList);
                trackList.add(track);
            }
        } else {
            log.error("Cannot create a track with given slots...Required Duration at least: {}, Now: {}", ServiceConstants.NINE_HOURS_IN_MINUTES, (sum + 60));
        }

        log.info("Number of tracks: {}", numberOfTracks);

        return trackList;

    }

    private void allocateSlot(AgendaSlot slot, Track track) {
        int remainingTime = track.getRemainingTime() - slot.getDuration();
        if (remainingTime < 0) {
            log.error("No remaining time for slot : {}", track.getName());
            return;
        }
        track.setRemainingTime(remainingTime);
        track.getSlots().add(slot);
        log.info("Track after allocation: {}", track);
    }

    private void fillSlot(Track track, int duration, List<Conference> conferenceList) throws CloneNotSupportedException {
        sumUp(conferenceList, duration);
        Set<String> keySet = sumMap.keySet();
        Optional<String> key = keySet.stream().findAny();
        List<Conference> conferencesToRemove = sumMap.get(key.get());
        conferenceList.removeAll(conferencesToRemove);
        conferencesToRemove.forEach(conference -> {
            allocateSlot(new AgendaSlot()
                    .setConferenceName(conference.getName())
                    .setDuration(conference.getDuration()), track);
        });
        log.info("track agenda: {} key: {}", track, key.get());

    }


    static List<Conference> sumDurations(List<Conference> numbers, int target, List<Conference> partial) {
        int s = 0;
        for (Conference x : partial) {
            s += x.getDuration();
        }
        if (s == target) {
            sumMap.put(String.valueOf(partial.hashCode()), partial);
            return partial;
        }
        if (s >= target) {
            return numbers;
        }
        for (int i = 0; i < numbers.size(); i++) {
            List<Conference> remaining = new ArrayList<>();
            Conference n = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) remaining.add(numbers.get(j));
            List<Conference> partial_rec = new ArrayList<>(partial);
            partial_rec.add(n);
            sumDurations(remaining, target, partial_rec);
        }
        return numbers;
    }

    static List<Conference> sumUp(List<Conference> numbers, int target) throws CloneNotSupportedException {
        List<Conference> copy = new ArrayList<>(numbers.size());

        for (Conference foo: numbers) {
            copy.add((Conference) foo.clone());
        }
        return sumDurations(copy, target, new ArrayList<>());
    }

}
