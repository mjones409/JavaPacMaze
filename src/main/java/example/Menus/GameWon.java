package example.Menus;

import example.Js;

import static example.MainClass.*;

public class GameWon implements Menu {
    @Override
    public void update() {
        Js.push();
        Js.fill(119, 221, 119);
        Js.square(END_GAME_SQUARE_LOCATION, END_GAME_SQUARE_LOCATION, END_GAME_SQUARE_SIZE);
        Js.fill(0, 0, 0);
        Js.text("YOU WON!\nPress SPACE to restart.", HALF_CANVAS_SIZE, HALF_CANVAS_SIZE);
        Js.pop();

        if (gameState.SPACE_UP) {
            gameState.IS_NEW_GAME = true;
        }
    }
}
