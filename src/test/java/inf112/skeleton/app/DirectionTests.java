package inf112.skeleton.app;


import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class DirectionTests {
    private Direction direction;
    private Vector2 vector;

    @Before
    public void setUp(){
        direction = new Direction();
        vector = new Vector2(5,5);
    }

    @Test
    public void testDirectionHasDefaultHeadingNORTH(){
        assertTrue(direction.heading == Direction.NominalDirection.NORTH);
    }

    @Test
    public void testDirectionCanRotate90Degrees(){
        direction.heading = direction.rotate90(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.EAST);
    }

    @Test
    public void testDirectionCanRotate180Degrees(){
        direction.heading = direction.rotate180(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.SOUTH);
    }

    @Test
    public void testDirectionCanRotate270Degrees(){
        direction.heading = direction.rotate270(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.WEST);
    }

    @Test
    public void testPositionInDirectionNorthIsCorrect(){
        assertTrue((direction.getPositionInDirection((int)vector.x,(int)vector.y, Direction.NominalDirection.NORTH).y == 6));
    }
    @Test
    public void testPositionInDirectionEastIsCorrect(){
        assertTrue((direction.getPositionInDirection((int)vector.x,(int)vector.y, Direction.NominalDirection.EAST).x == 6));
    }
    @Test
    public void testPositionInDirectionSouthIsCorrect(){
        assertTrue((direction.getPositionInDirection((int)vector.x,(int)vector.y, Direction.NominalDirection.SOUTH).y == 4));
    }
    @Test
    public void testPositionInDirectionWestIsCorrect(){
        assertTrue((direction.getPositionInDirection((int)vector.x,(int)vector.y, Direction.NominalDirection.WEST).x == 4));
    }

}
