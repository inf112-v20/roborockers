package inf112.skeleton.app;


import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTests {

    @Before
    public void setUp(){
        Player player = new Player(2, 2, "Spillers navn", 3, 1, 1);
    }


    @Test
    public void testRotate90(){
        Player player = new Player(2, 2, "", 3, 1, 1);
        player.rotateClockWise(1);
        assertEquals(player.heading.heading, Direction.NominalDirection.EAST);
    }

    @Test
    public void playerCanTakeNonLethalDamage() {
        Player player = new Player(2, 2, "", 3, 1, 1);
        int initialHP = player.healthPoints;
        player.takeDamage(1);
        assertTrue(player.healthPoints != initialHP);
    }

    @Test
    public void playerCanTakeLethalDamageAndLoseLife() {
        Player player = new Player(2, 2, "", 3, 1, 1);
        player.checkpoint = new Vector2(3,3);
        int initialX = player.xPosition, initialY = player.yPosition;
        int initialLives = player.remainingLives;
        player.takeDamage(20);
        assertTrue(player.healthPoints == 9 && player.remainingLives != initialLives);
        assertTrue(player.remainingLives != initialLives);
        assertTrue(initialX != player.xPosition && initialY != player.yPosition);
    }

    @Test
    public void playerCanFullHealInEventOfPowerdown() {
        Player player = new Player(2, 2, "", 3, 1, 1);
        player.takeDamage(5);
        player.fullHeal();
        assertEquals(player.healthPoints, 9);
    }

    /*
    Other tests to implement when further along:
        Move functionality
        Handling when player is out of the game
        Handling when player is starting powerdown and should not be dealt cards/or play them


     */

}
