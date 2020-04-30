package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robo Rally - The Game";
        cfg.width = RallyGame.SCREEN_WIDTH;
        cfg.height = RallyGame.SCREEN_HEIGHT;
        cfg.resizable = false;

        new LwjglApplication(new RallyGame( ), cfg);
    }
}
