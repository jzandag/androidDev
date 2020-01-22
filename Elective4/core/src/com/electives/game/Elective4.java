package com.electives.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.electives.game.screens.LevelsScreen;
import com.electives.game.screens.PrologueScreen;

public class Elective4 extends Game {

    public static final String TITLE = "Escape Room";
	public static final String VERSION = "v0.0.0.0reallyEarly";
    public static final int GAME_WIDTH = 480;
    public static final int GAME_HEIGHT = 700;
	public static final float PPM = 100;

	public static final short NOTHING_BIT = 0;
	public static final short DEFAULT_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short WALL_BIT = 4;
	public static final short KEY_BIT = 8;
	public static final short KILLER_BIT = 16;
	public static final short WALLM_BIT = 32;
	public static final short XWALL_BIT = 64;

	public Music music;

	public SpriteBatch batch;
	public static AssetManager assets;

	/*  WARNING: Using AssetManager in a static way can cause issues, espescially on Android
	*	Instead you may want to pass around AssetManager to those classes that need it .
	*	I will use it in the static context to save time for now.
	* */

	@Override
	public void create () {
		batch = new SpriteBatch();

		//TODO
		System.out.println("[Current level] "+Gdx.app.getPreferences(Elective4.TITLE).getInteger("progress"));
		int state = Gdx.app.getPreferences(Elective4.TITLE).getInteger("progress");
		/*if(state == 0){
            Gdx.app.getPreferences(Elective4.TITLE).putInteger("progress", 1);
			Gdx.app.getPreferences(Elective4.TITLE).flush();
		    setScreen(new PrologueScreen(this));
        }*/

		assets = new AssetManager();
		assets.load("audio/illuminati.wav", Music.class);
		assets.load("audio/die.wav", Sound.class);
		assets.load("audio/Cheering.wav", Sound.class);
		assets.load("audio/gunShot.wav", Sound.class);
		assets.load("audio/hitwall.wav",Sound.class);
		assets.load("audio/wow.mp3",Sound.class);
		assets.finishLoading();

		music = assets.get("audio/illuminati.wav", Music.class);
		music.setLooping(true);
		music.setVolume(0.4f);
		music.play();
		assets.get("audio/gunShot.wav", Sound.class).play();
		setScreen(new LevelsScreen(this));


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
