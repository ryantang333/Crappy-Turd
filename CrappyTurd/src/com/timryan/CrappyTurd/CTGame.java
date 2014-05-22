package com.timryan.CrappyTurd;

import com.badlogic.gdx.Game;
import com.timryan.Screens.LogoScreen;
import com.timryan.CTHelpers.AssetLoader;

public class CTGame extends Game {
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new LogoScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}