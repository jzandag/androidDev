package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.electives.game.Elective4;
import com.electives.game.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Zidrex Andag on 12/28/2019.
 */
public class SplashScreen implements Screen {

    private Elective4 game;
    private Texture splashTexture;

    private Sprite splash;
    private TweenManager tweenManager;

    public SplashScreen(Elective4 game){
        this.game = game;
    }
    @Override
    public void show() {
        //apply preferences
        Gdx.graphics.setVSync(SettingsScreen.vSync());

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        splashTexture = new Texture("img/sp.jpg");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA,2f).target(1).repeatYoyo(1,0.5f).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MainMenuScreen(game));
            }
        }).start(tweenManager);
        tweenManager.update(Float.MIN_VALUE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        splash.draw(game.batch);
        game.batch.end();

        tweenManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        splash.setSize(width,height);

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
        splash.getTexture().dispose();
        splashTexture.dispose();
    }
}
