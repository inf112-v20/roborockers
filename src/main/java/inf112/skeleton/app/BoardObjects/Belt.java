package inf112.skeleton.app.BoardObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Participants.GameActor;

import java.util.ArrayList;
import java.util.Arrays;

public class Belt implements BoardObject {
    private Vector2 position;
    private Direction.NominalDirection direction;
    private Vector2 pushingTo;
    private int distance;
    private ArrayList<Integer> oneUp = new ArrayList<>(Arrays.asList(42, 43, 49, 57, 65, 69));
    private ArrayList<Integer> oneRight = new ArrayList<>(Arrays.asList(35, 41, 52, 58, 61, 66));
    private ArrayList<Integer> oneDown = new ArrayList<>(Arrays.asList(33, 36, 50, 59, 62, 67));
    private ArrayList<Integer> oneLeft = new ArrayList<>(Arrays.asList(34, 44, 51, 60, 68, 70));
    private ArrayList<Integer> twoUp = new ArrayList<>(Arrays.asList(13, 26, 27, 73, 77, 84));
    private ArrayList<Integer> twoRight = new ArrayList<>(Arrays.asList(14, 19, 25, 74, 78, 81));
    private ArrayList<Integer> twoDown = new ArrayList<>(Arrays.asList(20, 21, 17, 75, 82, 86));
    private ArrayList<Integer> twoLeft = new ArrayList<>(Arrays.asList(18, 22, 28, 76, 83, 85));

    public Belt(int x, int y, int ID){
        this.position = new Vector2(x, y);

        if(oneUp.contains(ID)){
            pushingTo = new Vector2(position.x, position.y + 1);
            direction = Direction.NominalDirection.NORTH;
            distance = 1;
        }
        else if(oneRight.contains(ID)){
            pushingTo = new Vector2(position.x + 1, position.y);
            direction = Direction.NominalDirection.EAST;
            distance = 1;

        }
        else if(oneDown.contains(ID)){
            pushingTo = new Vector2(position.x, position.y - 1);
            direction = Direction.NominalDirection.SOUTH;
            distance = 1;

        }
        else if(oneLeft.contains(ID)){
            pushingTo = new Vector2(position.x - 1, position.y);
            direction = Direction.NominalDirection.WEST;
            distance = 1;

        }
        else if(twoUp.contains(ID)){
            pushingTo = new Vector2(position.x, position.y + 2);
            direction = Direction.NominalDirection.NORTH;
            distance = 2;

        }
        else if(twoRight.contains(ID)){
            pushingTo = new Vector2(position.x + 2, position.y);
            direction = Direction.NominalDirection.EAST;
            distance = 2;
        }
        else if(twoDown.contains(ID)){
            pushingTo = new Vector2(position.x, position.y - 2);
            direction = Direction.NominalDirection.SOUTH;
            distance = 2;

        }
        else if(twoLeft.contains(ID)){
            pushingTo = new Vector2(position.x - 2, position.y);
            direction = Direction.NominalDirection.WEST;
            distance = 2;
        }
        else {
            //object is not a belt, but a mender
            if(ID == 7 || ID == 15){
                distance = -1;
            }
            //object is a rotator
            else{
                distance = 0;
            }


        }
    }
    @Override
    public void update(GameActor player){
        player.setXPosition((int)pushingTo.x);
        player.setYPosition((int)pushingTo.y);
    }
    @Override
    public Vector2 getPushingTo(){ return pushingTo;}

    @Override
    public int getDistance() {return distance;}

    @Override
    public Direction.NominalDirection getDirection(){return direction;}
}
