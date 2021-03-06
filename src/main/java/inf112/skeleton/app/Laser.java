package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Wall;

public class Laser {
    public Vector2 position;
    private Direction direction;
    private int damage;

    public Laser(int ID, int x, int y){
        this.position = new Vector2(x,y);
        switch (ID){
            case 37:
                direction = new Direction(Direction.NominalDirection.NORTH);
                this.damage = 1;
                break;

            case 38:
                direction = new Direction(Direction.NominalDirection.EAST);
                this.damage = 1;
                break;

            case 45:
                direction = new Direction(Direction.NominalDirection.SOUTH);
                this.damage = 1;
                break;

            case 46:
                direction = new Direction(Direction.NominalDirection.WEST);
                this.damage = 1;
                break;

            case 87:
                direction = new Direction(Direction.NominalDirection.NORTH);
                this.damage = 2;
                break;

            case 93:
                direction = new Direction(Direction.NominalDirection.EAST);
                this.damage = 2;
                break;

            case 94:
                direction = new Direction(Direction.NominalDirection.SOUTH);
                this.damage = 2;
                break;

            case 95:
                direction = new Direction(Direction.NominalDirection.WEST);
                this.damage = 2;
                break;
        }
    }

    public Laser(int x, int y, Direction.NominalDirection nominalDirection){
        position = new Vector2(x,y);
        damage = 1;
    }

    public Vector2 laserHit(Board board){
        Vector2 hitPosition = new Vector2(position.x, position.y);
        int i = 0;
        while(true){
            if(board.positionIsOutOfBounds(hitPosition)) return null;
            Wall wall = board.wallObjects[(int)hitPosition.x][(int)hitPosition.y];
            if(wall != null){
                if(wall.blocksMovementTowards(direction.rotate180(direction.heading)) && i != 0){
                    return null;
                }
                if(board.playerLayer.getCell((int)hitPosition.x, (int)hitPosition.y) != null){
                    return hitPosition;
                }
                if(wall.blocksMovementTowards(direction.heading)) {
                    return null;
                }
            }
            if(board.playerLayer.getCell((int)hitPosition.x, (int)hitPosition.y) != null){
                return hitPosition;
            }
            hitPosition = direction.getPositionInDirection((int)hitPosition.x,(int)hitPosition.y,direction.heading);
            i += 1;
        }
    }

    public int getDamage() { return damage; }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
