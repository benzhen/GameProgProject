package space_defender;

import gameControls.Game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*
 * 
 * This class starts the game, if you want to test something
 * just run this class.
 * 
 * 
*/
public class Start {
	/*
	public static void main(String[] args) {
		Playing_Field playing_field = new Playing_Field();

	}
	*/
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Defense";
		config.width = 1024;
		config.height = 768; 
		new LwjglApplication(new Game(), config);
	}
	
}
