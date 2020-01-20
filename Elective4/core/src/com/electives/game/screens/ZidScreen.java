package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.electives.game.Elective4;

/**
 * Created by Zidrex Andag on 12/29/2019.
 */
public class ZidScreen implements Screen {

    private Elective4 game;

    private Texture img;

    public ZidScreen(Elective4 game){
        this.game = game;

    }

    @Override
    public void show() {

        img = new Texture(Gdx.files.internal("zid.jpg"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(img,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {
        game.dispose();
        img.dispose();
    }
}
