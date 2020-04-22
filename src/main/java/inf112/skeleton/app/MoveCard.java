package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;

public class MoveCard implements Comparable<MoveCard> {
    public boolean isRotator;
    public int priorityValue;
    public int amountOfMoves;
    public Texture texture;
    public boolean isSelected;
    private String assetName;

    /*
    Construct either a rotator card or a movement card with trumping values and values corresponding to how many times
     */
    public MoveCard(int priorityValue, int amountOfMoves, boolean isRotatorCard) {
        this.isSelected = false;
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
                    this.assetName = "Cards/RightTurn.png";
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

    public void toggleCard(){
        int assetNameLen = assetName.length();
        String s = "";
        if(!isSelected){
            isSelected = true;
            s += assetName.substring(0,assetNameLen-4);
            s += "S.png";
        }
        else{
            isSelected = false;
            s += assetName.substring(0, assetNameLen-4);
            s += ".png";
        }
        updateTexture(s);
    }

    public void updateTexture(String s){
        this.texture = new Texture(s);
    }

    @Override
    public int compareTo(MoveCard mc) {
        return (priorityValue < mc.priorityValue) ? -1 : ((priorityValue == mc.priorityValue) ? 0 : 1);
    }
}
