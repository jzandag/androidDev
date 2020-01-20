package com.electives.game.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.electives.game.Elective4;
import com.electives.game.screens.GameOverScreen;

/**
 * Created by Zidrex Andag on 1/14/2020.
 */
public class Killer extends InteractiveTileObject {
    public Killer(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(Elective4.KILLER_BIT);
    }

    @Override
    public void onBodyHit() {
        setCategoryFilter(Elective4.KILLER_BIT);
        //((Game)Gdx.app.getApplicationListener()).setScreen(new GameOverScreen());
    }
}
