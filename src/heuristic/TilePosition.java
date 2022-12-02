/**
 * Bryan Lee
 * CSC 480
 */

package heuristic;

import state.State;

public class TilePosition implements IHeuristic {

    /**
     *
     * Calculates how many tiles are not in the correct position compared to the goal board
     */
    @Override
    public int calculateValue(State initialState, State goalState) {
        int incorrectPositions = 0;
        int[][] initialBoard = initialState.getGameBoard();
        int[][] goalBoard = goalState.getGameBoard();

        for(int i = 0; i < State.rows; i++){
            for(int j = 0; j < State.columns; j++){
                if(initialBoard[i][j] == 0) continue;
                if(initialBoard[i][j] != goalBoard[i][j]){
                    incorrectPositions++;
                }
            }
        }
        return  incorrectPositions;
    }
}
