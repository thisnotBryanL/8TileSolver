/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;

import state.State;
import state.NextStateGenerator;

import java.util.*;

public class AStar extends Algorithm {

    public AStar(State initialState, State goalState) {
        super(initialState, goalState);
    }

    /**
     *  Using a priority queue data structure to implement AStar, queue will sort each state based off of the lowest
     *      value for h(n) + g(n) and select that state.
     */
    @Override
    public Stack<State> search() {
        // Priority Queue initialize with heuristic value comparator
        Comparator<State> heuristicValSorter = Comparator.comparing(State::getHeuristicValue);
        PriorityQueue<State> queue = new PriorityQueue<>(heuristicValSorter);

        Stack<State> result = new Stack<>();

        int searchSpace = 0;

        // Repeated state checking
        Set<State> visited = new HashSet<>();

        NextStateGenerator generator = new NextStateGenerator();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            searchSpace++;
            State currNode = queue.remove();

            // If goal is found while searching return the search path of current node
            if (currNode.goalTest(goalState)) {
                this.searchSpace = searchSpace;
                result = currNode.getGoalPath();
                return result;
            }

            // Retrieves all possible next states
            List<State> nextStates = generator.getNextStates(currNode);

            // Add all possible nodes that have not yet been visited
            for (State state : nextStates) {
                // Set heuristic value of current node
                state.setHeuristicValue(goalState);
                if (!visited.contains(state)) {
                    queue.add(state);
                    visited.add(state);
                }
            }
        }
        return result;
    }
}
