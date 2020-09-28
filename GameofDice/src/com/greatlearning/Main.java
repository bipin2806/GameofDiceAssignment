package com.greatlearning;

import com.greatlearning.constants.*;
import com.greatlearning.model.*;
import com.greatlearning.utils.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            //Error if number of arguments is not equals 2
            if (args.length != Constants.TWO) {
                System.err.println(Constants.INVALID_ARG_ERR_MSG);
                return;
            }

            //Number of Players
            int n = Integer.parseInt(args[0]);
            //Points to accumulate
            int m = Integer.parseInt(args[1]);

            if (n < 1 || m < 1) {
                System.err.println(Constants.INVALID_ARG_ERR_MSG);
                return;
            }

            GameUtil gameUtil = GameUtil.getInstance();
            PrintUtil printUtil = PrintUtil.getInstance();

            //List of players with randomly assigned order
            List<Player> players = gameUtil.getPlayerListBasedOnOrder(n);

            System.out.println("\n\t\t-*-*-*-*-  GAME STARTS  -*-*-*-*-");
            while (true) {
                for (Player player : players) {
                    //Check if player has skip penalty or accumulated points
                    if (!player.isCompletedGame() && !player.isSkipChance()) {
                        System.out.format(Constants.ROLL_DICE_MSG, player.getName());

                        //Do the dice roll
                        int diceNumber = gameUtil.rollDice();
                        player.setTotal(player.getTotal() + diceNumber);

                        diceNumber = checkIfDiceGetsOneOrSix(m, gameUtil, player, diceNumber);

                        System.out.format(Constants.DICE_VALUE_MSG, player.getName(), diceNumber);
                        player.setPrevDiceValue(diceNumber);

                        gameUtil.assignRanks(players);
                        printUtil.displayCurrentRankTable(players);

                        //When any player accumulate the total points, mark player as completed
                        if (player.getTotal() >= m) {
                            player.setCompletedGame(true);
                        }
                    } else if (player.isSkipChance()) {
                        System.out.format(Constants.ONE_PENALTY_MSG, player.getName());
                        player.setSkipChance(false);
                        player.setPrevDiceValue(0);
                    }
                }
                //Break out of loop when all players complete the game
                if (players.stream().filter(Player::isCompletedGame).count() == players.size())
                    break;
            }

            printUtil.displayFinalResults(players);
        } catch (NumberFormatException nfe) {
            System.err.println("Number Format Exception occurred(hint: Check input arguments), msg: " + nfe.getMessage());
        } catch (Exception e) {
            System.err.println("General Exception occurred, msg: " + e.getMessage());
        }

    }

    private static int checkIfDiceGetsOneOrSix(int m, GameUtil gameUtil, Player player, int diceNumber) {
        if (diceNumber == Constants.ONE && player.getPrevDiceValue() == Constants.ONE) {
            System.err.format(Constants.CONSECUTIVE_ONE_MSG, player.getName(), diceNumber);
            player.setSkipChance(true);
        }

        if (diceNumber == Constants.SIX) {
            System.out.format(Constants.SIX_BONUS_MSG, player.getName(), diceNumber, player.getName());
            if (player.getTotal() < m) {
                //Do the dice roll
                diceNumber = gameUtil.rollDice();
                player.setTotal(player.getTotal() + diceNumber);
            }
        }
        return diceNumber;
    }
}