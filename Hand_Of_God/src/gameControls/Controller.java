package gameControls;

import gameObjects.AlienShip;
import gameObjects.Barriers;
import gameObjects.Base;
import gameObjects.GameObject;
import gameObjects.RedFighter;
import gameObjects.SmallFighter;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
	ArrayList<GameObject> drawableObjects; 
	
	private float deltaTime;
	
	private int screenHeight, screenWidth;
	
	public Controller(){
		drawableObjects = new ArrayList<GameObject>(); 
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		/* Old code from Asteroids game for initialization
		 * 
		initShip();
		initAsteroids(10);
		initSound();
		*/
		initBase();
		
		initBarriers();
		
		initAlienShips(5);
		
		//initRedFighter();
		
		//initSmallFighter();
		
		
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
			if(gObj instanceof Barriers){
				((Barriers) gObj).update(deltaTime);
				
				if(((Barriers) gObj).getRemove()){
					drawableObjects.remove(i);
				}
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
