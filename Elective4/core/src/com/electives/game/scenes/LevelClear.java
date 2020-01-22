package com.electives.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.electives.game.Elective4;
import com.electives.game.levels.Level01;
import com.electives.game.levels.Level02;
import com.electives.game.levels.Level03;
import com.electives.game.levels.Level04;
import com.electives.game.levels.Level05;
import com.electives.game.levels.Level06;
import com.electives.game.levels.Level07;
import com.electives.game.levels.Level08;
import com.electives.game.levels.Level09;
import com.electives.game.levels.Level10;
import com.electives.game.levels.Level11;
import com.electives.game.levels.Level12;
import com.electives.game.levels.Level13;
import com.electives.game.levels.Level14;
import com.electives.game.levels.Level15;
import com.electives.game.levels.Level16;
import com.electives.game.levels.Level17;
import com.electives.game.levels.Level18;
import com.electives.game.screens.LevelsScreen;
import com.electives.game.screens.MainMenuScreen;
import com.electives.game.screens.PlayScreen;

/**
 * Created by Zidrex Andag on 1/18/2020.
 */
public class LevelClear {

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    public Window window;

    public LevelClear(final Elective4 game, final int level){
        TextureAtlas atlas = new TextureAtlas("ui/tr.pack");
        Skin skin = new Skin(Gdx.files.internal("ui/levelSkin.json"),atlas);

        window = new Window(" ",skin);
        window.setBounds(0,0,300,300);
        window.setFillParent(true);
        ImageButton home = new ImageButton(skin,"home");
        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("tangina mo home");
                game.setScreen(new LevelsScreen(game));
            }
        });

        ImageButton next = new ImageButton(skin,"next");
        next.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(level);
                switch(level){
                    case 1:
                        game.setScreen(new Level02(game));
                        break;
                    case 2:
                        game.setScreen(new Level03(game));
                        break;
                    case 3:
                        game.setScreen(new Level04(game));
                        break;
                    case 4:
                        game.setScreen(new Level05(game));
                        break;
                    case 5:
                        game.setScreen(new Level06(game));
                        break;
                    case 6:
                        game.setScreen(new Level07(game));
                        break;
                    case 7:
                        game.setScreen(new Level08(game));
                        break;
                    case 8:
                        game.setScreen(new Level09(game));
                        break;
                    case 9:
                        game.setScreen(new Level10(game));
                        break;
                    case 10:
                        game.setScreen(new Level11(game));
                        break;
                    case 11:
                        game.setScreen(new Level12(game));
                        break;
                    case 12:
                        game.setScreen(new Level13(game));
                        break;
                    case 13:
                        game.setScreen(new Level14(game));
                        break;
                    case 14:
                        game.setScreen(new Level15(game));
                        break;
                    case 15:
                        game.setScreen(new Level16(game));
                        break;
                    case 16:
                        game.setScreen(new Level17(game));
                        break;
                    case 17:
                        game.setScreen(new Level18(game));
                        break;
                    case 18:
                        game.setScreen(new Level18(game));
                        break;
                }
            }
        });

        ImageButton restart = new ImageButton(skin,"restart");
        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(level);
                switch(level){
                    case 1:
                        game.setScreen(new Level01(game));
                        break;
                    case 2:
                        game.setScreen(new Level02(game));
                        break;
                    case 3:
                        game.setScreen(new Level03(game));
                        break;
                    case 4:
                        game.setScreen(new Level04(game));
                        break;
                    case 5:
                        game.setScreen(new Level05(game));
                        break;
                    case 6:
                        game.setScreen(new Level06(game));
                        break;
                    case 7:
                        game.setScreen(new Level07(game));
                        break;
                    case 8:
                        game.setScreen(new Level08(game));
                        break;
                    case 9:
                        game.setScreen(new Level09(game));
                        break;
                    case 10:
                        game.setScreen(new Level10(game));
                        break;
                    case 11:
                        game.setScreen(new Level11(game));
                        break;
                    case 12:
                        game.setScreen(new Level12(game));
                        break;
                    case 13:
                        game.setScreen(new Level13(game));
                        break;
                    case 14:
                        game.setScreen(new Level14(game));
                        break;
                    case 15:
                        game.setScreen(new Level15(game));
                        break;
                    case 16:
                        game.setScreen(new Level16(game));
                        break;
                    case 17:
                        game.setScreen(new Level17(game));
                        break;
                    case 18:
                        game.setScreen(new Level18(game));
                        break;
                }
            }
        });
        Label clear = new Label("Level Clear",skin,"levelClear");
        clear.setFontScale(1.5f);
        //window.debug();
        //window.getTitleLabel().
        window.add(clear).colspan(3).spaceBottom(20).row();
        window.add(home).width(50).height(50);
        window.add(next).width(50).height(50);
        window.add(restart).width(50).height(50);
        window.pack();
    }
}
