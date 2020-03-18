package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Robo Rally - The Game";
        cfg.width = 700;
        cfg.height = 1000;
        cfg.resizable = true;

        new LwjglApplication(new RallyGame( ), cfg);
    }
}
