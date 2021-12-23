public class Fight implements Runnable{
    private Player player;
    private Monster monster;

    public Fight(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    @Override
    public void run(){
        boolean playerAttack = true;
        while (!player.isDestroyed() && !monster.isDestroyed()){
            if (playerAttack){
                System.out.println(player.getName() + " attacked with damage " + player.attack(monster));
                playerAttack = false;
            }
            else{
                System.out.println(monster.getName() + " attacked with damage " + monster.attack(player));
                playerAttack = true;
            }

            if (player.isDestroyed()) System.out.println("You lost. Game over");

            if (monster.isDestroyed()){
                System.out.println("You Won!");
                System.out.println("You earned " + monster.getGold() + " gold and gained " +
                        monster.getExperience() + " experience");
                System.out.println("You have " + player.getHealth() + " hp left.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
