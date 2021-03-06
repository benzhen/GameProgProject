package gameControls;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.InputProcessor;

import gameObjects.ClickCheck;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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
	private Music backgroundRadiation;
	private Sound explode, laser, ufo, redSummonSound, smallSummonSound, gameOver;
	private float deltaTime;
	
	private int screenHeight, screenWidth;
	private BigInteger score;
	
	private float x;
	private float y;
	boolean paused = false;
	
	Base base = new Base();
	private Boolean BaseHit = false;	//Boolean checks if base was hit by a ship
	private float waitAlien = 0;
	private float waitRed = 0;
	private float waitSmall = 0;
	  
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
	
	private void initClickCheck(float posX, float posY){
		    //Add a comment to this line
			int w = 1; 
			int h = 1; 
			Pixmap pmap = new Pixmap(w, h, Format.RGBA8888); // TODO: Check Image Format
			//Add a comment to this line
			pmap.setColor(1, 1, 1, 1);
			pmap.drawLine(0, h, w/2, 0);
			pmap.drawLine(w, h, w/2, 0);
			pmap.drawLine(1, h-1, w, h-1);
			ClickCheck check = new ClickCheck(new Texture(pmap), posX, posY);
			drawableObjects.add(check);
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
		redSummonSound.play();
		drawableObjects.add(red);
	}
	
	private void initSmallFighter(){
		SmallFighter small = new SmallFighter();
		smallSummonSound.play();
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
		
		if(!paused){
			waitAlien += deltaTime;
			waitSmall += deltaTime;
			waitRed += deltaTime;
			
			if(BaseHit == false){
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
			}
			
			
			
			//adding hits to ships and checking if base has been hit
			for(int i=0; i<drawableObjects.size(); i++){
				
				GameObject gObj = drawableObjects.get(i);
				if(gObj instanceof Base){
					((Base) gObj).update(deltaTime);
					if(BaseHit == true){
						drawableObjects.remove((Base) gObj);
						gameOver.play();
						explode.play(1.0f);		//Game over if base is hit
	
					}
				}
				if(BaseHit == false){
					if(gObj instanceof AlienShip){
						((AlienShip) gObj).update(deltaTime);
						
						if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseY())){
							((AlienShip) gObj).addHit();
							explode.play(0.15f);
						}
						
						if(base.sprite.getBoundingRectangle().overlaps(((AlienShip) gObj)
								.sprite.getBoundingRectangle()) && BaseHit != true){
							BaseHit = true;
						}
						
					}
					
					if(gObj instanceof RedFighter){
						((RedFighter) gObj).update(deltaTime);
		
						if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseY())){
							((RedFighter) gObj).addHit();
						}
						
						
						if(base.sprite.getBoundingRectangle().overlaps(((RedFighter) gObj)
								.sprite.getBoundingRectangle()) && BaseHit != true){
							BaseHit = true;
						}
						
					}
					if(gObj instanceof SmallFighter){
						((SmallFighter) gObj).update(deltaTime);
						
						if(gObj.sprite.getBoundingRectangle().contains(getMouseX(), getMouseY())){
							((SmallFighter) gObj).addHit();
						}
						
						
						//Check if Alien shp hit the base.
						if(base.sprite.getBoundingRectangle().overlaps(((SmallFighter) gObj)
								.sprite.getBoundingRectangle()) && BaseHit != true){
							BaseHit = true;
						}
					}
				}
			}
			
			if(!BaseHit){
				//This is removing the ships after they have taken the appropriate amount of hits and add score
				for(int i=0;i<drawableObjects.size();i++){
					GameObject gObj = drawableObjects.get(i);
					
					if(gObj instanceof AlienShip){
						if(((AlienShip) gObj).getRemove()){
							drawableObjects.remove(i);
							score = score.add(new BigInteger("10"));
						}
					}
					
					if(gObj instanceof SmallFighter){
						if(((SmallFighter) gObj).getRemove()){
							drawableObjects.remove(i);
							score = score.add(new BigInteger("20"));
						}
					}
					
					if(gObj instanceof RedFighter){
						if(((RedFighter) gObj).getRemove()){
							drawableObjects.remove(i);
							score = score.add(new BigInteger("30"));
						}
					}
				}
			}
			
			//This is barriers logic
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
							
							if(gObj2.sprite.getBoundingRectangle().overlaps( ((Barriers) gObj).sprite.getBoundingRectangle()) ){
								drawableObjects.remove((AlienShip) gObj2);
								((Barriers) gObj).addHit();
							}
						}
						
						if(gObj2 instanceof SmallFighter){
							
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
			
		}
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
		  float PosX = Gdx.input.getX(); 	//Polls mouse for input of X value
		  float PosY = Gdx.input.getY();	//Polls mouse for input of Y value.
		  setMouseXY(PosX,screenHeight - PosY);
		  //System.out.println("X: " + PosX + ", Y: " + PosY);
		  //initClickCheck(PosX, PosY);
 
		}
		
		/*
		 
		//Automatically spawn ships for debugging purposes 
		if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN)){
			initRedFighter();
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)){
			initSmallFighter();
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)){
			initAlienShips(1);
		}
		*/
		
		//pause button
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			if(paused){
				paused = false;
			}
			else{
				paused = true;
			}
		}
	}
		
	private void initSound(){
		explode = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/Bomb_Exploding-Sound_Explorer-68256487.mp3"));
		laser = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/pew.wav"));
		ufo = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/alien ufo loop.wav"));
		backgroundRadiation = Gdx.audio.newMusic(Gdx.files.internal("sounds/sfx/ambientbackground.mp3"));
		backgroundRadiation.setLooping(true);
		backgroundRadiation.play();
		redSummonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/redSummon.wav"));
		smallSummonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/smallSummon.wav"));
		gameOver = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/gameover.mp3"));
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
		if (this.laser != null) {
			laser.dispose();
		}
		if (this.ufo != null) {
			ufo.dispose();
		}
		if (this.backgroundRadiation != null) {
			backgroundRadiation.dispose();
		}
		if (this.redSummonSound != null) {
			redSummonSound.dispose();
		}
		if (this.smallSummonSound != null) {
			smallSummonSound.dispose();
		}
		if (this.gameOver != null) {
			gameOver.dispose();
		}
	}
	
	public void setMouseXY(float x, float y){
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
