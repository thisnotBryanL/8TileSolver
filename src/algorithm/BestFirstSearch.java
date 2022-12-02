/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;

import state.State;
import state.NextStateGenerator;

import java.util.*;

public class BestFirstSearch extends Algorithm {
    public BestFirstSearch(State initialState, State goalState) {
        super(initialState, goalState);
    }

    /**
     *  Using a priority queue to select the node with the lowest heuristic value
     */
    @Override
    public Stack<State> search() {
        Comparator<State> heuristicValSorter = Comparator.comparing(State::getHeuristicValue);
        PriorityQueue<State> queue = new PriorityQueue<>(heuristicValSorter);

        int searchSpace = 0;
        Stack<State> result = new Stack<>();
        Set<State> visited = new HashSet<>();
        NextStateGenerator generator = new NextStateGenerator();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            searchSpace++;
            State current = queue.remove();
            if (current.equals(goalState)) {
                System.out.println("\n==GOAL FOUND===");
                this.searchSpace = searchSpace;
                result = current.getGoalPath();
                return result;
            }

            List<State> nextStates = generator.getNextStates(current);

            for (State state : nextStates) {
                state.setHeuristicValue(goalState);
                if (!visited.contains(state)){
                    queue.add(state);
                    visited.add(state);
                }

            }
        }
        return result;
    }

}
