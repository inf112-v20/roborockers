package inf112.skeleton.app;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WallTests {

    @Test
    public void testWallID8(){
        Wall wall = new Wall(8);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assert(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));

    }
    @Test
    public void testWallID16(){
        Wall wall = new Wall(16);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assert(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
    }
    @Test
    public void testWallID23(){
        Wall wall = new Wall(23);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
    }

    @Test
    public void testWallID24(){
        Wall wall = new Wall(24);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assert (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }

    @Test
    public void testWallID29(){
        Wall wall = new Wall(29);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
    }

    @Test
    public void testWallID30(){
        Wall wall = new Wall(30);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }

    @Test
    public void testWallID31(){
        Wall wall = new Wall(31);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }

    @Test
    public void testWallID32(){
        Wall wall = new Wall(32);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assert(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
    }

    @Test
    public void testWallID37(){
        Wall wall = new Wall(37);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
    }
    @Test
    public void testWallID38(){
        Wall wall = new Wall(38);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }
    @Test
    public void testWallID45(){
        Wall wall = new Wall(45);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }

    @Test
    public void testWallID46(){
        Wall wall = new Wall(46);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }
    @Test
    public void testWallID87(){
        Wall wall = new Wall(87);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
    }
    @Test
    public void testWallID93(){
        Wall wall = new Wall(93);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }
    @Test
    public void testWallID94(){
        Wall wall = new Wall(94);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }
    @Test
    public void testWallID95(){
        Wall wall = new Wall(95);
        assert(wall.blocksMovementTowards(Direction.NominalDirection.EAST));
        assertFalse (wall.blocksMovementTowards(Direction.NominalDirection.NORTH));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.WEST));
        assertFalse(wall.blocksMovementTowards(Direction.NominalDirection.SOUTH));
    }
}