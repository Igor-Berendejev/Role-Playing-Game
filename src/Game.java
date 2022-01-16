import java.io.*;

public class Game implements Runnable {
    private Monster monster;
    private Player player;
    private MedicineTrader medTrader = new MedicineTrader();

    public void run(){
        System.out.println("Welcome! Type in your name");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        player = new Player(getInput(reader));
        boolean isGameOver = false;
        while (!isGameOver) {
            printMenu();
            String userChoice = getInput(reader);
            if (userChoice.equals("1")) medTrader.sell(player);
            else if (userChoice.equals("2")){
                makeFight();
                if (player.isDestroyed()) isGameOver = true; // end the game if player has 0 HP after the fight
            }
            else if(userChoice.equals("3")) isGameOver = true; // end the game if user chooses Exit option
            // in case player input does not match menu options
            else System.out.println("Invalid input");
        }
        System.out.println("Goodbye!");
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

    private String getInput(BufferedReader reader){
        String input;
        try{
            input = reader.readLine();
        } catch (IOException e){
            input = "Sorry, could not read your input";
            e.printStackTrace();
        }
        return input;
    }
}
