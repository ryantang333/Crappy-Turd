package com.timryan.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.timryan.TweenAccessors.SpriteAccessor;
import com.timryan.CTHelpers.AssetLoader;
import com.timryan.CrappyTurd.CTGame;

public class StoryScreen1A implements Screen {

	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private CTGame game;

	public StoryScreen1A(CTGame game, Sprite sprite) {
		this.game = game;
		this.sprite = sprite;
	}
	
	@Override
	public void show() {
		sprite.setColor(1, 1, 1, 0);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		sprite.setSize(width, height);
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
		setupTween();
		batcher = new SpriteBatch();
	}

	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new StoryScreen1B(game, new Sprite (AssetLoader.storyScreen1B)));
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, 2f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 1.5f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}

	@Override
	public void render(float delta) {
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
