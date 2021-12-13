package com.ayush.service;

import com.ayush.dao.TeamDao;
import com.ayush.models.Team;

public class TeamService {

    private TeamDao teamDao;

    public TeamService(){
        this.teamDao = TeamDao.getInstance();
    }

    public Team getTeam(String teamName){
        return this.teamDao.getTeam(teamName);
    }



}
