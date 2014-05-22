package com.timryan.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {

	private Random r;

	private Rectangle tpUp, tpDown, standUp, standDown;

	public static final int VERTICAL_GAP = 45;
	public static final int TP_WIDTH = 24;
	public static final int TP_HEIGHT = 11;
	private float groundY;

	private boolean isScored = false;

	// When Pipe's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public Pipe(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		// Initialize a Random object for Random number generation
		r = new Random();
		tpUp = new Rectangle();
		tpDown = new Rectangle();
		standUp = new Rectangle();
		standDown = new Rectangle();

		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		// The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle

		standUp.set(position.x, position.y, width, height);
		standDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));

		// Our skull width is 24. The bar is only 22 pixels wide. So the skull
		// must be shifted by 1 pixel to the left (so that the skull is centered
		// with respect to its bar).

		// This shift is equivalent to: (TP_WIDTH - width) / 2
		tpUp.set(position.x - (TP_WIDTH - width) / 2, position.y + height
				- TP_HEIGHT, TP_WIDTH, TP_HEIGHT);
		tpDown.set(position.x - (TP_WIDTH - width) / 2, standDown.y,
				TP_WIDTH, TP_HEIGHT);

	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		height = r.nextInt(90) + 15;
		isScored = false;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

	public Rectangle getTpUp() {
		return tpUp;
	}

	public Rectangle getTpDown() {
		return tpDown;
	}

	public Rectangle getStandUp() {
		return standUp;
	}

	public Rectangle getStandDown() {
		return standDown;
	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), standUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), standDown)
					|| Intersector.overlaps(bird.getBoundingCircle(), tpUp) || Intersector
						.overlaps(bird.getBoundingCircle(), tpDown));
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
