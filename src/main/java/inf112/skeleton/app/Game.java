package inf112.skeleton.app;

import inf112.skeleton.app.Screens.MenuScreen;
import inf112.skeleton.app.Screens.WinnerAnnouncementScreen;

import java.util.*;

public class Game {

    private static ArrayList<GameActor> playerList;
    private static Deck playDeck;
    private static Board board;
    private RallyGame game;

    public Game(Board board, RallyGame game, ArrayList<GameActor> playerList) {
        this.playerList = playerList;
        this.playDeck = new Deck(null);
        this.board = board;
        this.game = game;
    }

    public void runGame(){
        while(board.winner == null){
            startGameRound();
        }
    }

    public void startGameRound() {
        if (playerList.size() >= 2 && board.winner == null) {
            playDeck.shuffle();
            int topOfDeck = 0;
            int i = 0;
            while (i < playerList.size()){
                GameActor ga = playerList.get(i);
                if(ga.getRemainingLives() == 0){
                    playerList.remove(i);
                    board.playerObjects.remove(i);
                    board.playerLayer.setCell(ga.getXPosition(), ga.getYPosition(), null);
                    continue;
                }
                if(ga.getPowerDownStatus() != 1){
                    int cardsToBeDealt = 9 - (9 - ga.getHealthPoints());
                    ga.receiveCards(new ArrayList<MoveCard>(playDeck.listOfMoveCards.subList(topOfDeck, topOfDeck + cardsToBeDealt)));
                    topOfDeck += cardsToBeDealt;
                }
                i++;
            }
        }
        else{
            game.setScreen(new WinnerAnnouncementScreen(game, board.winner));
        }
    }

    public void gamePhases() {
        for (int i = 0; i < 5; i++) {
            ArrayList<MoveCard> mcQueue = new ArrayList<MoveCard>();
            ArrayList<GameActor> playerQueue = new ArrayList<GameActor>();
            for (GameActor player : playerList) {
                if(player.getHealthPoints() > 0) {
                    mcQueue.add(player.getProgramCard()[i]);
                    playerQueue.add(player);
                }
            }
            while(!mcQueue.isEmpty()){
                int nextPlayerToMove = mcQueue.indexOf(Collections.max(mcQueue));
                playerQueue.get(nextPlayerToMove).doMove(board, i);
                playerQueue.remove(nextPlayerToMove);
                mcQueue.remove(nextPlayerToMove);
            }
            board.updateBoard();
        }
    startGameRound();
    }
}
