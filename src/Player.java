public class Player extends Character implements Fighter{

    public Player (String name){
        super(name, 100, 10, 30, 10, 10);
    }
    @Override
    public int attack(Character character){
        if (getSkill()*3 > Math.random()*100){
            character.damage(getStrength());
            if (character.isDestroyed()){
                setGold(character.getGold());
                setExperience(character.getExperience());
            }
            return getStrength();
        }
        return 0;
    }
}
