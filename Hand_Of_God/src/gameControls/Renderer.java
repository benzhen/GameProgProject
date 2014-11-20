package gameControls;

import gameObjects.GameObject;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {
	private SpriteBatch spriteBatch;
	private Controller control;
	BitmapFont font;
	
	public Renderer(Controller c){
		control = c;
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		
		
		
	}
	
	public void render(){
		spriteBatch.begin();
		renderBackground();
		
		for(GameObject gObj : control.getDrawableObjects()){
			gObj.sprite.draw(spriteBatch);
		}
		
		
		spriteBatch.end();
	}
	
	public void renderBackground(){
		
	}
}
