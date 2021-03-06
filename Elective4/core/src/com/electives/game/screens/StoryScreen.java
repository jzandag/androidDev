package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.electives.game.Elective4;
import com.electives.game.levels.Level01;
import com.electives.game.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Zidrex Andag on 1/19/2020.
 */
public class StoryScreen implements Screen {
    private static final float PANEL_DURATION = 2;

    private Elective4 game;
    private OrthographicCamera camera;

    private TweenManager tweenManager;
    private Sprite panel1;
    private Sprite panel2;
    private Sprite panel3;
    private Sprite panel4;
    private Sprite panel5;
    private Sprite panel6;
    private Sprite panel7;
    private Sprite panel8;


    public StoryScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();

        panel1 = new Sprite(new Texture("img/panel1.jpg"));
        panel2 = new Sprite(new Texture("img/panel2.jpg"));
        panel3 = new Sprite(new Texture("img/panel3.jpg"));
        panel4 = new Sprite(new Texture("img/panel4.jpg"));
        panel5 = new Sprite(new Texture("img/panel5.jpg"));
        panel6 = new Sprite(new Texture("img/panel6.jpg"));
        panel7 = new Sprite(new Texture("img/panel7.jpg"));
        panel8 = new Sprite(new Texture("img/panel8.png"));

        camera.viewportWidth = Elective4.GAME_WIDTH;
        camera.viewportHeight = Elective4.GAME_HEIGHT;
        camera.update();
        tweenManager = new TweenManager();

        Sprite[] sprites = new Sprite[]{panel1,panel2, panel3, panel4, panel5, panel6, panel7, panel8};

        for (Sprite sp : sprites){
            sp.setSize(sp.getWidth(),sp.getHeight());
            sp.setOrigin(0,0);
            sp.setPosition(0,0);
        }

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Timeline.createSequence()
                .push(Tween.set(panel1, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel2, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel3, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel4, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel5, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel6, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel7, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel8, SpriteAccessor.ALPHA).target(0))
                .beginSequence()
                .push(Tween.to(panel1, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel1, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel2, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel2, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel3, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel3, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel4, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel4, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel5, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel5, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel6, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel6, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel7, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel7, SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel8, SpriteAccessor.ALPHA,PANEL_DURATION).target(1))
                .push(Tween.to(panel8, SpriteAccessor.ALPHA,0.5f).target(0))
                .end().setCallback(new TweenCallback() {
                    @Override
                    public void onEvent(int type, BaseTween<?> source) {
                        game.setScreen(new Level01(game));
                    }
                }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        tweenManager.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        panel1.draw(game.batch);
        panel2.draw(game.batch);
        panel3.draw(game.batch);
        panel4.draw(game.batch);
        panel5.draw(game.batch);
        panel6.draw(game.batch);
        panel7.draw(game.batch);
        panel8.draw(game.batch);
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

    }

    @Override
    public void dispose() {
        panel1.getTexture().dispose();
        panel2.getTexture().dispose();
        panel3.getTexture().dispose();
        panel4.getTexture().dispose();
        panel5.getTexture().dispose();
        panel6.getTexture().dispose();
        panel7.getTexture().dispose();
        panel8.getTexture().dispose();


    }
}
