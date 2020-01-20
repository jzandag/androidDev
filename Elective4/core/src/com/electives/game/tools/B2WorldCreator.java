package com.electives.game.tools;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.electives.game.Elective4;
import com.electives.game.sprites.ExplodingWall;
import com.electives.game.sprites.Key;
import com.electives.game.sprites.Killer;
import com.electives.game.sprites.MovingWall;
import com.electives.game.sprites.Wall;

/**
 * Created by Zidrex Andag on 1/8/2020.
 */
public class B2WorldCreator {
    private Array<MovingWall> movingWalls;

    public B2WorldCreator(World world, TiledMap map){
        // Creating objects from the tiledmap RENDERING
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        //wall
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Wall(world,map,rect);
        }
        //Killer
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Killer(world,map,rect);
        }
        //Keys
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Key(world,map,rect);
        }
        //moving wall
        movingWalls = new Array<>();
        int count = 0;
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            movingWalls.add(new MovingWall(world,rect,count++));
        }
        //exploding block
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new ExplodingWall(world,map,rect);
        }
    }

    public Array<MovingWall> getMovingWalls() {
        return movingWalls;
    }
}
