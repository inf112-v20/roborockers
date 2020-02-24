package inf112.skeleton.app;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class deckTests {

    @Test
    public void testDeckBeingShuffled(){
        Deck deckOfCards = new Deck();
        Deck copyDeck = new Deck(deckOfCards.listOfMoveCards);
        copyDeck.shuffle();
        assertTrue(deckOfCards.listOfMoveCards == copyDeck.listOfMoveCards);
    }


}
