package com.electives.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.electives.game.Elective4;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Zidrex Andag on 12/29/2019.
 */
public class LevelsScreen implements Screen {

    private Elective4 game;

    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private Skin skin;
    private ScrollPane scrollPane;
    private TextButton playBtn, backBtn;
    public LevelsScreen(Elective4 game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        table.setFillParent(true);
        table.debug();
        List list = new List(skin);
        list.setItems(new String[]{"Level 1","Level 2","Level 3","Level 4","Level 5",
                "Level 6","Level 7","Level 8","Level 9","Level 10","Level 12",
                "Level 13","Level 14","Level 15","Level 16","Level 17","Level 18"});
        scrollPane = new ScrollPane(list,skin);

        playBtn = new TextButton("PLAY", skin);
        playBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
        playBtn.pad(15);

        backBtn = new TextButton("BACK", skin,"small");
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(sequence(moveTo(0,stage.getHeight(),0.5f),run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainMenuScreen(game));
                    }
                })));
            }
        });
        backBtn.pad(10);

        //putting stuff together
        table.add().width(table.getWidth() /3);
        table.add("SELECT LEVEL").width(table.getWidth()/3);
        table.add().width(table.getWidth() /3).row();
        table.add(scrollPane).left().expandY();
        table.add(playBtn);
        table.add(backBtn).bottom().right();


        stage.addActor(table);

        stage.addAction(sequence(moveTo(0,stage.getHeight()), moveTo(0,0,0.5f)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        // that's what you probably want in case of a UI
        stage.getViewport().update(width,height,true);

        table.invalidateHierarchy();
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
        stage.dispose();
        atlas.dispose();
        skin.dispose();
    }
}
