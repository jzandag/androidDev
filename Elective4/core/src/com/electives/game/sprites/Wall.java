package com.electives.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
public class Wall extends InteractiveTileObject {

    public Wall(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        fixture.setUserData(this);
        setCategoryFilter(Elective4.WALL_BIT);
    }

    @Override
    public void onBodyHit() {
        Elective4.assets.get("audio/hitwall.wav", Sound.class).play();

    }
}
