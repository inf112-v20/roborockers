package inf112.skeleton.app.Participants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Board;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Laser;
import inf112.skeleton.app.MoveCard;

import java.util.ArrayList;

public class ComputerPlayer extends GameActor {

    public ComputerPlayer(int x, int y, String name, int amountOfLives, int playerNumber, int nmrOfFlags){
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

    @Override
    public void startRound() {
        if(powerdownStatus == 1){
            powerdownStatus = 0;
            fullHeal();
            hand.clear();
            for(int i = 0; i < programCard.length; i++){
                programCard[i] = null;
            }
            return;
        }
        if(powerdownStatus == 2) powerdownStatus = 1;
    }

    @Override
    public void programRobot(Board board) {
        {
            if(healthPoints < 4){
                announcePowerdown();
            }
            for (int i = 0; i < Math.min(hand.size(), 5); i++){
                programCard[i] = hand.get(i);
            }
        }
    }

    @Override
    public void receiveCards(ArrayList<MoveCard> dealtCards, Board board){
        hand.clear();
        for(int i = 0; i < 9 - (9-healthPoints); i++){
            hand.add(dealtCards.remove(dealtCards.size()-1));
        }
        programRobot(board);
    }
    @Override
    public String createPlayerStatus(){
        String string = "";
        string += getName() + ", Lives: ";
        string += getRemainingLives() + ", HP: ";
        string += getHealthPoints() + ", PDS: ";
        string += powerdownStatus;
        return string;
    }

    public Vector2 findPositionOfNextFlag(Board board){
        for(int x = 0; x < board.getBoardWidth(); x++){
            for(int y = 0; y < board.getBoardHeight(); y++){
                TiledMapTileLayer.Cell flag = board.flagLayer.getCell(x,y);
                int flagID = -1;
                if(flag != null){
                    flagID = flag.getTile().getId();
                }
                if(flagID == board.checkpointFlags[numberOfFlagsVisited]){
                    return new Vector2(x, y);
                }
            }
        }
        return null;
    }
}
