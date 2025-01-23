package example;


import example.Ai.Behaviors.EnemyBehavior;

import java.util.ArrayList;

import static example.MainClass.*;

public class Map {

    private final int[][] tiles = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private final int[][] pellets = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    void update() {
        if (noPelletsLeft()) {
            gameState.IS_GAME_OVER = true;
            gameState.IS_GAME_WON = true;
        }
        for (int i = 0; i < BLOCKS_PER_SIDE; i++) {
            for (int j = 0; j < BLOCKS_PER_SIDE; j++) {
                if (pellets[j][i] == 1) {
                    Js.push();
                    Js.fill(255, 255, 255);
                    Js.noStroke();
                    Js.circle(i * BLOCK_SIZE + BLOCK_SIZE / 2, j * BLOCK_SIZE + BLOCK_SIZE / 2, CIRCLE_SIZE);
                    Js.pop();
                }
            }
        }

        for (int i = 0; i < BLOCKS_PER_SIDE; i++) {
            for (int j = 0; j < BLOCKS_PER_SIDE; j++) {
                if (tiles[j][i] == 1) {
                    Js.fill(37, 35, 231);
                    //Js.noStroke();
                    Js.square(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    public void takePellet(Vector2 playerPos) {
        pellets[playerPos.getY()][playerPos.getX()] = 0;
    }

    private boolean noPelletsLeft() {
        for (int i = 0; i < BLOCKS_PER_SIDE; i++) {
            for (int j = 0; j < BLOCKS_PER_SIDE; j++) {
                if (pellets[j][i] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidMoveForEnemy(Vector2 pos, ArrayList<EnemyBehavior> enemies) {

        for (EnemyBehavior behavior : enemies) {
            if (behavior.getCurrentPosition().equals(pos)) {
                return false;
            }
        }
        return isValidMove(pos.getX(), pos.getY());
    }

    public boolean isValidMove(int x, int y) {
        return tiles[y][x] == 0;
    }

    private void updateTiles() {

        if (gameState.DOUBLE_CLICK) {
            int x = gameState.MOUSE_X, y = gameState.MOUSE_Y;
            while (x % BLOCK_SIZE != 0) {
                x--;
            }
            while (y % BLOCK_SIZE != 0) {
                y--;
            }
            x = x / BLOCK_SIZE;
            y = y / BLOCK_SIZE;
            if (tiles[y][x] > 0) {
                tiles[y][x] = 0;
            } else {
                tiles[y][x] = 1;
            }

        }
    }

    private String printTiles() {
        String result = "";
        for (int i = 0; i < BLOCKS_PER_SIDE; i++) {
            result += "{";
            for (int j = 0; j < BLOCKS_PER_SIDE; j++) {

                if (tiles[i][j] == 1) {
                    result += "1,";
                }
                if (tiles[i][j] == 0) {
                    result += "0,";
                }
            }
            result = result.substring(0, result.length() - 1);
            result += "}\n";
        }
        return result;
    }

}
