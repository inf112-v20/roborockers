
package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import java.util.ArrayList;

public class Player{
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    private static String name;
    public static MoveCard[] programCard;
    public ArrayList<MoveCard> selectableCards;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand;
    public static Texture playerTexture;
    public Cell playerCell = new Cell();
    public TextureRegion[][] playerTxRegion;
    public boolean[] flagWinCondition;
    private Laser playerLaser;

    public Player(int x, int y, String name, int amountOfLives, int playerNumber, int nmrOfFlags){
        this.xPosition = x;
        this.yPosition = y;
        this.name = name;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
        this.heading = new Direction();
        this.playerLaser = new Laser(x,y,heading.heading);
        this.playerTexture = new Texture("Player"+ playerNumber +".png");
        this.playerTxRegion = TextureRegion.split(playerTexture, 300, 300);
        this.playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        this.flagWinCondition = new boolean[nmrOfFlags];
    }
    /*
        For testing purposes the LibGDX and all texture classes will fail if the LibGDX library has not yet been
        Instantiated, therefore this constructor will allow testing on game functionality by foregoing texture
        implementation.
    */
    public Player(int x, int y, int amountOfLives, int nmrOfFlags){
        this.xPosition = x;
        this.yPosition = y;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
        this.heading = new Direction();
        this.flagWinCondition = new boolean[nmrOfFlags];
    }

    public void startRound(Game game){
        if(powerdownStatus == 1){
            powerdownStatus = 0;
            fullHeal();
            return;
        }
        if(powerdownStatus > 1) powerdownStatus -= 1;
        programRobot();
    }

    public void doMove(Board board, int phaseNumber){
        if(healthPoints > 0){
            MoveCard nextMove = programCard[phaseNumber];
            if(nextMove.isRotator){
                rotateClockWise(nextMove.amountOfMoves);
            }
            else{
                if(nextMove.amountOfMoves < 0){
                    attemptToMoveForward(board, nextMove.amountOfMoves);
                }
                else attemptToMoveBackward(board, nextMove.amountOfMoves);
            }
        }

    }

    public void programRobot(){
    }

    public void announcePowerdown (){
        powerdownStatus = 2;
    }
    public void fullHeal(){
        healthPoints = 9;
    }
    public void receiveCards(ArrayList<MoveCard> dealtCards){
        hand.clear();
        for(int i = 0; i < 9 - (9-healthPoints); i++){
            hand.add(dealtCards.remove(dealtCards.size()-1));
        }
    }

    public void attemptToMoveForward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.heading) == false) return;
        //Recusively call to move as far as possible
        if(steps > 1) attemptToMoveForward(board, steps - 1);
    }

    public void attemptToMoveBackward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.rotate180(heading.heading)) == false) return;

        if(steps > 1){
            attemptToMoveBackward(board, steps - 1);
        }
    }

    public boolean attemptToMoveInDirection(Board board, Direction.NominalDirection nominalDirection){
        if(board.willCollideWithWall(xPosition,yPosition,nominalDirection)) return false;
        if(board.willGoIntoHole(xPosition,yPosition,nominalDirection) || board.willGoOutOfTheMap(xPosition,yPosition,nominalDirection)){
            loseALife();
            return false;
        }
        if(board.willCollideWithPlayer(xPosition,yPosition,nominalDirection)){
            //push player if possible, update yPosition and return true
            // otherwise return false
        }
        else{
            if(nominalDirection == Direction.NominalDirection.NORTH) yPosition += 1;
            else if(nominalDirection == Direction.NominalDirection.EAST) xPosition += 1;
            else if(nominalDirection == Direction.NominalDirection.SOUTH) yPosition -= 1;
            else xPosition -= 1;
        }
        return true;
    }

    public void rotateClockWise(int numberOf90Degrees){
        if(numberOf90Degrees < 0 || numberOf90Degrees > 3){
            throw new IllegalArgumentException("This amount of clock wise rotations dont make sense game wise");
        }
        else{
            if(numberOf90Degrees == 1){
                heading.heading = heading.rotate90(heading.heading);
            }
            else if(numberOf90Degrees == 2){
                heading.heading = heading.rotate180(heading.heading);
            }
            else heading.heading = heading.rotate270(heading.heading);
        }
        playerCell.setRotation(4 - rotateHelper());
    }
    public int rotateHelper(){
        if(heading.heading == Direction.NominalDirection.NORTH){
            return 0;
        }
        else if(heading.heading == Direction.NominalDirection.EAST){
            return 1;
        }
        else if(heading.heading == Direction.NominalDirection.SOUTH){
            return 2;
        }
        else{
            return 3;
        }

    }

    public void takeDamage(int damage){
        if(healthPoints - damage <= 0){
            loseALife();
        }
        else{
            healthPoints = healthPoints - damage;
        }
    }

    public void loseALife(){
        if(remainingLives > 1){
            remainingLives -= 1;
            healthPoints = 9;
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
        }
        else{
            /*
            Notify server that this player has died and does not have any more lives
             */
            remainingLives = 0;
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
            System.out.println(Player.name + " has died");
        }
    }
    public void updateTxRegion(){
        if(healthPoints == 0){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
        }
        else if(healthPoints > 0 ){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        }
    }
    public String getName(){
        return name;
    }

    public Laser getPlayerLaser(){
        Vector2 laserStart = heading.getPositionInDirection(xPosition,yPosition,heading.heading);
        playerLaser.position.x = laserStart.x;
        playerLaser.position.y = laserStart.y;
        playerLaser.setDirection(heading);
        return playerLaser;
    }
}