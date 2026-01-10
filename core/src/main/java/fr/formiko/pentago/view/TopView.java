package fr.formiko.pentago.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import fr.formiko.pentago.Main;
import fr.formiko.pentago.model.Point;
import fr.formiko.pentago.model.State;
import fr.formiko.pentago.model.TurnState;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class TopView  extends Actor {
    
    private Main mainController;
    Label label;
    public TopView(Main mainController) {
        super();
        this.setPosition(0, Gdx.graphics.getHeight() - 40);
        this.mainController = mainController;
        LabelStyle style = new LabelStyle(Fonts.getFont(30), Color.WHITE);
        this.label = new Label(mainController.getTurnState().toString(), style);     
        this.label.setPosition(this.getX(), this.getY());
        System.out.printf("Top View position : (%f,%f)", this.getX(), this.getY());
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeDrawer shape = Main.getShapeDrawer(batch);
        this.label.setText(mainController.getTurnState().toString());
        label.draw(batch, parentAlpha);
    }

}