package inf112.skeleton.app;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class Belt {
    private Vector2 position;
    private Direction.NominalDirection direction;
    private Vector2 pushingTo;
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
        }
        else if(oneRight.contains(ID)){
            pushingTo = new Vector2(position.x + 1, position.y);

        }
        else if(oneDown.contains(ID)){
            pushingTo = new Vector2(position.x, position.y - 1);

        }
        else if(oneLeft.contains(ID)){
            pushingTo = new Vector2(position.x - 1, position.y);

        }
        else if(twoUp.contains(ID)){
            pushingTo = new Vector2(position.x, position.y + 2);

        }
        else if(twoRight.contains(ID)){
            pushingTo = new Vector2(position.x + 2, position.y);
        }
        else if(twoDown.contains(ID)){
            pushingTo = new Vector2(position.x, position.y - 2);

        }
        else if(twoLeft.contains(ID)){
            pushingTo = new Vector2(position.x - 2, position.y);
        }
        else {
            System.out.println("Invalid ID for creating a conveyorbelt");
        }
    }

    public Vector2 pushPlayerTo(){
        return pushingTo;
    }

}
