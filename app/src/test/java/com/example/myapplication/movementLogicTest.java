package com.example.myapplication;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.myapplication.model.Obstacle;
import com.example.myapplication.viewmodel.PlayerViewModel;
import com.example.myapplication.model.DefaultMovement;
import com.example.myapplication.model.SlowMovement;

import java.util.ArrayList;


public class movementLogicTest {

    @Test
    public void defaultUpdate() {
        PlayerViewModel playerVM = PlayerViewModel.getPlayerVM();
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(-999, -999, 0, 0));
        DefaultMovement dMove = new DefaultMovement(playerVM.getPlayer());
        playerVM.setMovementStrategy(dMove);
        playerVM.setPositionVM(50, 50);
        playerVM.getPlayer().setDimensions(1000, 1000, 0,0);

        playerVM.updatePositionVM("up", obstacles);
        playerVM.updatePositionVM("up", obstacles);
        playerVM.updatePositionVM("down", obstacles);
        playerVM.updatePositionVM("left", obstacles);
        playerVM.updatePositionVM("right", obstacles);
        playerVM.updatePositionVM("right", obstacles);
        String pos = (playerVM.getXVM() + " " + playerVM.getYVM());
        String expected = "60.0 40.0";

        assertEquals(pos, expected);


    }

    @Test
    public void changeStrategy() {

        PlayerViewModel playerVM = PlayerViewModel.getPlayerVM();
        playerVM.resetPlayerVM();
        playerVM = PlayerViewModel.getPlayerVM();
        DefaultMovement dMove = new DefaultMovement(playerVM.getPlayer());
        SlowMovement sMove = new SlowMovement(playerVM.getPlayer());
        playerVM.setMovementStrategy(dMove);
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(-999, -999, 0, 0));
        playerVM.setPositionVM(0, 0);
        playerVM.getPlayer().setDimensions(3000, 3000, 0,0);

        playerVM.updatePositionVM("down", obstacles);
        playerVM.updatePositionVM("right", obstacles);

        playerVM.setMovementStrategy(sMove);
        playerVM.updatePositionVM("down", obstacles);
        playerVM.updatePositionVM("right", obstacles);

        String pos = (playerVM.getXVM() + " " + playerVM.getYVM());
        String expected = "15.0 15.0";

        assertEquals(pos, expected);

    }
}
