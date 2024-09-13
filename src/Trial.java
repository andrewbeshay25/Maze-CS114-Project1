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

/*
SAMPLE MAZE:
    xxxxxxxxxxx
    + xxxx xxxx
    x         x
    xxxxxxxxx x
    x         -
    xxxxxxxxxxx

ANSWER KEY:
    xxxxxxxxxxx
    ++xxxx.xxxx
    x+++++++++x
    xxxxxxxxx+x
    x........+-
    xxxxxxxxxxx

1) Try finding path to the left, go left. If not, try going straight. If not, try going right. If none, print 'no path found'
1E) Scan for all directions, if more than one is found then trigger a 'choice'. If not, go in the only direction and mark it with (+)s

2) If 'choice' is triggered, try going in the first direction found and mark it with pluses(+),
    if it's not a path, go back to the choice spot and mark that path with dots (.).

3) If the path is fine, repeat.


1) find path:
    -if one found, trigger function with an old loc & direction
    - if multiple is found:
        - trigger choice function to mark the last trusted loc and that function should know the wrong and right paths taken
        - find path and repeat


2) Taking Choices, Recursion, and Back-Tracking:
    - keep going until you find a choice, take the first option in the choices, keep following until you hit a dead end, then back track following your trails of +s
    until you reach the last trusted location of the choice making, then try the second option. All of this while recursion-ing the choice making because you might
    be hit with another choice before finishing the first set of choices.

Functionalities:
    Finding the entrance
    triggering a Choice
    laying +s and .s where the maze goes.
    Something to know where I am at all times

Dead ends:
    can't go nowhere? I mark it as a dead end, the place I am in -> "."
    Back track -> check for a choice.. no choices?, back track with a "."
    Until I find another option where I don't backtrack no more but go in that direction

Found exit:
    Each + would return true to the + behind it as a finale to the line of recursions called beforehand.

Base case:
    Finding the exit (cx, cy)


*Keep an array of spots you are going but unsure of, when made sure its a dad end, turn all these spots (indexes into .s)


 */