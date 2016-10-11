package com.n11.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by serhat on 10/7/16.
 */
@Embeddable
public class AgendaSlot implements Serializable{
    private int start;

    private int end;

    private int duration;

    private String conferenceName;

    private int order;

    public AgendaSlot() {
    }


    public int getStart() {
        return start;
    }

    public AgendaSlot setStart(int start) {
        this.start = start;
        return this;
    }

    public int getEnd() {
        return end;
    }

    public AgendaSlot setEnd(int end) {
        this.end = end;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AgendaSlot setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public AgendaSlot setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public AgendaSlot setOrder(int order) {
        this.order = order;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("start", start)
                .append("end", end)
                .append("duration", duration)
                .append("conferenceName", conferenceName)
                .append("order", order)
                .toString();
    }
}
