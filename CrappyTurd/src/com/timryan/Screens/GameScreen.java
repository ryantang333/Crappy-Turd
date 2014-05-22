package com.timryan.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.timryan.CTHelpers.AssetLoader;
import com.timryan.CTHelpers.InputHandler;
import com.timryan.CrappyTurd.CTGame;
import com.timryan.GameWorld.GameRenderer;
import com.timryan.GameWorld.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	private InputHandler input;

	public GameScreen(CTGame game) {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		int midPointY = (int) (gameHeight / 2);
		int midPointX = (int) (gameWidth / 2);

		world = new GameWorld(midPointX, midPointY, game);
		input = new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight);
		Gdx.input.setInputProcessor(input);
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		world.setRenderer(renderer);
		
		if(AssetLoader.isFinKill1()){
			world.startLevel(4);
		}else if(AssetLoader.isFinKill2()){
			world.startLevel(8);
		}else if(AssetLoader.isFinKill3()){
			world.startLevel(12);
		}else{
			AssetLoader.setCurrentLevel(0);
		}
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
		if(input.isPressed1()){
			world.changeScreen(1);
		}if(input.isPressedGas()){
			AssetLoader.kill.play();
			world.changeScreen(AssetLoader.getCurrentLevel());
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
