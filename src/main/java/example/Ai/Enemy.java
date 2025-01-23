package example.Ai;


import example.Ai.Behaviors.EnemyBehavior;
import example.Map;
import example.Vector2;

import java.util.ArrayList;

import static example.MainClass.BLOCK_SIZE;

class Enemy {
    private final MoveEnemy move;
    private final EnemyBehavior behavior;

    EnemyBehavior getBehavior() {
        return behavior;
    }

    Enemy(MoveEnemy move, EnemyBehavior behavior) {
        this.move = move;
        this.behavior = behavior;
    }

    Vector2 getPosition() {
        return behavior.getCurrentPosition();
    }

    void updateGameOver() {
        this.move.move(behavior.getCurrentPosition().getX() * BLOCK_SIZE,
                behavior.getCurrentPosition().getY() * BLOCK_SIZE);
    }

    void update(Map map, ArrayList<EnemyBehavior> behaviors) {
        behavior.updateCurrentPosition(map, behaviors);
        var pos = behavior.getCurrentPosition();
        this.move.move(pos.getX() * BLOCK_SIZE, pos.getY() * BLOCK_SIZE);
    }

}
