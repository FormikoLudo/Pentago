package fr.formiko.pentago;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.formiko.pentago.model.Board;
import fr.formiko.pentago.model.BoardLocation;
import fr.formiko.pentago.model.Point;
import fr.formiko.pentago.model.RotatingDirection;
import fr.formiko.pentago.model.State;
import fr.formiko.pentago.model.TurnState;
import fr.formiko.pentago.view.BoardActor;
import space.earlygrey.shapedrawer.ShapeDrawer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private TurnState turnState;
    private static ShapeDrawer schdraw;
    private Stage stage;
    private Board board;
    private BoardActor boardActor;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        this.board = new Board();
        this.stage = new Stage();
        boardActor = new BoardActor(board, 600, 600, this);
        stage.addActor(boardActor);

        turnState = TurnState.PLAYER_1_PLACE;

        Gdx.input.setInputProcessor(stage);
    }

    public void fullRotateWithLog(Board board, RotatingDirection rotatingDirection, BoardLocation boardLocation) {
        for (int i = 0; i < 4; i++) {
            board.rotate(rotatingDirection, boardLocation);
            Gdx.app.log("Board have been updated", board.toString());
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }


    public static ShapeDrawer getShapeDrawer(Batch batch) {
        if (schdraw == null) {
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.drawPixel(0, 0);
            Texture texture = new Texture(pixmap); // remember to dispose of later
            pixmap.dispose();
            TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
            schdraw = new ShapeDrawer(batch, region);
        }
        return schdraw;
    }

    public void boardClicked(Point point) {
        if (turnState == TurnState.PLAYER_1_PLACE) {
            if (board.place(point, State.WHITE)) {
                boardActor.updateCellActorsFromBoard();
                turnState = TurnState.PLAYER_1_ROTATE;
            }
        } else if (turnState == TurnState.PLAYER_2_PLACE) {
            if (board.place(point, State.BLACK)) {
                boardActor.updateCellActorsFromBoard();
                turnState = TurnState.PLAYER_2_ROTATE;
            }
        } else if (turnState == TurnState.PLAYER_1_ROTATE) {
            board.rotate(RotatingDirection.CLOCK_WHISE, BoardLocation.fromPoint(point));
            boardActor.updateCellActorsFromBoard();
            turnState = TurnState.PLAYER_2_PLACE;
        } else if (turnState == TurnState.PLAYER_2_ROTATE) {
            board.rotate(RotatingDirection.CLOCK_WHISE, BoardLocation.fromPoint(point));
            boardActor.updateCellActorsFromBoard();
            turnState = TurnState.PLAYER_1_PLACE;
        } else {
            Gdx.app.log("INFO", "GAME IS ALREADY OVER");
        }
    }
}
