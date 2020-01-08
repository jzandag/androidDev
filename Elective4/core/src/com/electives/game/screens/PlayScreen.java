package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.electives.game.Elective4;
import com.electives.game.scenes.Hud;
import com.electives.game.sprites.Assassin;
import com.electives.game.tools.B2WorldCreator;

/**
 * Created by Zidrex Andag on 12/30/2019.
 */
public class PlayScreen implements Screen {

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;

    private Elective4 game;

    private OrthographicCamera cam;
    private Viewport viewport;

    //tiledMap variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Hud hud;

    //box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private Assassin assassin;

    private TextureAtlas atlas;


    public PlayScreen(Elective4 game) {
        this.game = game;
    }

    @Override
    public void show() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Elective4.GAME_WIDTH / Elective4.PPM, Elective4.GAME_HEIGHT / Elective4.PPM,cam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map/level25.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Elective4.PPM);

        cam.position.set(viewport.getWorldWidth() / 2 ,viewport.getWorldHeight() / 2,0);
        //cam.setToOrtho(false, viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);

        hud = new Hud(game.batch);

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        assassin = new Assassin(world,this);
        atlas = new TextureAtlas("assassin-sprite.pack");
    }

    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            assassin.b2body.applyLinearImpulse(new Vector2(0,4), assassin.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && assassin.b2body.getLinearVelocity().x <= 2)
            assassin.b2body.applyLinearImpulse(new Vector2(0.1f, 0), assassin.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && assassin.b2body.getLinearVelocity().x >= -2)
            assassin.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), assassin.b2body.getWorldCenter(), true);

    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);

        world.step(TIMESTEP,VELOCITY_ITERATIONS,POSITION_ITERATIONS);

        assassin.update(dt);

        //attach our game cam to player.x coordinate
        cam.position.y = assassin.b2body.getPosition().y;

        cam.update();
        renderer.setView(cam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void render(float delta) {
        //separates our update logic from render method
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //reders the game map
        renderer.render();

        //box2d debugrederer debuglines
        b2dr.render(world,cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        assassin.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        /*cam.viewportWidth = width / 25;
        cam.viewportHeight = height / 25;
        cam.update();*/
        viewport.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
