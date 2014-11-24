package gameControls;

import gameObjects.AlienShip;
import gameObjects.Base;
import gameObjects.GameObject;
import gameObjects.RedFighter;
import gameObjects.SmallFighter;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
	ArrayList<GameObject> drawableObjects; 
	Base base;
	private float deltaTime;
	
	private int screenHeight, screenWidth;
	
	public Controller(){
		drawableObjects = new ArrayList<GameObject>(); 
		/* Old code from Asteroids game for initialization
		 * 
		initShip();
		initAsteroids(10);
		initSound();
		*/
		base = new Base();
		//initAlienShips(1);
		
		//initRedFighter();
		
		initSmallFighter();
		
		//drawableObjects.add(base);
		screenHeight = Gdx.graphics.getHeight();
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
	public void update(){
		//System.out.println("ArraySize= " + drawableObjects.size());
		
		deltaTime = Gdx.graphics.getDeltaTime();
		processMouseInput();
		
		for(int i=0; i<drawableObjects.size(); i++){
			GameObject gObj = drawableObjects.get(i);
			if(gObj instanceof Base){
				((Base) gObj).update(deltaTime);
			}
			if(gObj instanceof AlienShip){
				((AlienShip) gObj).update(deltaTime);
			}
			if(gObj instanceof RedFighter){
				((RedFighter) gObj).update(deltaTime);
			}
			if(gObj instanceof SmallFighter){
				((SmallFighter) gObj).update(deltaTime);
			}
		}
		
	}
	
	public ArrayList<GameObject> getDrawableObjects(){
		return drawableObjects;
	}
	
	//Process left mouse clicks
	private void processMouseInput(){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		
		}
	}
	
	private void initSound(){
		
	}
	
	public void dispose(){
		/* For every sound a new dispose with this template 
		 * 
		if(Sound != null){
			Sound.dispose();
		}
		*/
		
	}
	
	
}
