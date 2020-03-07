package inf112.skeleton.app;


import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class DirectionTests {
    @Test
    public void testDirectionHasDefaultHeadingNORTH(){
        Direction direction = new Direction();
        assertTrue(direction.heading == Direction.NominalDirection.NORTH);
    }

    @Test
    public void testDirectionCanRotate90Degrees(){
        Direction direction = new Direction();
        direction.heading = direction.rotate90(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.EAST);
    }

    @Test
    public void testDirectionCanRotate180Degrees(){
        Direction direction = new Direction();
        direction.heading = direction.rotate180(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.SOUTH);
    }

    @Test
    public void testDirectionCanRotate270Degrees(){
        Direction direction = new Direction();
        direction.heading = direction.rotate270(direction.heading);
        assertTrue(direction.heading == Direction.NominalDirection.WEST);
    }
}
