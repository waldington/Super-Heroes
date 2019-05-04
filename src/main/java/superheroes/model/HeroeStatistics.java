package superheroes.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroeStatistics {

    private int health;
    private int attack;
    private int defense;

    public void increaseHealth(int amount) {
        this.health += amount;
    }

    public void increaseAttack(int amount) {
        this.attack += amount;
    }

    public void increaseDefense(int amount) {
        this.defense += amount;
    }
}
