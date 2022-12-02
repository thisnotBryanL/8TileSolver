/**
 * Bryan Lee
 * CSC 480
 */

package state;

import heuristic.IHeuristic;

import java.awt.*;
import java.util.Stack;

public class State {
    // Board initialization
    public static int rows = 3;
    public static int columns = 3;
    private final int[][] gameBoard;

    // Used as pointer state
    private final State parent;

    // Holds position of empty tile
    private final Point emptyTile;

    // Current to reach this state
    private int cost;

    // Current distance to the goal state
    private int goalDistance;

    // Heuristic being used for current state
    private static IHeuristic heuristic;


    public State(int[][] board, State parent) {
        this.gameBoard = new int[rows][columns];
        this.parent = parent;
        this.emptyTile = new Point(0, 0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    emptyTile.x = j;
                    emptyTile.y = i;
                } else
                    this.gameBoard[i][j] = board[i][j];
            }
        }
    }

    public static void setHeuristic(IHeuristic heuristic) {
        State.heuristic = heuristic;
    }

    // Calculate cost of current state using the gameboard as the cost per move
    public void setCost() {
        // If parent is null then this state is the root state
        if(parent == null){
            this.cost = 0;
        }else {
            int rows = State.rows;
            int columns = State.columns;

            int rowInit = 0;
            int colInit = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (this.gameBoard[i][j] == 0) {
                        rowInit = i;
                        colInit = j;
                        break;
                    }
                }
            }
            // Running total of cost to reach this state
            this.cost = parent.getCost() + parent.gameBoard[rowInit][colInit];
        }
    }

    public int getCost() {
        return cost;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGoalDistance(State goalState) {
        // Use heuristic to calculate h(n)
        this.goalDistance = heuristic.calculateValue(this, goalState);
    }

    public int getHeuristicValue() {
        return goalDistance + cost;
    }

    public void setHeuristicValue(State goalState) {
        setGoalDistance(goalState);
    }

    public State getParent() {
        return parent;
    }

    public Point getEmptyTile() {
        return emptyTile;
    }

    // Retrieves the path by backtracking from goal state once path is found
    public Stack<State> getGoalPath() {
        State current = this;
        Stack<State> path = new Stack<State>();
        while (current != null) {
            path.push(current);
            current = current.getParent();
        }
        return path;
    }

    public void print(){
        for(int i = 0; i < gameBoard.length; i++){
            System.out.print("[ ");
            for(int j = 0; j < gameBoard[i].length; j++){
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.print("]\n");
        }
    }

    public boolean goalTest(State goalState){
        return this.equals(goalState);
    }

    @Override
    public boolean equals(Object toCheck) {
        if (!(toCheck instanceof State)) return false;
        State stateToCheck = (State) toCheck;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.gameBoard[i][j] != stateToCheck.getGameBoard()[i][j] && (this.gameBoard[i][j] >= 0 && stateToCheck.getGameBoard()[i][j] >= 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j] >= 0) {
                    result = prime * result + gameBoard[i][j];
                }else{
                    result = prime * result - 1;
                }
            }
        }
        return result;
    }
}
