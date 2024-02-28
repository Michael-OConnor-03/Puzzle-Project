import java.io.*;
import java.util.Scanner;

public class Main {
	static String[][] puzzle;
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		String choice = " ";
		Puzzles p = new Puzzles(puzzle);
		
		while(choice != "1") {
			System.out.println("1. Exit   2. Show unsolved puzzles   3. Solve a puzzle");
			choice = in.nextLine();
			
			switch(choice) {
				case "1":
					System.out.println("Exited the game.");
					System.exit(0);
					break;
				case "2":
					Puzzles.showUnsolvedPuzzles();
					break;
				case "3":
					p.moveAndPlayPuzzle(puzzle);
					break;
				default:
					System.out.println("Invalid input.");
			}
		}

		in.close();
	}
}
