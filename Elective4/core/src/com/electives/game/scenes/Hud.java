package com.electives.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.electives.game.Elective4;

/**
 * Created by brentaureli on 8/17/15.
 */
public class Hud implements Disposable {

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;

    //Scene2D widgets
    private Label countdownLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label roomLabel;

    public boolean timerStop;

    public Hud(SpriteBatch sb, int level){
        //define our tracking variables
        worldTimer = 50;
        timeCount = 0;
        timerStop = false;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(Elective4.GAME_WIDTH, Elective4.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);
        TextureAtlas atlas = new TextureAtlas("ui/tr.pack");
        Skin skin = new Skin(Gdx.files.internal("ui/levelSkin.json"),atlas);
        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel =new Label("ROOM "+level, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        roomLabel = new Label("ESCAPE ROOM", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //ImageButton pause = new ImageButton(skin.getDrawable("sf-pause"));
        //add our labels to our table, padding the top, and giving them all equal width with expandX

        //table.add(pause).width(40).height(40).right().colspan(3).top().padTop(10).padRight(2).row();
        table.add(roomLabel).expandX().padTop(10);
        table.add().expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(levelLabel).expandX();
        table.add().expandX();
        table.add(countdownLabel).expandX();

        //add our table to the stage
        stage.addActor(table);

    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1 && !timerStop){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() {
        if(timeUp)
            Elective4.assets.get("audio/die.wav", Sound.class).play(0.5f);
        return timeUp; }
}
