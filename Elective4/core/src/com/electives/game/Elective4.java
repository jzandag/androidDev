package com.electives.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.electives.game.screens.SplashScreen;

public class Elective4 extends Game {

	public static final String TITLE = "AssasinXn";
	public static final String VERSION = "v0.0.0.0reallyEarly";
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 700;
	public static final float PPM = 100;

	private Music music;

	public SpriteBatch batch;
	public AssetManager assets;

	/*  WARNING: Using AssetManager in a static way can cause issues, espescially on Android
	*	Instead you may want to pass around AssetManager to those classes that need it .
	*	I will use it in the static context to save time for now.
	* */

	@Override
	public void create () {
		batch = new SpriteBatch();

		assets = new AssetManager();
		assets.load("audio/illuminati.wav", Music.class);
		assets.load("gfx/gunShot.wav", Sound.class);
		assets.finishLoading();

		music = assets.get("audio/illuminati.wav", Music.class);
		music.setLooping(true);
		music.setVolume(0.4f);
		music.play();
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
		//super.dispose();
		//batch.dispose();
		//music.dispose();
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
