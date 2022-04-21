# Role-Playing-Game

A simple console RPG.

![image](https://user-images.githubusercontent.com/90723839/164559672-5ba6c391-586f-45df-a16a-2e8f3b5a66d2.png)


At the beginning of the game player has to submit his name and is the offered to chose an option from the menu:
1. Go to the trader
2. Go to the dark forest
3. Exit
Going to the trader will lead the player to a Medicine trader where he can buy additional HP for golden coins.

Going to the dark forest will lead the player to a fight with a monster (Goblin or Skeleton will be picked randomly by the game).
A fight (class Fight implementing Callable) will start in a separate thread. 
Player and Monster will be hitting eachother until one of them wins.
The probability that a hit lands depends on the fighter skill (a parameter of the class), damage made by the hit depends on the fighters strength:
```java
public int attack(Character character){
        if (getSkill()*3 > Math.random()*100){
            character.damage(getStrength());
            // if player wins he gets monsters gold, experience and
            // player skill is set to 2*experience
            if (character.isDestroyed()){
                setGold(getGold() + character.getGold());
                setExperience(getExperience() + character.getExperience());
                setSkill(getExperience() * 2);
            }
            return getStrength();
        }
        return 0;
    }
```

If the player wins, he gets monsters golden coins and experience, otherwise the game ends.

After the fight the player (if he won) is again offered the menu options.
