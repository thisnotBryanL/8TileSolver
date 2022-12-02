/**
 * Bryan Lee
 * CSC 480
 */

package algorithm;

import state.State;
import state.NextStateGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearch extends Algorithm {
    public DepthFirstSearch(State initialState, State goalState) {
        super(initialState, goalState);
    }

    /**
     *  Using a stack for DFS
     */
    @Override
    public Stack<State> search() {
        int searchSpace = 0;
        Stack<State> resultPath = new Stack<>();
        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();
        NextStateGenerator generator = new NextStateGenerator();

        stack.add(initialState);
        visited.add(initialState);
        while (!stack.isEmpty()) {
            searchSpace++;
            State current = stack.pop();
            if (current.equals(goalState)) {
                System.out.println("\n==GOAL FOUND==\n");
                System.out.println("Nodes Explored: " + searchSpace);
                this.searchSpace = searchSpace;
                resultPath = current.getGoalPath();
                return resultPath;
            }

            List<State> nextStates = generator.getNextStates(current);
            for (State state : nextStates) {
                if (!visited.contains(state)){
                    stack.push(state);
                    visited.add(state);
                }
            }
        }
        return resultPath;
    }
}
