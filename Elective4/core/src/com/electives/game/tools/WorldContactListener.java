package com.electives.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.electives.game.Elective4;
import com.electives.game.sprites.ExplodingWall;
import com.electives.game.sprites.Killer;
import com.electives.game.sprites.MovingWall;
import com.electives.game.sprites.Player;
import com.electives.game.sprites.Wall;

/**
 * Created by Zidrex Andag on 1/14/2020.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch(cDef){
            case Elective4.PLAYER_BIT | Elective4.KILLER_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.PLAYER_BIT)
                    ((Player) fixA.getUserData()).hit();
                else
                    ((Player) fixB.getUserData()).hit();
                Gdx.app.log("Killer","touched");
                break;
            case Elective4.PLAYER_BIT | Elective4.WALL_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.WALL_BIT) {
                    ((Wall) fixA.getUserData()).onBodyHit();
                    //((Player) fixB.getUserData()).currentDirection = Player.Direction.NULL;
                }else{
                    ((Wall) fixB.getUserData()).onBodyHit();
                    //((Player) fixA.getUserData()).currentDirection = Player.Direction.NULL;
                }
                break;
            case Elective4.PLAYER_BIT | Elective4.KEY_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.PLAYER_BIT)
                    ((Player) fixA.getUserData()).victory();
                else
                    ((Player) fixB.getUserData()).victory();
                break;
            case Elective4.WALLM_BIT | Elective4.KILLER_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.WALLM_BIT)
                    ((MovingWall) fixA.getUserData()).reverseVelocity(true,true);
                else {
                    ((MovingWall) fixB.getUserData()).reverseVelocity(true, true);
                }
                break;
            case Elective4.WALLM_BIT | Elective4.WALL_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.WALLM_BIT)
                    ((MovingWall) fixA.getUserData()).reverseVelocity(true,true);
                else
                    ((MovingWall) fixB.getUserData()).reverseVelocity(true,true);
                break;
            case Elective4.WALLM_BIT:
                ((MovingWall) fixA.getUserData()).reverseVelocity(true,true);
                ((MovingWall) fixB.getUserData()).reverseVelocity(true,true);
                break;
            case Elective4.XWALL_BIT | Elective4.PLAYER_BIT:
                if(fixA.getFilterData().categoryBits == Elective4.XWALL_BIT) {
                    ((Player) fixB.getUserData()).currentDirection = Player.Direction.NULL;
                    ((ExplodingWall) fixA.getUserData()).onBodyHit();
                }
                else {
                    ((Player) fixA.getUserData()).currentDirection = Player.Direction.NULL;
                    ((ExplodingWall) fixB.getUserData()).onBodyHit();
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        float platform_y = 0;
        float ball_y = 0;
        if(fixA.getUserData() == Killer.class || fixB.getUserData() == Killer.class){
            Gdx.app.log("tangina", "");

                //contact.setEnabled(false);

        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
