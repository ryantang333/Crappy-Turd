package com.timryan.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Fart extends Scrollable{
	
	private Random r;
	private Rectangle fart;
	private boolean isScored = false;
	
	public Fart(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		fart = new Rectangle();
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		fart.set(position.x, position.y, width, height);
	}

	@Override
	public void reset(float newX) {
		super.reset(newX);
		// Change the height to a random number
		position.y = r.nextInt(90);
		isScored = false;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

	public Rectangle getFart() {
		return fart;
	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), fart));
		}
		return false;
	}
	
	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
}