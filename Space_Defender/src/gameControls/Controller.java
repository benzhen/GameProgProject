package gameControls;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import gameObjects.AlienShip;
import gameObjects.Barriers;
import gameObjects.Base;
import gameObjects.GameObject;
import gameObjects.RedFighter;
import gameObjects.SmallFighter;

import java.math.BigInteger;
import java.util.ArrayList;

public class Controller {
	ArrayList<GameObject> drawableObjects; 
	private Sound explode, laser, ufo;
	private float deltaTime;
	
	private int screenHeight, screenWidth;
	private BigInteger score;
	
	private int x;
	private int y;
	
	Base base = new Base();
	private Boolean BaseHit = false;	//Boolean checks if base was hit by a ship
	private int waitAlien = 0;
	private int waitRed = 0;
	private int waitSmall = 0;
	  
	public Controller(){
		drawableObjects = new ArrayList<GameObject>(); 
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		
		score = new BigInteger("0");
		/* Old code from Asteroids game for initialization
		 * 
		initShip();
		initAsteroids(10);
		initSound();
		*/
		initBase();
		initSound();		
		initBarriers();
		
		initAlienShips(5);
		
		initRedFighter();
		
		initSmallFighter();
		
		
	}
	private void initBase(){
		Base base = new Base();
		drawableObjects.add(base);
	}
	//Initialize the Alien ships to appear at start of screen
	private void initAlienShips(int num){
		for(int i=0;i<num;i++){
			AlienShip alien = new AlienShip();
			
			drawableObjects.add(alien);
		}
	}
	
	private void initRedFighter(){
		RedFighter red = new RedFighter();
		drawableObjects.add(red);
	}
	
