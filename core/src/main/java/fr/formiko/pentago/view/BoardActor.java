package fr.formiko.pentago.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import fr.formiko.pentago.Main;
import fr.formiko.pentago.model.Board;
import fr.formiko.pentago.model.Point;
import fr.formiko.pentago.model.State;
import java.util.HashMap;
import java.util.Map;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BoardActor extends Group {
    private static final Color backgroundColor = Color.BROWN;
    private static final Color lineColor = new Color(0x552a03ff);
    private static final int lineSize = 4;
    private Board board;
    private Map<Point, CellActor> cells;
    private Main mainController;

    public BoardActor(Board board, int width, int height, Main mainController) {
        this.board = board;
        setSize(width, height);
        this.mainController = mainController;
        int widthCell = width / Board.SIZE;
        int heightCell = height / Board.SIZE;
        cells = new HashMap<>();
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                CellActor cellActor = new CellActor(new Point(i, j), mainController);
                cellActor.setPosition(i * widthCell, getWidth() - heightCell - (j * heightCell));
                cellActor.setSize(widthCell, heightCell);
                addActor(cellActor);
                cells.put(new Point(i, j), cellActor);
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
        shape.filledRectangle(0, this.getY(Align.center) - lineSize, this.getWidth(), lineSize, lineColor);
        shape.filledRectangle(this.getX(Align.center) - lineSize, 0, lineSize, this.getHeight(), lineColor);
        super.draw(batch, parentAlpha);
    }
}
