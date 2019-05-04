package superheroes.util;


import superheroes.model.Team;

public class TeamUtils {

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
