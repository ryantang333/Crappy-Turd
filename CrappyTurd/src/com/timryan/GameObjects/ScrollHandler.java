package com.timryan.GameObjects;

import com.timryan.CTHelpers.AssetLoader;
import com.timryan.GameWorld.GameWorld;

public class ScrollHandler {

	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;
	private Plunger plunger;
	private Fart fart;
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 49;
	public static final int BOSS_FLAT_SCROLL_SPEED = -120;
	public static final int BOSS_UP_SCROLL_SPEED = -100;
	public static final int BOSS_JUMP_SCROLL_SPEED = -80;
	public static final int BOSS_GAP = 250;
	public static final int FART_GAP = 300;
	
	private boolean complete = false, gassed = false, bossComplete = false;
	
	private int pipes, level;
	
	private GameWorld gameWorld;

	public ScrollHandler(GameWorld gameWorld, float yPos) {
		this.gameWorld = gameWorld;
		pipes = -1;
		level = pipes;
		frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);
		pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
		pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
		pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
		plunger = new Plunger(pipe3.getTailX() + BOSS_GAP, 49, 7, 65, BOSS_FLAT_SCROLL_SPEED);
		fart = new Fart(plunger.getTailX() + FART_GAP, 49, 16, 16, SCROLL_SPEED);
	}
	
	public void updateReady(float delta) {
		frontGrass.update(delta);
		backGrass.update(delta);

		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());
		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}

	}

	public void update(float delta) {
		frontGrass.update(delta);
		backGrass.update(delta);
		pipe1.update(delta);
		pipe2.update(delta);
		pipe3.update(delta);
		
		/*
		 *  Check if any of the pipes are scrolled left, 
		 *  and reset accordingly
		 */
		if(pipes == -1){
			if (pipe1.isScrolledLeft()){
				pipe1.reset(pipe3.getTailX() + PIPE_GAP);
			} else if (pipe2.isScrolledLeft()) {
				pipe2.reset(pipe1.getTailX() + PIPE_GAP);
			} else if (pipe3.isScrolledLeft()) {
				pipe3.reset(pipe2.getTailX() + PIPE_GAP);
			}
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());
		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}
		
		/*
		 * Checks if any pipes are scrolled left in story mode.
		 * If so, reset and subtract from the total number 
		 * of pipes.
		 */
		if(pipes > 3){
			if (pipe1.isScrolledLeft()){
				pipe1.reset(pipe3.getTailX() + PIPE_GAP);
				pipes--;
			} else if (pipe2.isScrolledLeft()) {
				pipe2.reset(pipe1.getTailX() + PIPE_GAP);
				pipes--;
			} else if (pipe3.isScrolledLeft()) {
				pipe3.reset(pipe2.getTailX() + PIPE_GAP);
				pipes--;
			}
		}
		
		/*
		 * In boss mode, if all pipes are out of sight, 
		 * start updating the plunger and farts.
		 */
		if(level == 4 || level == 8 || level == 12){
			if(pipe1.isScrolledLeft() && pipe2.isScrolledLeft() &&
					pipe3.isScrolledLeft() && gameWorld.isRunning()){
				bossComplete = true;
				plunger.update(delta);
				fart.update(delta);
				if(plunger.isScrolledLeft()){
					plunger.reset(plunger.getTailX() + BOSS_GAP);
				}
				if(fart.isScrolledLeft()){
					fart.reset(fart.getTailX() + FART_GAP);
				}
				/*
				 * Checks if the fart score meets the kill threshold for each boss level
				 */
				if(level == 4 && gameWorld.getFartScore() >= 3){
					gassed = true;
				}else if(level == 8 && gameWorld.getFartScore() >= 5){
					gassed = true;
				}else if(level == 12 && gameWorld.getFartScore() >= 10){
					gassed = true;
				}
			}
		}else if(pipe1.isScrolledLeft() && pipe2.isScrolledLeft() &&
				pipe3.isScrolledLeft() && gameWorld.isRunning()){
			if (gameWorld.getScore() > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(gameWorld.getScore());
			}
			complete = true;
		}
	}

	public void stop() {
		frontGrass.stop(); 
		backGrass.stop();
		pipe1.stop();
		pipe2.stop();
		pipe3.stop();
		plunger.stop();
		fart.stop();
	}

	public boolean collides(Bird bird) {

		if (!pipe1.isScored()
				&& pipe1.getX() + (pipe1.getWidth() / 2) < bird.getX()
						+ bird.getWidth()) {
			addScore(1);
			pipe1.setScored(true);
			AssetLoader.coin.play();
		} else if (!pipe2.isScored()
				&& pipe2.getX() + (pipe2.getWidth() / 2) < bird.getX()
						+ bird.getWidth()) {
			addScore(1);
			pipe2.setScored(true);
			AssetLoader.coin.play();
		} else if (!pipe3.isScored()
				&& pipe3.getX() + (pipe3.getWidth() / 2) < bird.getX()
						+ bird.getWidth()) {
			addScore(1);
			pipe3.setScored(true);
			AssetLoader.coin.play();
		} 
		if (!fart.isScored() && fart.collides(bird)) {
			addFartScore(1);
			fart.setScored(true);
			AssetLoader.breath.play();
		}

		return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3
				.collides(bird) || plunger.collides(bird) || fart.collides(bird));
	}

	private void addScore(int increment) {
		gameWorld.addScore(increment);
	}
	
	private void addFartScore(int increment) {
		gameWorld.addFartScore(increment);
	}
	
	public void setComplete(boolean c){
		complete = c;
	}
	
	public void setBossComplete(boolean b){
		bossComplete = b;
	}
	
	public void setGassed(boolean g){
		gassed = g;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public boolean isBossComplete() {
		return bossComplete;
	}
	
	public boolean isGassed() {
		return gassed;
	}

	public Grass getFrontGrass() {
		return frontGrass; 
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public Pipe getPipe1() {
		return pipe1;
	}

	public Pipe getPipe2() {
		return pipe2;
	}

	public Pipe getPipe3() {
		return pipe3;
	}
	
	public Plunger getPlunger() {
		return plunger;
	}

	public Fart getFart(){
		return fart;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getPipes() {
		return pipes;
	}

	public void onRestart(int lev) {
		level = lev;
		switch(level){
			case 1:
				pipes = 5;
				break;
			case 2:
				pipes = 10;
				break;
			case 3:
				pipes = 15;
				break;
			case 4:
				pipes = 20;
				break;
			case 5:
				pipes = 35;
				break;
			case 6:
				pipes = 40;
				break;
			case 7:
				pipes = 45;
				break;
			case 8:
				pipes = 50;
				break;
			case 9:
				pipes = 70;
				break;
			case 10:
				pipes = 80;
				break;
			case 11:
				pipes = 90;
				break;
			case 12:
				pipes = 100;
				break;
			default:
				pipes = -1;
				break;
		}
		frontGrass.onRestart(0, SCROLL_SPEED);
		backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
		pipe1.onRestart(210, SCROLL_SPEED);
		pipe2.onRestart(pipe1.getTailX() + PIPE_GAP, SCROLL_SPEED);
		pipe3.onRestart(pipe2.getTailX() + PIPE_GAP, SCROLL_SPEED);
		if(level == 4){
			plunger.onRestart(pipe3.getTailX() + BOSS_GAP, BOSS_FLAT_SCROLL_SPEED, 1);
		}else if(level == 8){
			plunger.onRestart(pipe3.getTailX() + BOSS_GAP, BOSS_UP_SCROLL_SPEED, 2);
		}else if(level == 12){
			plunger.onRestart(pipe3.getTailX() + BOSS_GAP, BOSS_JUMP_SCROLL_SPEED, 3);
		}else{
			plunger.onRestart(pipe3.getTailX() + BOSS_GAP, BOSS_FLAT_SCROLL_SPEED, 1);
		}
		fart.onRestart(plunger.getTailX() + FART_GAP, SCROLL_SPEED);
		setComplete(false);
		setGassed(false);
	}

}
