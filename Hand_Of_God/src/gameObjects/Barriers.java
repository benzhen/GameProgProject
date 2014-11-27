package gameObjects;

import java.util.ArrayList;

import gameControls.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Barriers extends GameObject implements Updatable {
	private AssetManager assets;
	private TextureAtlas atlas;
	private int screenHeight, screenWidth;
	
	private float xPos, yPos;
	private boolean remove = false;
	private int hit=0;
	
	private ArrayList<Sprite> barriers;
	
	public Barriers(String dir){
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		
		assets = new AssetManager();
		assets.load("barrier.txt", TextureAtlas.class);
		assets.finishLoading();
		
		atlas = assets.get("barrier.txt");
		
		barriers = new ArrayList<Sprite>();
		barriers.add(new Sprite(atlas.findRegion("glowtube")));
		barriers.add(new Sprite(atlas.findRegion("glowtube2")));
		barriers.add(new Sprite(atlas.findRegion("left_hbar")));
		barriers.add(new Sprite(atlas.findRegion("right_hbar")));
		
		
		if(dir.equals(Constants.BARRIER_LEFT)){
			xPos = 355;
			yPos = 378;
			
			barriers.get(2).setOrigin(barriers.get(2).getWidth()/2, barriers.get(2).getHeight()/2);
			barriers.get(2).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
			barriers.get(2).setPosition(xPos, yPos);
			barriers.get(2).rotate(90);
			
			xPos = 295;
			yPos = 410;
			
			for(int i=0;i<barriers.size()/2;i++){
				barriers.get(i).setOrigin(barriers.get(i).getWidth()/2, barriers.get(i).getHeight()/2);
				barriers.get(i).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
				barriers.get(i).setPosition(xPos, yPos);
				barriers.get(i).rotate(90);
			}
			
		}
		else if(dir.equals(Constants.BARRIER_RIGHT)){
			xPos = 520;
			yPos = 350;
			
			barriers.get(2).setOrigin(barriers.get(2).getWidth()/2, barriers.get(2).getHeight()/2);
			barriers.get(2).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
			barriers.get(2).setPosition(xPos, yPos);
			barriers.get(2).rotate(270);
			
			xPos = 435;
			yPos = 410;
			
			for(int i=0;i<barriers.size()/2;i++){
				barriers.get(i).setOrigin(barriers.get(i).getWidth()/2, barriers.get(i).getHeight()/2);
				barriers.get(i).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
				barriers.get(i).setPosition(xPos, yPos);
				barriers.get(i).rotate(90);
			}
		}
		else if(dir.equals(Constants.BARRIER_TOP)){
			xPos = 452;
			yPos = 435;
			
			barriers.get(2).setOrigin(barriers.get(2).getWidth()/2, barriers.get(2).getHeight()/2);
			barriers.get(2).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
			barriers.get(2).setPosition(xPos, yPos);
			
			xPos = 450;
			yPos = 435;
			
			for(int i=0;i<barriers.size()/2;i++){
				barriers.get(i).setOrigin(barriers.get(i).getWidth()/2, barriers.get(i).getHeight()/2);
				barriers.get(i).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
				barriers.get(i).setPosition(xPos, yPos);
			}
			
		}
		else if(dir.equals(Constants.BARRIER_BOTTOM)){
			xPos = 423;
			yPos = 295;
			
			barriers.get(2).setOrigin(barriers.get(2).getWidth()/2, barriers.get(2).getHeight()/2);
			barriers.get(2).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
			barriers.get(2).setPosition(xPos, yPos);
			barriers.get(2).rotate(180);
			
			xPos = 450;
			yPos = 320;
			
			for(int i=0;i<barriers.size()/2;i++){
				barriers.get(i).setOrigin(barriers.get(i).getWidth()/2, barriers.get(i).getHeight()/2);
				barriers.get(i).setSize(Constants.BARRIER_WIDTH, Constants.BARRIER_HEIGHT);
				barriers.get(i).setPosition(xPos, yPos);
			}
			
		}
		
		
		sprite = barriers.get(0);
	}
	
	@Override
	public void update(float deltaTime){
		if(hit == 1){
			sprite = barriers.get(1);
		}
		else if(hit == 2){
			sprite = barriers.get(2);
		}
		
		//System.out.println("Barriers:" + " Hit= " + hit + ", remove= " + remove);
	}
	
	public void addHit(){
		if(hit >= 2){
			remove = true;
		}
		else{
			hit += 1;
		}
	}
	
	public boolean getRemove(){
		return remove;
	}
}
