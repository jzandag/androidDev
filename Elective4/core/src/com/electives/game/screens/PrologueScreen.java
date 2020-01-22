package com.electives.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.electives.game.Elective4;
import com.electives.game.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Zidrex Andag on 1/9/2020.
 */
public class PrologueScreen implements Screen {

    private Elective4 game;
    private Texture[] plot;
    private Texture background;

    private Sprite[] splash;

    private Stage stage; //done
    private Skin skin; //done
    private Table table; //done
    private TweenManager tweenManager;

    private Sound btnClick;

    public PrologueScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        //TODO array of Textures
        plot = new Texture[]{new Texture("img/production.png"),
                new Texture("img/collab.png")};
        btnClick = game.assets.get("audio/gunShot.wav", Sound.class);
        splash = new Sprite[]{new Sprite(plot[0]),
                new Sprite(plot[1])
                    };

        background = new Texture("img/template.png");
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("ui/style.json"),new TextureAtlas("ui/tr.pack"));
        table = new Table(skin);

        table.setFillParent(true);

        Label buttonSkip= new Label("Skip...", skin,"skip");
        buttonSkip.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Gdx.net.openURI("https://maypasokba.com");
                game.setScreen(new MainMenuScreen(game));
            }
        });
        //buttonSkip.pad(15);
        //buttonSkip.padTop(10);
        int i = 0;
        for(Texture t : plot) {
            //splash[i].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            splash[i].setPosition(Gdx.graphics.getWidth()/2-splash[i].getTexture().getWidth() / 2, Gdx.graphics.getHeight()/2 - splash[i].getTexture().getHeight() / 2);
            splash[i].setAlpha(0);
            splash[i].scale(0.2f);
            i++;
        }
        //game.music.stop();

        //start of plot animation
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Tween.set(splash[0], SpriteAccessor.ALPHA).target(0).start(tweenManager);//0 alpha = transparent
        Tween.set(splash[1], SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splash[0], SpriteAccessor.ALPHA, 1f).target(1).start(tweenManager);
        Tween.to(splash[0], SpriteAccessor.ALPHA, 1f).target(0).delay(1).start(tweenManager);
        Tween.to(splash[1], SpriteAccessor.ALPHA, 2f).target(1).delay(2).start(tweenManager);
        Tween.to(splash[1], SpriteAccessor.ALPHA, 1f).target(0).delay(1).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
            }
        }).delay(3).start(tweenManager);

        //table.add(buttonSkip);
        stage.addActor(table);
        // heading color animation
        /*Timeline.createSequence()
                .beginSequence()
                .push(Tween.set(splash[0], SpriteAccessor.ALPHA).target(0)) //0 alpha = transparent
                .push(Tween.set(splash[1], SpriteAccessor.ALPHA).target(0))
                .push(Tween.to(splash[0], SpriteAccessor.ALPHA, 2f).target(1))
                .push(Tween.to(splash[0], SpriteAccessor.ALPHA, 1f).target(0)).delay(1)
                .push(Tween.to(splash[1], SpriteAccessor.ALPHA, 2f).target(1)).delay(2)
                .push(Tween.to(splash[1], SpriteAccessor.ALPHA, 1f).target(0)).delay(1)
                .end().setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
                    }
        }).start(tweenManager);*/
        tweenManager.update(Float.MIN_VALUE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            game.batch.draw(background,0,0,stage.getWidth(),stage.getHeight());
            splash[0].draw(game.batch);
        splash[1].draw(game.batch);
        game.batch.end();

        stage.act(delta);
        stage.draw();
        tweenManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        //15x22
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
        skin.dispose();
        background.dispose();
        for(Texture t: plot)
            t.dispose();
        for(Sprite s: splash)
            s.getTexture().dispose();
    }
}
