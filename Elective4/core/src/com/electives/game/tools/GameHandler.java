package com.electives.game.tools;

/**
 * Created by Zidrex Andag on 1/18/2020.
 */
public class GameHandler {

    public void onSwipe(int X, int Y, int downX, int downY) {
        String direction = "";
        if(Math.abs(X-downX) > Math.abs(Y-downY)){
            if(X > downX){
                direction = "right";
            } else if(X < downX){
                direction = "left";
            }
        }else{
            if(Y > downY){
                direction = "down";
            } else if(Y < downY){
                direction = "up";
            }
        }
    }

}
