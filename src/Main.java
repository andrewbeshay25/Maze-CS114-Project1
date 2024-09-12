import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// File Path: /Users/andrew/Documents/Fall 2024/CS114/CS114_Project1_Maze/src/maze.dat

public class Main {
    public static void main(String[] args) {
        int count = 0;

        char[][] maze = readMaze("maze.dat");

        int x = findEntrance(maze)[0];
        int y = findEntrance(maze)[1];


        if (findPath(maze, maze.length, maze[0].length, x, y, count)){
            System.out.println("Maze solution found");
        } else{
            System.out.println("No solution found");
        }
    }
    public static char[][] readMaze(String filePath){
        int row, col;
        try {
            Scanner scan = new Scanner(new File(filePath));
            scan.reset();
            // Figure out rows and columns
            String firstLine = scan.nextLine();

            String[] dimensions = firstLine.split(" ");

            row = Integer.parseInt(dimensions[0]);
            col = Integer.parseInt(dimensions[1]);

            char[][] maze = new char[row][col];

            while (scan.hasNext()) {
                for(int i = 0; i <= row-1; i++){
                    String nextLine = scan.nextLine();
                    maze[i] = nextLine.toCharArray();
                }
            }
            scan.close();

            return maze;
        } catch (FileNotFoundException e) {
            System.out.println("Hey man, I understand things can be difficult right now but there's a small issue. " +
                    "The file you're trying to read is not found :(\nGood luck tho! I know you got this!");
            return null;
        }
    }
    public static int[] findEntrance(char[][] maze) {
        int[] entrancePoint = new int[2];

        for (int i = 0; i <= maze.length - 1; i++) {
            for (int j = 0; j <= maze[i].length - 1; j++) {

                if (i > 0 && i < maze.length - 1) {
                    if (maze[i][0] == '+') {
                        entrancePoint[0] = 0;
                        entrancePoint[1] = i;
                    } else if (maze[i][maze[i].length - 1] == '+') {
                        entrancePoint[0] = maze[i].length-1;
                        entrancePoint[1] = i;
                    }
                } else if (maze[i][j] == '+') {
                    entrancePoint[0] = j;
                    entrancePoint[1] = i;
                }
            }
        }
        return entrancePoint;
    }

    public static boolean findPath(char[][] maze, int rows, int cols, int cx, int cy, int count) {
        if (cx < 0 || cx > cols-1 || cy < 0 || cy > rows-1 || maze[cy][cx] == 'X' || (count>0 && maze[cy][cx] == '+')) {
            return false;
        }
        if (maze[cy][cx] == '-'){

            for (char[] i : maze) {
                for (char j : i) {
                    System.out.print(j);
                }
                System.out.println();
            }
            return true;
        }

        maze[cy][cx] = '+';
        count++;

        if (findPath(maze, rows, cols, cx + 1, cy, count)) {
            return true;
        } else if (findPath(maze, rows, cols, cx - 1, cy, count)) {
            return true;
        } else if (findPath(maze, rows, cols, cx, cy + 1, count)) {
            return true;
        } else if (findPath(maze, rows, cols, cx, cy - 1, count)) {
            return true;
        } else{
            maze[cy][cx] = '.';
            return false;
        }
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