/**
 * Bryan Lee
 * CSC 480
 */

package state;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NextStateGenerator {

    /**
     * Main method to check if the corresponding move is valid, if so add it to the list of valid states and return
     *  the list.
     */
    public List<State> getNextStates(State currentState) {
        Point emptyTile = currentState.getEmptyTile();

        List<State> nextStates = new ArrayList<>();
        State moveState;

        if (checkRight(emptyTile, State.columns)) {
            moveState = moveStateRight(State.rows, State.columns, emptyTile, currentState);
            moveState.setCost();
            nextStates.add(moveState);
        }
        if (checkDown(emptyTile, State.rows)) {
            moveState = moveStateDown(State.rows, State.columns, emptyTile, currentState);
            moveState.setCost();
            nextStates.add(moveState);
        }
        if (checkUp(emptyTile)) {
            moveState = moveStateUp(State.rows, State.columns, emptyTile, currentState);
            moveState.setCost();
            nextStates.add(moveState);
        }
        if (checkLeft(emptyTile, State.columns)) {
            moveState = moveStateLeft(State.rows, State.columns, emptyTile, currentState);
            moveState.setCost();
            nextStates.add(moveState);
        }
        return nextStates;
    }

    private void copyBoard(int[][] newBoard, int rows, int columns, State parent) {
        int[][] currentBoard = parent.getGameBoard();
        for (int i = 0; i < rows; i++) {
            if (columns >= 0) System.arraycopy(currentBoard[i], 0, newBoard[i], 0, columns);
        }
    }

    /**
     * Move methods used to move the empty tile to the corresponding move position
     */
    private State moveStateRight(int rows, int columns, Point emptyField, State parent) {
        int[][] rightBoard = new int[rows][columns];
        copyBoard(rightBoard, rows, columns, parent);

        // Swapping empty tile with the tile directly to the right
        int temp = rightBoard[emptyField.y][emptyField.x];
        rightBoard[emptyField.y][emptyField.x] = rightBoard[emptyField.y][emptyField.x + 1];
        rightBoard[emptyField.y][emptyField.x + 1] = temp;

        return new State(rightBoard, parent);
    }

    private State moveStateLeft(int rows, int columns, Point emptyTile, State parent) {
        int[][] leftBoard = new int[rows][columns];
        copyBoard(leftBoard, rows, columns, parent);

        // Swapping empty tile with the tile directly to the left
        int temp = leftBoard[emptyTile.y][emptyTile.x];
        leftBoard[emptyTile.y][emptyTile.x] = leftBoard[emptyTile.y][emptyTile.x - 1];
        leftBoard[emptyTile.y][emptyTile.x - 1] = temp;

        return new State(leftBoard, parent);
    }

    private State moveStateUp(int rows, int columns, Point emptyTile, State parent) {
        int[][] upBoard = new int[rows][columns];
        copyBoard(upBoard, rows, columns, parent);

        // Swapping empty tile with the tile directly above
        int temp = upBoard[emptyTile.y][emptyTile.x];
        upBoard[emptyTile.y][emptyTile.x] = upBoard[emptyTile.y - 1][emptyTile.x];
        upBoard[emptyTile.y - 1][emptyTile.x] = temp;

        return new State(upBoard, parent);
    }

    private State moveStateDown(int rows, int columns, Point emptyTile, State parent) {
        int[][] downBoard = new int[rows][columns];
        copyBoard(downBoard, rows, columns, parent);

        // Swapping empty tile with the tile directly below
        int temp = downBoard[emptyTile.y][emptyTile.x];
        downBoard[emptyTile.y][emptyTile.x] = downBoard[emptyTile.y + 1][emptyTile.x];
        downBoard[emptyTile.y + 1][emptyTile.x] = temp;

        return new State(downBoard, parent);
    }


    /**
     * Methods used to check boundaries and valid moves
     */
    private boolean checkRight(Point emptyTile, int columns) {
        return emptyTile.x % columns != columns - 1;
    }

    private boolean checkLeft(Point emptyTile, int columns) {
        return emptyTile.x % columns != 0;
    }

    private boolean checkUp(Point emptyTile) {
        return (emptyTile.y - 1) >= 0;
    }

    private boolean checkDown(Point emptyTile, int rows) {
        return (emptyTile.y + 1) < rows;
    }

}
