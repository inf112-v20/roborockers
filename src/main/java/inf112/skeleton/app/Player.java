import Direction;
public class Player{
    public static int xPosition;
    public static int yPosition;
    public static heading;
    public static Vector2 checkpoint;
    public static int remainingLives;
    public static int healthPoints = 9;
    public static  String name;
    public static MoveCard[] programCard;
    public static selectableCards;
    public int powerdownStatus;
    public ArrayList<MoveCard> hand;

    public Player(int x, int y, String name, int amountOfLives){
        this.xPosition = x;
        this.yPosition = y;
        this.name = name;
        this.remainingLives = amountOfLives;
        this.checkpoint = new Vector2(x, y);
        this.powerdownStatus = 0;
    }

    public void announcePowerdown (){
        /*When a player announces they would like to powerdown the powerdown variable has a value of 1 - ONE the player
        is in powerdown mode, upon the start of each round, this variable is deincremented by one (unless already at 0)
        this way
         */
        powerdownStatus = 3;
    }
    public void

    public void receiveCards(int numberOfCardsToBeDealt){

    }

    public boolean attemptToMove(Game game, int steps){

    }

    public static void rotateClockWise(int numberOf90Degrees){
        if(numberOf90Degrees < 0 || numberOf90Degrees > 3){
            throw new IllegalArgumentException("This amount of clock wise rotations dont make sense game wise");
        }
        else{
            //figure out how to make enum that makes sense, with heading/directions in the 4 capital directions
            //NB: PLACEHOLDER until we figure out functionality
            heading = heading + numberOf90Degrees;
        }
    }

    public void takeDamage(int damage){
        if(healthPoints - damage <= 0){
            loseALife();
        }
        else{
            healthPoints = healthPoints - damage;
        }
    }

    public void loseALife(){
        if(remainingLives > 1){
            remainingLives -= 1;
            healthPoints = 9;
            //må man gi spilledriveren en tilbakemelding når det skjer noe med instansene eller hodler det at de vet
            //?
            xPosition = checkpoint.x;
            yPosition = checkpoint.y;
        }
        else{
            /*
            Notify server that this player has died and does not have any more lives
             */
            remainingLives = 0;
        }
    }




}