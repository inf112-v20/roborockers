package inf112.skeleton.app;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class DeckTests {
    private Deck deck;
    private HashSet<Integer> set;

    @Before
    public void setUp(){
        deck = new Deck(null, true);
        set = new HashSet();
    }

    @Test
    public void testDeckBeingShuffled(){
        Deck copyDeck = new Deck(new ArrayList<MoveCard>(deck.listOfMoveCards));
        copyDeck.shuffle();
        assertFalse(deck.listOfMoveCards.equals(copyDeck.listOfMoveCards));
    }

    @Test
    public void testDeckContainsOnlyCardsWithUniquePriorityValues(){
        for (MoveCard card : deck.listOfMoveCards) {
            assertFalse(set.contains(card.priorityValue));
            set.add(card.priorityValue);
        }
    }
}
