package inf112.skeleton.app.Participants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Laser;
import inf112.skeleton.app.MoveCard;

import java.util.ArrayList;

public class Player extends GameActor {

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
    public void startRound() {
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
    public void programRobot() {
    }

    @Override
    public void receiveCards(ArrayList<MoveCard> dealtCards){
        hand.clear();
        for(int i = 0; i < 9 - (9-healthPoints); i++){
            hand.add(dealtCards.remove(dealtCards.size()-1));
        }
    }

    public Integer positionOfSpecificMoveCardInProgramCard(MoveCard card){
        for (int i = 0; i < programCard.length; i++) {
            if(programCard[i] == card) return i;
        }
        return null;
    }
}
