package gameObjects;

import gameControls.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Base extends GameObject implements Updatable {
	private int screenWidth, screenHeight;
	
	public Base(){
		sprite = new Sprite(new Texture("WB_baseu3_d0.png"));
		sprite.setSize(Constants.SHIP_SIZE,Constants.SHIP_SIZE);
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		sprite.setPosition(screenWidth/2 - Constants.SHIP_SIZE/2, screenHeight/2 - Constants.SHIP_SIZE/2);
		sprite.setOriginCenter();
		
	}

	@Override
	public void update(float deltaTime){
		sprite.rotate(30 * deltaTime);
		//System.out.println("Updating Base, " + screenWidth +", " + screenHeight);
	}
	
	
}
