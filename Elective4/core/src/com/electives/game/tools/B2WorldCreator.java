package com.electives.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;
import com.electives.game.sprites.Ground;

/**
 * Created by Zidrex Andag on 1/8/2020.
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map){
        // Creating objects from the tiledmap RENDERING
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        //ground
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Ground(world,map,rect);
        }
        //coins
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ Elective4.PPM, (rect.getY() + rect.getHeight() / 2)/ Elective4.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2/ Elective4.PPM, rect.getHeight() / 2/ Elective4.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
