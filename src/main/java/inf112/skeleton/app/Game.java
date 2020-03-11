package inf112.skeleton.app;

import java.util.*;

public class Game {

    private static ArrayList<Player> playerList;
    private static Deck playDeck;

    public Game() {
        this.playerList = new ArrayList<Player>();
        this.playDeck = new Deck(null);
    }

    public void startGameRound() {
        while (!playerList.isEmpty()) {
            playDeck.shuffle();
            int topOfDeck = 0;
            for (Player player : playerList) {
                if(player.remainingLives == 0){
                    playerList.remove(player);
                    continue;
                }
                if(player.powerdownStatus != 1){
                    player.receiveCards((ArrayList<MoveCard>) playDeck.listOfMoveCards.subList(topOfDeck, (9 - (9-player.healthPoints))));
                    topOfDeck += (9 - (9 - player.healthPoints));
                }
                player.startRound(this);
            }
            gamePhases();
        }
    }

    public void gamePhases() {
        for (int i = 0; i < 5; i++) {
            ArrayList<MoveCard> mcQueue = new ArrayList<MoveCard>();
            ArrayList<Player> playerQueue = new ArrayList<Player>();
            for (Player player : playerList) {
                if(player.healthPoints > 0) {
                    mcQueue.add(player.selectableCards.get(i));
                    playerQueue.add(player);
                }
            }
            while(!mcQueue.isEmpty()){
                int nextPlayerToMove = mcQueue.indexOf(Collections.max(mcQueue));
                playerQueue.get(nextPlayerToMove).doMove(this, i);
                playerQueue.remove(nextPlayerToMove);
                mcQueue.remove(nextPlayerToMove);
            }
            //Fire all lasers simultaneously
            //check all players on rotators/on belts and update map accordingly
        }
    }

}
