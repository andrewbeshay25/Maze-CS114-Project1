import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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