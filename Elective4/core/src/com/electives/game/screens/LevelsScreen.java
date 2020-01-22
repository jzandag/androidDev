package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by Zidrex Andag on 12/29/2019.
 */
public class LevelsScreen implements Screen {

    private Elective4 game;

    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private Skin skin;
    private ScrollPane scrollPane;
    private TextButton backBtn;

    private ShapeRenderer rect;

    public LevelsScreen(Elective4 game) {
        this.game = game;
    }

    @Override
    public void show() {
        rect = new ShapeRenderer();

        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/tr.pack");
        skin = new Skin(Gdx.files.internal("ui/levelSkin.json"),atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        table.setFillParent(true);

        skin.get(List.ListStyle.class).selection.setBottomHeight(3);
        skin.get(List.ListStyle.class).selection.setTopHeight(3);
        final List list = new List(skin);

        list.setItems(new String[]{"Level 1","Level 2","Level 3","Level 4","Level 5",
                "Level 6","Level 7","Level 8","Level 9","Level 10","Level 11","Level 12",
                "Level 13","Level 14","Level 15","Level 16","Level 17","Level 18","End","Gago","Ka", "Ba"});

        //  Sample
        ImageTextButton storyButton = new ImageTextButton(" Prologue",skin,"unlocked");
        storyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: Prologue");
                game.setScreen(new StoryScreen(game));
            }
        });
        ImageTextButton level1 = new ImageTextButton(" Room 1\n Lobby Room",skin,"unlocked");
        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 1");
                game.setScreen(new Level01(game));
            }
        });

        ImageTextButton level2 = new ImageTextButton(" Room 2\n Bed Room",skin,"unlocked");
        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 2");
                game.setScreen(new Level02(game));
            }
        });
        ImageTextButton level3 = new ImageTextButton(" Room 3\n Ground Room",skin,"unlocked");
        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 3");
                game.setScreen(new Level03(game));
            }
        });
        ImageTextButton level4 = new ImageTextButton(" Room 4\n Entrance Room",skin,"unlocked");
        level4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 4");
                game.setScreen(new Level04(game));
            }
        });

        ImageTextButton level5 = new ImageTextButton(" Room 5\n Mountain Room",skin,"unlocked");
        level5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 5");
                game.setScreen(new Level05(game));
            }
        });

        ImageTextButton level6 = new ImageTextButton(" Room 6\n Dessert Room",skin,"unlocked");
        level6.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 6");
                game.setScreen(new Level06(game));
            }
        });

        ImageTextButton level7 = new ImageTextButton(" Room 7\n Living Room",skin,"unlocked");
        level7.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 7");
                game.setScreen(new Level07(game));
            }
        });

        ImageTextButton level8 = new ImageTextButton(" Room 8\n Comfort Room",skin,"unlocked");
        level8.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 8");
                game.setScreen(new Level08(game));
            }
        });

        ImageTextButton level9 = new ImageTextButton(" Room 9\n Farm Room",skin,"unlocked");
        level9.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 9");
                game.setScreen(new Level09(game));
            }
        });


        ImageTextButton level10 = new ImageTextButton(" Room 10\n Sewage Room",skin,"unlocked");
        level10.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 10");
                game.setScreen(new Level10(game));
            }
        });

        ImageTextButton level11 = new ImageTextButton(" Room 11\n Meditation Room",skin,"unlocked");
        level11.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 11");
                game.setScreen(new Level11(game));
            }
        });

        ImageTextButton level12 = new ImageTextButton(" Room 12\n Campsite Room",skin,"unlocked");
        level12.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 12");
                game.setScreen(new Level12(game));
            }
        });

        ImageTextButton level13 = new ImageTextButton(" Room 13\n Dining Room",skin,"unlocked");
        level13.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 13");
                game.setScreen(new Level13(game));
            }
        });

        ImageTextButton level14 = new ImageTextButton(" Room 14\n Forest Room",skin,"unlocked");
        level14.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 14");
                game.setScreen(new Level14(game));
            }
        });
        ImageTextButton level15 = new ImageTextButton(" Room 15\n Road Room",skin,"unlocked");
        level15.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 15");
                game.setScreen(new Level15(game));
            }
        });
        ImageTextButton level16 = new ImageTextButton(" Room 16\n Storage Room",skin,"unlocked");
        level16.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 16");
                game.setScreen(new Level16(game));
            }
        });

        ImageTextButton level17 = new ImageTextButton(" Room 17\n Port Room",skin,"unlocked");
        level17.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 17");
                game.setScreen(new Level17(game));
            }
        });

        ImageTextButton level18 = new ImageTextButton(" Room 18\n Temple Room",skin,"unlocked");
        level18.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: 18");
                game.setScreen(new Level18(game));
            }
        });
        ImageTextButton epilogue = new ImageTextButton(" Epilogue",skin,"unlocked");
        epilogue.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected level: Prologue");
                game.setScreen(new EpilogueScreen(game));
            }
        });

        ImageTextButton locked2 = new ImageTextButton(" Room 2\n LOCKED",skin,"locked");
        ImageTextButton locked3 = new ImageTextButton(" Room 3\n LOCKED",skin,"locked");
        ImageTextButton locked4 = new ImageTextButton(" Room 4\n LOCKED",skin,"locked");
        ImageTextButton locked5 = new ImageTextButton(" Room 5\n LOCKED",skin,"locked");
        ImageTextButton locked6 = new ImageTextButton(" Room 6\n LOCKED",skin,"locked");
        ImageTextButton locked7 = new ImageTextButton(" Room 7\n LOCKED",skin,"locked");
        ImageTextButton locked8 = new ImageTextButton(" Room 8\n LOCKED",skin,"locked");
        ImageTextButton locked9 = new ImageTextButton(" Room 9\n LOCKED",skin,"locked");
        ImageTextButton locked10 = new ImageTextButton(" Room 10\n LOCKED",skin,"locked");
        ImageTextButton locked11 = new ImageTextButton(" Room 11\n LOCKED",skin,"locked");
        ImageTextButton locked12 = new ImageTextButton(" Room 12\n LOCKED",skin,"locked");
        ImageTextButton locked13 = new ImageTextButton(" Room 13\n LOCKED",skin,"locked");
        ImageTextButton locked14 = new ImageTextButton(" Room 14\n LOCKED",skin,"locked");
        ImageTextButton locked15 = new ImageTextButton(" Room 15\n LOCKED",skin,"locked");
        ImageTextButton locked16 = new ImageTextButton(" Room 16\n LOCKED",skin,"locked");
        ImageTextButton locked17 = new ImageTextButton(" Room 17\n LOCKED",skin,"locked");
        ImageTextButton locked18 = new ImageTextButton(" Room 18\n LOCKED",skin,"locked");

        Table scrollTable = new Table();
        scrollTable.add(storyButton).spaceBottom(20).row();
        scrollTable.add(level1).spaceBottom(20).row();
        if(isLocked(2)) scrollTable.add(locked2).spaceBottom(20).row(); else scrollTable.add(level2).spaceBottom(20).row();
        if(isLocked(3)) scrollTable.add(locked3).spaceBottom(20).row(); else scrollTable.add(level3).spaceBottom(20).row();
        if(isLocked(4)) scrollTable.add(locked4).spaceBottom(20).row(); else scrollTable.add(level4).spaceBottom(20).row();
        if(isLocked(5)) scrollTable.add(locked5).spaceBottom(20).row(); else scrollTable.add(level5).spaceBottom(20).row();
        if(isLocked(6)) scrollTable.add(locked6).spaceBottom(20).row(); else scrollTable.add(level6).spaceBottom(20).row();
        if(isLocked(7)) scrollTable.add(locked7).spaceBottom(20).row(); else scrollTable.add(level7).spaceBottom(20).row();
        if(isLocked(8)) scrollTable.add(locked8).spaceBottom(20).row(); else scrollTable.add(level8).spaceBottom(20).row();
        if(isLocked(9)) scrollTable.add(locked9).spaceBottom(20).row(); else scrollTable.add(level9).spaceBottom(20).row();
        if(isLocked(10)) scrollTable.add(locked10).spaceBottom(20).row(); else scrollTable.add(level10).spaceBottom(20).row();
        if(isLocked(11)) scrollTable.add(locked11).spaceBottom(20).row(); else scrollTable.add(level11).spaceBottom(20).row();
        if(isLocked(12)) scrollTable.add(locked12).spaceBottom(20).row(); else scrollTable.add(level12).spaceBottom(20).row();
        if(isLocked(13)) scrollTable.add(locked13).spaceBottom(20).row(); else scrollTable.add(level13).spaceBottom(20).row();
        if(isLocked(14)) scrollTable.add(locked14).spaceBottom(20).row(); else scrollTable.add(level14).spaceBottom(20).row();
        if(isLocked(15)) scrollTable.add(locked15).spaceBottom(20).row(); else scrollTable.add(level15).spaceBottom(20).row();
        if(isLocked(16)) scrollTable.add(locked16).spaceBottom(20).row(); else scrollTable.add(level16).spaceBottom(20).row();
        if(isLocked(17)) scrollTable.add(locked17).spaceBottom(20).row(); else scrollTable.add(level17).spaceBottom(20).row();
        if(isLocked(18)) scrollTable.add(locked18).spaceBottom(20).row(); else scrollTable.add(level18).spaceBottom(20).row();
        scrollTable.add(epilogue).spaceBottom(20).row();
        //
        scrollPane = new ScrollPane(scrollTable,skin);

        /*playBtn = new TextButton("PLAY", skin, "small");
        playBtn.addListener(new ClickListener(){
            @Override
            public void     clicked(InputEvent event, float x, float y) {
                //game.setScreen(new PlayScreen(game));
                int levelChoice = list.getSelectedIndex() + 1;
                System.out.println("Selected level:" + levelChoice);
                switch (levelChoice){
                    case 1:
                        game.setScreen(new PlayScreen(game));
                        break;
                    case 18:
                        game.setScreen(new ZidScreen(game));
                        break;
                    default:
                        break;
                }
            }
        });
        playBtn.pad(10);*/

        backBtn = new TextButton("BACK", skin,"small");
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(sequence(moveTo(0,stage.getHeight(),0.5f),run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new MainMenuScreen(game));
                    }
                })));
            }
        });
        backBtn.pad(10);

        //putting stuff together
        //table.debug();
        table.add();
        table.add("SELECT ROOM").colspan(2);
        table.add().row();
        table.add(scrollPane).colspan(4).center().expandY().row();
        table.add();/*
        table.add(playBtn).pad(20);*/
        table.add(backBtn).colspan(2).pad(20);
        table.add();


        stage.addActor(table);

        stage.addAction(sequence(moveTo(0,stage.getHeight()), moveTo(0,0,0.5f)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rect.setAutoShapeType(true);
        rect.begin();
        rect.set(ShapeRenderer.ShapeType.Filled);
        rect.rect(0,0,stage.getWidth(),stage.getHeight()/2, new Color(128/255f,70/255f,27/255f,0),new Color(128/255f,70/255f,27/255f,0),Color.BLACK,Color.BLACK);
        rect.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        // that's what you probably want in case of a UI
        stage.getViewport().update(width,height,true);

        table.invalidateHierarchy();
    }

    public boolean isLocked(int level){
        int progressLevel = Gdx.app.getPreferences(Elective4.TITLE).getInteger("progress");
        //System.out.println(level + " tangina "+progressLevel);
        if(level > progressLevel )
            return true;
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        //System.out.println("kinginamo");
        game.dispose();
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        rect.dispose();

    }
}
