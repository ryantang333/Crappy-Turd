package com.timryan.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.timryan.CTHelpers.AssetLoader;

public class Plunger extends Scrollable {

	private Random r;
	private Rectangle head, handle;
	private PlungerLayout plungerLayout;
	private float startingHeight, jumpCap;
	private boolean up = false;
	public enum PlungerLayout{
		HORIZONTAL, VERTICAL, JUMP
	}

	public Plunger(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		startingHeight = y;
		jumpCap = startingHeight;
		r = new Random();
		head = new Rectangle();
		handle = new Rectangle();
		plungerLayout = PlungerLayout.HORIZONTAL;
	}

	@Override
	public void update(float delta) {
		switch (plungerLayout) {
		case HORIZONTAL:{
			position.add(velocity.cpy().scl(delta));
			handle.set(position.x, position.y, height, width);
			head.set(position.x - width, position.y - 11, 25, 29);
			if (position.x + height < 0) {
				isScrolledLeft = true;
			}
			break;
		}
		case VERTICAL:{
			super.update(delta);
			handle.set(position.x, position.y, width, height);
			head.set(position.x - 11, position.y + height, 29, 26);
			break;
		}
		case JUMP:{
			super.update(delta);
			if (!up && position.y < 110){
				position.sub(vertVelocity.cpy().scl(delta));
				if(position.y > 110){
					AssetLoader.plunger.play();
					up = true;
				}
			}
			if (up && position.y > jumpCap){
				position.add(vertVelocity.cpy().scl(delta));
				if(position.y < jumpCap){
					up = false;
					jumpCap = (jumpCap*3)/4;
				}
			}
			handle.set(position.x, position.y, width, height);
			head.set(position.x - 11, position.y + height, 29, 26);
			break;
		}
		default:
			break;
		}
		
	}

	@Override
	public void reset(float newX) {
		
		// Change the height to a random number
		switch (plungerLayout) {
		case VERTICAL:{
			super.reset(newX);
			position.y = r.nextInt(110);
			super.setScrolledLeft(false);
			break;
		}
		case HORIZONTAL:{
			super.reset(newX);
			position.y = r.nextInt(165);
			super.setScrolledLeft(false);
			break;
		}
		case JUMP:{
			position.y = r.nextInt(90);
			position.x = newX + r.nextInt(30);
			super.setScrolledLeft(false);
			break;
		}
		default:
			break;
		}
	}

	public void onRestart(float x, float scrollSpeed, int layout) {
		velocity.x = scrollSpeed;
		vertVelocity.y = scrollSpeed;
		setPlungerLayout(layout);
		reset(x);
	}
	
	public void setPlungerLayout(int layout){
		if (layout == 1){
			plungerLayout = PlungerLayout.HORIZONTAL;
		}else if (layout == 2){
			plungerLayout = PlungerLayout.VERTICAL;
		}else if (layout == 3){
			plungerLayout = PlungerLayout.JUMP;
		}
	}

	public Rectangle getHead() {
		return head;
	}

	public Rectangle getHandle() {
		return handle;
	}
	
	public PlungerLayout getLayout(){
		return plungerLayout;
	}
	
	public float getStartingHeight(){
		return startingHeight;
	}
	
	public boolean isUp(){
		return up;
	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), head)
					|| Intersector.overlaps(bird.getBoundingCircle(), handle));
		}
		return false;
	}
}