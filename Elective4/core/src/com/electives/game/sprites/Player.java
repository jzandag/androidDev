package com.electives.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.electives.game.Elective4;
import com.electives.game.screens.PlayScreen;
import com.electives.game.screens.ZidPlayScreens;
import com.electives.game.tools.InputController;
import com.electives.game.tools.SimpleDirectionGestureDetector;

/**
 * Created by Zidrex Andag on 1/8/2020.
 */
public class Player extends Sprite {
    public enum State {STANDING,STANDINGLEFT,RUNNING, RUNNINGLEFT, KILLING, DEAD, WIN}

    public enum Direction {UP, DOWN, LEFT, RIGHT, NULL}

    public State currentState;
    public State previousState;
    public Direction currentDirection;

    public World world;
    public Body b2body;
    private TextureRegion playerStand;
    private TextureRegion playerStandLeft;
    private TextureRegion playerDead;
    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerLeft;
    public float stateTimer;
    private boolean isUp;
    private boolean isLeft;
    private boolean isDead;
    private boolean isWin;

    public Player(World world, ZidPlayScreens screen, int positionX, int positionY) {
        super(screen.getAtlas().findRegion("player1"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        isLeft = false;

        Array<TextureRegion> frames = new Array<>();
        frames.add(screen.getAtlas().findRegion("player2"));
        frames.add(screen.getAtlas().findRegion("player3"));
        playerRun = new Animation<>(0.1f, frames);
        frames.clear();
        frames.add(screen.getAtlas().findRegion("player2Left"));
        frames.add(screen.getAtlas().findRegion("player3Left"));
        playerLeft = new Animation<>(0.1f,frames);

        definePlayer(positionX, positionY);
        playerStand = screen.getAtlas().findRegion("player1");
        playerDead = screen.getAtlas().findRegion("player1");
        playerStandLeft = screen.getAtlas().findRegion("player1Left");
        setBounds(0, 0, 32 / Elective4.PPM, 32 / Elective4.PPM);
        setRegion(playerStand);
        isWin = false;
    }

    public void definePlayer(int x, int y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set((32 * x + 15) / Elective4.PPM, (32 * y + 15) / Elective4.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(15 / Elective4.PPM);
        fixtureDef.filter.categoryBits = Elective4.PLAYER_BIT;
        fixtureDef.filter.maskBits = Elective4.DEFAULT_BIT | Elective4.WALL_BIT | Elective4.KILLER_BIT
                | Elective4.KEY_BIT | Elective4.WALLM_BIT | Elective4.XWALL_BIT;

        fixtureDef.shape = circleShape;
        b2body.createFixture(fixtureDef).setUserData(this);

        PolygonShape surround = new PolygonShape();
        surround.setAsBox(15 / Elective4.PPM, 15 / Elective4.PPM);
        fixtureDef.shape = surround;
        fixtureDef.isSensor = true;

        b2body.createFixture(fixtureDef).setUserData(this);

        surround.dispose();
        circleShape.dispose();
        currentState = State.STANDING;
        currentDirection = Direction.NULL;
        SimpleDirectionGestureDetector sg = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onLeft() {
                currentDirection = Direction.LEFT;
            }

            @Override

            public void onRight() {
                currentDirection = Direction.RIGHT;
            }

            @Override
            public void onUp() {
                currentDirection = Direction.UP;
            }

            @Override
            public void onDown() {
                currentDirection = Direction.DOWN;
            }

        });
        Gdx.input.setInputProcessor(sg);

    }

    public void handleInput(float dt) {
        //TODO

        if (currentState != State.DEAD && currentState != State.WIN) {
            //System.out.println(currentState);

            if (b2body.getLinearVelocity().x == 0 && b2body.getLinearVelocity().y == 0) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                    currentDirection = Direction.UP;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                    currentDirection = Direction.RIGHT;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                    currentDirection = Player.Direction.LEFT;
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                    currentDirection = Direction.DOWN;
                }
            }
            switch (currentDirection) {
                case NULL:
                    b2body.applyLinearImpulse(new Vector2(0, 0), b2body.getWorldCenter(), true);
                    break;
                case UP:
                    b2body.applyLinearImpulse(new Vector2(0, 2f), b2body.getWorldCenter(), true);
                    break;
                case DOWN:
                    b2body.applyLinearImpulse(new Vector2(0, -2f), b2body.getWorldCenter(), true);
                    break;
                case LEFT:
                    b2body.applyLinearImpulse(new Vector2(-2f, 0), b2body.getWorldCenter(), true);
                    break;
                case RIGHT:
                    b2body.applyLinearImpulse(new Vector2(2f, 0), b2body.getWorldCenter(), true);
                    break;
            }
        } else if (currentState == State.WIN ) {
            b2body.applyLinearImpulse(new Vector2(0, 0), b2body.getWorldCenter(), true);
        } else {
            b2body.applyLinearImpulse(new Vector2(0, -0.1f), b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt) {
        handleInput(dt);
        if(b2body.getLinearVelocity().x == 0 && b2body.getLinearVelocity().y == 0) {
            previousState = currentState;
            currentState = State.STANDING;
            //currentDirection = Direction.NULL;
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        //System.out.println(currentDirection);

        switch (currentState) {
            case DEAD:
                region = playerDead;
                break;
            case RUNNING:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case RUNNINGLEFT:
                region = playerLeft.getKeyFrame(stateTimer, true);
                break;
            case WIN:
            case STANDING:
            default:
                //currentDirection = Direction.NULL;
                if(currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT)
                    region = playerStandLeft;
                    //region = playerStand;
                else
                    region = playerStand;
                break;

        }
        //System.out.println(currentState);
        //System.out.println(b2body.getLinearVelocity().y);
        if ((b2body.getLinearVelocity().y < 0 || currentDirection == Direction.DOWN) && !region.isFlipY()) {
            region.flip(false, true);
        } else if ((b2body.getLinearVelocity().y > 0 || currentDirection == Direction.UP) && region.isFlipY()) {
            region.flip(true, true);
        }

        if((b2body.getLinearVelocity().x < 0 || currentDirection == Direction.LEFT) && region.isFlipX())
            region.flip(true,true);
        else if((b2body.getLinearVelocity().x > 0 || currentDirection == Direction.RIGHT) && !region.isFlipX())
            region.flip(true,false);
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if (isDead)
            return State.DEAD;
        if (isWin)
            return State.WIN;
        else if (b2body.getLinearVelocity().y > 0 || b2body.getLinearVelocity().y < 0 || b2body.getLinearVelocity().x > 0 || b2body.getLinearVelocity().x < 0)
            if(currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT)
                return State.RUNNINGLEFT;
            else
                return State.RUNNING;
        else
            return State.STANDING;

    }

    public void hit() {
        b2body.applyLinearImpulse(new Vector2(0, 0), b2body.getWorldCenter(), true);
        Elective4.assets.get("audio/illuminati.wav", Music.class).stop();
        Elective4.assets.get("audio/die.wav", Sound.class).play(0.5f);
        previousState = currentState;
        currentState = State.DEAD;
        isDead = true;
        //currentDirection = Direction.NULL;
        Filter filter = new Filter();
        filter.maskBits = Elective4.NOTHING_BIT;
        for (Fixture fixture : b2body.getFixtureList())
            fixture.setFilterData(filter);
        b2body.applyLinearImpulse(new Vector2(0, 4), b2body.getWorldCenter(), true);

    }

    public void victory() {
        if (!isWin)
            Elective4.assets.get("audio/Cheering.wav", Sound.class).play(0.2f);
        Elective4.assets.get("audio/wow.mp3", Sound.class).play();
        previousState = currentState;
        currentState = State.WIN;
        isWin = true;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isDead() {
        return isDead;
    }

    public void dispose() {
        playerStand.getTexture().dispose();
        playerDead.getTexture().dispose();/*
        for (TextureRegion t: playerRun.getKeyFrames())
            t.getTexture().dispose();*/
        world.dispose();
    }
}
