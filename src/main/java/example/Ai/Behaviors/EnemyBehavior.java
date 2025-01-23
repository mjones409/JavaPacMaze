package example.Ai.Behaviors;

import example.Map;
import example.Vector2;

import java.util.ArrayList;

import static example.MainClass.RANDOM;

public class EnemyBehavior {
    private static final long SPEED_MS = 300;
    private long nextUpdateTimeMs;
    protected Vector2 currentPosition;

    ///  Prevent ghosts from moving back and forth.
    private MovePositions lastMove = null;

    private final ArrayList<MovePositions> moves = new ArrayList<>() {{
        add(MovePositions.UP);
        add(MovePositions.DOWN);
        add(MovePositions.LEFT);
        add(MovePositions.RIGHT);
    }};

    public EnemyBehavior(Vector2 startingPosition) {
        this.currentPosition = startingPosition;
        // Create random number so enemies do not necessarily move at the same time.
        int initialMoveDelay = RANDOM.nextInt(1500) + 1000;
        this.nextUpdateTimeMs = System.currentTimeMillis() + initialMoveDelay;
    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }


    public void updateCurrentPosition(Map map, ArrayList<EnemyBehavior> behaviors) {
        if (System.currentTimeMillis() <= nextUpdateTimeMs) {
            return;
        }
        nextUpdateTimeMs = System.currentTimeMillis() + SPEED_MS;
        randomize(moves);

        ///  sometimes you have to move backwards if you ran in to another enemy.
        for (int i = 0; i < 2; i++) {

            for (MovePositions move : moves) {
                ///  Prevent ghosts from moving back and forth.
                if (movingBack(move) && i == 0) {
                    continue;
                }

                // MOVE LEFT
                if (move.equals(MovePositions.LEFT)) {
                    var potentialSpot = new Vector2(currentPosition.getX() - 1, currentPosition.getY());
                    if (map.isValidMoveForEnemy(potentialSpot, behaviors)) {
                        currentPosition = potentialSpot;
                        lastMove = move;
                        return;
                    }
                }

                // MOVE RIGHT
                if (move.equals(MovePositions.RIGHT)) {
                    var potentialSpot = new Vector2(currentPosition.getX() + 1, currentPosition.getY());
                    if (map.isValidMoveForEnemy(potentialSpot, behaviors)) {
                        currentPosition = potentialSpot;
                        lastMove = move;
                        return;
                    }
                }

                // MOVE UP
                if (move.equals(MovePositions.UP)) {
                    var potentialSpot = new Vector2(currentPosition.getX(), currentPosition.getY() - 1);
                    if (map.isValidMoveForEnemy(potentialSpot, behaviors)) {
                        currentPosition = potentialSpot;
                        lastMove = move;
                        return;
                    }
                }

                // MOVE DOWN
                if (move.equals(MovePositions.DOWN)) {
                    var potentialSpot = new Vector2(currentPosition.getX(), currentPosition.getY() + 1);
                    if (map.isValidMoveForEnemy(potentialSpot, behaviors)) {
                        currentPosition = potentialSpot;
                        lastMove = move;
                        return;
                    }
                }
            }
        }
    }

    ///  Prevent ghosts from moving back and forth.
    private boolean movingBack(MovePositions move) {
        var opposite = getOppositeMove(move);
        return lastMove == opposite;
    }

    ///  Prevent ghosts from moving back and forth.
    private MovePositions getOppositeMove(MovePositions move) {
        switch (move) {
            case UP:
                return MovePositions.DOWN;
            case DOWN:
                return MovePositions.UP;
            case LEFT:
                return MovePositions.RIGHT;
            case RIGHT:
                return MovePositions.LEFT;
        }
        return null;
    }

    /// Randomizes elements in arraylist.
    private void randomize(ArrayList<MovePositions> list) {
        ArrayList<MovePositions> temp = new ArrayList<>();
        while (!list.isEmpty()) {
            int randomNumber = RANDOM.nextInt(list.size());
            temp.add(list.remove(randomNumber));
        }
        for (MovePositions move : temp) {
            list.add(move);
        }
    }
}
