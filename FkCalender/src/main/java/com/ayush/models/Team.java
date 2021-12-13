package com.ayush.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Team extends Participant{

    private List<User>teamMembers;
    private List<Event> events;
    public Team(String name) {
        super(name);
        this.teamMembers = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public Team(String name, List<User>users){
        this(name);
        this.teamMembers = users;
    }
}
