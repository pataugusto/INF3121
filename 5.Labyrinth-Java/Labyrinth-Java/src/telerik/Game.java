package telerik;

import java.util.Scanner;



public class Game {
	public static void main(String[] args){
		generirane labyrinth = new generirane();
		labyrinth.initializeScoreBoard();

		while(true){
			labyrinth.initializeMaze();

			while((labyrinth.playersCurrentColumn!=0)&&(labyrinth.playersCurrentColumn!=6)
					&&(labyrinth.playersCurrentRow!=0)&&(labyrinth.playersCurrentRow!=6)){
				labyrinth.inputCommand();

			}

			inputOutput(labyrinth);

			labyrinth.isExit = false;
			labyrinth.playersCurrentColumn = 3;
			labyrinth.playersCurrentRow = 3;
			labyrinth.playersMovesCount = 0;
		}
	}

	/**
	 * Method used for handling user input and output.
	 * @param labyrinth labyrinth is a object for the game
     */
	public static void inputOutput(generirane labyrinth) {
		System.out.println();
		labyrinth.printMaze();
		System.out.println("Congratulations! You escaped in "+labyrinth.playersMovesCount+" moves.");
		System.out.println();


		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name : ");
		String name = scanner.next();
		Player player = new Player(name,labyrinth.playersMovesCount);
		if(labyrinth.board.addPlayerToChart(player)){
			System.out.println("Your score is in top 5!");
			labyrinth.board.printBoard(labyrinth.board.list);
		}
	}
}
