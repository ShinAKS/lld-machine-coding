package com.ayush.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class TimeSlot implements Comparable<TimeSlot>{

    private Date start;
    private Date end;

    @Override
    public int compareTo(TimeSlot o) {
        return this.start.compareTo(o.start);
    }
}
