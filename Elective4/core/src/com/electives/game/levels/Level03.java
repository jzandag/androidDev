package com.electives.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.electives.game.Elective4;
import com.electives.game.scenes.Hud;
import com.electives.game.scenes.LevelClear;
import com.electives.game.screens.ZidPlayScreens;
import com.electives.game.sprites.Player;
import com.electives.game.tools.B2WorldCreator;

/**
 * Created by Zidrex Andag on 1/16/2020.
 */
public class Level03 extends ZidPlayScreens implements Screen {
    private static final int LEVEL_NUMBER = 3;

    public Level03(Elective4 game) {
        super(game);
        // Only changes needed
        map = new TmxMapLoader().load("map/Level 03 Underground Room.tmx");
        player = new Player(world,this, 3,11);
    }

    @Override
    public void show() {
        hud = new Hud(game.batch,LEVEL_NUMBER);
        levelClear = new LevelClear(game,LEVEL_NUMBER);
        creator = new B2WorldCreator(world,map);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Elective4.PPM);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(player.isDead() || hud.isTimeUp()) {
            hud.timerStop = true;
            if (player.stateTimer >= 2) {
                Elective4.assets.get("audio/illuminati.wav", Music.class).play();
                System.out.println("Time to move to level screen");
                game.setScreen(new Level03(game));
            }
        }

        if(player.isWin()){
            hud.timerStop = true;
            Elective4.assets.get("audio/illuminati.wav", Music.class).stop();
            if (player.stateTimer >= 2) {
                checkGameProgress();
                Elective4.assets.get("audio/illuminati.wav", Music.class).play();
                stage.addActor(levelClear.window);
                stage.act();
                stage.draw();
                Gdx.input.setInputProcessor(stage);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    private void checkGameProgress(){
        int progressLevel = Gdx.app.getPreferences(Elective4.TITLE).getInteger("progress");
        if(LEVEL_NUMBER > progressLevel )
            Gdx.app.getPreferences(Elective4.TITLE).putInteger("progress",++progressLevel);
        //System.out.println("progress" + progressLevel);
        Gdx.app.getPreferences(Elective4.TITLE).flush();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
