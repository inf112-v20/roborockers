package inf112.skeleton.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotatorTests {
    private Rotator rotatorCW;
    private Rotator rotatorCCW;

    @Before
    public void setUp(){
        rotatorCCW = new Rotator(53, 2, 2);
        rotatorCW = new Rotator(54, 2, 2);
    }

    @Test
    public void rotatorCWReturnsCorrectOrientationCaseNorth(){
        assertTrue(rotatorCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.NORTH) == Direction.NominalDirection.EAST);
    }

    @Test
    public void rotatorCWReturnsCorrectOrientationCaseEast(){
        assertTrue(rotatorCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.EAST) == Direction.NominalDirection.SOUTH);
    }
    @Test
    public void rotatorCWReturnsCorrectOrientationCaseSouth(){
        assertTrue(rotatorCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.SOUTH) == Direction.NominalDirection.WEST);
    }
    @Test
    public void rotatorCWReturnsCorrectOrientationCaseWest(){
        assertTrue(rotatorCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.WEST) == Direction.NominalDirection.NORTH);
    }
    @Test
    public void rotatorCCWReturnsCorrectOrientationCaseNorth(){
        assertTrue(rotatorCCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.NORTH) == Direction.NominalDirection.WEST);
    }
    @Test
    public void rotatorCCWReturnsCorrectOrientationCaseEast(){
        assertTrue(rotatorCCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.EAST) == Direction.NominalDirection.NORTH);
    }
    @Test
    public void rotatorCCWReturnsCorrectOrientationCaseSouth(){
        assertTrue(rotatorCCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.SOUTH) == Direction.NominalDirection.EAST);
    }
    @Test
    public void rotatorCCWReturnsCorrectOrientationCaseWest(){
        assertTrue(rotatorCCW.rotatePlayerToNewNominalDirection(Direction.NominalDirection.WEST) == Direction.NominalDirection.SOUTH);
    }
}
