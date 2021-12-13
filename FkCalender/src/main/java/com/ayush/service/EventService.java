package com.ayush.service;

import com.ayush.dao.EventDao;
import com.ayush.exception.UserUnavailableException;
import com.ayush.models.Event;
import com.ayush.models.Team;
import com.ayush.models.TimeSlot;
import com.ayush.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventService {

    private EventDao eventDao;

    private UserService userService;

    private TeamService teamService;

    public EventService(UserService userService,TeamService teamService){
        this.eventDao = EventDao.getInstance();
        this.userService = userService;
        this.teamService = teamService;
    }

    public void createEvent(String eventName, List<User> users , List<Team>teams, int repsNeeded,Date start, Date end){

        List<User>participants = new ArrayList<>();
        for (User user : users){
            if (userAvailable(user,start, end))participants.add(user);
            else throw new UserUnavailableException();
        }

        for (Team team : teams){

            int repsGot = 0;
            for (User teamUser : team.getTeamMembers()){
                if (!users.contains(teamUser) && userAvailable(teamUser,start,end)){
                    participants.add(teamUser);
                    repsGot++;

                    if (repsGot==repsNeeded)break;
                }
            }
            if (repsGot<repsNeeded)throw new UserUnavailableException();
        }

        eventDao.addEvent(eventName, participants,start, end);
        for (User user : participants)userService.addEvent(user,eventDao.getEvent(eventName));
    }


    private boolean userAvailable(User user, Date start, Date end){
        Event dummyEvent = new Event("dummy",null,new TimeSlot(start, end));
        Event event = user.getEvents().lower(dummyEvent);
        if (event!=null && event.getTimeSlot().getEnd().compareTo(dummyEvent.getTimeSlot().getStart())>0)return false;
        return true;
    }
}
