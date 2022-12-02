/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;
import state.State;
import java.util.Stack;

/**
 * Super class used to hold algorithm metrics
 */
public abstract class Algorithm  {
    protected int searchSpace;

    protected State initialState;
    protected State goalState;


    public Algorithm(State initialState, State goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public int getSearchSpace() {
        return searchSpace;
    }

    // Method used by every algorithm class to solve the corresponding algorithm
    public abstract Stack<State> search();

}