package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;
import com.electives.game.InputController;

/**
 * Created by Zidrex Andag on 12/30/2019.
 */
public class PlayScreen implements Screen {
    public static final float PXM = 32;

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITY_ITERATIONS = 8;
    private static final int POSITION_ITERATIONS = 3;

    private Elective4 game;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera cam;



    public PlayScreen(Elective4 game) {
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f),true);
        debugRenderer = new Box2DDebugRenderer();

        cam = new OrthographicCamera();

        //escape key
        Gdx.input.setInputProcessor(new InputController(){
            @Override
            public boolean keyDown(int keycode) {
                if(keycode == Input.Keys.ESCAPE)
                    game.setScreen(new LevelsScreen(game));
                return true;
            }
        });

        //BALL
        //Body def
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,1);

        //ball shape
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        //fixture def -- physicacl properties of a body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape; //
        fixtureDef.density = 2.5f;//kilogram - mass per sq meter
        fixtureDef.friction = .25f;//how rubbiness the body 1- cannot slide over something, 0 - slides like ice
        fixtureDef.restitution = .75f; //bounciness 1 - 100% reflected, 0 - no bounciness;

        world.createBody(bodyDef).createFixture(fixtureDef);

        shape.dispose();

        //GROUND
        //body def
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);

        //shape
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{
                new Vector2(-500,0), new Vector2(500,0)
        });


        //fixture
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = groundShape;

        world.createBody(bodyDef).createFixture(fixtureDef);

        groundShape.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, cam.combined);

        world.step(TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth =width / 25;
        cam.viewportHeight = height / 25;
        cam.update();
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
        world.dispose();
        debugRenderer.dispose();
    }
}
