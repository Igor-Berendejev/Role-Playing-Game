public abstract class Character {
    private String name;
    private int health;
    private int gold;
    private int skill;
    private int experience;
    private int strength;
    private final int MAX_HEALTH = 100;

    public Character(String name, int health, int gold, int skill, int experience, int strength) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.skill = skill;
        this.experience = experience;
        this.strength = strength;
    }

    public boolean isDestroyed(){
        return health <= 0;
    }

    protected void damage (int damage){
        health -= damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getSkill() {
        return skill;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
