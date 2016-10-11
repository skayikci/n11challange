package com.n11.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * Created by serhat on 10/7/16.
 */
@Document(collection = "agenda")
public class Track {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    @Embedded
    private List<AgendaSlot> slots;

    private Integer remainingTime;

    public Track() {
    }

    public String getId() {
        return id;
    }

    public Track setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Track setName(String name) {
        this.name = name;
        return this;
    }

    public List<AgendaSlot> getSlots() {
        return slots;
    }

    public Track setSlots(List<AgendaSlot> slots) {
        this.slots = slots;
        return this;
    }

    public Integer getRemainingTime() {
        return remainingTime;
    }

    public Track setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("slots", slots)
                .append("remainingTime", remainingTime)
                .toString();
    }
}
