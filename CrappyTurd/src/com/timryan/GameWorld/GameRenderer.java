package com.timryan.GameWorld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.timryan.CTHelpers.AssetLoader;
import com.timryan.CTHelpers.InputHandler;
import com.timryan.GameObjects.Bird;
import com.timryan.GameObjects.Fart;
import com.timryan.GameObjects.Grass;
import com.timryan.GameObjects.Pipe;
import com.timryan.GameObjects.Plunger;
import com.timryan.GameObjects.ScrollHandler;
import com.timryan.TweenAccessors.Value;
import com.timryan.TweenAccessors.ValueAccessor;
import com.timryan.ui.SimpleButton;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY, midPointX;

	// Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;
	private Plunger plunger;
	private Fart fart;

	// Game Assets
	private TextureRegion bg, grass, birdMid, tpUp, tpDown, stand, ready,
			ctLogo, gameOver, highScore, scoreboard, star, noStar, fartCloud,
			plungerUpHead, plungerUpStand, plungerSideHead, plungerSideStand,
			story, options, complete, plungerSquish, plungerStretch, plungerDead;
	private Animation birdAnimation, releaseGasAnimation;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons, gameOverButtons, optionButtons,
	storyButtons, levelButtons, storyGameOverButtons;
	
	private Color transitionColor;
	private SimpleButton menuButton, releaseGasButton;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.midPointX = myWorld.getMidPointX();
		this.menuButton = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButton();
		this.releaseGasButton = ((InputHandler) Gdx.input.getInputProcessor())
				.getGasButton();
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();
		this.gameOverButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getGameOverButtons();
		this.storyButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getStoryButtons();
		this.optionButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getOptionButtons();
		this.levelButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getLevelButtons();
		this.storyGameOverButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getStoryGameOverButtons();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, .5f);
	}

	private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
		fart = scroller.getFart();
		plunger = scroller.getPlunger();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		tpUp = AssetLoader.tpUp;
		tpDown = AssetLoader.tpDown;
		stand = AssetLoader.stand;
		plungerUpHead = AssetLoader.plungerUpHead;
		plungerUpStand = AssetLoader.plungerUpStand;
		plungerSideHead = AssetLoader.plungerSideHead;
		plungerSideStand = AssetLoader.plungerSideStand;
		plungerSquish = AssetLoader.plungerSquish;
		plungerStretch = AssetLoader.plungerStretch;
		plungerDead = AssetLoader.plungerDead;
		releaseGasAnimation = AssetLoader.releaseGasAnimation;
		ready = AssetLoader.ready;
		ctLogo = AssetLoader.ctLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		star = AssetLoader.star;
		noStar = AssetLoader.noStar;
		fartCloud = AssetLoader.fart;
		story = AssetLoader.story;
		options = AssetLoader.options;
		complete = AssetLoader.complete;
	}
	
	private void drawPlungerHead(float runTime){
		if(plunger.getLayout().equals(Plunger.PlungerLayout.VERTICAL)){
			batcher.draw(plungerUpHead, plunger.getHead().getX(), 
					plunger.getHead().getY(), plunger.getHead().getWidth(), 
					plunger.getHead().getHeight());
		}else if(plunger.getLayout().equals(Plunger.PlungerLayout.HORIZONTAL)){
			batcher.draw(plungerSideHead, plunger.getHead().getX(), 
					plunger.getHead().getY(), plunger.getHead().getWidth(), 
					plunger.getHead().getHeight());
		}else if(plunger.getLayout().equals(Plunger.PlungerLayout.JUMP)){
			if(plunger.getY() >= 100){
				batcher.draw(plungerSquish, plunger.getHead().getX(), 
						plunger.getHead().getY(), plunger.getHead().getWidth(), 
						plunger.getHead().getHeight());
			}else if(plunger.isUp()){
				batcher.draw(plungerStretch, plunger.getHead().getX(), 
						plunger.getHead().getY(), plunger.getHead().getWidth(), 
						plunger.getHead().getHeight());
			}else{
				batcher.draw(plungerUpHead, plunger.getHead().getX(), 
						plunger.getHead().getY(), plunger.getHead().getWidth(), 
						plunger.getHead().getHeight());
			}
		}
	}
	
	private void drawPlungerStand(){
		if(plunger.getLayout().equals(Plunger.PlungerLayout.VERTICAL)
				|| plunger.getLayout().equals(Plunger.PlungerLayout.JUMP)){
			batcher.draw(plungerUpStand, plunger.getHandle().getX(), 
					plunger.getHandle().getY(),	plunger.getHandle().getWidth(), 
					plunger.getHandle().getHeight());
		}else if(plunger.getLayout().equals(Plunger.PlungerLayout.HORIZONTAL)){
			batcher.draw(plungerSideStand, plunger.getHandle().getX(), 
					plunger.getHandle().getY(),	plunger.getHandle().getWidth(), 
					plunger.getHandle().getHeight());
		}		
	}
	
	private void drawFart() {
		batcher.draw(fartCloud, fart.getX(), fart.getY(), fart.getWidth(), fart.getHeight());
	}

	private void drawGrass() {
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawTP() {
		batcher.draw(tpUp, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(tpDown, pipe1.getX() - 1,
				pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(tpUp, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(tpDown, pipe2.getX() - 1,
				pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

		batcher.draw(tpUp, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(tpDown, pipe3.getX() - 1,
				pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
	}

	private void drawPipes() {
		batcher.draw(stand, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
				pipe1.getHeight());
		batcher.draw(stand, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
				pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

		batcher.draw(stand, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
				pipe2.getHeight());
		batcher.draw(stand, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
				pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

		batcher.draw(stand, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
				pipe3.getHeight());
		batcher.draw(stand, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
				pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
	}

	private void drawBirdCentered(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59, bird.getY() - 15,
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, 0);
	}

	private void drawBirdTop(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59, bird.getY() - 55,
				bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
	}
	
	private void drawBirdReady(float runTime) {
		if (bird.shouldntFlap()) {
			batcher.draw(birdMid, bird.getX(), bird.getY(),
					bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
					bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		} else {
			batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
					bird.getY(), bird.getWidth() / 2.0f,
					bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
					1, 1, bird.getRotation());
		}
	}
	
	private void drawMenuButton() {
		menuButton.draw(batcher);
	}

	private void drawMenuUI() {
		batcher.draw(ctLogo, 136 / 2 - 56, midPointY - 50,
				ctLogo.getRegionWidth() / 1.2f, ctLogo.getRegionHeight() / 1.2f);
		
		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}
	}

	private void drawRestartUI() {
		for (SimpleButton button : gameOverButtons) {
			button.draw(batcher);
		}
	}
	
	private void drawOptionTitle(){
		batcher.draw(options, midPointX - 28, 20, 56, 22);
	}

	private void drawOptionUI() {
		for (SimpleButton button : optionButtons) {
			button.draw(batcher);
		}
	}
	
	private void drawStoryTitle(){
		batcher.draw(story, midPointX - story.getRegionWidth()/2, 20, 56, 22);
	}
	
	private void drawStoryUI() {
		for (SimpleButton button : storyButtons) {
			button.draw(batcher);
		}
	}
	
	private void drawLevelUI() {
		for (SimpleButton button : levelButtons) {
			button.draw(batcher);
		}
	}
	
	private void drawStoryGameOverUI() {
		for (SimpleButton button : storyGameOverButtons) {
			button.draw(batcher);
		}
	}
	
	private void drawScoreboard() {
		batcher.draw(scoreboard, 22, midPointY - 30, 97, 37);
		batcher.draw(noStar, 25, midPointY - 15, 10, 10);
		batcher.draw(noStar, 37, midPointY - 15, 10, 10);
		batcher.draw(noStar, 49, midPointY - 15, 10, 10);
		batcher.draw(noStar, 61, midPointY - 15, 10, 10);
		batcher.draw(noStar, 73, midPointY - 15, 10, 10);

		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25, midPointY - 15, 10, 10);
		}

		int length = ("" + myWorld.getScore()).length();

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
				104 - (2 * length), midPointY - 20);

		int length2 = ("" + AssetLoader.getHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				104 - (2.5f * length2), midPointY - 3);
	}
	
	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}
	
	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 50, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 82);
		AssetLoader.whiteFontBig.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 83);
	}
	
	private void drawFartScore() {
		int length = ("GAS:" + myWorld.getFartScore()).length();
		AssetLoader.shadow.draw(batcher, "GAS:" + myWorld.getFartScore(),
				midPointX - (4 * length), midPointY + 90);
		AssetLoader.font.draw(batcher, "GAS:" + myWorld.getFartScore(),
				midPointX - (4 * length), midPointY + 91);
	}

	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 50, 96, 14);
	}
	
	private void drawComplete(){
		batcher.draw(complete, 136 / 2 - 56, midPointY - 50,
				ctLogo.getRegionWidth() / 1.2f, ctLogo.getRegionHeight() / 1.2f);
	}
	
	private void drawReleaseGas(float runTime){
		batcher.draw(releaseGasAnimation.getKeyFrame(runTime), releaseGasButton.getX(),
				releaseGasButton.getY(), releaseGasButton.getWidth(), 
				releaseGasButton.getHeight());
	}
	
	private void drawPlungerDead(){

		//batcher.draw(highScore, 22, midPointY - 50, 96, 14);
		batcher.draw(plungerDead, midPointX, 
				midPointY + 58, plungerDead.getRegionWidth(), plungerDead.getRegionHeight());
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Draw Grass
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Draw Dirt
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();

		batcher.draw(bg, 0, midPointY + 23, 136, 43);
		
		drawPipes();
		batcher.enableBlending();
		drawTP();
		drawGrass();
		
		if (myWorld.isRunning()) {
			drawBirdReady(runTime);
			drawScore();
			if(myWorld.getScroller().getLevel() == 4 || myWorld.getScroller().getLevel() == 8 ||
					myWorld.getScroller().getLevel() == 12){
				if(scroller.isBossComplete()){
					drawPlungerStand();
					drawPlungerHead(runTime);					
				}
				drawFartScore();
				drawFart();
				if(myWorld.getScroller().isGassed()){
					drawReleaseGas(runTime);
				}
			}
		} else if (myWorld.isReady()) {
			drawBirdReady(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI();
		} else if (myWorld.isGameOver() || myWorld.isHighScore()) {
			if(myWorld.getScroller().getLevel() != -1){
				if(myWorld.getScroller().isComplete()){
					drawBirdCentered(runTime);
					drawComplete();
					drawLevelUI();
					drawMenuButton();
				}
				else if(AssetLoader.isFinKill()){
					drawBirdCentered(runTime);
					drawComplete();
					drawLevelUI();
					drawMenuButton();
					drawPlungerDead();
				}
				else{
					drawMenuButton();
					drawScoreboard();
					drawBirdReady(runTime);
					if(myWorld.isGameOver()){
						drawGameOver();
					}else if(myWorld.isHighScore()){
						drawHighScore();
					}
					drawStoryGameOverUI();
				}
			}else{
				drawMenuButton();
				drawScoreboard();
				drawBirdReady(runTime);
				if(myWorld.isGameOver()){
					drawGameOver();
				}else if(myWorld.isHighScore()){
					drawHighScore();
				}
				drawRestartUI();
			}
		}else if (myWorld.isOption()) {
			drawOptionUI();
			drawMenuButton();
			drawOptionTitle();
			drawBirdTop(runTime);
		}else if (myWorld.isStory()) {
			drawStoryTitle();
			drawStoryUI();
			drawMenuButton();
			drawBirdTop(runTime);
		}

		
		

		batcher.end();
		drawTransition(delta);

	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

}
