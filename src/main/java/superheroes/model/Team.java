package superheroes.model;

import lombok.Getter;
import superheroes.util.InvalidHeroTeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Team {

    @Getter()
    private TeamType type;
    @Getter
    private List<AbstractHero> heroes;
    @Getter
    private AbstractHero teamLeader;
    @Getter
    private Side side;
    private boolean isTeamBuffed;

    public Team(TeamType type) {
        this.type = type;
        this.heroes = new ArrayList<>();
    }

    public void addHeroToTeam(AbstractHero hero) throws InvalidHeroTeamException {
        if (!hero.type.equals(this.type)) {
            throw new InvalidHeroTeamException(this, hero);
        }
        if (this.heroes.isEmpty() || this.teamLeader.getPower() < hero.getPower()) {
            this.teamLeader = hero;
        }
        this.heroes.add(hero);
        this.moreSuperHero();
        this.moreGoodPowerThan();
    }

    public int getTeamPower() {
        return this.heroes.stream()
                .mapToInt(value -> value.getPower())
                .sum();
    }

    public void buffTeamPower() {
        if (!isTeamBuffed) {
            isTeamBuffed = true;
            this.heroes.stream()
                    .filter(hero -> hero instanceof SuperHero)
                    .forEach(hero -> hero.getStats().increaseDefense(10));

            this.heroes.stream()
                    .filter(hero -> hero instanceof Villain)
                    .forEach(hero -> hero.getStats().increaseHealth(10));
        }
    }

    private void moreSuperHero() {
        long numberOfSuperHero = this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero)
                .count();

        long numerOfVillain = heroes.size() - numberOfSuperHero;

        if (numberOfSuperHero > numerOfVillain) {
            this.side = Side.GOOD;
        } else if (numberOfSuperHero < numerOfVillain) {
            this.side = Side.EVIL;
        } else {
            this.side = Side.UNKNOWN;
        }
    }

    private void moreGoodPowerThan() {
        int sumOfSuperHeroPower = this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero)
                .mapToInt(value -> value.getPower())
                .sum();

        int sumOfVillainPower = this.heroes.stream()
                .filter(hero -> hero instanceof Villain)
                .mapToInt(value -> value.getPower())
                .sum();

        this.setTeamSide(sumOfSuperHeroPower, sumOfVillainPower);
    }

    private void setTeamSide(long superHeroesValue, long vilainValue) {
        if (superHeroesValue > vilainValue) {
            this.side = Side.GOOD;
        } else if (superHeroesValue < vilainValue) {
            this.side = Side.EVIL;
        } else {
            this.side = Side.UNKNOWN;
        }
    }

    public boolean isAnyheroesStillAlive() {
        return this.heroes.stream()
                .anyMatch(AbstractHero::isAlive);
    }

    public AbstractHero getRandomAliveHero() {
        List<AbstractHero> aliveHeroes = this.heroes.stream()
                .filter(AbstractHero::isAlive)
                .collect(Collectors.toList());
        return aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
    }

    private Stream<AbstractHero> getSuperHeroesStream() {
        return this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero);
    }

    private Stream<AbstractHero> getVillainStream() {
        return this.heroes.stream()
                .filter(hero -> hero instanceof Villain);
    }
}

