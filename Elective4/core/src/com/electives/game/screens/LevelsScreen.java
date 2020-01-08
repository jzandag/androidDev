package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.electives.game.Elective4;

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
    private TextButton playBtn, backBtn;

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
        /*
        table1 = new Table(skin);
        table1.add(new Image(texture1)).expandY().fillY();
        table1.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
        table1.add(new Label("Look at this axe I stole!", skin)).expandY().fillY();

        Table innerContainer = new Table();
        innerContainer.add(table1).expand().fill();
        innerContainer.row();

        scrollpane = new ScrollPane(innerContainer);
        container.add(scrollpane).fill().expand();
        stage.add(container)
        *
        * */
        skin.get(List.ListStyle.class).selection.setBottomHeight(3);
        skin.get(List.ListStyle.class).selection.setTopHeight(3);
        final List list = new List(skin);

        list.setItems(new String[]{"Level 1","Level 2","Level 3","Level 4","Level 5",
                "Level 6","Level 7","Level 8","Level 9","Level 10","Level 11","Level 12",
                "Level 13","Level 14","Level 15","Level 16","Level 17","Level 18","End","Gago","Ka", "Ba"});
        scrollPane = new ScrollPane(list,skin);

        playBtn = new TextButton("PLAY", skin, "small");
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
        playBtn.pad(10);

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
        table.add("SELECT LEVEL").colspan(2);
        table.add().row();
        table.add(scrollPane).colspan(4).center().expandY().row();
        table.add();
        table.add(playBtn).pad(20);
        table.add(backBtn).pad(20);
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
        rect.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/2, new Color(0.1294f,0.1294f,0.1294f,0),new Color(0.1294f,0.1294f,0.1294f,0),Color.BLACK,Color.BLACK);
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

        game.dispose();
        stage.dispose();
        atlas.dispose();
        skin.dispose();
    }
}
