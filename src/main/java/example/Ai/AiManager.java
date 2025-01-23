package example.Ai;

import example.Ai.Behaviors.EnemyBehavior;
import example.Js;
import example.Map;
import example.Player;
import example.Vector2;

import java.util.ArrayList;

import static example.MainClass.gameState;

public class AiManager {


    private final MoveEnemy redMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyRed(x, y);
        }
    };

    private final MoveEnemy blueMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyBlue(x, y);
        }
    };

    private final MoveEnemy pinkMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyPink(x, y);
        }
    };

    private final MoveEnemy orangeMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyOrange(x, y);
        }
    };

    private final MoveEnemy greenMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyGreen(x, y);
        }
    };

    private final MoveEnemy purpleMove = new MoveEnemy() {
        @Override
        public void move(int x, int y) {
            Js.moveEnemyPurple(x, y);
        }
    };

    private final Player player;
    private final Map map;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<EnemyBehavior> behaviors = new ArrayList<>();


    public AiManager(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    /// Add enemies based on difficulty.
    private void populateEnemies() {
        Enemy enemyRed = new Enemy(redMove, new EnemyBehavior(new Vector2(8, 7)));
        enemies.add(enemyRed);
        behaviors.add(enemyRed.getBehavior());

        if (gameState.getDifficulty() > 1) {
            Enemy enemyBlue = new Enemy(blueMove, new EnemyBehavior(new Vector2(6, 6)));
            enemies.add(enemyBlue);
            behaviors.add(enemyBlue.getBehavior());
        }
        if (gameState.getDifficulty() > 2) {
            Enemy enemyPink = new Enemy(pinkMove, new EnemyBehavior(new Vector2(6, 7)));
            enemies.add(enemyPink);
            behaviors.add(enemyPink.getBehavior());
        }

        if (gameState.getDifficulty() > 3) {
            Enemy enemyOrange = new Enemy(orangeMove, new EnemyBehavior(new Vector2(8, 6)));
            enemies.add(enemyOrange);
            behaviors.add(enemyOrange.getBehavior());
        }
        if (gameState.getDifficulty() > 4) {
            Enemy enemyGreen = new Enemy(greenMove, new EnemyBehavior(new Vector2(7, 7)));
            enemies.add(enemyGreen);
            behaviors.add(enemyGreen.getBehavior());
        }
        if (gameState.getDifficulty() > 5) {
            Enemy enemyPurple = new Enemy(purpleMove, new EnemyBehavior(new Vector2(7, 6)));
            enemies.add(enemyPurple);
            behaviors.add(enemyPurple.getBehavior());
        }

    }

    private boolean firstUpdate = true;

    public void update() {
        if (firstUpdate) {
            populateEnemies();
            firstUpdate = false;
        }
        for (Enemy enemy : enemies) {
            checkIfGameIsOver(enemy);
            if (gameState.IS_GAME_OVER) {
                enemy.updateGameOver();
            } else {
                enemy.update(map, behaviors);
                checkIfGameIsOver(enemy);
            }
        }
    }

    private void checkIfGameIsOver(Enemy enemy) {
        if (enemy.getPosition().equals(player.getPosition())) {
            gameState.IS_GAME_OVER = true;
        }
    }
}
