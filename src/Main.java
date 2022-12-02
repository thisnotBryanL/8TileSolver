/**
 * Bryan Lee
 * CSC 480
 */

import algorithm.*;
import heuristic.*;
import state.State;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Initial Boards
        int [][] goalBoard = new int[][]{{1,2,3},{8,0,4},{7,6,5}};
        int [][] easyBoard = new int[][]{{1,3,4},{8,6,2},{7,0,5}};
        int [][] mediumBoard = new int[][]{{2,8,1},{0,4,3},{7,6,5}};
        int [][] hardBoard = new int[][]{{5,6,7},{4,0,8},{3,2,1}};

        // Initial States for each board
        State easyState = new State(easyBoard,null);
        State mediumState = new State(mediumBoard,null);
        State hardState = new State(hardBoard,null);
        State goalState = new State(goalBoard,null);

        boolean gameStart = true;
        Scanner scanner = new Scanner(System.in);
        int puzzleSelection = 0;
        int algoSelection = 0;
        Algorithm algo;
        State initState;

        // Game loop
        while(gameStart){
            System.out.println("Hello welcome to an 8-tile solver by Bryan Lee");
            System.out.println("Select a Puzzle level by entering the corresponding number:");
            System.out.println("1)Easy ");
            easyState.print();
            System.out.println("\n2)Medium ");
            mediumState.print();
            System.out.println("\n3)Hard");
            hardState.print();

            puzzleSelection = Integer.parseInt(scanner.nextLine());
            switch(puzzleSelection){
                case 2:
                    initState = mediumState;
                    break;
                case 3:
                    initState = hardState;
                    break;
                default:
                    initState = easyState;
            }

            System.out.println("Please choose the algorithm you would like to use:");
            System.out.println("1) Breadth First Search");
            System.out.println("2) Depth First Search");
            System.out.println("3) Uniform Cost Search");
            System.out.println("4) Best First Search");
            System.out.println("5) A* Search (h1(Incorrect Tile Position))");
            System.out.println("6) A* Search (h2(Manhattan))");
            algoSelection = Integer.parseInt((scanner.nextLine()));

            switch (algoSelection){
                case 2:
                    algo = new DepthFirstSearch(initState,goalState);
                    break;
                case 3:
                    algo = new UniformCostSearch(initState,goalState);
                    break;
                case 4:
                    State.setHeuristic(new TilePosition());
                    algo = new BestFirstSearch(initState,goalState);
                    break;
                case 5:
                    State.setHeuristic(new TilePosition());
                    algo = new AStar(initState,goalState);
                    break;
                case 6:
                    State.setHeuristic(new Manhattan());
                    algo = new AStar(initState,goalState);
                    break;
                default:
                    algo = new BreathFirstSearch(initState,goalState);
            };

            // Solve puzzle
            long startTime = System.currentTimeMillis();
            Stack<State> result = algo.search();
            long endTime = System.currentTimeMillis();

            int totalCost = result.peek().getCost();
            int totalNodes = result.size();

            // Display result path
            while(!result.isEmpty()){
                State curr = result.pop();
                curr.print();
                System.out.println();

                if(result.size() == 0){
                    totalCost = curr.getCost();
                }
            }

            // Display metrics
            System.out.println("Total Search Space: " + algo.getSearchSpace());
            System.out.println("Total cost: " + totalCost);
            System.out.println("Total path nodes: " + (totalNodes-1));
            System.out.println("Time elapsed: " + (endTime-startTime) + "ms\n");


            System.out.println("Would you like to try another combination? (y\\n)");
            String tryAgain = scanner.nextLine();
            if(!tryAgain.equals("y")){
                gameStart = false;
            }
        }
    }
}


