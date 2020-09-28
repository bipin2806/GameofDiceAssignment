package com.greatlearning.utils;

import com.greatlearning.model.*;

import java.util.*;
import java.util.stream.*;

public class PrintUtil {

    private static final PrintUtil INSTANCE = new PrintUtil();

    public static PrintUtil getInstance() {
        return INSTANCE;
    }

    public void displayCurrentRankTable(List<Player> players) {
        List<Player> playersSortedByRank = players.stream().sorted(Comparator.comparingInt(Player::getRank)).collect(Collectors.toList());
        System.out.println("\n\t\t------  CURRENT RANK TABLE  -------");
        playersSortedByRank.forEach(player -> System.out.println("\t\t\t " + player.getName() + ", Rank-" + player.getRank() + ", Total-" + player.getTotal()));
        System.out.println("\t\t-----------------------------------");
    }

    public void displayFinalResults(List<Player> players) {
        GameUtil gameUtil = GameUtil.getInstance();
        List<Player> playersSortedByRank = players.stream().sorted(Comparator.comparingInt(Player::getRank)).collect(Collectors.toList());
        System.out.println("\n\t\t-*-*-*-*-  GAME COMPLETE  -*-*-*-*-");
        System.out.println("\t\t---------  FINAL RESULTS  ---------");
        playersSortedByRank.forEach(player -> System.out.println("\t\t\t " + player.getName() + ", Rank-" + player.getRank() + ", Total-" + player.getTotal()));
        System.out.println("\t\t-----------------------------------");

        //Close the scanner
        gameUtil.getScanner().close();
    }

}