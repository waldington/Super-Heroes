package superheroes.model;

import lombok.Getter;
import lombok.Setter;
import superheroes.util.InvalidHeroTeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Team {

    @Getter
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
        if (!hero.type.equals(this.type))
            throw new InvalidHeroTeamException(this, hero);
        if (this.heroes.isEmpty() || this.teamLeader.getPower() < hero.getPower()) {
            this.teamLeader = hero;
        }
            this.heroes.add(hero);

            this.moreSuperHero();
            this.moreGoodPowerThan();

        }


//
//    metoda ze zlapaniem bledu od razu w metodzie  - to jest blad poniewaz blad obslugujemy w klasie,
//      gdzie odpalamy  nasza metode

//    public void addHeroToTeam(AbstractHero hero) {
//        try {
//            if (!hero.type.equals(this.type)) {
//                throw new InvalidHeroTeamException(" Hero type !equals Team type");
//            } else if (hero.type.equals(this.type)) {
//                if (this.heroes.isEmpty() || this.teamLeader.getPower() < hero.getPower()) {
//                    this.teamLeader = hero;
//                }
//                this.heroes.add(hero);
//                this.moreSuperHero();
//                this.moreGoodPowerThan();
//            }
//        } catch (InvalidHeroTeamException e) {
//            e.printStackTrace();
//        }
//    }


// Metoda dodawania bohatera do listy zwracajaca flage :D
//    public boolean addHeroToTeam(AbstractHero hero) {
//        if (hero.type.equals(this.type)) {
//            if (this.heroes.isEmpty() || this.teamLeader.getPower() < hero.getPower()) {    //sprawdzamy hero.getPower przy dodawaniu do listy heroes -teamLedera
//                this.teamLeader = hero;                                                 //eliminuje to metode getTeamLeader (szukanie przez stream)
//            }                                                   //powoduje to szybsze wykonanie algorytmu obliczeniowego
//            this.heroes.add(hero);                              //jezeli dodawany hero jest jedyny na lilscie to on jest teamLeaderem
//                                                                // a jak kolejny dodawany jest silniejszy ton on zostaje teamLeaderem.
//            this.moreSuperHero();       //wywolanie metody przy kazdym dodaniu bohatera
//            this.moreGoodPowerThan();   //wywolanie metody przy kazdym dodaniu bohatera
//            return true;
//        }
//        return false;
//    }
//    public AbstractHero getTeamLeader() {
//        final Optional<AbstractHero> maxPower = this.heroes.stream()
//                .sorted((h1, h2) -> {
//                    if (h1.getPower() > h2.getPower()){
//                        return 1;
//                    }else if (h1.getPower() < h2.getPower()){
//                        return -1;
//                    }
//                    return 0;
//                }).findFirst();
//        return maxPower.orElse(null);
//    }

    // inna metoda (bezpieczniejsza)

//    public AbstractHero getTeamLeader() {
//        final Optional<AbstractHero> maxPower = this.heroes.stream()
//                .max((o1, o2) -> {
//                    if (o1.getPower() > o2.getPower()) {
//                        return 1;
//                    } else if (o1.getPower() < o2.getPower()) {
//                        return -1;
//                    }
//                    return 0;
//                });
//        return maxPower.orElse(null);
//    }

    public int getTeamPower() {
        final int sumOfPower = this.heroes.stream()
                .mapToInt(value -> value.getPower())    // .mapToInt(AbstractHero :: getPower)
                .sum();
        return sumOfPower;
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
        final long numberOfSuperHero = this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero)
                .count();

        final long numerOfVillain = heroes.size() - numberOfSuperHero;  //alternatywna metoda do obliczenia liczby zloczyncow
//                    this.heroes.stream()                                  zaoszczedzamy czas i zasoby obliczeniowe sprzetu
//                    .filter(hero -> hero instanceof Villain)
//                    .count();

        if (numberOfSuperHero > numerOfVillain) {               // metoda standardowa do okreslania stron
            this.side = Side.GOOD;                              //mozna ja zamienic na metodesetTeamSide
        } else if (numberOfSuperHero < numerOfVillain) {
            this.side = Side.EVIL;
        } else {
            this.side = Side.UNKNOWN;
        }
    }

    private void moreGoodPowerThan() {
        final int sumOfSuperHeroPower = this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero)
                .mapToInt(value -> value.getPower())
                .sum();

        final int sumOfVillainPower = this.heroes.stream()
                .filter(hero -> hero instanceof Villain)
                .mapToInt(value -> value.getPower())
                .sum();

        this.setTeamSide(sumOfSuperHeroPower,sumOfVillainPower);   //wywolanie specjalnej metody do okreslania podzialu na strony
    }                                                               //zamiast standardowego ifa ja w metodzie wyzej

    private void setTeamSide (long superHeroesValue, long vilainValue){
        if (superHeroesValue > vilainValue) {
            this.side = Side.GOOD;
        } else if (superHeroesValue < vilainValue) {
            this.side = Side.EVIL;
        } else {
            this.side = Side.UNKNOWN;
        }
    }

    public boolean isAnyheroesStillAlive (){
        return this.heroes.stream()                             // alternatywna metoda
                .anyMatch(AbstractHero::isAlive);             // .filter(AbstractHero::isAlive)
                                                             //    .findAny()
                                                             //     .isPresent();
    }

    public AbstractHero getRandomAliveHero (){
        List<AbstractHero> aliveHeroes = this.heroes.stream()
                .filter(AbstractHero::isAlive)
                .collect(Collectors.toList());

        Random random = new Random();
        int randomNumber = random.nextInt(aliveHeroes.size());

        return aliveHeroes.get(randomNumber);
    }

    private Stream<AbstractHero> getSuperHeroesStream (){
        return this.heroes.stream()
                .filter(hero -> hero instanceof SuperHero);
    }

    private Stream<AbstractHero> getVillainStream (){
        return this.heroes.stream()
                .filter(hero -> hero instanceof Villain);
    }

}

