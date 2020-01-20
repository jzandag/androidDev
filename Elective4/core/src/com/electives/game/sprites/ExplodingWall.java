package com.electives.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;

/**
 * Created by Zidrex Andag on 1/18/2020.
 */
public class ExplodingWall extends InteractiveTileObject {

    public ExplodingWall(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Elective4.XWALL_BIT);
    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("xwall","touched");
        Elective4.assets.get("audio/gunShot.wav", Sound.class).play();
        setCategoryFilter(Elective4.NOTHING_BIT);
        getCell().setTile(null);
    }
}
