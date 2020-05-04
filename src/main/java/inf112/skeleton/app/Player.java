
package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import java.util.ArrayList;


public class Player implements GameActor{
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    private String name;
    public MoveCard[] programCard;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand = new ArrayList<>();
    public Texture playerTexture;
    public Cell playerCell = new Cell();
    public TextureRegion[][] playerTxRegion;
    public int numberOfFlagsVisited = 0;
    private Laser playerLaser;
    public boolean hasProgrammedRobot = false;

    public Player(int x, int y, String name, int amountOfLives, int playerNumber, int nmrOfFlags){
        this.xPosition = x;
        this.yPosition = y;
        this.name = name;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
        this.heading = new Direction();
        this.programCard = new MoveCard[5];
        this.playerLaser = new Laser(x,y,heading.heading);
        this.playerTexture = new Texture("Player"+ playerNumber +".png");
        this.playerTxRegion = TextureRegion.split(playerTexture, 300, 300);
        this.playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
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
    }
    @Override
    public int getXPosition(){
        return xPosition;
    }
    @Override
    public int getYPosition(){
        return yPosition;
    }
    @Override
    public void setXPosition(int x){
        xPosition = x;
    }
    @Override
    public void setYPosition(int y){
        yPosition = y;
    }
    @Override
    public int getRemainingLives(){
        return remainingLives;
    }

    @Override
    public int getPowerDownStatus(){
        return powerdownStatus;
    }
    @Override
    public int getHealthPoints(){
        return healthPoints;
    }
    @Override
    public void healPlayer(int healAmount){
        healthPoints += healAmount;
        if(healthPoints > 9) healthPoints = 9;
    }
    @Override
    public MoveCard[] getProgramCard(){
        return programCard;
    }
    @Override
    public int getNumberOfFlagsVisited(){return numberOfFlagsVisited;}
    @Override
    public void setNumberOfFlagsVisited(){numberOfFlagsVisited += 1;}
    @Override
    public Cell getPlayerCell(){ return playerCell; }

    @Override
    public void startRound(){
        if(powerdownStatus == 1){
            powerdownStatus = 0;
            fullHeal();
            hand.clear();
            hasProgrammedRobot = true;
            for(int i = 0; i < programCard.length; i++){
                programCard[i] = null;
            }
            return;
        }
        if(powerdownStatus == 2) powerdownStatus = 1;
    }
    @Override
    public void doMove(Board board, int phaseNumber){
        if(healthPoints > 0){
            MoveCard nextMove = programCard[phaseNumber];
            if(nextMove.isRotator){
                rotateClockWise(nextMove.amountOfMoves);
            }
            else{
                if(nextMove.amountOfMoves > 0){
                    attemptToMoveForward(board, nextMove.amountOfMoves);
                }
                else attemptToMoveBackward(board, nextMove.amountOfMoves);
            }
        }

    }
    @Override
    public void programRobot(){

    }
    @Override
    public void announcePowerdown (){
        powerdownStatus = 2;
    }
    @Override
    public void fullHeal(){
        healthPoints = 9;
    }
    @Override
    public void receiveCards(ArrayList<MoveCard> dealtCards){
        hand.clear();
        for(int i = 0; i < 9 - (9-healthPoints); i++){
            hand.add(dealtCards.remove(dealtCards.size()-1));
        }
    }
    @Override
    public void attemptToMoveForward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.heading) == false) return;
        //Recusively call to move as far as possible
        if(steps > 1) attemptToMoveForward(board, steps - 1);
    }
    @Override
    public void attemptToMoveBackward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.rotate180(heading.heading)) == false) return;

        if(steps > 1){
            attemptToMoveBackward(board, steps - 1);
        }
    }
    @Override
    public boolean attemptToMoveInDirection(Board board, Direction.NominalDirection nominalDirection){
        if(board.willCollideWithWall(xPosition,yPosition,nominalDirection)) return false;
        if(board.willGoIntoHole(xPosition,yPosition,nominalDirection) || board.willGoOutOfTheMap(xPosition,yPosition,nominalDirection)){
            loseALife();
            return false;
        }

        if(board.willCollideWithPlayer(xPosition,yPosition,nominalDirection)){
            Direction dir = new Direction(nominalDirection);
            Vector2 position = dir.getPositionInDirection(xPosition,yPosition,dir.heading);
            GameActor playerToPush = board.playerAtPosition(position);
            if(playerToPush.attemptToMoveInDirection(board, nominalDirection)){
                if(nominalDirection == Direction.NominalDirection.NORTH) yPosition += 1;
                else if(nominalDirection == Direction.NominalDirection.EAST) xPosition += 1;
                else if(nominalDirection == Direction.NominalDirection.SOUTH) yPosition -= 1;
                else xPosition -= 1;
            }
            else{return false;}
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
    @Override
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
    @Override
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
    @Override
    public void takeDamage(int damage){
        if(healthPoints - damage <= 0){
            loseALife();
        }
        else{
            healthPoints = healthPoints - damage;
        }
    }
    @Override
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
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
            System.out.println(this.name + " has died");
        }
    }
    @Override
    public void updateTxRegion(){
        if(healthPoints == 0){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
        }
        else if(healthPoints > 0 ){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        }
    }
    @Override
    public String getName(){
        return name;
    }

    /**
     * Updates the value of players laser to correspond with players current position and heading and returns laser
     */
    @Override
    public Laser getPlayerLaser(){
        Vector2 laserStart = heading.getPositionInDirection(xPosition,yPosition,heading.heading);
        playerLaser.position.x = laserStart.x;
        playerLaser.position.y = laserStart.y;
        playerLaser.setDirection(heading);
        return playerLaser;
    }
    @Override
    public void updateCheckpoint(){
        checkpoint.x = xPosition;
        checkpoint.y = yPosition;
    }
    @Override
    public String createPlayerStatus(){
        String string = "";
        string += getName() + " , Lives: ";
        string += getRemainingLives() + " , HP: ";
        string += getHealthPoints();
        return string;
    }
}