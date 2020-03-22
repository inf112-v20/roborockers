package inf112.skeleton.app;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTests {

    @Test
    public void testDeckBeingShuffled(){
        Deck deckOfCards = new Deck(null);
        Deck copyDeck = new Deck(new ArrayList<MoveCard>(deckOfCards.listOfMoveCards));
        copyDeck.shuffle();
        assertFalse(deckOfCards.listOfMoveCards.equals(copyDeck.listOfMoveCards));
    }


}
