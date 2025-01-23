package example;

import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;

import java.util.Random;


public class MainClass {
    public static GameState gameState;

    public static final int BLOCK_SIZE = 57;
    public static final int CIRCLE_SIZE = 10;
    public static final int BLOCKS_PER_SIDE = 15;

    public static final Random RANDOM = new Random();
    private static boolean firstSetup = true;

    private static final int W_KEY = 87;
    private static final int A_KEY = 65;
    private static final int S_KEY = 83;
    private static final int D_KEY = 68;
    private static final int SPACE_KEY = 32;
    private static final int ONE_KEY = 49;
    private static final int TWO_KEY = 50;
    private static final int THREE_KEY = 51;
    private static final int FOUR_KEY = 52;
    private static final int FIVE_KEY = 53;
    private static final int SIX_KEY = 54;

    // 57 * 15 = 855
    private static final int CANVAS_SIZE = 855;
    public static final int HALF_CANVAS_SIZE = CANVAS_SIZE / 2;

    public static final int END_GAME_SQUARE_SIZE = 300;
    public static final int END_GAME_SQUARE_LOCATION = HALF_CANVAS_SIZE - END_GAME_SQUARE_SIZE / 2;

    public static void main(String[] args) {
        if (firstSetup) {
            init();
            firstSetup = false;
            return;
        }
        runJava();

    }

    private static void init() {
        gameState = new GameState(6);
        Js.createCanvas(CANVAS_SIZE, CANVAS_SIZE);
        var document = HTMLDocument.current();
        HTMLCanvasElement canvas = (HTMLCanvasElement) document.getElementById("canvas");
        // Add mouse move event listener
        canvas.addEventListener("mousemove", event -> {
            MouseEvent mouseEvent = (MouseEvent) event;
            gameState.MOUSE_X = mouseEvent.getClientX();
            gameState.MOUSE_Y = mouseEvent.getClientY();
        });
        canvas.addEventListener("mousedown", event -> {
            MouseEvent mouseEvent = (MouseEvent) event;
            gameState.MOUSE_DOWN = true;
        });
        canvas.addEventListener("mouseup", event -> {
            MouseEvent mouseEvent = (MouseEvent) event;
            gameState.MOUSE_DOWN = false;
        });
        canvas.addEventListener("dblclick", event -> {
            gameState.DOUBLE_CLICK = true;
        });
        canvas.addEventListener("keyup", new EventListener<KeyboardEvent>() {
            @Override
            public void handleEvent(KeyboardEvent event) {
                switch (event.getKeyCode()) {
                    case SPACE_KEY:
                        gameState.SPACE_UP = true;
                    case ONE_KEY:
                        gameState.ONE_UP = true;
                    case TWO_KEY:
                        gameState.TWO_UP = true;
                    case THREE_KEY:
                        gameState.THREE_UP = true;
                    case FOUR_KEY:
                        gameState.FOUR_UP = true;
                    case FIVE_KEY:
                        gameState.FIVE_UP = true;
                    case SIX_KEY:
                        gameState.SIX_UP = true;
                    case W_KEY:
                        gameState.W_UP = true;
                        break;
                    case A_KEY:
                        gameState.A_UP = true;
                        break;
                    case S_KEY:
                        gameState.S_UP = true;
                        break;
                    case D_KEY:
                        gameState.D_UP = true;
                        break;
                }
            }
        });
    }

    private static void runJava() {
        Js.background(54, 69, 79);

        gameState.map.update();

        gameState.player.update();

        gameState.aiManager.update();

        gameState.getMenu().update();

        if (gameState.INVALID_GAME_STATE) {
            gameState = new GameState(gameState.getDifficulty());
            gameState.IS_GAME_OVER = false;
            gameState.IS_NEW_GAME = false;
            gameState.IS_GAME_WON = false;
        }

        clearFlags();

    }

    private static void clearFlags() {
        gameState.DOUBLE_CLICK = false;
        gameState.W_UP = false;
        gameState.A_UP = false;
        gameState.S_UP = false;
        gameState.D_UP = false;
        gameState.SPACE_UP = false;
        gameState.ONE_UP = false;
        gameState.TWO_UP = false;
        gameState.THREE_UP = false;
        gameState.FOUR_UP = false;
        gameState.FIVE_UP = false;
        gameState.SIX_UP = false;
    }
}