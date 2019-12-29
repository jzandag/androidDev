package com.electives.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.electives.game.Elective4;

/**
 * Created by Zidrex Andag on 12/28/2019.
 */
public class MainMenuScreen implements Screen {

    private Elective4 game;

    private Stage stage; //done
    private TextureAtlas atlas; //done
    private Skin skin; //done
    private Table table; //done
    private TextButton buttonExit,buttonPlay;
    private BitmapFont white,black; //done
    private Label heading;

    public MainMenuScreen(Elective4 game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //creating fonts
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        //creating button
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button-up");
        textButtonStyle.down = skin.getDrawable("button-down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        buttonExit = new TextButton("Zid and Dessa",textButtonStyle);
        buttonExit.pad(20);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //creating heading
        Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.WHITE);
        heading = new Label(Elective4.TITLE, headingStyle);
        heading.setFontScale(3);

        //putting stuff together
        table.add(heading);
        table.row();
        table.add(buttonExit);
        table.debug(); //enables all the debug line
        stage.addActor(table);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        //handles btn click
        /*if(buttonExit.isPressed()){
            game.setScreen(new ZidScreen(game));
        }*/
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
