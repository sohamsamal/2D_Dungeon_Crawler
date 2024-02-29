package com.example.myapplication;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.model.Leaderboard;
import com.example.myapplication.model.Player;
import com.example.myapplication.viewmodel.PlayerViewModel;

public class leaderboardTest {

    Leaderboard leaderboard;

    @Test
    public void leaderboardUpdate() {

        Leaderboard leaderboard = Leaderboard.getLeaderboard();
        Player player = Player.getPlayer();
        player.setName("third");
        player.setScore(50);
        player.setTime("blank");
        leaderboard.addPlayer(player);

        player.resetPlayer();
        player = Player.getPlayer();
        player.setName("first");
        player.setScore(100);
        player.setTime("blank");
        leaderboard.addPlayer(player);


        player.resetPlayer();
        player = Player.getPlayer();
        player.setName("second");
        player.setScore(75);
        player.setTime("blank");
        leaderboard.addPlayer(player);

        String[] expected = new String[]{"first", "second", "third", "NA", "NA", "NA"};

        leaderboard.resetLeaderboard();
        assertEquals(expected, leaderboard.returnPlayerList());
    }



    @Test
    public void playerSingleton() {
        PlayerViewModel player = PlayerViewModel.getPlayerVM();
        player.setNameVM("delete");
        PlayerViewModel newPlayer = PlayerViewModel.getPlayerVM();
        newPlayer.setNameVM("replace");

        String expected = "replace";
        assertEquals(expected, player.getNameVM());
    }

}
