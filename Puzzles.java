import java.io.*;
import java.io.IOException;

public class Puzzles {
	public String[][] p;
	public boolean isItSolved;
	
	public Puzzles(String[][] p) {
		this.p = p;
	}
	
	public String[][] puzzleSelector(String[][] puzzle) throws IOException {
		System.out.println("Choose puzzle:  1. 3x3   2. 4x4   3. 5x5");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String choice = reader.readLine();
		
		switch(choice) {
			case "1":
				puzzle = new String[3][3];
				break;
			case "2":
				puzzle = new String[4][4];
				break;
			case "3":
				puzzle = new String[5][5];
				break;
			default:
				System.out.println("Invalid input.");
		}
		
		return puzzle;
	}

	public static String[][] fillPuzzle(String[] s, String[][] p, int strSize, int puzzleSize) {
		//This method is used to fill up the 2D array with the needed puzzle pieces
		//I didn't really need this but it helped minimize repetition
		int a = 0, index = 0;
		while(a < strSize) {
			for(int j = 0; j < puzzleSize; j++) {
				p[index][j] = s[a];
				a++;
				if(a % puzzleSize == 0 && a > 1) {
					index++;
				}
			}
		}
		
		return p;
	}
	
	public static void printPuzzle(String[][] puzzle) {
		//Prints the puzzle
		//Also here to minimize repetition lik with fillPuzzle
		switch(puzzle.length) {
		case 3:
			for(int i = 0; i < 3; i++) {
				System.out.println("-----");
					System.out.println(puzzle[i][0] + "|" + puzzle[i][1] + "|" + puzzle[i][2]);
			}
			System.out.println("-----" + "\n");
			break;
		case 4:
			for(int i = 0; i < 4; i++) {
				System.out.println("------------");
				System.out.println(puzzle[i][0] + "|" + puzzle[i][1] + "|" + puzzle[i][2] + 
						"|" + puzzle[i][3]);
			}
			System.out.println("------------" + "\n");
			break;
		case 5:
			for(int i = 0; i < 5; i++) {
				System.out.println("--------------");
				System.out.println( puzzle[i][0] + "|" + puzzle[i][1] + "|" + puzzle[i][2] + 
						"|" + puzzle[i][3] + "|" + puzzle[i][4]);
			}
			System.out.println("--------------");
			break;
		}
	}
	
	public static void showUnsolvedPuzzles() throws IOException {
		BufferedReader read3 = new BufferedReader(new FileReader("3x3.txt"));
		BufferedReader read4 = new BufferedReader(new FileReader("4x4.txt"));
		BufferedReader read5 = new BufferedReader(new FileReader("5x5.txt"));
		
		String line = read3.readLine();
		String line2 = read4.readLine();
		String line3 = read5.readLine();
		
		String[] arr = line.split(",");
		String[] arr2 = line2.split(",");
		String[] arr3 = line3.split(",");
		
		String[][] p1 = new String[3][3];
		String[][] p2 = new String[4][4];
		String[][] p3 = new String[5][5];
		
		String[][] unsolved3x3 = fillPuzzle(arr, p1, arr.length, 3);
		String[][] unsolved4x4 = fillPuzzle(arr2, p2, arr2.length, 4);
		String[][] unsolved5x5 = fillPuzzle(arr3, p3, arr3.length, 5);
		
		
		printPuzzle(unsolved3x3);
		printPuzzle(unsolved4x4);
		printPuzzle(unsolved5x5);
		
		read3.close();
		read4.close();
		read5.close();
	}
	
	public String[][] fileReader(String[][] puzzle) throws IOException {
		//This method works with fillPuzzle to fill the 2D array with needed puzzle pieces based on the choice in puzzleSelector
		String[][] puzzles = puzzleSelector(puzzle);
		String fileName = "";
		int size = 0;
		for(int i = 1; i < puzzles.length; i++) {
			size++;
		}
		
		switch(size+1) { //get the name of the file to access based on 2D array size
			case 3:
				fileName = "3x3.txt";
				break;
			case 4:
				fileName = "4x4.txt";
				break;
			case 5:
				fileName = "5x5.txt";
				break;
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = reader.readLine();
		String[] arr = line.split(",");
		puzzle = fillPuzzle(arr, puzzles, arr.length, puzzles.length);
		puzzle[size][size] = " ";
		reader.close();
		return puzzle;
	}
	
	public boolean checkForSolve(String [][] puzzle) throws IOException {
		boolean check = false;
		int rows = puzzle.length - 1;
		int columns = puzzle.length - 1;
		String[][] solvedPuzzle = new String[rows][columns];
		int value = 1;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				solvedPuzzle[i][j] = Integer.toString(value); 
				//solvedPuzzle is filled with the correctly ordered puzzle pieces with each loop iteration
				if(puzzle[i][j].equals(solvedPuzzle[i][j])) {
					check = true;
				}
				else {
					check = false;
					break; //if the puzzle doesn't match with solvedPuzzle, loop ends
				}
				value++;
			}
		}

		return check;
	}
	
	public void moveAndPlayPuzzle(String[][] puzzle) throws IOException {
		isItSolved = false;
		p = fileReader(puzzle);

		printPuzzle(p);
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int newColumn, newRow, oldRow, oldColumn;
		String oldLocation, newLocation, choice;
		
		while(isItSolved == false) {
			System.out.println("What would you like to move? Enter row and column separated by a comma(,):");
			oldLocation = input.readLine();
			String[] a = oldLocation.split(",");
			oldRow = Integer.parseInt(a[0]) - 1;
			oldColumn = Integer.parseInt(a[1]) - 1;
			choice = p[oldRow][oldColumn];
			
			System.out.println("Where would you like to move it? Enter row and column separated by a comma(,):");
			newLocation = input.readLine();
			String[] s = newLocation.split(",");
			newRow = Integer.parseInt(s[0]) - 1;
			newColumn = Integer.parseInt(s[1]) - 1;
			String choiceDestination = p[newRow][newColumn];
				
			if(p[newRow][newColumn].equals(" ")) {
				p[oldRow][oldColumn] = choiceDestination;
				p[newRow][newColumn] = choice;
				printPuzzle(p);
			}
			else {
				System.out.println("That can't be moved there. Pick another input: ");
			}
			
			isItSolved = checkForSolve(p);
		}
		
		if(isItSolved == true) {
			printPuzzle(p);
			System.out.println("Congradulations! You solved the puzzle!");
		}
	}
}
