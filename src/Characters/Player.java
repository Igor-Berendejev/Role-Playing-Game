package Characters;

public class Player extends Character implements Fighter{

    public Player (String name){
        super(name, 100, 50, 30, 10, 10);
    }
    // returns the damage caused by this player attack
    // that equals to player strength
    // or 0 if the attack was unsuccessful
    @Override
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
}
