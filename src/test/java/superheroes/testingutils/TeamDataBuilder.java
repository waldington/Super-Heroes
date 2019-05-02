package superheroes.testingutils;

import superheroes.model.AbstractHero;
import superheroes.model.Team;
import superheroes.model.TeamType;
import superheroes.util.InvalidHeroTeamException;

import java.util.ArrayList;
import java.util.List;

public class TeamDataBuilder {

    private TeamType type;
    private List<AbstractHero> heroes;

    public TeamDataBuilder(){
        this.type = TeamType.BLUE;
        this.heroes = new ArrayList<>();
    }

    public TeamDataBuilder withHero (AbstractHero hero){
        heroes.add(hero);
        return  this;
    }

    public TeamDataBuilder withTypa (TeamType type){
        this.type = type;
        return this;
    }

    public Team build () throws InvalidHeroTeamException {
        Team team = new Team(type);
        for (AbstractHero hero : heroes) {
            team.addHeroToTeam(hero);
        }
        return team;
    }

    public TeamDataBuilder withWeakHeroes (){
       return this.withHero(new AbstractHeroDataBuilder()
        .witWeakStats()
        .withType(this.type)
        .buildSuperHero());
    }

    public Team buildPowerfullTeam () throws InvalidHeroTeamException {
        return this
                .withHero(new AbstractHeroDataBuilder()
                    .withStrongStats()
                    .withType(this.type)
                    .buildSuperHero())
                .build();
    }
}
