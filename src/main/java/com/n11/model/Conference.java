package com.n11.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * Created by serhat on 10/4/16.
 */
@Document(collection = "conference")
@CompoundIndex(
        name = "conference_unique",
        unique = true,
        dropDups = true,
        def = "{'name' : 1, 'duration' : 1}"
)
public class Conference implements Serializable, Cloneable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private Integer duration;

    public Conference() {
    }

    public String getId() {
        return id;
    }

    public Conference setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Conference setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Conference setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("duration", duration)
                .append("name", name)
                .append("id", id)
                .toString();
    }
}
