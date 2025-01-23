package example;

import static example.MainClass.BLOCK_SIZE;
import static example.MainClass.gameState;

public class Player {
    private final int START_X = 7;
    private final int START_Y = 13;
    private Vector2 position = new Vector2(START_X, START_Y);
    private final Map map;

    public Player(Map map) {
        this.map = map;
        Js.movePlayer(position.getX() * BLOCK_SIZE, position.getY() * BLOCK_SIZE);
    }

    public void update() {
        if (!gameState.IS_GAME_OVER) {
            updatePosition();
        }
        Js.movePlayer(position.getX() * BLOCK_SIZE, position.getY() * BLOCK_SIZE);
        map.takePellet(position);
    }

    public Vector2 getPosition() {
        return position;
    }

    private void updatePosition() {

        if (gameState.W_UP && moveUp()) {
            return;
        }
        if (gameState.A_UP && moveLeft()) {
            return;
        }
        if (gameState.S_UP && moveDown()) {
            return;
        }
        if (gameState.D_UP) {
            moveRight();
        }

    }

    private boolean moveUp() {
        int x = position.getX();
        int y = position.getY() - 1;
        if (map.isValidMove(x, y)) {
            position = new Vector2(x, y);
            return true;
        }
        return false;
    }

    private boolean moveLeft() {
        int x = position.getX() - 1;
        int y = position.getY();
        if (map.isValidMove(x, y)) {
            position = new Vector2(x, y);
            return true;
        }
        return false;
    }

    private boolean moveDown() {
        int x = position.getX();
        int y = position.getY() + 1;
        if (map.isValidMove(x, y)) {
            position = new Vector2(x, y);
            return true;
        }
        return false;
    }

    private boolean moveRight() {
        int x = position.getX() + 1;
        int y = position.getY();
        if (map.isValidMove(x, y)) {
            position = new Vector2(x, y);
            return true;
        }
        return false;
    }

}
