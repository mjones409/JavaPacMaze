package example.Menus;

import example.Js;

import static example.MainClass.*;

public class NewGame implements Menu {
    private static final String SELECT_DIFFICULTY = "Select difficulty by pressing\none of the following numbers";
    private static final String DIFFICULTIES = "\n1\n2\n3\n4\n5\n6";

    @Override
    public void update() {
        Js.push();
        Js.fill(174, 198, 207);
        Js.square(END_GAME_SQUARE_LOCATION, END_GAME_SQUARE_LOCATION, END_GAME_SQUARE_SIZE);
        Js.fill(0, 0, 0);
        Js.text("New Game\nWASD to move\n" + SELECT_DIFFICULTY + DIFFICULTIES, HALF_CANVAS_SIZE, HALF_CANVAS_SIZE - 50);
        Js.pop();
        if (gameState.ONE_UP ||
                gameState.TWO_UP ||
                gameState.THREE_UP ||
                gameState.FOUR_UP ||
                gameState.FIVE_UP ||
                gameState.SIX_UP) {
            gameState.IS_NEW_GAME = false;
            gameState.IS_GAME_OVER = false;
            gameState.INVALID_GAME_STATE = true;
        }

        if (gameState.ONE_UP) {
            gameState.setDifficulty(1);
        } else if (gameState.TWO_UP) {
            gameState.setDifficulty(2);
        } else if (gameState.THREE_UP) {
            gameState.setDifficulty(3);
        } else if (gameState.FOUR_UP) {
            gameState.setDifficulty(4);
        } else if (gameState.FIVE_UP) {
            gameState.setDifficulty(5);
        } else if (gameState.SIX_UP) {
            gameState.setDifficulty(6);
        }

    }
}
