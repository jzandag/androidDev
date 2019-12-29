package com.electives.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.electives.game.screens.SplashScreen;

public class Elective4 extends Game {

	public static final String TITLE = "Elective 4";
	public static final String VERSION = "v0.0.0.0reallyEarly";
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 700;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
