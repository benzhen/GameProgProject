package gameControls;

import gameObjects.Base;
import gameObjects.GameObject;

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
		drawableObjects.add(base);
		screenHeight = Gdx.graphics.getHeight();
	}
	//Initialize the Alien ships to appear at start of screen
	private void initShips(int num){
		
	}
	
	public void update(){
		deltaTime = Gdx.graphics.getDeltaTime();
		processMouseInput();
		
		for(int i=0; i<drawableObjects.size(); i++){
			GameObject gObj = drawableObjects.get(i);
			if(gObj instanceof Base){
				((Base) gObj).update(deltaTime);
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
