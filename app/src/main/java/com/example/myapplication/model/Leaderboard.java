package com.example.myapplication.model;


public class Leaderboard {
    private double[] playerScoreList;
    private String[] playerList;
    private String[] timeList;
    private static Leaderboard leaderboard;
    private Leaderboard() {
        playerScoreList = new double[]{0, 0, 0, 0, 0, 0};
        playerList = new String[]{"NA", "NA", "NA", "NA", "NA", "NA"};
        timeList = new String[]{"NA", "NA", "NA", "NA", "NA", "NA"};
        //size = 0;
    }

    public static synchronized Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            leaderboard =  new Leaderboard();
        }
        return leaderboard;
    }

    public void addPlayer(Player player) {

        // Adds/replaces last element of arrays, then sorts
        playerList[5] = player.getName();
        playerScoreList[5] = player.getScore();
        timeList[5] = player.getTime();
        sortLists();
    }

    public void sortLists() {

        // Iterates once backwards through array to sort
        for (int i = 5; i > 0; i--) {
            if (playerScoreList[i] >= playerScoreList[i - 1]) {
                double stemp = playerScoreList[i];
                playerScoreList[i] = playerScoreList[i - 1];
                playerScoreList[i - 1] = stemp;

                String ptemp = playerList[i];
                playerList[i] = playerList[i - 1];
                playerList[i - 1] = ptemp;

                String ttemp = timeList[i];
                timeList[i] = timeList[i - 1];
                timeList[i - 1] = ttemp;
            }
        }
    }

    public String[] returnPlayerList() {
        return playerList;
    }

    public double[] returnPlayerScoreList() {
        return playerScoreList;
    }

    public String[] returnTimeList() {
        return timeList;
    }

    public void resetLeaderboard() {
        leaderboard = null;
    }
}
