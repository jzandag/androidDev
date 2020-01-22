package com.electives.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;
import com.electives.game.screens.PlayScreen;

/**
 * Created by brentaureli on 9/14/15.
 */
public abstract class MovingObjects extends Sprite {
    protected World world;
    public Body b2body;
    public Vector2 velocity;

    public MovingObjects(World world, Rectangle bounds, int count){
        this.world = world;
        setPosition(bounds.getX() / Elective4.PPM + bounds.getWidth() / 2/ Elective4.PPM, bounds.getY()/Elective4.PPM + bounds.getHeight()/2/Elective4.PPM);
        defineEnemy();
        if(count % 2 == 0)
            velocity = new Vector2(3f, 0);
        else
            velocity = new Vector2(0, 3f);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);

    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}
