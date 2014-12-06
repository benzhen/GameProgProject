package gameObjects;

import gameControls.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class RedFighter extends GameObject implements Updatable {
	private AssetManager assets;
	private TextureAtlas atlas;
	private int screenHeight, screenWidth;
	private Vector2  vec;
	
	private float state, xPos, yPos;
	private boolean remove = false;
	private int hit=0;

	public RedFighter(){
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		
		assets = new AssetManager();
		assets.load("redFighter.txt", TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get("redFighter.txt");
		
		state = MathUtils.random(1,4);
		
		if(state == 1){
			xPos = 0;
			yPos = MathUtils.random(768);
		}
		else if(state == 2){
			xPos = 1024;
			yPos = MathUtils.random(768);
		}
		else if(state == 3){
			xPos = MathUtils.random(1024);
			yPos = 768;
		}
		else if(state == 4){
			xPos = MathUtils.random(1024);
			yPos = 0;
		}
	
		sprite = new Sprite(atlas.findRegion("redfighter0005"));
		sprite.setSize(Constants.REDFIGHTER_SIZE, Constants.REDFIGHTER_SIZE);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(xPos, yPos);
		
		vec = new Vector2(screenWidth/2 - xPos, screenHeight/2 - yPos);
		vec.nor();
		vec.scl(Constants.REDFIGHTER_SPEED);
		
		//System.out.println("Before: " + "SpriteAngle= " + sprite.getRotation() + ", vecAngle= " + vec.angle());
		
		sprite.rotate(vec.angle() + 270);
		
		//System.out.println("After: " + "SpriteAngle= " + sprite.getRotation() + ", vecAngle= " + vec.angle());
	}
	
	@Override
	public void update(float deltaTime){
		sprite.translate(vec.x * deltaTime, vec.y * deltaTime);
		
		//System.out.println("RedFighter:"+ " Hit= " + hit + ", remove= " + remove);
	}
	
	public void addHit(){
		if(hit >= 7){
			remove = true;
		}
		else{
			hit ++;
		}
	}
	
	public boolean getRemove(){
		return remove;
	}
}
