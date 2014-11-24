package gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public abstract class AniGameObject {
	private boolean drawable;
	private boolean updatable;
	public Array<Sprite> aniSprite;
	
	public AniGameObject(){
	}
	
	public boolean isDrawable(){
		return drawable;
	}
	public boolean isUpdatable(){
		return updatable;
	}
	public void setIsDrawable(boolean val){
		drawable = val;
	}
	public void setIsUpdatable(boolean val){
		updatable = val;
	}

}
