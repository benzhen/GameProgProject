package gameObjects;

import gameControls.Constants;

//import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class AlienShip extends GameObject implements Updatable {
	private AssetManager assets;
	private TextureAtlas atlas;
	//private ArrayList<Sprite> arrayImages;
	//private int counter=0;
	private int screenHeight, screenWidth;
	//private boolean end= false;
	private Vector2  vec;
	
	private float state, xPos, yPos;
	private boolean remove = false;
	private int hit=0;
	
	public AlienShip(){
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		
		//arrayImages = new ArrayList<Sprite>();
		
		assets = new AssetManager();
		assets.load("alienShip.txt", TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get("alienShip.txt");
		
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
		
		sprite = new Sprite(atlas.findRegion("alien10001"));
		sprite.setSize(Constants.ALIENSHIP_SIZE, Constants.ALIENSHIP_SIZE);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(xPos, yPos);
		
		/*
		addSprite("alien10001", atlas);
		addSprite("alien10002", atlas);
		addSprite("alien10003", atlas);
		addSprite("alien10004", atlas);
		addSprite("alien10005", atlas);
		addSprite("alien10006", atlas);
		addSprite("alien10007", atlas);
		addSprite("alien10008", atlas);
		addSprite("alien10009", atlas);
		addSprite("alien10010", atlas);
		addSprite("alien10011", atlas);
		addSprite("alien10012", atlas);
		addSprite("alien10013", atlas);
		addSprite("alien10014", atlas);
		addSprite("alien10015", atlas);
		*/
		
		vec = new Vector2(screenWidth/2 - xPos, screenHeight/2 - yPos);
		vec.nor();
		vec.scl(Constants.ALIENSHIP_SPEED);
	}
	
	@Override
	public void update(float deltaTime){
		//System.out.println("ArraySize = " + arrayImages.size() + ", Counter= " + counter);
		
		//sprite = arrayImages.get(counter);
		
		sprite.translate(vec.x * deltaTime, vec.y * deltaTime);
		
		//System.out.println("AlienShip:"+ " Hit= " + hit + ", remove= " + remove);
		
		//System.out.println("Positions: " + sprite.getX() + ", " + sprite.getY());
		
		/*
		if(end){
			counter --;
			if(counter == 0){
				end = false;
			}
		}
		else{
			counter ++;
			if(counter >= 14){
				end = true;
			}
		}
		*/
		
	}
	
	/*
	private void addSprite(String image, TextureAtlas atlas){
		Sprite alien = new Sprite(atlas.findRegion(image));
		alien.setSize(Constants.ALIENSHIP_SIZE, Constants.ALIENSHIP_SIZE);
		alien.setOriginCenter();
		arrayImages.add(alien);
	}
	*/
	
	public void addHit(){
		if(hit >= 2){
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
