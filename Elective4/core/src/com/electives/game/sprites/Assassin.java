package com.electives.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;
import com.electives.game.screens.PlayScreen;

/**
 * Created by Zidrex Andag on 1/8/2020.
 */
public class Assassin extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion assassinStand;

    public Assassin(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("assassin"));
        this.world = world;
        defineAssassin();
        assassinStand = new TextureRegion(getTexture(), 0,0,16,16);
        setBounds(0,0,16 / Elective4.PPM,16 / Elective4.PPM);
        setRegion(assassinStand);
    }

    public void defineAssassin(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Elective4.PPM, 32 / Elective4.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5 / Elective4.PPM);

        fixtureDef.shape = circleShape;
        b2body.createFixture(fixtureDef);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }
}
