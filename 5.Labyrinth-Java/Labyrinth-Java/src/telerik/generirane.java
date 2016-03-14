package telerik;

import java.util.Random;
import java.util.Scanner;



public class generirane {
	public boolean isVisited[][] = new boolean[7][7];
	public char maze[][] = new char[7][7];
	public int playersCurrentRow;
	public int playersCurrentColumn;
	public String command;
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board;
    Scanner scanner;
    Random randomgenerator;


    public generirane() {
        scanner = new Scanner(System.in);
        randomgenerator = new Random();
    }


    /**
     * Method used for creating the maze.
     * While creating the maze, the method will call for isSolvable()
     * to see if the maze is solvable.
     */
	void initializeMaze(){

		// Generates a new maze until at least one solution is found
		do {
			for(int row=0;row<7;row++){
				for(int column=0;column<7;column++){

					isVisited[row][column]=false;
					if(randomgenerator.nextInt(2)==1){
						maze[row][column] = 'X';
					}
					else {
						maze[row][column] = '-';
					}
				}
			}
		}
		while(!isSolvable(3, 3));

		playersCurrentRow = 3;
		playersCurrentColumn = 3;

		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}	
	public void initializeScoreBoard(){
		board = new HighScoreBoard();
	}

	/**
	 * Recursive method for testing if the maze is solvable or not
	 * @param row row number
	 * @param col column number
     * @return true / false depending if solvable
     */
	public boolean isSolvable(int row, int col){
		if(row==6||col==6 || row==0 || col==0) {
			isExit = true;
			return isExit;
		}

		if(maze[row-1][col]=='-') {
			if(!isVisited[row-1][col]) {
				isVisited[row][col] = true;
				isSolvable(row - 1, col);
			}
		}
		if(maze[row+1][col]=='-') {
			if(!isVisited[row+1][col]){
                isVisited[row][col]=true;
                isSolvable(row+1, col);
			}
		}
		if(maze[row][col-1]=='-'){
			if(!isVisited[row][col-1]) {
				isVisited[row][col] = true;
				isSolvable(row, col - 1);
			}
		}
		if(maze[row][col+1]=='-') {
			if(!isVisited[row][col+1]) {
				isVisited[row][col] = true;
				isSolvable(row, col + 1);
			}
		}
		return isExit;
	}

	/**
	 * Method for displaying the maze for the player
	 */
	void printMaze(){
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				System.out.print(maze[row][column]+" ");
			}

			System.out.println();

		}
	}

	/**
	 * Method for handling user input.
	 * Will check if input is right or wrong.
	 */
	public void inputCommand(){

		System.out.println("Enter your next move : L(left), " +
				"R(right), U(up), D(down) ");
		command = scanner.next();


		if (!command.equals("exit")) {
			if(command.equals("restart")){
				isExit = false;
				initializeMaze();

			} else if(command.equals("top")){
				if(board.list.size()>0){
					board.printBoard(board.list);
				} else{
                    System.out.println("The High score board is empty!");
				}
			} else if(command.length()>1){
				System.out.println("Invalid command!");
			} else {
				movePlayer(command.charAt(0));
			}
		} else {
			System.out.println("Good bye!");
			System.exit(0);
		}
	}

	/**
	 * Increasing column
	 */
    private void addColumn() {
        playersCurrentColumn++;
        playersMovesCount++;
    }

	/**
	 * Decreasing column
	 */
    private void decreaseColumn() {
        playersCurrentColumn--;
        playersMovesCount++;
    }

	/**
	 * Increasing row
	 */
    private void addRow() {
        playersCurrentRow++;
        playersMovesCount++;
    }

	/**
	 * Decreasing row
	 */
    private void decreaseRow() {
        playersCurrentRow--;
        playersMovesCount++;
    }

	/**
	 * Moving the player on the board, given that the command is a legal one.
	 * @param firstLetter movement letter
     */
	public void movePlayer(char firstLetter){
        firstLetter = Character.toLowerCase(firstLetter);

        boolean invalidMove = true;

		if (firstLetter == 'l') {
			if (maze[playersCurrentRow][playersCurrentColumn - 1] != 'X') {


				swapCells(playersCurrentRow, playersCurrentRow,
						playersCurrentColumn, playersCurrentColumn - 1);

                decreaseColumn();

                invalidMove = false;

			}
		} else if (firstLetter == 'r') {
			if (maze[playersCurrentRow][playersCurrentColumn + 1] != 'X') {

                swapCells(playersCurrentRow, playersCurrentRow,
						playersCurrentColumn, playersCurrentColumn + 1);
				System.out.println();
				printMaze();

                addColumn();

                invalidMove = false;

			}
		} else if (firstLetter == 'u') {
			if (maze[playersCurrentRow - 1][playersCurrentColumn] != 'X') {
				swapCells(playersCurrentRow, playersCurrentRow - 1,
						playersCurrentColumn, playersCurrentColumn);


                decreaseRow();
                invalidMove = false;


			}
		} else if (firstLetter == 'd') {
			if (maze[playersCurrentRow + 1][playersCurrentColumn] != 'X') {
				swapCells(playersCurrentRow, playersCurrentRow + 1,
						playersCurrentColumn, playersCurrentColumn);


                addRow();
                invalidMove = false;

			}
		} else {
			System.out.println("Invalid command!");
		}

        if(invalidMove) {
            System.out.println("Invalid move!");
            printMaze();
        }


	}


    /**
     * Method for moving the player on the board. This will swap '*' with a '-'
     * @param currentRow where the player are now
     * @param newRow new row for player
     * @param currentColumn where the player are now
     * @param newColumn new column for player
     */
	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){
		char previousCell = maze[currentRow][currentColumn];
		maze[currentRow][currentColumn] = maze[newRow][newColumn];
		maze[newRow][newColumn] = previousCell;
		System.out.println();
		printMaze();

	}
	
	
}