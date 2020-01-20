package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.electives.game.Elective4;
import com.electives.game.scenes.Hud;
import com.electives.game.scenes.LevelClear;
import com.electives.game.sprites.MovingObjects;
import com.electives.game.sprites.Player;
import com.electives.game.tools.B2WorldCreator;
import com.electives.game.tools.WorldContactListener;

/**
 * Created by Zidrex Andag on 1/19/2020.
 */
public class ZidPlayScreens {
    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;

    protected Elective4 game;

    protected OrthographicCamera cam;
    protected Viewport viewport;

    //tiledMap variables
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;
    protected Hud hud;

    //box2d variables
    protected World world;
    protected Box2DDebugRenderer b2dr;
    protected Player player;
    protected B2WorldCreator creator;

    protected LevelClear levelClear;
    protected Stage stage;
    protected TextureAtlas atlas;

    public ZidPlayScreens(Elective4 game){

        this.game = game;

        cam = new OrthographicCamera();
        stage = new Stage();
        viewport = new FitViewport(Elective4.GAME_WIDTH / Elective4.PPM, Elective4.GAME_HEIGHT / Elective4.PPM,cam);
        atlas = new TextureAtlas("ui/sprite.pack");
        TextureAtlas atlas2 = new TextureAtlas("ui/tr.pack");
        Skin skin = new Skin(Gdx.files.internal("ui/levelSkin.json"),atlas2);



        cam.position.set(viewport.getWorldWidth() / 2 ,viewport.getWorldHeight() / 2,0);


        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();




        world.setContactListener(new WorldContactListener());
        skin.dispose();

    }

    public void update(float dt){
        world.step(TIMESTEP,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
        player.update(dt);

        //attach our game cam to player.x coordinate
        //cam.position.y = player.b2body.getPosition().y;
        for(MovingObjects o: creator.getMovingWalls()){
            o.update(dt);
            o.b2body.setActive(true);/*
            System.out.println(o.b2body.getLinearVelocity().x);*/
        }
        hud.update(dt);
        cam.update();
        renderer.setView(cam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public void render(float delta){
        //separates our update logic from render method
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //reders the game map
        renderer.render();

        //box2d debugrederer debuglines
        //b2dr.render(world,cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for(MovingObjects o : creator.getMovingWalls()){
            o.draw(game.batch);
        }
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if(player.isDead() || hud.isTimeUp()) {
            hud.timerStop = true;
            if (player.stateTimer >= 2) {
                Elective4.assets.get("audio/illuminati.wav", Music.class).play();
                System.out.println("Time to move to level screen");
                game.setScreen(new LevelsScreen(game));
            }
        }


    }

    public void dispose(){
        stage.dispose();

        game.dispose();
        map.dispose();
        renderer.dispose();
        player.dispose();

        //world.dispose();
        System.out.println("ZidPlay Screen disposed");
        b2dr.dispose();
        hud.dispose();
    }

    public void resize(int width, int height){
        /*cam.viewportWidth = width / 25;
        cam.viewportHeight = height / 25;
        cam.update();*/
        viewport.update(width,height);
    }

    public World getWorld() {
        return world;
    }
    public TiledMap getMap() {
        return map;
    }




}
