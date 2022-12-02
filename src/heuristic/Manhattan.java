/**
 * Bryan Lee
 * CSC 480
 */

package heuristic;
import state.State;
import java.awt.*;

public class Manhattan implements IHeuristic {

    @Override
    public int calculateValue(State currentState, State goalState) {
        int[][] currentBoard = currentState.getGameBoard();
        int difference = 0;

        Point[] manhattanPoints = new Point[100];

        for (int i = 0; i < State.rows; i++) {
            for (int j = 0; j < State.columns; j++) {
                if (currentBoard[i][j] >= 0) {
                    manhattanPoints[currentBoard[i][j]] = searchGoalBoard(currentBoard[i][j], goalState);
                }
            }
        }

         for (int i = 0; i < State.rows; i++) {
             for (int j = 0; j < State.columns; j++) {
                 // Calculating the manhattan difference based off of how far the current tile is away from the goal tile
                 Point currPoint = manhattanPoints[currentBoard[i][j]];
                 difference += Math.abs(i - currPoint.getX()) + Math.abs(j - currPoint.getY());
             }
         }
        return difference;
    }

    // Helper function used to find where a selected tile is relative to the goal board
    private Point searchGoalBoard(int tile, State goalState) {
        Point point = null;
        for (int i = 0; i < State.rows; i++) {
            for (int j = 0; j < State.columns; j++) {
                if (tile == goalState.getGameBoard()[i][j])
                    point = new Point(i, j);
            }
        }
        return point;
    }
}