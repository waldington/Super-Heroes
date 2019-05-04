package superheroes.util;

import superheroes.model.AbstractHero;
import superheroes.model.Team;

public class InvalidHeroTeamException extends Exception {

    public InvalidHeroTeamException(String message) {
        super(message);
    }

    public InvalidHeroTeamException (Team team, AbstractHero hero){
        this("Different team type encountered while adding hero " + hero.getType() + " to team with type "+  team.getType());
    }
}
