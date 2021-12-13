package com.ayush.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Event implements Comparable<Event>{

    private String name;
    private List<User> userParticipants;
    private TimeSlot timeSlot;

    @Override
    public int compareTo(Event o) {
        return this.timeSlot.compareTo(o.timeSlot);
    }
}
