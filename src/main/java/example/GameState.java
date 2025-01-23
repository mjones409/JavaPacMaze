package example;

import example.Ai.AiManager;
import example.Menus.*;

public class GameState {
    public int MOUSE_X, MOUSE_Y;
    public boolean MOUSE_DOWN = false;
    public boolean A_UP = false;
    public boolean W_UP = false;
    public boolean S_UP = false;
    public boolean D_UP = false;
    public boolean SPACE_UP = false;
    public boolean ONE_UP = false;
    public boolean TWO_UP = false;
    public boolean THREE_UP = false;
    public boolean FOUR_UP = false;
    public boolean FIVE_UP = false;
    public boolean SIX_UP = false;
    public boolean IS_GAME_OVER = true;
    public boolean IS_GAME_WON = false;
    public boolean IS_NEW_GAME = true;
    public boolean INVALID_GAME_STATE = false;

    public final Map map = new Map();
    public final Player player = new Player(map);
    public final AiManager aiManager = new AiManager(player, map);
    public boolean DOUBLE_CLICK = false;

    private static final Menu gameOverMenu = new GameOver();
    private static final Menu gameWonMenu = new GameWon();
    private static final Menu inGameMenu = new InGame();
    private static final Menu newGameMenu = new NewGame();

    private int difficulty;

    public GameState(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Menu getMenu() {
        if (IS_NEW_GAME) {
            return newGameMenu;
        }
        if (IS_GAME_WON) {
            return gameWonMenu;
        }
        if (IS_GAME_OVER) {
            return gameOverMenu;
        }
        return inGameMenu;
    }
}
