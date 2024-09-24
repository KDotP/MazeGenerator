import java.util.Random;

public class Maze {
    private Node[][] maze;
    private boolean[][] map;
    private int mazeSize;

    // When created with no passed size, set size to 5
    public Maze() {
        mazeSize = 5;
        SetMazeSize(mazeSize);
    }

    public Maze(int size) {
        mazeSize = size;
        SetMazeSize(mazeSize);
    }

    private void SetMazeSize(int size)
    {
        maze = new Node[size][size];
        map = new boolean[size][size];
    }

    // Go through the entire Node[][] maze and set each tile to closed (except if it is an edge tile)
    // Also reset map to all false, indicating the tile has not yet been mapped
    public void ResetMaze()
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                String up = "c";
                String right = "c";
                String down = "c";
                String left = "c";

                if (i == 0) {
                    up = "e";
                } else if (i == maze.length - 1) {
                    down = "e";
                }

                if (j == 0) {
                    left = "e";
                } else if (j == maze[i].length - 1) {
                    right = "e";
                }

                maze[i][j] = new Node(up, right, down, left);
                map[i][j] = false;
            }
        }
    }

    // Go through the internal map and check if everything has already been connected to another node
    private boolean CheckIfFullyMapped() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // If anything is unconnected, return false, ending the function
                if (!map[i][j]) return false;
            }
        }
        // This will only return true if EVERY map location has been checked, and is mapped
        return true;
    }

    public void CreatePaths() {
        // If no 'visible' boolean is assigned, we'll assume that visible should be false
        // No need to reprogram the whole thing, just call the existing function with a set variable
        CreatePaths(false);
    }

    public void CreatePaths(boolean visible) {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int posY = random.nextInt(maze.length);
        int posX = random.nextInt(maze[posY].length);

        while(!CheckIfFullyMapped()) {
            boolean done = false;
            while(!done) {
                int directionRoll = random.nextInt(4);

                // If 0 is rolled from random, attempt to go up
                /*
                 * First, we check if going up by one would put the new position over the top
                 * If it does, skip it and return to the top of the loop
                 * You may also notice that posY and posX are flipped from where we normally expect them
                 * This is because it's an array, not a coordinate system
                 * The top left tile would be 0, 0 because that's where we start the double array
                 */ 
                if (directionRoll == 0 && posY - 1 > -1) {
                    // If the above tile has not yet been visited, establish a new connection
                    if(!map[posY-1][posX]) {
                        map[posY][posX] = true; // The current tile should be set to mapped, just in case
                        posY--; // Update the position. Remember, negetive is up
                        maze[posY][posX].SetDown("o"); // Set the NEW tile's 'down' link to 'o' for open
                        map[posY][posX] = true; // Set the new position's map to true
                        done = true; // Indicating a successful action, check if the maze is now complete
                    } 
                    // If the above tile HAS been visited AND is connected to the current tile, travel to above tile without change
                    else if (maze[posY-1][posX].GetDown()) {
                        posY--; // Travel to the new tile
                        // Go back to the top without checking if the map is done because no new tiles were set
                    }
                    // If above tile has already been connected, but not to current tile, do nothing
                    // Because the remainer of this loop relies on the roll, it will simply return to the top
                } 
                // If 1 is rolled from random, attempt to go right
                else if (directionRoll == 1 && posX + 1 < maze.length) {
                    // If the right tile has not yet been visited, establish a new connection
                    if(!map[posY][posX+1]) {
                        maze[posY][posX].SetRight("o");
                        map[posY][posX] = true;
                        posX++;
                        map[posY][posX] = true;
                        done = true;
                    } else if (maze[posY][posX].GetRight()) {
                        posX++;
                    }
                } 
                // If 2 is rolled from random, attempt to travel down
                else if (directionRoll == 2 && posY + 1 < maze.length) {
                    if(!map[posY+1][posX]) {
                        maze[posY][posX].SetDown("o");
                        map[posY][posX] = true;
                        posY++;
                        map[posY][posX] = true;
                        done = true;
                    } else if (maze[posY][posX].GetDown()) {
                        posY++;
                    }
                } 
                // If 3 is rolled from random, attempt to go left
                else if (directionRoll == 3 && posX - 1 > -1) {
                    if(!map[posY][posX-1]) {
                        map[posY][posX] = true;
                        posX--;
                        maze[posY][posX].SetRight("o");
                        map[posY][posX] = true;
                        done = true;
                    } else if (maze[posY][posX-1].GetRight()) {
                        posX--;
                    }
                }
            }
            // If we've asked to view the maze generation in action, print the in-progress maze
            if (visible) {
                System.out.println("new position: " + posX + ", " + posY);
                PrintMaze(posX, posY);
            }
        }

        // Print total execution time for import reasons, probably
        long endTime = System.currentTimeMillis();
        long exeTime = endTime - startTime;
        long seconds = (endTime - startTime) / 1000l;
        System.out.println("Completed maze generation in " + exeTime + "ms or " + seconds + " seconds.");
    }

    public void PrintMaze() {
        System.out.print(" ");
        for (int i = 0; i < maze.length; i++) {
            System.out.print("___ ");
        }
        System.out.print("\n");
        for (int i = 0; i < maze.length; i++)
        {
            System.out.print("|");
            for (int j = 0; j < maze[i].length; j++)
            {
                if (!maze[i][j].GetDown()) {
                    System.out.print("___");
                } else {
                    System.out.print("   ");
                }
                if (!maze[i][j].GetRight()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    /*
     * This void is kept private because any program that creates a maze doesn't know the current position in the maze
     * While it could be used to display a position, it's best practice to always exercise little trust
     * Use private wherever possible
     */
    private void PrintMaze(int posX, int posY) {
        System.out.print(" ");
        for (int i = 0; i < maze.length; i++) {
            System.out.print("___ ");
        }
        System.out.print("\n");
        for (int i = 0; i < maze.length; i++)
        {
            System.out.print("|");
            for (int j = 0; j < maze[i].length; j++)
            {
                if (i == posY && j == posX) {
                    if (!maze[i][j].GetDown()) {
                        System.out.print("_A_");
                    } else {
                        System.out.print(" A ");
                    }
                } else if (!maze[i][j].GetDown()) {
                    System.out.print("___");
                } else {
                    System.out.print("   ");
                }

                if (!maze[i][j].GetRight()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
