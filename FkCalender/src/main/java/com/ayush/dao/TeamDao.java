package com.ayush.dao;

import com.ayush.models.Team;
import com.ayush.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamDao {

    private static TeamDao INSTANCE;

    private Map<String, Team> teamMap;
    private TeamDao(){
        this.teamMap = new HashMap<>();
    }

    public static TeamDao getInstance(){
        if (INSTANCE==null)INSTANCE = new TeamDao();
        return INSTANCE;
    }


    public void createTeam(String teamName, List<User>members){
        this.teamMap.put(teamName,new Team(teamName,members));
    }

    public Team getTeam(String teamName){
        return this.teamMap.get(teamName);
    }
}
