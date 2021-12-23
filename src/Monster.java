public class Monster extends Character implements Fighter{

    public Monster(String name, int health, int gold, int skill, int experience, int strength){
        super(name, health, gold, skill, experience, strength);
    }

    @Override
    public int attack(Character character){
        if (getSkill()*3 > Math.random()*100){
            character.damage(getStrength());
            return getStrength();
        }
        return 0;
    }
}