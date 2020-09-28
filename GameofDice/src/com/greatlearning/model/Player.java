package com.greatlearning.model;


public class Player implements Comparable<Player> {

    private String name;
    private int prevDiceValue;
    private int total;
    private boolean skipChance;
    private int rank;
    private boolean completedGame;
    private int order;

    public Player(String name, int order) {
        super();
        this.name = name;
        this.order = order;
        init();
    }

    private void init() {
        this.rank = 1;
        this.total = 0;
        this.prevDiceValue = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrevDiceValue() {
        return prevDiceValue;
    }

    public void setPrevDiceValue(int prevDiceValue) {
        this.prevDiceValue = prevDiceValue;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isSkipChance() {
        return skipChance;
    }

    public void setSkipChance(boolean skipChance) {
        this.skipChance = skipChance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isCompletedGame() {
        return completedGame;
    }

    public void setCompletedGame(boolean completedGame) {
        this.completedGame = completedGame;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(Player o) {
        return getOrder() - o.getOrder();
    }

}