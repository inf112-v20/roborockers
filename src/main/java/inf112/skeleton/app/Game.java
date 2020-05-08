package inf112.skeleton.app;

import com.badlogic.gdx.Screen;
import inf112.skeleton.app.Participants.GameActor;
import inf112.skeleton.app.Screens.GameScreen;
import inf112.skeleton.app.Screens.WinnerAnnouncementScreen;

import java.util.*;

public class Game {

    private static ArrayList<GameActor> playerList;
    private static Deck playDeck;
    private static Board board;
    public RallyGame game;
    public  int currentPhase;

    public Game(Board board, RallyGame game, ArrayList<GameActor> playerList) {
        this.playerList = playerList;
        this.playDeck = new Deck(null);
        this.board = board;
        this.game = game;
        this.currentPhase = 0;
    }

    public void prepareNewRound() {
        if (board.winner == null) {
            playDeck.shuffle();
            removeDeadPlayers();
            cleanPlayersProgramCard();
            dealCards(board);
        } else{
            game.setScreen(new WinnerAnnouncementScreen(game, board.winner));
        }
    }

    public boolean nextPhase(){
        ArrayList<MoveCard> mcQueue = new ArrayList<MoveCard>();
        ArrayList<GameActor> playerQueue = new ArrayList<GameActor>();
        for (GameActor ga : playerList) {
            if(ga.getHealthPoints() > 0 && ga.getPowerDownStatus() != 1) {
                mcQueue.add(ga.getProgramCard()[currentPhase]);
                playerQueue.add(ga);
            }
        }
        while(!mcQueue.isEmpty()){
            int nextPlayerToMove = mcQueue.indexOf(Collections.max(mcQueue));
            playerQueue.get(nextPlayerToMove).doMove(board, currentPhase);
            playerQueue.remove(nextPlayerToMove);
            mcQueue.remove(nextPlayerToMove);
            if(playerList.size() == 1){
                game.setScreen(new WinnerAnnouncementScreen(game, playerList.get(0)));
            }
        }
        board.updateBoard();

        if(this.currentPhase == 4){
            this.currentPhase = 0;
            this.endOfTurn();
            return true;
        } else {
            this.currentPhase += 1;
            return false;
        }
    }

    public void endOfTurn(){
        for (GameActor ga : playerList) {
            if(ga.getPowerDownStatus() == 2){
                ga.powerDown();

            } else if (ga.getPowerDownStatus() == 1){
                ga.powerUp();
            }
        }
        prepareNewRound();
    }

    public void dealCards(Board board){
        int topOfDeck = 0;
        for(GameActor ga : playerList){
            ga.clearHand();
            int cardsToBeDealt = 9 - (9 - ga.getHealthPoints());
            if(ga.getPowerDownStatus() != 1){
                ArrayList<MoveCard> cardsToDeal = new ArrayList<MoveCard>(playDeck.listOfMoveCards.subList(topOfDeck, topOfDeck + cardsToBeDealt));
                ga.receiveCards(cardsToDeal, board);
            }
            topOfDeck += cardsToBeDealt;
        }
    }

    public void removeDeadPlayers(){
        int i = 0;
        while(i < playerList.size()){
            if(playerList.get(i).getRemainingLives() == 0){
                GameActor ga = playerList.get(i);
                playerList.remove(ga);
                board.playerLayer.setCell(ga.getXPosition(), ga.getYPosition(), null);
            }
            i++;
        }
        if(board.playerObjects.size() == 1){
            game.setScreen(new WinnerAnnouncementScreen(game, board.playerObjects.get(0)));
        }
    }

    public void cleanPlayersProgramCard(){
        for (GameActor ga : playerList){
            for (int i = 0; i < 9 - (9 - ga.getHealthPoints()); i++) {
                try{
                    ga.getProgramCard()[i] = null;
                }
                catch (Exception e){
                    break;
                }
            }
        }
    }


}
