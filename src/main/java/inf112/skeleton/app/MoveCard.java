package inf112.skeleton.app;

public class MoveCard {
    public boolean isRotator;
    //public boolean isReverse;
    public int priorityValue;
    public int amountOfMoves;
    public String description;

    /*
    Construct either a rotator card or a movement card with trumping values and values corresponding to how many times
     */
    public MoveCard(int priorityValue, int amountOfMoves, boolean isRotatorCard) {
        this.amountOfMoves = amountOfMoves;
        this.priorityValue = priorityValue;
        this.isRotator = isRotatorCard;
        if(isRotatorCard){

            int degrees = 90*amountOfMoves;
            this.description = "Rotate " + degrees + " degrees in the clock wise direction";
        }
        else{
            if(amountOfMoves < 0) this.description = "Move " + (amountOfMoves *-1) + " step backwards";
            else{
                this.description = "Move " + amountOfMoves + " steps forward";
            }
        }

    }
}
