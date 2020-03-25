package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class boardTests {
    private Board board;
    private Vector2 position;

    @Before
    public void setUp(){
        board = new Board(10,10);
        position = new Vector2(0,0);
    }

    @Test
    public void testPositionIsOutOfBoundsWithinBoard(){
        assertFalse(board.positionIsOutOfBounds(position));
    }

    @Test
    public void testPositionIsOutOfBoundsOutsideBoard1(){
        position.x = board.getBoardWidth() + 1;
        assertTrue(board.positionIsOutOfBounds(position));
    }

    @Test
    public void testPositionIsOutOfBoundsOutsideBoard2(){
        position.y = board.getBoardHeight() + 1;
        assertTrue(board.positionIsOutOfBounds(position));
    }

    @Test
    public void testPositionIsOutOfBoundsOutsideBoard3(){
        position.y -= 1;
        assertTrue(board.positionIsOutOfBounds(position));
    }

    @Test
    public void testWillGoOutOfBounds(){
        assertTrue(board.willGoOutOfTheMap((int)position.x,(int)position.y, Direction.NominalDirection.SOUTH));
    }

    @Test
    public void testWillGoOutOfBounds2(){
        assertFalse(board.willGoOutOfTheMap((int)position.x,(int)position.y, Direction.NominalDirection.NORTH));
    }

}
