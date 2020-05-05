package inf112.skeleton.app;


import com.badlogic.gdx.Game;
import inf112.skeleton.app.Screens.MenuScreen;
import inf112.skeleton.app.Screens.WinnerAnnouncementScreen;

public class RallyGame extends Game {

    public static int SCREEN_WIDTH = 700;
    public static int SCREEN_HEIGHT = 800;


    @Override
    public void create() {

         this.setScreen(new MenuScreen(this));
    }
}
