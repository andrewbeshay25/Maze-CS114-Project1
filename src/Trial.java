import java.util.Arrays;

public class Trial {
    public static void main(String[] args) {
        char[][] maze = {
                {'x', 'x', 'x', 'x', 'x', 'x'},
                {'+', ' ', 'x', ' ', 'x', 'x'},
                {'x', ' ', ' ', ' ', ' ', 'x'},
                {'x', 'x', 'x', 'x', ' ', '-'},
                {'x', 'x', 'x', 'x', 'x', 'x'}};
        int[] entrancePoint = new int[2];
        int[] currentLoc = new int[2];

        System.out.println("Entrance: " + Arrays.toString(entrancePoint = findEntrance(maze)));
        System.out.println("Current location: " + Arrays.toString(currentLoc = entrancePoint));

        System.out.println("Current Path: " + Arrays.toString(findPath(maze, currentLoc)));
    }


    public static int[] findEntrance(char[][] maze) {
        int[] entrancePoint = new int[2];

        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[i].length - 1; j++) {

                if (i > 0 && i < maze.length - 1) {
                    if (maze[i][0] == '+') {
                        entrancePoint[0] = i;
                        entrancePoint[1] = 0;
                    } else if (maze[i][maze[i].length - 1] == '+') {
                        entrancePoint[0] = i;
                        entrancePoint[1] = maze[i].length - 1;
                    }
                } else if (maze[i][j] == '+') {
                    entrancePoint[0] = i;
                    entrancePoint[1] = j;
                }
            }
        }
        return entrancePoint;
    }

    public static int[] findPath(char[][] maze, int[] location) {
        int[] directs = {0, 0, 0, 0}; // left, up, right, down
        int directions = 0;

        if (maze[location[0] + 1][location[1]] == ' ') {
            directs[3] = 1;
            directions++;
        } else if (maze[location[0] - 1][location[1]] == ' ') {
            directs[1] = 1;
            directions++;
        } else if (maze[location[0]][location[1] + 1] == ' ') {
            directs[2] = 1;
            directions++;
        } else if (maze[location[0]][location[1] - 1] == ' ') {
            directs[0] = 1;
            directions++;
        }

        if (directions > 1)
            makeChoice(maze, directs);

        return directs;
    } // left, up, right, down

    public static void makeChoice(char[][] maze, int[] directs) { //add more args

    }


    public static void findBetterApprochFindingEntrance(char[][] maze) {
        int[] entrancePoint = new int[2];

        long nano_startTime = System.nanoTime();
        //First function - scanning all chars
        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[i].length - 1; j++) {

                if (maze[i][j] == '+') {
                    entrancePoint[0] = i;
                    entrancePoint[1] = j;
                }
            }
        }
        long nano_endTime = System.nanoTime();

        System.out.println("Time taken first function in nano seconds: "
                + (nano_endTime - nano_startTime));


        nano_startTime = System.nanoTime();
        // Second function - scanning only edges
        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[i].length - 1; j++) {

                if (i > 0 && i < maze.length - 1) {
                    if (maze[i][0] == '+') {
                        entrancePoint[0] = i;
                        entrancePoint[1] = 0;
                    } else if (maze[i][maze[i].length - 1] == '+') {
                        entrancePoint[0] = i;
                        entrancePoint[1] = maze[i].length - 1;
                    }
                } else if (maze[i][j] == '+') {
                    entrancePoint[0] = i;
                    entrancePoint[1] = j;
                }
            }
        }
        nano_endTime = System.nanoTime();

        System.out.println("Time taken second function in nano seconds: "
                + (nano_endTime - nano_startTime));
    }
}