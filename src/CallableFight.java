import Characters.Fighter;
import Characters.Monster;
import Characters.Player;

import java.util.concurrent.Callable;

public class CallableFight implements Callable {
    private Player player;
    private Monster monster;

    public CallableFight(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    @Override
    public Fighter call() throws Exception {
        boolean playerAttack = true;
        Fighter winner = null;
        // fight continues until player and monster are alive
        while (!player.isDestroyed() && !monster.isDestroyed()){
            if (playerAttack){
                System.out.println(player.getName() + " attacked with damage " + player.attack(monster));
                playerAttack = false;
            }
            else{
                System.out.println(monster.getName() + " attacked with damage " + monster.attack(player));
                playerAttack = true;
            }

            if (player.isDestroyed()) {System.out.println("You lost. Game over"); winner = monster;}

            if (monster.isDestroyed()){
                System.out.println("You Won!");
                System.out.println("You earned " + monster.getGold() + " gold and gained " +
                        monster.getExperience() + " experience");
                System.out.println("You have " + player.getHealth() + " hp left.");
                winner = player;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return winner;
    }
}
