package com.timryan.GameWorld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.timryan.GameObjects.Bird;
import com.timryan.GameObjects.ScrollHandler;
import com.timryan.CTHelpers.AssetLoader;
import com.timryan.CrappyTurd.CTGame;
import com.timryan.Screens.*;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	private int fartScore = 0;
	private float runTime = 0;
	private int midPointY, midPointX;
	private CTGame game;
	private GameRenderer renderer;
	
	private GameState currentState;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, OPTION, STORY, LEVEL, BOSS
	}

	public GameWorld(int midPointX, int midPointY, CTGame game) {
		this.game = game;
		currentState = GameState.MENU;
		
		this.midPointY = midPointY;
		this.midPointX = midPointX;
		bird = new Bird(33, midPointY - 5, 17, 12);
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 137, 11);
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;
		case OPTION:
			updateReady(delta);
			break;
		case STORY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		case LEVEL:
			updateRunning(delta);
			break;
		case BOSS:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}
		bird.update(delta);
		scroller.update(delta);

		if (scroller.collides(bird) && bird.isAlive() 
				&& !scroller.getFart().collides(bird)) {
			scroller.stop();
			bird.die();
			AssetLoader.dead.play();
			renderer.prepareTransition(255, 255, 255, .3f);
		}
		if (scroller.getFart().collides(bird) && bird.isAlive()){
			scroller.getFart().reset(scroller.getFart().getTailX() + 
					ScrollHandler.FART_GAP);
		}
		
		if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {

			if (bird.isAlive()) {
				AssetLoader.dead.play();
				renderer.prepareTransition(255, 255, 255, .3f);
				bird.die();
			}
			if(scroller.isBossComplete()){
				scroller.setBossComplete(false);
			}
			scroller.stop();
			bird.decelerate();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
		
		if (scroller.isComplete() || AssetLoader.isFinKill()){
			AssetLoader.win.play();
			AssetLoader.setCurrentLevel(scroller.getLevel());
			if(scroller.getLevel() > AssetLoader.getLimit()){
				AssetLoader.setLimit(scroller.getLevel());
			}
			currentState = GameState.GAMEOVER;
			bird.updateReady(runTime);
		}
	}
	
	public void changeScreen(int level){
		if(level == 1){
			game.setScreen(new StoryScreen1A(game, new Sprite (AssetLoader.storyScreen1A)));
		}else{
			game.setScreen(new GasScreen(game, new Sprite (AssetLoader.gasScreen), level));
		}
	}
	
	public void exit(float delta) {
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public CTGame getGame() {
		return game;
	}
	
	public Bird getBird() {
		return bird;
	}

	public int getMidPointY() {
		return midPointY;
	}
	
	public int getMidPointX() {
		return midPointX;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}
	
	public int getFartScore() {
		return fartScore;
	}

	public void addScore(int increment) {
		score += increment;
	}
	
	public void addFartScore(int increment) {
		fartScore += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void openMain(){
		score = 0;
		fartScore = 0;
		AssetLoader.setCurrentLevel(0);
		AssetLoader.setFinKill(false);
		currentState = GameState.MENU;
		bird.onRestart(midPointY - 5);
		scroller.onRestart(-1);
	}
	
	public void openOptions() {
		currentState = GameState.OPTION;
		bird.onRestart(midPointY);
		scroller.onRestart(-1);
	}

	public void openStory() {
		AssetLoader.setCurrentLevel(0);
		AssetLoader.setFinKill(false);
		score = 0;
		fartScore = 0;
		currentState = GameState.STORY;
		bird.onRestart(midPointY);
		scroller.onRestart(-1);
	}
	
	public void startLevel(int level){
		score = 0;
		fartScore = 0;
		AssetLoader.setCurrentLevel(level);
		bird.onRestart(midPointY - 5);
		scroller.onRestart(level);
		if(AssetLoader.isFinKill()){
			currentState = GameState.RUNNING;
		}else{
			ready();
		}
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public boolean isOption() {
		return currentState == GameState.OPTION;
	}

	public boolean isStory() {
		return currentState == GameState.STORY;
	}
	
	public boolean isLevel() {
		return currentState == GameState.LEVEL;
	}
	
	public boolean isBoss() {
		return currentState == GameState.BOSS;
	}
	
	public GameState getCurrentState(){
		return currentState;
	}
	
	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

}
