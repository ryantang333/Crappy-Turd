package com.timryan.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.timryan.CTHelpers.AssetLoader;
import com.timryan.CTHelpers.InputHandler;
import com.timryan.CrappyTurd.CTGame;
import com.timryan.GameWorld.GameRenderer;
import com.timryan.GameWorld.GameWorld;

public class GameScreen1 implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	private InputHandler input;

	public GameScreen1(CTGame game) {
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
		world.startLevel(1);
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
