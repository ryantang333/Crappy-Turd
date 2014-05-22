package com.timryan.CTHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture, storyTexture, gasTexture;
	public static TextureRegion logo, yetiLogo, ctLogo, bg, grass, bird, birdDown,
			birdUp, tpUp, tpDown, stand, playButtonUp, playButtonDown,
			ready, gameOver, highScore, complete, scoreboard, star, noStar, 
			story, options, releaseGasUp, releaseGasDown, castle, flag,
			arcadeButtonUp, arcadeButtonDown, storyButtonUp, storyButtonDown,
			optionsButtonUp, optionsButtonDown, menuButtonUp, menuButtonDown,
			retryButtonUp, retryButtonDown, volumeLabelUp, volumeLabelDown,
			resetButtonUp, resetButtonDown, nextButtonUp, nextButtonDown,
			plungerUpHead, plungerUpStand, fart, plungerStretch,
			plungerSideHead, plungerSideStand, plungerSquish, plungerDead;
	public static TextureRegion storyScreen1A, storyScreen1B, storyScreen1C,
			storyScreen2A, storyScreen2B, storyScreen2C, storyScreen2D, 
			storyScreen3A, storyScreen3B, storyScreen3C,
			storyScreen4A, storyScreen4B, storyScreen4C, storyScreen4D,
			gasScreen;
	public static TextureRegion levelOneButtonUp, levelOneButtonDown,
			levelTwoButtonUp, levelTwoButtonDown, levelThreeButtonUp, levelThreeButtonDown,
			levelFourButtonUp, levelFourButtonDown, levelFiveButtonUp, levelFiveButtonDown,
			levelSixButtonUp, levelSixButtonDown, levelSevenButtonUp, levelSevenButtonDown,
			levelEightButtonUp, levelEightButtonDown, levelNineButtonUp, levelNineButtonDown,
			levelTenButtonUp, levelTenButtonDown, levelElevenButtonUp, levelElevenButtonDown,
			levelTwelveButtonUp, levelTwelveButtonDown;
	public static Animation birdAnimation, releaseGasAnimation;
	public static Sound dead, flap, coin, fall, win, kill, plunger, breath;
	public static BitmapFont font, shadow, whiteFont, whiteFontBig;
	private static Preferences prefs;

	public static void load() {
		
		logoTexture = new Texture(Gdx.files.internal("data/YETILOGO.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		yetiLogo = new TextureRegion(logoTexture, 0, 0, 512, 256);
		
		gasTexture = new Texture(Gdx.files.internal("data/storyScreens.png"));
		gasTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		gasScreen = new TextureRegion(gasTexture, 548, 486, 136, 242);
		
		storyTexture = new Texture(Gdx.files.internal("data/storyScreens.png"));
		storyTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		storyScreen1A = new TextureRegion(storyTexture, 0, 0, 136, 242);
		storyScreen1B = new TextureRegion(storyTexture, 137, 0, 136, 242);
		storyScreen1C = new TextureRegion(storyTexture, 274, 0, 136, 242);
		storyScreen2A = new TextureRegion(storyTexture, 0, 243, 136, 242);
		storyScreen2B = new TextureRegion(storyTexture, 137, 243, 136, 242);
		storyScreen2C = new TextureRegion(storyTexture, 274, 243, 136, 242);
		storyScreen2D = new TextureRegion(storyTexture, 411, 243, 136, 242);
		storyScreen3A = new TextureRegion(storyTexture, 0, 486, 136, 242);
		storyScreen3B = new TextureRegion(storyTexture, 137, 486, 136, 242);
		storyScreen3C = new TextureRegion(storyTexture, 274, 486, 136, 242);
		storyScreen4A = new TextureRegion(storyTexture, 0, 729, 136, 242);
		storyScreen4B = new TextureRegion(storyTexture, 137, 729, 136, 242);
		storyScreen4C = new TextureRegion(storyTexture, 274, 729, 136, 242);
		storyScreen4D = new TextureRegion(storyTexture, 411, 729, 136, 242);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);
		
		arcadeButtonUp = new TextureRegion(texture, 0, 119, 37, 16);
		arcadeButtonDown = new TextureRegion(texture, 37, 119, 37, 16);
		arcadeButtonUp.flip(false, true);
		arcadeButtonDown.flip(false, true);
		
		storyButtonUp = new TextureRegion(texture, 0, 136, 37, 16);
		storyButtonDown = new TextureRegion(texture, 37, 136, 37, 16);
		storyButtonUp.flip(false, true);
		storyButtonDown.flip(false, true);
		
		optionsButtonUp = new TextureRegion(texture, 0, 153, 37, 16);
		optionsButtonDown = new TextureRegion(texture, 37, 153, 37, 16);
		optionsButtonUp.flip(false, true);
		optionsButtonDown.flip(false, true);
		
		menuButtonUp = new TextureRegion(texture, 0, 170, 37, 16);
		menuButtonDown = new TextureRegion(texture, 37, 170, 37, 16);
		menuButtonUp.flip(false, true);
		menuButtonDown.flip(false, true);
		
		retryButtonUp = new TextureRegion(texture, 0, 187, 37, 16);
		retryButtonDown = new TextureRegion(texture, 37, 187, 37, 16);
		retryButtonUp.flip(false, true);
		retryButtonDown.flip(false, true);
		
		volumeLabelUp = new TextureRegion(texture, 75, 119, 37, 16);
		volumeLabelDown = new TextureRegion(texture, 112, 119, 37, 16);
		volumeLabelUp.flip(false, true);
		volumeLabelDown.flip(false, true);
		
		resetButtonUp = new TextureRegion(texture, 75, 136, 37, 16);
		resetButtonDown = new TextureRegion(texture, 112, 136, 37, 16);
		resetButtonUp.flip(false, true);
		resetButtonDown.flip(false, true);
		
		nextButtonUp = new TextureRegion(texture, 75, 153, 37, 16);
		nextButtonDown = new TextureRegion(texture, 112, 153, 37, 16);
		nextButtonUp.flip(false, true);
		nextButtonDown.flip(false, true);
		
		levelOneButtonUp = new TextureRegion(texture, 0, 205, 23, 23);
		levelOneButtonDown = new TextureRegion(texture, 26, 205, 23, 23);
		levelOneButtonUp.flip(false, true);
		levelOneButtonDown.flip(false, true);
		
		levelTwoButtonUp = new TextureRegion(texture, 0, 231, 23, 23);
		levelTwoButtonDown = new TextureRegion(texture, 26, 231, 23, 23);
		levelTwoButtonUp.flip(false, true);
		levelTwoButtonDown.flip(false, true);
		
		levelThreeButtonUp = new TextureRegion(texture, 0, 257, 23, 23);
		levelThreeButtonDown = new TextureRegion(texture, 26, 257, 23, 23);
		levelThreeButtonUp.flip(false, true);
		levelThreeButtonDown.flip(false, true);
		
		levelFourButtonUp = new TextureRegion(texture, 0, 283, 25, 25);
		levelFourButtonDown = new TextureRegion(texture, 26, 283, 25, 25);
		levelFourButtonUp.flip(false, true);
		levelFourButtonDown.flip(false, true);
		
		levelFiveButtonUp = new TextureRegion(texture, 55, 205, 23, 23);
		levelFiveButtonDown = new TextureRegion(texture, 81, 205, 23, 23);
		levelFiveButtonUp.flip(false, true);
		levelFiveButtonDown.flip(false, true);
		
		levelSixButtonUp = new TextureRegion(texture, 55, 231, 23, 23);
		levelSixButtonDown = new TextureRegion(texture, 81, 231, 23, 23);
		levelSixButtonUp.flip(false, true);
		levelSixButtonDown.flip(false, true);
		
		levelSevenButtonUp = new TextureRegion(texture, 55, 257, 23, 23);
		levelSevenButtonDown = new TextureRegion(texture, 81, 257, 23, 23);
		levelSevenButtonUp.flip(false, true);
		levelSevenButtonDown.flip(false, true);
		
		levelEightButtonUp = new TextureRegion(texture, 55, 283, 25, 25);
		levelEightButtonDown = new TextureRegion(texture, 81, 283, 25, 25);
		levelEightButtonUp.flip(false, true);
		levelEightButtonDown.flip(false, true);
		
		levelNineButtonUp = new TextureRegion(texture, 110, 205, 23, 23);
		levelNineButtonDown = new TextureRegion(texture, 136, 205, 23, 23);
		levelNineButtonUp.flip(false, true);
		levelNineButtonDown.flip(false, true);
		
		levelTenButtonUp = new TextureRegion(texture, 110, 231, 23, 23);
		levelTenButtonDown = new TextureRegion(texture, 136, 231, 23, 23);
		levelTenButtonUp.flip(false, true);
		levelTenButtonDown.flip(false, true);
		
		levelElevenButtonUp = new TextureRegion(texture, 110, 257, 23, 23);
		levelElevenButtonDown = new TextureRegion(texture, 136, 257, 23, 23);
		levelElevenButtonUp.flip(false, true);
		levelElevenButtonDown.flip(false, true);
		
		levelTwelveButtonUp = new TextureRegion(texture, 110, 283, 25, 25);
		levelTwelveButtonDown = new TextureRegion(texture, 136, 283, 25, 25);
		levelTwelveButtonUp.flip(false, true);
		levelTwelveButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 0, 81, 34, 7);
		ready.flip(false, true);
		
		gameOver = new TextureRegion(texture, 0, 89, 46, 7);
		gameOver.flip(false, true);
		
		highScore = new TextureRegion(texture, 0, 97, 48, 7);
		highScore.flip(false, true);

		scoreboard = new TextureRegion(texture, 52, 81, 97, 37);
		scoreboard.flip(false, true);
		
		complete = new TextureRegion(texture, 150, 55, 98, 22);
		complete.flip(false, true);
		
		story = new TextureRegion(texture, 150, 78, 56, 22);
		story.flip(false, true);
		
		options = new TextureRegion(texture, 150, 101, 72, 22);
		options.flip(false, true);

		star = new TextureRegion(texture, 0, 105, 10, 10);
		star.flip(false, true);
		noStar = new TextureRegion(texture, 13, 105, 10, 10);
		noStar.flip(false, true);

		ctLogo = new TextureRegion(texture, 0, 55, 135, 24);
		ctLogo.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);

		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		plungerUpHead = new TextureRegion(texture, 0, 344, 29, 26);
		plungerUpHead.flip(false, true);
		
		plungerUpStand = new TextureRegion(texture, 132, 336, 7, 65);
		plungerUpStand.flip(false, true);
		
		plungerSideHead = new TextureRegion(texture, 0, 313, 25, 29);
		plungerSideHead.flip(false, true);
		
		plungerSideStand = new TextureRegion(texture, 65, 386, 66, 7);
		plungerSideStand.flip(false, true);
		
		plungerSquish = new TextureRegion(texture, 0, 372, 29, 26);
		plungerSquish.flip(false, true);
		
		plungerStretch = new TextureRegion(texture, 0, 400, 29, 28);
		plungerStretch.flip(false, true);
		
		plungerDead = new TextureRegion(texture, 65, 401, 64, 29);
		plungerDead.flip(false, true);
		
		tpUp = new TextureRegion(texture, 192, 0, 24, 14);
		tpDown = new TextureRegion(tpUp);
		tpDown.flip(false, true);

		stand = new TextureRegion(texture, 136, 16, 22, 3);
		stand.flip(false, true);
		
		fart = new TextureRegion(texture, 136, 19, 16, 16);
		fart.flip(false, true);
		
		releaseGasUp = new TextureRegion(texture, 2, 430, 58, 18);
		releaseGasDown = new TextureRegion(texture, 2, 449, 58, 18);
		releaseGasUp.flip(false, true);
		releaseGasDown.flip(false, true);
		
		TextureRegion[] gas = {releaseGasUp, releaseGasDown};
		releaseGasAnimation = new Animation(0.06f, gas);
		releaseGasAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		castle = new TextureRegion(texture, 0, 600, 144, 99);
		castle.flip(false, true);
		
		flag = new TextureRegion(texture, 146, 600, 11, 11);
		flag.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/death.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/fart.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		win = Gdx.audio.newSound(Gdx.files.internal("data/success.wav"));
		kill = Gdx.audio.newSound(Gdx.files.internal("data/explode.wav"));
		plunger = Gdx.audio.newSound(Gdx.files.internal("data/plunger.wav"));
		breath = Gdx.audio.newSound(Gdx.files.internal("data/breath.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);
		
		whiteFontBig = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFontBig.setScale(.25f, -.25f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("CrappyTurd");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		if (!prefs.contains("limit")) {
			prefs.putInteger("limit", 0);
		}
		if (!prefs.contains("currentLevel")) {
			prefs.putInteger("currentLevel", 0);
		}
		if (!prefs.contains("isBoss")) {
			prefs.putBoolean("isBoss", false);
		}
		if (!prefs.contains("finIntro1")) {
			prefs.putBoolean("finIntro1", false);
		}
		if (!prefs.contains("finKill1")) {
			prefs.putBoolean("finKill1", false);
		}
		if (!prefs.contains("finKill2")) {
			prefs.putBoolean("finKill2", false);
		}
		if (!prefs.contains("finKill3")) {
			prefs.putBoolean("finKill3", false);
		}
		if (!prefs.contains("isGassed")) {
			prefs.putBoolean("isGassed", false);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static void setLimit(int level) {
		prefs.putInteger("limit", level);
		prefs.flush();
	}
	
	public static void setCurrentLevel(int level) {
		prefs.putInteger("currentLevel", level);
		prefs.flush();
	}
	
	public static void setFinIntro1(boolean b) {
		prefs.putBoolean("finIntro1", b);
		prefs.flush();
	}
	public static void setFinKill1(boolean b) {
		prefs.putBoolean("finKill1", b);
		prefs.flush();
	}
	public static void setFinKill2(boolean b) {
		prefs.putBoolean("finKill2", b);
		prefs.flush();
	}
	public static void setFinKill3(boolean b) {
		prefs.putBoolean("finKill3", b);
		prefs.flush();
	}
	public static void setFinKill(boolean b) {
		prefs.putBoolean("finKill1", b);
		prefs.putBoolean("finKill2", b);
		prefs.putBoolean("finKill3", b);
		prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static int getLimit() {
		return prefs.getInteger("limit");
	}
	
	public static int getCurrentLevel(){
		return prefs.getInteger("currentLevel");
	}
	
	public static boolean isFinIntro1() {
		return prefs.getBoolean("finIntro1");
	}
	public static boolean isFinKill1() {
		return prefs.getBoolean("finKill1");
	}
	public static boolean isFinKill2() {
		return prefs.getBoolean("finKill2");
	}
	public static boolean isFinKill3() {
		return prefs.getBoolean("finKill3");
	}
	
	public static boolean isFinKill() {
		return (prefs.getBoolean("finKill1") || prefs.getBoolean("finKill2") 
				|| prefs.getBoolean("finKill3"));
	}

	public static void dispose() {
		// We must dispose of the assets when we are finished.
		texture.dispose();
		dead.dispose();
		flap.dispose();
		coin.dispose();
		plunger.dispose();
		breath.dispose();
		kill.dispose();
		win.dispose();
		font.dispose();
		shadow.dispose();
	}

}