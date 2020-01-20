package com.electives.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.electives.game.Elective4;
import com.electives.game.screens.PlayScreen;

/**
 * Created by brentaureli on 9/14/15.
 */
public class MovingWall extends MovingObjects
{
    private float stateTime;
    private Texture sprite;
    private Array<TextureRegion> frames;
    private Rectangle bounds;

    public MovingWall(World world, Rectangle bounds,int count) {
        super(world, bounds,count);
        this.bounds = bounds;
        sprite = new Texture("ui/mwall.png");
        stateTime = 0;
        setBounds(getX()/Elective4.PPM, getY()/Elective4.PPM, 32 / Elective4.PPM, 32 / Elective4.PPM);
        setRegion(sprite);
    }

    public void update(float dt){
        stateTime += dt;
        if(stateTime >= 0.5) {
            reverseVelocity(true, true);
            stateTime = 0;
        }
        b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setTexture(sprite);

    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14 / Elective4.PPM, 14 / Elective4.PPM);
        fdef.filter.categoryBits = Elective4.WALLM_BIT;
        fdef.filter.maskBits = Elective4.WALL_BIT |
                Elective4.XWALL_BIT | Elective4.PLAYER_BIT | Elective4.KILLER_BIT
        | Elective4.KEY_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

    }

    public void draw(Batch batch){
        super.draw(batch);
    }

}
