/*
 * 'Maze Generator'
 * Created by Kieran Persoff
 */

import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // Create a single maze of 30 size
        int mazeSize = 30;
        Maze maze = new Maze(mazeSize);
        maze.ResetMaze();
        maze.CreatePaths();
        maze.PrintMaze();

        /*
         * Without making Maze into its own object, this file starts to handle too much
         * To make the file more readable, we split each major component into its own class
         * And, with Maze as an object, it becomes much easier to store multiple
         * Maze also handles strange tools like the boolean 'map' that each one keeps
         */

        // Create a random generator
        Random random = new Random();

        // Create 3 mazes, each of size between 10 and 30
        Maze[] mazeArray = new Maze[3];
        for (int i = 0; i < mazeArray.length; i++) {
            // Pull a random number between 10 (inclusive) and 31 (exclusive)
            int mazeRandomSize = random.nextInt(10, 31);

            // Create the new maze and perform all essential actions
            Maze newMaze = new Maze(mazeRandomSize);
            newMaze.ResetMaze();
            newMaze.CreatePaths();
            // In theory, we could choose to have the maze perform these actions as soon as it is created
            // But for this example, we won't

            // Set the current array index to the new maze
            mazeArray[i] = newMaze;
        }
    }
}