	private void initSmallFighter(){
		SmallFighter small = new SmallFighter();
		drawableObjects.add(small);
	}
	private void initBarriers(){
		Barriers topB = new Barriers(Constants.BARRIER_TOP);
		Barriers bottomB = new Barriers(Constants.BARRIER_BOTTOM);
		Barriers leftB = new Barriers(Constants.BARRIER_LEFT);
		Barriers rightB = new Barriers(Constants.BARRIER_RIGHT);
		
		drawableObjects.add(topB);
		drawableObjects.add(bottomB);
		drawableObjects.add(leftB);
		drawableObjects.add(rightB);
	}
	public void update(){
		//System.out.println("ArraySize= " + drawableObjects.size());
		
		deltaTime = Gdx.graphics.getDeltaTime();
		processMouseInput();
		
		
		
		waitAlien += deltaTime;
		waitSmall += deltaTime;
		waitRed += deltaTime;
		
		
		//Spawn 2 Alien ships after 4 seconds.
		if(waitAlien >= 4){
			initAlienShips(2);
			waitAlien = 0;
		}
		
		
		//Spawn a small fighter every 7 seconds.
		if(waitSmall >= 7){
			initSmallFighter();
			waitSmall = 0;
		}
		
		//Spawn a red fighter every 10 seconds.
		if(waitRed >= 10){
			initRedFighter();
			waitRed = 0;
		}
		
		
		for(int i=0; i<drawableObjects.size(); i++){
			
			GameObject gObj = drawableObjects.get(i);
			if(gObj instanceof Base){
				((Base) gObj).update(deltaTime);
				if(BaseHit = true){
					explode.play(1.0f);		//Game over if base is hit
				}
			}
			
			if(gObj instanceof AlienShip){
				((AlienShip) gObj).update(deltaTime);
				//If current ship contains mouse X and Y, then explode
				if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseX())){
					explode.play(1.0f);
					drawableObjects.remove((AlienShip) gObj);	//If sprite is clicked then remove the object
				}
				//Check if Alien ship hit the base.
				if(base.sprite.getBoundingRectangle().overlaps(((AlienShip) gObj)
						.sprite.getBoundingRectangle()) && BaseHit != true){
					BaseHit = true;
				}
				
				
			}
			if(gObj instanceof RedFighter){
				((RedFighter) gObj).update(deltaTime);
				if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseX())){
					explode.play(1.0f);
					drawableObjects.remove((RedFighter) gObj);	//If sprite is clicked then remove the object
				}
				//Check if Alien ship hit the base.
				if(base.sprite.getBoundingRectangle().overlaps(((RedFighter) gObj)
						.sprite.getBoundingRectangle()) && BaseHit != true){
					BaseHit = true;
				}
				
			}
			if(gObj instanceof SmallFighter){
				((SmallFighter) gObj).update(deltaTime);
				if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseX())){
					explode.play(1.0f);
					drawableObjects.remove((SmallFighter) gObj);	//If sprite is clicked then remove the object
				}
				//Check if Alien shp hit the base.
				if(base.sprite.getBoundingRectangle().overlaps(((SmallFighter) gObj)
						.sprite.getBoundingRectangle()) && BaseHit != true){
					BaseHit = true;
				}
				
			}
			
		}
		
		for(int i=0; i<drawableObjects.size();i++){
			GameObject gObj = drawableObjects.get(i);
			
			//Checks if ship hits them, if they do then destroy the ship
			//and remove one hit point from the barriers.
			if(gObj instanceof Barriers){
				((Barriers) gObj).update(deltaTime);
				
				if(((Barriers) gObj).getRemove()){
					drawableObjects.remove(i);
				}
				
				for(int j=0;j<drawableObjects.size();j++){
					GameObject gObj2 = drawableObjects.get(j);
					
					if(gObj2 instanceof AlienShip){
						if(gObj2.sprite.getBoundingRectangle().contains(getMouseX(), getMouseY())){
							drawableObjects.remove((AlienShip) gObj2);
						}
						
						if(gObj2.sprite.getBoundingRectangle().overlaps( ((Barriers) gObj).sprite.getBoundingRectangle()) ){
							drawableObjects.remove((AlienShip) gObj2);
							((Barriers) gObj).addHit();
						}
					}
					
					if(gObj2 instanceof SmallFighter){
						if(gObj2.sprite.getBoundingRectangle().contains(getMouseX(), getMouseY())){
							drawableObjects.remove((SmallFighter) gObj2);
						}
						
						if(gObj2.sprite.getBoundingRectangle().overlaps( ((Barriers) gObj).sprite.getBoundingRectangle()) ){
							drawableObjects.remove((SmallFighter) gObj2);
							((Barriers) gObj).addHit();
						}
					}
					
					if(gObj2 instanceof RedFighter){
						if(gObj2.sprite.getBoundingRectangle().overlaps( ((Barriers) gObj).sprite.getBoundingRectangle()) ){
							drawableObjects.remove((RedFighter) gObj2);
							((Barriers) gObj).addHit();
						}
					}
					
					
				}
				
			}
			
		}
		
		score = score.add(new BigInteger("3"));
		
	}
	public BigInteger getScore()
	{
		return score;
	}
	public ArrayList<GameObject> getDrawableObjects(){
		return drawableObjects;
	}
	
	//Process left mouse clicks
	private void processMouseInput(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		  int PosX = Gdx.input.getX(); //Polls mouse for input of X value
		  int PosY = Gdx.input.getY();	//Polls mouse for input of Y value.
		  
		  setMouseXY(PosX, PosY);		//Set X and Y
		  
		  /**
		  for (int i = 0; i < this.drawableObjects.size(); i++)
		  {
			GameObject gObj = drawableObjects.get(i);
			
			if (gObj instanceof AlienShip)
			{
			  //Idea is that whenever mouse is clicked and its position is in rectangle, delete selected sprite
			  //java.awt.Rectangle bounds = gObj.sprite.getBoundingRectangle();
			  //explode.play(1.0f);
			}
		  }
		}
			
		**/
		  
		}
		  
		if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)){
			for(int i=0; i<drawableObjects.size(); i++){
				GameObject gObj = drawableObjects.get(i);
				if(gObj instanceof Barriers){
					((Barriers) gObj).addHit();
				}
			}
		}
	}
	private void initSound(){
		explode = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/Bomb_Exploding-Sound_Explorer-68256487.mp3"));
		laser = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/pew.wav"));
		ufo = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/alien ufo loop.wav"));
	}
	
	public void dispose(){
		/* For every sound a new dispose with this template 
		 * 
		if(Sound != null){
			Sound.dispose();
		}
		*/
		if (this.explode != null) {
			explode.dispose();
		}
	}
	
	public void setMouseXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public float getMouseX(){
		return x;
	}
	
	public float getMouseY(){
		return y;
	}
	
}