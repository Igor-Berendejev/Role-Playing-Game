import java.io.*;

public class Game implements Runnable {
    private Monster monster;
    private Player player;

    public void run(){
        System.out.println("Welcome! Type in your name");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String playerName = reader.readLine();
            player = new Player(playerName);
            String userChoice;
            printMenu();
            while (!(userChoice = reader.readLine()).equals("3")) {
                if (userChoice.equals("1")) System.out.println("The trader is not at work yet");
                else if (userChoice.equals("2")){
                    makeFight();
                    if (player.isDestroyed())break;
                }
                else System.out.println("Invalid input");
                printMenu();
            }
            System.out.println("Goodbye!");
        }catch (IOException e){

        }
    }

    private void printMenu(){
        System.out.println("Where do you want to go?");
        System.out.println("1.To the trader\n2.To the dark forest\n3.Exit");
    }

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
