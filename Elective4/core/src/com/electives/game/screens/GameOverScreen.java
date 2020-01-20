package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.electives.game.Elective4;

/**
 * Created by Zidrex Andag on 1/14/2020.
 */
public class GameOverScreen implements Screen {

    private Elective4 game;

    private Viewport viewport;
    private Stage stage;

    public GameOverScreen(Elective4 game){
        this.game = game;
        Skin skin = new Skin(Gdx.files.internal("levelSkin.json"));
        viewport = new FitViewport(Elective4.GAME_WIDTH, Elective4.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        Table table = new Table(skin);
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", skin, "playagain");
        Label playAgainLabel = new Label("Click to Play Again", skin, "playagain");

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().spaceBottom(10);


        stage.addActor(table);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        if(Gdx.input.justTouched())
            game.setScreen(new LevelsScreen(game));
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
        stage.dispose();
        game.dispose();
    }
}
