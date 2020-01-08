package com.electives.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;

/**
 * Created by Zidrex Andag on 1/8/2020.
 */
public class Ground extends InteractiveTileObject {

    public Ground(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);

    }
}
