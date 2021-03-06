package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//Add a comment to this line
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ClickCheck extends GameObject implements Updatable {
	
	private boolean remove;
	int screenWidth;
	int screenHeight;
	
	public ClickCheck(Texture pmap, float posX, float posY){
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		sprite = new Sprite(pmap);
		sprite.setOrigin(pmap.getWidth()/2, pmap.getHeight()/2);
		sprite.setPosition(posX, screenHeight - posY);
		
		
		setIsDrawable(true);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
	}
	
	public boolean getRemove(){
		return remove;
	}
}