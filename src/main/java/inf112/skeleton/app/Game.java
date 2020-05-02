package inf112.skeleton.app;

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
        while (playerList.size() >= 2 || board.winner != null) {
            playDeck.shuffle();
            int topOfDeck = 0;
            for (GameActor player : playerList) {
                if(0 == player.getRemainingLives()){
                    playerList.remove(player); //Kan muligens feile med liste iterable etc...
                    board.playerObjects.remove(player);
                    board.playerLayer.setCell(player.getXPosition(),player.getYPosition(), null);

                    continue;
                }
                if(player.getPowerDownStatus() != 1){
                    player.receiveCards((ArrayList<MoveCard>) playDeck.listOfMoveCards.subList(topOfDeck, (9 - (9-player.getHealthPoints()))));
                    topOfDeck += (9 - (9 - player.getHealthPoints()));
                }
                player.startRound(this);
            }
            gamePhases();
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
    }
}
