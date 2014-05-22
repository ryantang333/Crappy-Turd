package com.timryan.CTHelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.timryan.GameObjects.Bird;
import com.timryan.GameWorld.GameWorld;
import com.timryan.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private Bird myBird;
	
	private GameWorld myWorld;

	private List<SimpleButton> menuButtons, gameOverButtons, optionButtons, 
	storyButtons, levelButtons, storyGameOverButtons;

	private SimpleButton arcadeButton, storyButton, optionsButton, menuButton, retryButton, 
		resetScoreButton, levelOneButton, levelTwoButton, levelThreeButton, 
		levelFourButton, levelFiveButton, levelSixButton, levelSevenButton, levelEightButton, 
		levelNineButton, levelTenButton, levelElevenButton, levelTwelveButton, storyMenuButton,
		nextButton, releaseGasButton, storyRetryButton;

	private float scaleFactorX;
	private float scaleFactorY;
	
	private boolean isPressed1 = false, isPressedGas = false;
	
	private int limit;

	public InputHandler(GameWorld myWorld, float scaleFactorX,
		float scaleFactorY) {
		this.myWorld = myWorld;
		myBird = myWorld.getBird();

		int midPointY = myWorld.getMidPointY();
		int midPointX = myWorld.getMidPointX();
		
		
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		gameOverButtons = new ArrayList<SimpleButton>();
		optionButtons = new ArrayList<SimpleButton>();
		storyButtons = new ArrayList<SimpleButton>();
		levelButtons = new ArrayList<SimpleButton>();
		storyGameOverButtons = new ArrayList<SimpleButton>();
		
		/*
		 * Game Buttons
		 */
		
		arcadeButton = new SimpleButton(
				midPointX - midPointX/2 - AssetLoader.arcadeButtonUp.getRegionWidth()/2,
				midPointY + 30, midPointX/2, midPointY/7, AssetLoader.arcadeButtonUp,
				AssetLoader.arcadeButtonDown);
		
		storyButton = new SimpleButton(
				midPointX + midPointX/2 - AssetLoader.storyButtonUp.getRegionWidth()/2,
				midPointY + 30,  midPointX/2, midPointY/7,
				AssetLoader.storyButtonUp, AssetLoader.storyButtonDown);
		
		optionsButton = new SimpleButton(
				midPointX - (AssetLoader.optionsButtonUp.getRegionWidth() / 2),
				midPointY + 90, midPointX/2, midPointY/7, AssetLoader.optionsButtonUp,
				AssetLoader.optionsButtonDown);
		
		menuButton = new SimpleButton(
				midPointX - (AssetLoader.menuButtonUp.getRegionWidth() / 2),
				midPointY + 90, midPointX/2, midPointY/7, AssetLoader.menuButtonUp,
				AssetLoader.menuButtonDown);
		
		retryButton = new SimpleButton(
				midPointX - (AssetLoader.retryButtonUp.getRegionWidth() / 2),
				midPointY + 30, midPointX/2, midPointY/7, AssetLoader.retryButtonUp,
				AssetLoader.retryButtonDown);
		
		resetScoreButton = new SimpleButton(
				midPointX - (AssetLoader.resetButtonUp.getRegionWidth() / 2),
				midPointY - midPointY/5, midPointX/2, midPointY/7, AssetLoader.resetButtonUp,
				AssetLoader.resetButtonDown);
		
		releaseGasButton = new SimpleButton(
				midPointX - (AssetLoader.releaseGasUp.getRegionWidth() / 2), midPointY + 90,
				AssetLoader.releaseGasUp.getRegionWidth(), midPointY/6,
				AssetLoader.releaseGasUp, AssetLoader.releaseGasDown);
		
		/*
		 * Level UI
		 */
		nextButton = new SimpleButton(
				midPointX + midPointX/2 - AssetLoader.nextButtonUp.getRegionWidth()/2,
				midPointY + 30, midPointX/2, midPointY/7, AssetLoader.nextButtonUp,
				AssetLoader.nextButtonDown);
		
		storyMenuButton = new SimpleButton(
				midPointX - midPointX/2 - AssetLoader.storyButtonUp.getRegionWidth()/2,
				midPointY + 30, midPointX/2, midPointY/7, AssetLoader.storyButtonUp,
				AssetLoader.storyButtonDown);
		
		/*
		 * Level GameOver
		 */
		storyRetryButton = new SimpleButton(
				midPointX + midPointX/2 - AssetLoader.retryButtonUp.getRegionWidth()/2,
				midPointY + 30, midPointX/2, midPointY/7, AssetLoader.retryButtonUp,
				AssetLoader.retryButtonDown);
		
		
		/*
		 * Story Buttons
		 */
		levelOneButton = new SimpleButton(
				midPointX/8, midPointY*3/4, midPointX/4, midPointY/8, AssetLoader.levelOneButtonUp,
				AssetLoader.levelOneButtonDown
				);
		levelTwoButton = new SimpleButton(
				(midPointX*5)/8, midPointY*3/4, midPointX/4, midPointY/8, AssetLoader.levelTwoButtonUp,
				AssetLoader.levelTwoButtonDown
				);
		levelThreeButton = new SimpleButton(
				(midPointX*9)/8, midPointY*3/4, midPointX/4, midPointY/8, AssetLoader.levelThreeButtonUp,
				AssetLoader.levelThreeButtonDown
				);
		levelFourButton = new SimpleButton(
				(midPointX*13)/8, midPointY*3/4, midPointX/4, midPointY/8, AssetLoader.levelFourButtonUp,
				AssetLoader.levelFourButtonDown
				);
		levelFiveButton = new SimpleButton(
				midPointX/8, midPointY, midPointX/4, midPointY/8, AssetLoader.levelFiveButtonUp,
				AssetLoader.levelFiveButtonDown
				);
		levelSixButton = new SimpleButton(
				(midPointX*5)/8, midPointY, midPointX/4, midPointY/8, AssetLoader.levelSixButtonUp,
				AssetLoader.levelSixButtonDown
				);
		levelSevenButton = new SimpleButton(
				(midPointX*9)/8, midPointY, midPointX/4, midPointY/8, AssetLoader.levelSevenButtonUp,
				AssetLoader.levelSevenButtonDown
				);
		levelEightButton = new SimpleButton(
				(midPointX*13)/8, midPointY, midPointX/4, midPointY/8, AssetLoader.levelEightButtonUp,
				AssetLoader.levelEightButtonDown
				);
		levelNineButton = new SimpleButton(
				midPointX/8, midPointY*5/4, midPointX/4, midPointY/8, AssetLoader.levelNineButtonUp,
				AssetLoader.levelNineButtonDown
				);
		levelTenButton = new SimpleButton(
				(midPointX*5)/8, midPointY*5/4, midPointX/4, midPointY/8, AssetLoader.levelTenButtonUp,
				AssetLoader.levelTenButtonDown
				);
		levelElevenButton = new SimpleButton(
				(midPointX*9)/8, midPointY*5/4, midPointX/4, midPointY/8, AssetLoader.levelElevenButtonUp,
				AssetLoader.levelElevenButtonDown
				);
		levelTwelveButton = new SimpleButton(
				(midPointX*13)/8, midPointY*5/4, midPointX/4, midPointY/8, AssetLoader.levelTwelveButtonUp,
				AssetLoader.levelTwelveButtonDown
				);
		
		storyButtons.add(levelOneButton);
		storyButtons.add(levelTwoButton);
		storyButtons.add(levelThreeButton);
		storyButtons.add(levelFourButton);
		storyButtons.add(levelFiveButton);
		storyButtons.add(levelSixButton);
		storyButtons.add(levelSevenButton);
		storyButtons.add(levelEightButton);
		storyButtons.add(levelNineButton);
		storyButtons.add(levelTenButton);
		storyButtons.add(levelElevenButton);
		storyButtons.add(levelTwelveButton);
		menuButtons.add(storyButton);
		menuButtons.add(arcadeButton);
		menuButtons.add(optionsButton);
		gameOverButtons.add(retryButton);
		optionButtons.add(resetScoreButton);
		levelButtons.add(storyMenuButton);
		levelButtons.add(nextButton);
		storyGameOverButtons.add(storyRetryButton);
		storyGameOverButtons.add(storyMenuButton);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		limit = AssetLoader.getLimit();
		
		if (myWorld.isMenu()){
			arcadeButton.isTouchDown(screenX, screenY);
			storyButton.isTouchDown(screenX, screenY);
			optionsButton.isTouchDown(screenX, screenY);
		} else if (myWorld.isReady()){
			myWorld.start();
			myBird.onClick();
		} else if (myWorld.isRunning()){
			myBird.onClick();
			if(myWorld.getScroller().isGassed()){
				releaseGasButton.isTouchDown(screenX, screenY);
			}
		} else if (myWorld.isOption()){
			menuButton.isTouchDown(screenX, screenY);
			resetScoreButton.isTouchDown(screenX, screenY);
		} else if (myWorld.isStory()){
			menuButton.isTouchDown(screenX, screenY);
			levelOneButton.isTouchDown(screenX, screenY);
			if (limit >= 1){
				levelTwoButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 2){
				levelThreeButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 3){
				levelFourButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 4){
				levelFiveButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 5){
				levelSixButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 6){
				levelSevenButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 7){
				levelEightButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 8){
				levelNineButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 9){
				levelTenButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 10){
				levelElevenButton.isTouchDown(screenX, screenY);
			}
			if (limit >= 11){
				levelTwelveButton.isTouchDown(screenX, screenY);
			}
			
		} else if (myWorld.isLevel()){
			myWorld.start();
			myBird.onClick();
		}

		if (myWorld.isGameOver() || myWorld.isHighScore()){
			menuButton.isTouchDown(screenX, screenY);
			if(myWorld.getScroller().getLevel() != -1){
				if(myWorld.getScroller().isComplete() || AssetLoader.isFinKill()){
					for(SimpleButton tempbutton : levelButtons){
						tempbutton.isTouchDown(screenX, screenY);
					}
				}else{
					for(SimpleButton tempbutton : storyGameOverButtons){
						tempbutton.isTouchDown(screenX, screenY);
					}
				}
			}
			else{
				retryButton.isTouchDown(screenX, screenY);				
			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if (myWorld.isRunning()){
			if(myWorld.getScroller().isGassed()){
				releaseGasButton.isTouchUp(screenX, screenY);
				isPressedGas = true;
				return true;
			}
		}
		if (myWorld.isMenu()) {
			if (arcadeButton.isTouchUp(screenX, screenY)) {
				myWorld.ready();
				return true;
			}
			if (storyButton.isTouchUp(screenX, screenY)) {
				myWorld.openStory();
				return true;
			}
			if (optionsButton.isTouchUp(screenX, screenY)) {
				myWorld.openOptions();
				return true;
			}
		}
		if (myWorld.isOption()) {
			if (resetScoreButton.isTouchUp(screenX, screenY)) {
				AssetLoader.setHighScore(0);
				return true;
			}
			if (menuButton.isTouchUp(screenX, screenY)) {
				myWorld.openMain();
				return true;
			}
		}
		
		if (myWorld.isStory()) {
			if (menuButton.isTouchUp(screenX, screenY)) {
				myWorld.openMain();
				return true;
			}
			if (levelOneButton.isTouchUp(screenX, screenY)) {
				if(!AssetLoader.isFinIntro1()){
					isPressed1 = true;
					//myWorld.changeScreen(1);
				}
				else{
					myWorld.startLevel(1);
				}
				return true;
			}
			if (levelTwoButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(2);
				return true;
			}
			if (levelThreeButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(3);
				return true;
			}
			if (levelFourButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(4);
				return true;
			}
			if (levelFiveButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(5);
				return true;
			}
			if (levelSixButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(6);
				return true;
			}
			if (levelSevenButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(7);
				return true;
			}
			if (levelEightButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(8);
				return true;
			}
			if (levelNineButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(9);
				return true;
			}
			if (levelTenButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(10);
				return true;
			}
			if (levelElevenButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(11);
				return true;
			}
			if (levelTwelveButton.isTouchUp(screenX, screenY)) {
				myWorld.startLevel(12);
				return true;
			}
		}
		
		if (myWorld.isGameOver() || myWorld.isHighScore()){
			if (menuButton.isTouchUp(screenX, screenY)) {
				myWorld.openMain();
				return true;
			}
			if(myWorld.getScroller().getLevel() != -1){
				if (storyMenuButton.isTouchUp(screenX, screenY)) {
					myWorld.openStory();
					return true;
				}
				if(myWorld.getScroller().isComplete()){
					if (nextButton.isTouchUp(screenX, screenY)) {
						int nextLevel = myWorld.getScroller().getLevel() + 1;
						myWorld.startLevel(nextLevel);
						return true;
					}
				}else if(AssetLoader.isFinKill()){
					if (nextButton.isTouchUp(screenX, screenY)) {
						if (AssetLoader.getCurrentLevel() == 4){
							AssetLoader.setFinKill(false);
							myWorld.startLevel(5);
						}else if (AssetLoader.getCurrentLevel() == 8){
							AssetLoader.setFinKill(false);
							myWorld.startLevel(9);
						}else if (AssetLoader.getCurrentLevel() == 12){
							AssetLoader.setFinKill(false);
							myWorld.startLevel(-1);
						}
						return true;
					}
				}else{
					if (storyRetryButton.isTouchUp(screenX, screenY)) {
						myWorld.startLevel(myWorld.getScroller().getLevel());
						return true;
					}
				}
			}
			else{
				if (retryButton.isTouchUp(screenX, screenY)) {
					myWorld.startLevel(myWorld.getScroller().getLevel());
					return true;
				}
			}
			
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		// Can now use Space Bar to play the game
		if (keycode == Keys.SPACE) {

			if (myWorld.isMenu()) {
				myWorld.ready();
			} else if (myWorld.isReady()) {
				myWorld.start();
			}

			myBird.onClick();

			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				myWorld.startLevel(myWorld.getScroller().getLevel());
			}

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}
	
	public List<SimpleButton> getStoryButtons() {
		return storyButtons;
	}
	
	public List<SimpleButton> getOptionButtons() {
		return optionButtons;
	}
	
	public List<SimpleButton> getGameOverButtons() {
		return gameOverButtons;
	}
	
	public List<SimpleButton> getLevelButtons() {
		return levelButtons;
	}
	
	public List<SimpleButton> getStoryGameOverButtons() {
		return storyGameOverButtons;
	}

	public SimpleButton getMenuButton() {
		return menuButton;
	}
	
	public SimpleButton getGasButton() {
		return releaseGasButton;
	}
	
	public boolean isPressed1(){
		return isPressed1;
	}
	
	public boolean isPressedGas(){
		return isPressedGas;
	}
}
