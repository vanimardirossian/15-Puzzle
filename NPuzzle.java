import java.util.Scanner;
/**
* Implements the user input/output part of the game logic.
*/
public class NPuzzle {
/**
* The instance of Tiles (either ArrayTiles or MatrixTiles) to implement the input/output for.
*/
	private Tiles tiles;
/**
* Constructor for NPuzzle. Instantiates tiles.
* @param tiles Instance of ArrayTiles or MatrixTiles to be used.
*/
	public NPuzzle(Tiles tiles) {
		this.tiles = tiles;
	}
/**
* Implements details of game play.
*/
	public void play() {
		Scanner keyboard = new Scanner(System.in);
		print();
		while (!tiles.isSolved()) {
			System.out.print("Please make a move by inputting U, D, L, R; or stop the game by inputting q: ");
			char command = keyboard.next().charAt(0);
			if (command == 'q') {
				System.out.println("Ending the game.");
				return;
			}
			tiles.move(Tiles.Direction.fromChar(command));
			print();
		}
		System.out.println("You solved the puzzle!");
	}	
/**
* Prints the game board.
*/
	public void print() {
		System.out.println("- " + tiles.getMoveCount() + " moves");
		
		
		int SIZE = Tiles.SIZE;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++)
				System.out.print("-----");
			System.out.println("-");
		
			for (int j = 0; j < SIZE; j++)
				if (tiles.getTile(i, j) == Tiles.EMPTY)
					System.out.printf("|    ");
				else
					System.out.printf("| %2d ", this.tiles.getTile(i, j));
			System.out.println("|");
		}
		for (int j = 0; j < SIZE; j++)
			System.out.print("-----");
		System.out.println("-");
	}
	

	public static void main (String[] args) {
		Tiles t = null;
		
		try {
			if(args[0].equals("--array"))
				t = new ArrayTiles(args[1]);
				
			else
				t = new MatrixTiles(args[args.length - 1]);
		}
		catch(ConfigurationFormatException e) {
			System.out.println(e.getMessage());
			System.exit(0);
			
		}
		catch(InvalidConfigurationException e) {
			System.out.println(e.getMessage());
			System.exit(0);
			
		}


		if (!t.isSolvable()) {
			System.out.println("The game is not solvable. Quitting.");
			System.exit(0);
		}
		
		NPuzzle np = new NPuzzle(t);
		np.play();

	}
}
