package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

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

            case 38:
                direction = new Direction(Direction.NominalDirection.EAST);
                this.damage = 1;

            case 45:
                direction = new Direction(Direction.NominalDirection.SOUTH);
                this.damage = 1;

            case 46:
                direction = new Direction(Direction.NominalDirection.WEST);
                this.damage = 2;

            case 87:
                direction = new Direction(Direction.NominalDirection.NORTH);
                this.damage = 2;

            case 93:
                direction = new Direction(Direction.NominalDirection.EAST);
                this.damage = 2;

            case 94:
                direction = new Direction(Direction.NominalDirection.SOUTH);
                this.damage = 2;

            case 95:
                direction = new Direction(Direction.NominalDirection.WEST);
                this.damage = 2;

        }
    }

    public Vector2 laserHit(Board board){
        Vector2 hitPosition = new Vector2(position.x, position.y);
        while(true){
            if(board.positionIsOutOfBounds(hitPosition)) return null;
            if(board.playerLayer != null) return hitPosition;
            Wall wall = board.wallObjects[(int)hitPosition.x][(int)hitPosition.y];
            if(wall != null){
                if(wall.blocksMovementTowards(direction.rotate180(direction.heading))){
                    return null;
                }
            }
            hitPosition = direction.getPositionInDirection((int)hitPosition.x,(int)hitPosition.y,direction.heading);
            }
        }

    public int getDamage() { return damage; }
}
