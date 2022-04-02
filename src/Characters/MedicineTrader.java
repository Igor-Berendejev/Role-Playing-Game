package Characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MedicineTrader extends Trader{

    @Override
    public void sell(Player player) {
        // no need to sell medicine if player has maximum health
        if (player.getHealth() == Character.MAX_HEALTH){
            System.out.println("You have maximum HP");
        }
        else if (player.getGold() < 2) System.out.println("Sorry, you do not have enough money");
        else {
            printMenu();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input;
                int requestedHP;
                while (!(input = reader.readLine()).equalsIgnoreCase("E")) {
                    requestedHP = Integer.parseInt(input);
                    int neededGold = requestedHP * 2;
                    if (neededGold > player.getGold()) System.out.println("You have only " + player.getGold() +
                            " golden coins.");
                    else {
                        // if player did not request for HP that will be over maximum possible
                        if ((player.getHealth() + requestedHP) <= Character.MAX_HEALTH) {
                            player.heal(requestedHP);
                            player.setGold(player.getGold() - neededGold);
                            System.out.println("You now have " + player.getHealth() + " HP and " +
                                    player.getGold() + " golden coins left.");
                            break;
                        }
                        // if requested HP will be over maximum possible
                        // set HP to maximum and reduce gold according to sold HP
                        else {
                            player.heal(Character.MAX_HEALTH - player.getHealth());
                            player.setGold(player.getGold() -
                                    (neededGold - (Character.MAX_HEALTH - player.getHealth()) * 2));
                            System.out.println("You now have " + player.getHealth() + " HP and " +
                                    player.getGold() + " golden coins left.");
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for HP number. Please try again");
                sell(player);
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.println("Hello. 1HP costs 2 golden coins. How much HP do you want to buy?");
        System.out.println("Type 'E' to go back to main menu");
    }
}
