import Characters.*;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Game implements Runnable {
    private Monster monster;
    private Player player;
    private MedicineTrader medTrader = new MedicineTrader();
    ExecutorService executor = Executors.newSingleThreadExecutor();

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
                if (player.isDestroyed()) {isGameOver = true; executor.shutdown();} // end the game if player has 0 HP after the fight
            }
            else if(userChoice.equals("3")) {isGameOver = true; executor.shutdown();}  // end the game if user chooses Exit option
            // in case player input does not match menu options
            else System.out.println("Invalid input");
        }
        executor.shutdown();
        System.out.println("Goodbye!");
    }

    private void printMenu(){
        System.out.println(player);
        System.out.println("Where do you want to go?");
        System.out.println("1.To the trader\n2.To the dark forest\n3.Exit");
    }

    // creates a monster (Characters.Goblin or Characters.Skeleton with 50/ 50 probability) and
    // starts a fight in separate thread
    private void makeFight(){
        if (Math.random() > 0.5) monster = new Goblin();
        else monster = new Skeleton();
        CallableFight fight = new CallableFight(player,monster);
        Future<Fighter> result = executor.submit(fight);
        try {
            Fighter winner = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
