package com.ayush.models;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class User extends Participant{

    private TimeSlot workingHours;

    private Optional<Team> teams;

    private TreeSet<Event> events;

    public User(String name, Date start, Date end) {
        super(name);
        this.workingHours = new TimeSlot(start,end);
        this.teams = Optional.empty();
        this.events = new TreeSet<>();
    }
}
