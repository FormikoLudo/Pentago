package fr.formiko.pentago;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.formiko.pentago.model.Board;
import fr.formiko.pentago.model.BoardLocation;
import fr.formiko.pentago.model.RotatingDirection;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        Board board = new Board();
        Gdx.app.log("Board have been updated", board.toString());
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
        batch.begin();
        batch.draw(image, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
