package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Participants.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTests {
    private Player player;

    @Before
    public void setUp(){
        player = new Player(2, 2, 3, 4);
    }

    @Test
    public void testRotate90(){
        player.rotateClockWise(1);
        assertEquals(player.heading.heading, Direction.NominalDirection.EAST);
    }

    @Test
    public void playerCanTakeNonLethalDamage() {
        int initialHP = player.healthPoints;
        player.takeDamage(1);
        assertTrue(player.healthPoints != initialHP);
    }

    @Test
    public void playerCanTakeLethalDamageAndLoseLife() {
        player.checkpoint = new Vector2(3,3);
        int initialX = player.xPosition, initialY = player.yPosition;
        int initialLives = player.remainingLives;
        player.takeDamage(20);
        assertTrue(player.healthPoints == 9);
        assertTrue(player.remainingLives != initialLives);
        assertTrue(initialX != player.xPosition && initialY != player.yPosition);
        assertTrue(player.xPosition == player.checkpoint.x && player.yPosition == player.checkpoint.y);
    }

    @Test
    public void playerCanFullHealInEventOfPowerdown() {
        player.takeDamage(5);
        player.fullHeal();
        assertEquals(player.healthPoints, 9);
    }
}
