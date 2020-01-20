package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.electives.game.Elective4;
import com.electives.game.tween.ActorAccessor;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Zidrex Andag on 12/28/2019.
 */
public class MainMenuScreen implements Screen {

    private Elective4 game;

    private Texture background;

    private Stage stage; //done
    private Skin skin; //done
    private Table table; //done
    private TweenManager tweenManager;
    private AssetManager assets;

    public MainMenuScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        background = new Texture("img/keysbg.jpg");

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/style.json"),new TextureAtlas("ui/tr.pack"));

        table = new Table(skin);
        table.setBounds(0,0,stage.getWidth(), stage.getHeight());
        table.setFillParent(true);

        //creating heading
        Label heading = new Label("Escape\nRoom", skin);
        heading.setFontScale(1.9f);
        heading.setWidth(Gdx.graphics.getWidth() - 40);
        TextButton buttonPlay = new TextButton("Play now", skin,"playBtn");
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.assets.get("audio/gunShot.wav", Sound.class).play();
                game.setScreen(new LevelsScreen(game));
            }
        });
        buttonPlay.getLabel().setFontScale(2);
        buttonPlay.pad(70);
        buttonPlay.padTop(65);

        ImageButton buttonSettings = new ImageButton(skin,"settingsBtn");
        buttonSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Settings btn");
                game.setScreen(new SettingsScreen(game));
            }
        });
        buttonSettings.setTransform(true);

        //putting stuff together
        table.debug();
        //table.add(buttonSettings).width(40).height(40).right().top().colspan(2).padTop(40).padRight(20).spaceBottom(80);
        table.add(buttonSettings).width(80).height(80).right().top().colspan(2).padTop(40).padRight(20).spaceBottom(80);
        table.row();
        table.add(heading).expandX().colspan(2).spaceBottom(100);
        table.row();
        table.add(buttonPlay).top().colspan(2).expandY().spaceBottom(20);
        table.row();

        stage.addActor(table);

        // creating animations
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());


        // heading color animation
        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAccessor.RGB, 2.5f).target(0.721566f, 0.0588235f, 0.03921569f))
                .push(Tween.to(heading, ActorAccessor.RGB, 2.5f).target(0.259f, 0.05118f, 0.035433f))
                //.push(Tween.to(heading, ActorAccessor.RGB, 2.5f).target(1f, 1, 1))
                .end().start(tweenManager);//.repeatYoyo(Tween.INFINITY, -1)
        //headings and button fade in
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay,ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading,ActorAccessor.ALPHA,0.25f).target(0))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.15f).target(1))
                .end().start(tweenManager);

        //table fade in
        //Tween.from(heading, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
        Tween.from(table, ActorAccessor.Y, 0.5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

        //tweenManager.update(Gdx.graphics.getDeltaTime());
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background,0 ,0,stage.getWidth(), stage.getHeight());
        game.batch.end();

        stage.act(delta);
        stage.draw();

        tweenManager.update(delta);

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
        skin.dispose();
        background.dispose();
    }
}
