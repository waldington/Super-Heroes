package superheroes.util;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import superheroes.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class AbstractHeroUtils {


    public static AbstractHero createHeroFromString(String superHeroString)  {
        String[] heroParts = superHeroString.split(";");
        System.out.println(heroParts.length);
        if (heroParts.length != 6){
            throw new InvalidHeroDataException("Wrong number of fields expected 6 " +  " but was: " + heroParts.length );
        }
        int health;
        int attack;
        int defense;
    try {
        health = Integer.valueOf(heroParts[2]);
        attack = Integer.valueOf(heroParts[3]);
         defense = Integer.valueOf(heroParts[4]);
    } catch (NumberFormatException ex){
        throw new InvalidHeroDataException("One of hero statistics has wrong format");
    }

        if ("SuperHero".equals(heroParts[0])) {
            return new SuperHero(heroParts[1],
                    new HeroeStatistics(health, attack, defense),
                    TeamType.valueOf(heroParts[5]));
        } else {
            return new Villain(heroParts[1],
                    new HeroeStatistics(health, attack, defense),
                    TeamType.valueOf(heroParts[5]));
        }
    }

    public static void saveHeroesToFile(List<AbstractHero> heroes, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            heroes.forEach(hero -> printWriter.write(hero.parseToString() + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  List<AbstractHero> readHeroesFromFile (String fileName){
        List<AbstractHero> heroes =  new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line =  bufferedReader.readLine();
                while (line != null){
                    System.out.println(line+"jjjj");
                    createHeroFromString(line);
                    line = bufferedReader.readLine();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}