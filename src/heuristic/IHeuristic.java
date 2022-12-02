/**
 * Bryan Lee
 * CSC 480
 */

package heuristic;

import state.State;

/**
 * Heuristic interface used to maintain both heuristics
 */
public interface IHeuristic {
    int calculateValue(State initState, State goalState);
}
