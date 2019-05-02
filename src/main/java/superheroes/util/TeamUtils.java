package superheroes.util;

import superheroes.model.AbstractHero;
import superheroes.model.Team;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TeamUtils {

    Team team;


    public static boolean compareTeamPower (Team teamA, Team teamB){
        if (teamA.getTeamPower() > teamB.getTeamPower()){
            return true;
        }
        return false;
    }

    public static boolean compareTeamPowerAfterBuffed (Team teamA, Team teamB){
        teamA.buffTeamPower();
        teamB.buffTeamPower();
        compareTeamPower(teamA, teamB);
        return false;
    }
}
