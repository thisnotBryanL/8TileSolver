/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;

import state.State;
import state.NextStateGenerator;

import java.util.*;

public class BreathFirstSearch extends Algorithm {
    public BreathFirstSearch(State initialState, State goalState) {
        super(initialState, goalState);
    }

    /**
     * Using a standard queue for BFS
     */
    @Override
    public Stack<State> search() {
        int searchSpace = 0;
        Stack<State> resultPath = new Stack<>();
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        NextStateGenerator generator = new NextStateGenerator();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            searchSpace++;
            State current = queue.remove();
            if (current.equals(goalState)) {
                System.out.println("\n==GOAL FOUND==\n");
                this.searchSpace = searchSpace;
                resultPath = current.getGoalPath();
                return resultPath;
            }

            List<State> nextStates = generator.getNextStates(current);
            for (State state : nextStates) {
                if (!visited.contains(state)){
                    queue.add(state);
                    visited.add(state);
                }
            }
        }
        return resultPath;
    }

}
