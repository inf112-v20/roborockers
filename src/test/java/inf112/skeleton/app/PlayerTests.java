package inf112.skeleton.app;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTests {

    @Before
    public void setUp(){
        Player player = new Player(2, 2, "Spillers navn", 3, 1);
    }


    @Test
    public void testRotate90(){
        Player player = new Player(2, 2, "", 3, 1);
        player.rotateClockWise(1);
        assertEquals(player.heading.heading, Direction.NominalDirection.EAST);
    }
}
