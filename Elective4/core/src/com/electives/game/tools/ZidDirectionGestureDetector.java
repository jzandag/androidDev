package com.electives.game.tools;

import com.badlogic.gdx.InputAdapter;

/**
 * Created by Zidrex Andag on 1/18/2020.
 */
public class ZidDirectionGestureDetector extends InputAdapter {
    public interface DirectionListener {
        void onLeft();

        void onRight();

        void onUp();

        void onDown();
    }
}
