package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    private BitmapFont white,black; //done
    private TweenManager tweenManager;

    private Music music;

    public MainMenuScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        background = new Texture("img/moonbg.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/Melody.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),new TextureAtlas("ui/atlas.pack"));

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //creating fonts
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        //creating heading
        Label heading = new Label(Elective4.TITLE, skin);
        heading.setFontScale(2);

        //creating button
        /*TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;*/

        TextButton buttonPlay = new TextButton("Play Now", skin);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Gdx.net.openURI("https://maypasokba.com");
                game.setScreen(new LevelsScreen(game));
            }
        });
        buttonPlay.pad(15);

        TextButton buttonSettings = new TextButton("Settings", skin);
        buttonSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });
        buttonSettings.pad(15);

        TextButton buttonExit = new TextButton("Exit",skin);
        buttonExit.pad(15);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //putting stuff together
        table.add(heading).spaceBottom(20);
        table.row();
        table.add(buttonPlay).spaceBottom(10);
        table.row();
        table.add(buttonSettings).spaceBottom(15);
        table.row();
        table.add(buttonExit);
        //table.debug(); //enables all the debug line
        stage.addActor(table);

        // creating animations
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());


        // heading color animation
        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0.259f, 0.05118f, 0.035433f))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0.157480f, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0.031496f, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0.14173228f, 0.448818f))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0.503937f, 0.448818f))
                .end().repeat(Tween.INFINITY, -1).start(tweenManager);

        //headings and button fade in
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay,ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit,ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading,ActorAccessor.ALPHA,0.25f).target(0))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.15f).target(1))
                .push(Tween.to(buttonExit,ActorAccessor.ALPHA,0.25f).target(1))
                .end().start(tweenManager);

        //table fade in
        Tween.from(heading, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
        Tween.from(table, ActorAccessor.Y, 0.5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

        //tweenManager.update(Gdx.graphics.getDeltaTime());
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background,0 ,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        white.dispose();
        black.dispose();
        background.dispose();
    }
}
