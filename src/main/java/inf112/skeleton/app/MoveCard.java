package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;

public class MoveCard implements Comparable<MoveCard> {
    public boolean isRotator;
    public int priorityValue;
    public int amountOfMoves;
    public Texture texture;
    private String assetName;

    public MoveCard(int priorityValue, int amountOfMoves, boolean isRotatorCard) {
        this.amountOfMoves = amountOfMoves;
        this.priorityValue = priorityValue;
        this.isRotator = isRotatorCard;
        if(isRotatorCard){
            switch(amountOfMoves){
                case(1):
                    this.assetName = "Cards/RightTurn.png";
                    break;
                case(2):
                    this.assetName = "Cards/U-Turn.png";
                    break;
                case(3):
                    this.assetName = "Cards/LeftTurn.png";
                    break;

            }
        }
        else{
            if(amountOfMoves > 0){
                this.assetName = "Cards/Move" + String.valueOf(amountOfMoves) + ".png";
            }
            else{
                this.assetName = "Cards/MoveBack.png";
            }
        }
        this.texture = new Texture(assetName);
    }

    /**
     * For testing Deck/MoveCard without textures
     * @param priorityValue
     * @param amountOfMoves
     * @param isRotatorCard
     * @param forTesting
     */
    public MoveCard(int priorityValue, int amountOfMoves, boolean isRotatorCard, boolean forTesting) {
        this.amountOfMoves = amountOfMoves;
        this.priorityValue = priorityValue;
        this.isRotator = isRotatorCard;
        if(isRotatorCard){
            switch(amountOfMoves){
                case(1):
                    this.assetName = "Cards/RightTurn.png";
                    break;
                case(2):
                    this.assetName = "Cards/U-Turn.png";
                    break;
                case(3):
                    this.assetName = "Cards/LeftTurn.png";
                    break;

            }
        }
        else{
            if(amountOfMoves > 0){
                this.assetName = "Cards/Move" + String.valueOf(amountOfMoves) + ".png";
            }
            else{
                this.assetName = "Cards/MoveBack.png";
            }
        }
    }

    @Override
    public int compareTo(MoveCard mc) {
        return (priorityValue < mc.priorityValue) ? -1 : ((priorityValue == mc.priorityValue) ? 0 : 1);
    }
}
