
package inf112.skeleton.app.Participants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Laser;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.MoveCard;

import java.util.ArrayList;


abstract public class GameActor {
    public int xPosition;
    public int yPosition;
    public Direction heading;
    public Vector2 checkpoint;
    public int remainingLives;
    public int healthPoints = 9;
    public String name;
    public MoveCard[] programCard;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand = new ArrayList<>();
    public Texture playerTexture;
    public Cell playerCell = new Cell();
    public TextureRegion[][] playerTxRegion;
    public int numberOfFlagsVisited = 0;
    public Laser playerLaser;
    public boolean hasProgrammedRobot = false;


    public int getXPosition(){
        return xPosition;
    }

    public int getYPosition(){
        return yPosition;
    }

    public void setXPosition(int x){
        xPosition = x;
    }

    public void setYPosition(int y){
        yPosition = y;
    }

    public int getRemainingLives(){
        return remainingLives;
    }


    public int getPowerDownStatus(){
        return powerdownStatus;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void healPlayer(int healAmount){
        healthPoints += healAmount;
        if(healthPoints > 9) healthPoints = 9;
    }

    public MoveCard[] getProgramCard(){
        return programCard;
    }

    public int getNumberOfFlagsVisited(){return numberOfFlagsVisited;}

    public void setNumberOfFlagsVisited(){numberOfFlagsVisited += 1;}

    public Cell getPlayerCell(){ return playerCell; }

    abstract public void startRound();

    /**
     * Prompts the GameActor to attempt the move corresponding to the MoveCard in the position of phaseNumber
     * in their programCard
     * @param board
     * @param phaseNumber
     */
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

    /**
     * Clears the players hand
     */
    public void clearHand(){
        hand.clear();
    }

    /**
     * as is should only do anything int he ComputerPlayer class, as the Player
     * should do this manually in the GameScreen class
     */
    abstract public void programRobot(Board board);

    /**
     * GameActor announces powerdown and its powerdown status is set to 2, to indicate that powerdown
     * should be initiated upon the start of the second to first start of round.
     */
    public void announcePowerdown (){
        powerdownStatus = 2;
    }

    /**
     * GameActor heals to full HP
     */
    public void fullHeal(){
        healthPoints = 9;
    }

    /**
     * Deals the correct amount of cards to each GameActor, separate functionality for Player and ComputerPlayer
     * @param dealtCards
     */
    abstract public void receiveCards(ArrayList<MoveCard> dealtCards, Board board);

    /**
     * Recursive call to keep attempting to move forwards until either the number of steps have been met or something
     * is preventing further movement
     * @param board
     * @param steps
     */
    public void attemptToMoveForward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.heading) == false) return;
        //Recusively call to move as far as possible
        if(steps > 1) attemptToMoveForward(board, steps - 1);
    }

    /**
     * Recursive call to keep attempting to move backwards until either the number of steps have been met or something
     * is preventing further movement
     * @param board
     * @param steps
     */
    public void attemptToMoveBackward(Board board, int steps){
        if(attemptToMoveInDirection(board, heading.rotate180(heading.heading)) == false) return;

        if(steps > 1){
            attemptToMoveBackward(board, steps - 1);
        }
    }

    /**
     * Prompt the GameActor to attempt moving in a specific direction
     * @param board
     * @param nominalDirection
     * @return true / false if GameActor moved successfully
     */
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
                if(nominalDirection == Direction.NominalDirection.NORTH) setYPosition(getYPosition()+1);
                else if(nominalDirection == Direction.NominalDirection.EAST) setXPosition(getXPosition()+1);
                else if(nominalDirection == Direction.NominalDirection.SOUTH) setYPosition(getYPosition()-1);
                else setXPosition(getXPosition()-1);
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

    /**
     * Rotates the GameActor clockwise 1-3 times
     * @param numberOf90Degrees
     */
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

    /**
     * Helper function to allow players to rotate according to the 4 nominal directions
     * @return int each corresponding to just one nominal direction
     */
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

    /**
     * GameActor has taken damage
     * @param damage
     */
    public void takeDamage(int damage){
        if(healthPoints - damage <= 0){
            loseALife();
        }
        else{
            healthPoints = healthPoints - damage;
        }
    }

    /**
     * Update GameActors information after losing a life
     */
    public void loseALife(){
        if(remainingLives > 1){
            remainingLives -= 1;
            healthPoints = 9;
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
        }
        else{
            remainingLives = 0;
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
            xPosition = (int)checkpoint.x;
            yPosition = (int)checkpoint.y;
        }
    }

    /**
     * Updates the GameActor's on screen appearance indicating if the player is dead or not
     * Currently this is rarely/not used as the dead players get reset or removed swiftly
     */
    public void updateTxRegion(){
        if(healthPoints == 0){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][1]));
        }
        else if(healthPoints > 0 ){
            playerCell.setTile(new StaticTiledMapTile(playerTxRegion[0][0]));
        }
    }

    /**
     * @return the GameActors name
     */
    public String getName(){
        return name;
    }

    /**
     * Updates the value of GameActors laser to correspond with players current position and heading
     * @return the Laser
     */
    public Laser getPlayerLaser(){
        Vector2 laserStart = heading.getPositionInDirection(xPosition,yPosition,heading.heading);
        playerLaser.position.x = laserStart.x;
        playerLaser.position.y = laserStart.y;
        playerLaser.setDirection(heading);
        return playerLaser;
    }

    /**
     * updated the checkpoint of the GameActor to its current position
     */
    public void updateCheckpoint(){
        checkpoint.x = xPosition;
        checkpoint.y = yPosition;
    }

    /**
     * @param card
     * @return the position of card in the programCard, null if it is not in the programCard
     */
    public Integer positionOfSpecificMoveCardInProgramCard(MoveCard card){
        for (int i = 0; i < programCard.length; i++) {
            if(programCard[i] == card) return i;
        }
        return null;
    }

    /**
     * Add one remaining life to the players tally
     */
    public void gainALife(){
        remainingLives +=1;
    }

    /**
     * Builds a string with the most important status information of the GameActor
     * @return The string
     */
    public String createPlayerStatus(){
        String string = "";
        string += getName() + ", Lives: ";
        string += getRemainingLives() + ", HP: ";
        string += getHealthPoints() + ", PDS: ";
        string += powerdownStatus;
        return string;
    }

    public void powerDown(){
        this.powerdownStatus = 1;
    };

    public void powerUp(){
        this.powerdownStatus = 0;
        this.fullHeal();
    };
}