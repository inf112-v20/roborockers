package inf112.skeleton.app;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    public ArrayList<MoveCard> listOfMoveCards;

    public Deck(ArrayList<MoveCard> list){
        if(list == null){
            this.listOfMoveCards = makeNewDeck();
        }
        else{
            this.listOfMoveCards = list;
        }
    }

    private ArrayList<MoveCard> makeNewDeck(){
        ArrayList<MoveCard> newDeck = new ArrayList<MoveCard>();
        Random random = new Random();

        for(int i = 0; i < 100; i++){
            boolean isRotatorCard = random.nextBoolean();
            int amountOfMoves = random.nextInt(4);
            if(amountOfMoves == 0 && isRotatorCard == true) amountOfMoves = random.nextInt(4-1) + 1;
            newDeck.add(new MoveCard(i, amountOfMoves, isRotatorCard));
        }
        return newDeck;
    }

    private void swap(int first, int second, ArrayList<MoveCard> list){
        MoveCard temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    public void shuffle(){
        ArrayList<MoveCard> list = listOfMoveCards;
        Random random = new Random();
        for (int i = 0; i < list.size(); i++) {
            swap(i, random.nextInt(list.size()), list);
        }
        this.listOfMoveCards = list;
    }

}