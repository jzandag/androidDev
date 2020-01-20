package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.electives.game.Elective4;
import com.electives.game.tween.SpriteAccessor;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Zidrex Andag on 1/19/2020.
 */
public class StoryScreen implements Screen {

    private Elective4 game;
    private OrthographicCamera camera;

    private TweenManager tweenManager;
    private Sprite panel1;
    private Sprite panel2;


    public StoryScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        panel1 = new Sprite(new Texture("img/moonbg.jpg"));
        panel2 = new Sprite(new Texture("img/lonely.jpg"));
        camera.viewportWidth = Elective4.GAME_WIDTH;
        camera.viewportHeight = Elective4.GAME_HEIGHT;
        camera.update();
        tweenManager = new TweenManager();

        Sprite[] sprites = new Sprite[]{panel1,panel2};

        for (Sprite sp : sprites){
            sp.setSize(sp.getWidth(),sp.getHeight());
            sp.setOrigin(0,0);
            sp.setPosition(0,0);
        }

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Timeline.createSequence()
                .push(Tween.set(panel1, SpriteAccessor.ALPHA).target(0))
                .push(Tween.set(panel2, SpriteAccessor.ALPHA).target(0))
                .beginSequence()
                .push(Tween.to(panel1,SpriteAccessor.ALPHA,2).target(1))
                .push(Tween.to(panel1,SpriteAccessor.ALPHA,0.5f).target(0))
                .push(Tween.to(panel2,SpriteAccessor.ALPHA,2).target(1))
                .push(Tween.to(panel2,SpriteAccessor.ALPHA,0.5f).target(0))
                .end().start(tweenManager);
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

    }
}
