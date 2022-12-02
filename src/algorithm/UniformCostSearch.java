/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;

import state.State;
import state.NextStateGenerator;

import java.util.*;

public class UniformCostSearch extends Algorithm {
    public UniformCostSearch(State initialSate, State goalState){
        super(initialSate,goalState);
    }

    /**
     * Using a priority queue to get the lowest cost node and exploring that one
     */
    @Override
    public Stack<State> search() {
        Comparator<State> costValSorter = Comparator.comparing(State::getCost);

        int searchSpace = 0;
        Stack<State> resultPath = new Stack<>();
        PriorityQueue<State> queue = new PriorityQueue<>(costValSorter);
        Set<State> visited = new HashSet<>();
        NextStateGenerator generator = new NextStateGenerator();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            searchSpace++;
            State current = queue.remove();
            if (current.equals(goalState)) {
                System.out.println("\n===GOAL FOUND===\n");
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
