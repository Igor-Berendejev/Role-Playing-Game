import java.io.*;

public class Game implements Runnable {
    private Monster monster;
    private Player player;
    private MedicineTrader medTrader = new MedicineTrader();

    public void run(){
        System.out.println("Welcome! Type in your name");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            player = new Player(reader.readLine());
            String userChoice;
            printMenu();
            // the game continues until exit option is not chosen by player
            while (!(userChoice = reader.readLine()).equals("3")) {
                if (userChoice.equals("1")) medTrader.sell(player);
                else if (userChoice.equals("2")){
                    makeFight();
                    if (player.isDestroyed())break; // end the game if player has 0 HP after the fight
                }
                // in case player input does not match menu points
                else System.out.println("Invalid input");
                printMenu();
            }
            System.out.println("Goodbye!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void printMenu(){
        System.out.println(player);
        System.out.println("Where do you want to go?");
        System.out.println("1.To the trader\n2.To the dark forest\n3.Exit");
    }

    // creates a monster (Goblin or Skeleton with 50/ 50 probability) and
    // starts a fight in separate thread
    private void makeFight(){
        if (Math.random() > 0.5) monster = new Goblin();
        else monster = new Skeleton();
        Thread fight = new Thread(new Fight(player, monster));
        fight.start();
        try {
            fight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
