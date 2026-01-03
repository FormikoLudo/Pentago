package fr.formiko.pentago.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import fr.formiko.pentago.Main;
import fr.formiko.pentago.model.Board;
import fr.formiko.pentago.model.Point;
import fr.formiko.pentago.model.State;
import java.util.Map;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BoardActor extends Group {
    private static final Color backgroundColor = Color.BROWN;
    private Board board;
    private Map<Point, CellActor> cells;

    public BoardActor(Board board, int width, int height) {
        this.board = board;
        setSize(width, height);
        int widthCell = width / Board.SIZE;
        int heightCell = height / Board.SIZE;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                // sb.append(getState(new Point(i, j)));
                CellActor cellActor = new CellActor();
                cellActor.setPosition(i * widthCell, j * heightCell);
                cellActor.setSize(widthCell, heightCell);
                addActor(cellActor);
            }
        }
    }

    public void updateCellActorsFromBoard() {
        for (Point point : board.getAllPoints()) {
            State state = board.getState(point);
            cells.get(point).setState(state);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ShapeDrawer shape = Main.getShapeDrawer(batch);
        shape.filledRectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight(), backgroundColor);
        super.draw(batch, parentAlpha);
    }
}
