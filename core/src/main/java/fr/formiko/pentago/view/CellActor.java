package fr.formiko.pentago.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import fr.formiko.pentago.Main;
import fr.formiko.pentago.model.State;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class CellActor extends Actor {
    private State state;
    public CellActor() { this.state = State.EMPTY; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeDrawer shape = Main.getShapeDrawer(batch);
        shape.filledCircle(this.getX(Align.center), this.getY(Align.center), getWidth() / 2.5F, getCircleColor());
    }

    public Color getCircleColor() {
        return switch (state) {
            case BLACK -> Color.BLACK;
            case WHITE -> Color.WHITE;
            default -> Color.GRAY;
        };
    }
}
