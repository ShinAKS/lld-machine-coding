package com.ayush.dao;

import com.ayush.models.*;

import java.util.*;

public class EventDao {

    private static EventDao INSTANCE;

    private Map<String, Event> eventMap;
    private EventDao(){
        this.eventMap = new HashMap<>();
    }

    public static EventDao getInstance(){
        if (INSTANCE==null)INSTANCE = new EventDao();
        return INSTANCE;
    }
    public void addEvent(String name, List<User> users, Date start, Date end){

        List<User>participants = new ArrayList<>();

        users.stream().forEach(user->participants.add(user));

        this.eventMap.put(name, new Event(name,participants,new TimeSlot(start,end)));

    }

    public Event getEvent(String name){
        return this.eventMap.get(name);
    }
}
