package com.greatlearning.utils;

import com.greatlearning.constants.*;
import com.greatlearning.model.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class GameUtil {

    private final static GameUtil INSTANCE = new GameUtil();
    private final Scanner scanner = new Scanner(System.in);

    public Scanner getScanner() {
        return scanner;
    }

    public static GameUtil getInstance() {
        return INSTANCE;
    }

    public List<Player> getPlayerListBasedOnOrder(int n) {
        List<Integer> randIntegers = getRandomIntegerList(n);
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Player player = new Player("Player-" + i, randIntegers.get(i - 1));
            players.add(player);
        }
        Collections.sort(players);
        return players;
    }

    public int rollDice() {
        String key = scanner.nextLine();
        while (!key.equalsIgnoreCase(Constants.KEY_TO_ROLL)) {
            System.err.println(Constants.INVALID_KEY_TO_ROLL_MSG);
            key = scanner.nextLine();
        }
        return ThreadLocalRandom.current().nextInt(Constants.ONE, Constants.SIX + 1);
    }

    public void assignRanks(List<Player> players) {
        //Filter players list currently playing
        List<Player> playersStillPlaying = players.stream().filter(player -> !player.isCompletedGame()).collect(Collectors.toList());
        int playersCompletedCount = players.size() - playersStillPlaying.size();

        //Sort players based on total points in descending order
        playersStillPlaying.sort((Player p1, Player p2) -> p2.getTotal() - p1.getTotal());

        Player currentTopperPlaying = playersStillPlaying.get(0);
        currentTopperPlaying.setRank(playersCompletedCount + 1);

        for (int index = 1; index < playersStillPlaying.size(); index++) {
            Player current = playersStillPlaying.get(index);
            //Set same rank if having same points
            if (currentTopperPlaying.getTotal() == current.getTotal()) {
                current.setRank(currentTopperPlaying.getRank());
            } else {
                current.setRank(currentTopperPlaying.getRank() + 1);
            }
            currentTopperPlaying = current;
        }
    }

    private List<Integer> getRandomIntegerList(int n) {
        //Create list of random integers between 1 to n
        List<Integer> randIntegers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            randIntegers.add(i);
        }
        Collections.shuffle(randIntegers);
        return randIntegers;
    }
}