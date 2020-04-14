/**
* The abstract class which serves as a base for the
* ArrayTiles and MatrixTiles implementations of the 15 Puzzle game.
*/
public abstract class Tiles {

/**
* Direction has the values of the direction in which a move can be made.
* They have character parameters for ease of reading Directions from the keyboard.
*/
	protected enum Direction {
/**
* Value representing up.
*/
		UP('u'),
/**
* Value representing down.
*/
		DOWN('d'),
/**
* Value representing left.
*/
		LEFT('l'),
/**
* Value representing right.
*/
		RIGHT('r');
		
/**
* Represents the character corresponding to the move direction.
*/		
		private char moveChar;
/**
* Constructor for Direction. Initialises moveChar.
* @param moveChar Character representing the direction.
*/	
		Direction(char moveChar) {
			this.moveChar = moveChar;
		}
/**
* Takes a character c, and returns enum value corresponding to that character.
* @param c Character representing direction.
* @return Enum value corresponding to char c.
*/	
		static Direction fromChar(char c) {
			for(Direction d : Direction.values()) {
				if(d.moveChar == c) {
					return d;
				}
			}
			return null;
		}
	}
/**
* Represents the size of the puzzle board, which is of dimension SIZE x SIZE.
*/	
	public static final int SIZE = 4;
/**
* Represents the value of the empty tile.
*/
	public static final byte EMPTY = 0;
/**
* Represents the number of moves.
*/
	private int moves;
/**
* An instance of Configuration, used for initializing the game board.
*/
	private Configuration configuration;
	
/**
* Constructor for Tiles. Initialzes moves to 0, instantiates a Configuration with the String format.
* @param format A String representation of the content of the tiles.
* @throws ConfigurationFormatException Thrown by the Configuration constructor.
*/
	public Tiles(String format) throws ConfigurationFormatException {
		this.configuration = new Configuration(format);
		this.moves = 0;
	}
	
/**
* Checks if the current configuration of the board is solvable.
* @return true if current configuration is solvable, false otherwise.
*/	
	public boolean isSolvable() {
		int inversions = 0;
		int emp = 0;
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++) {
				if(getTile(i,j) == EMPTY)
					emp = i;
				
				for(int k = i; k < SIZE; k++) {
					for(int h = j; h < SIZE; h++) {
						if(getTile(i, j) < getTile(k, h))
							inversions++;
					}
				}
			}
		}
		
		if(emp % 2 == 0 && inversions % 2 == 0)
			return true;
		else if(emp % 2 != 0 && inversions % 2 != 0)
			return true;
		else
			return false;
	}
/**
* Ensures the validity of the configuration inputted by the user.
* @throws InvalidConfigurationException Thrown if configuration is invalid.
*/	
	public void ensureValidity() throws InvalidConfigurationException {
	
		boolean hasEmpty = false;
		boolean[] hasTile = new boolean[SIZE * SIZE];
		for(boolean t : hasTile) {
			t = false;
		}
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
			
				if(getTile(i, j) < 0 || getTile(i, j) > (SIZE * SIZE - 1)) {
					throw new InvalidConfigurationException("incorrect tile value " + getTile(i, j));
				}
				
				if((getTile(i, j) == EMPTY) && hasEmpty) {
					throw new InvalidConfigurationException("multiple empty spaces.");
				}
				
				if(getTile(i, j) == EMPTY) {
					hasEmpty = true;
				}
				
				if(hasTile[getTile(i, j)]) {
					throw new InvalidConfigurationException("multiple tiles with the value " + getTile(i, j) + ".");
				}
				
				hasTile[getTile(i, j)] = true;


			}
		}
	}
/**
* Accessor method for number of moves.
* @return Number of moves.
*/
	public int getMoveCount() {
		return moves;
	}
/**
* Increments the number of moves.
*/
	protected void incrementMoveCount() {
		moves++;
	}
/**
* Accessor method for the configuration.
* @return Configuration of the board.
*/
	protected Configuration getConfiguration() {
		return this.configuration;
	}
/**
* Internally calls the implementation of the moves, and increments the move count.
*/
	public void move(Direction direction) {
		moveImpl(direction);
		incrementMoveCount();
	}
/**
* An abstract method, implementing the moves made by the user.
* @param direction The direction in which the move is to be made.
*/
	protected abstract void moveImpl(Direction direction);
/**
* An abstract accessor method for the tile value in the given position.
* @param row Row of tile of interest.
* @param col Column of tile of interest.
* @return Value of tile at given position.
*/
	public abstract byte getTile(int row, int col);
/**
* An abstract mutator method for the tile in the given position.
* @param row Row of tile of interest.
* @param col Column of tile of interest.
* @param value New value of tile in given position. 
*/
	public abstract void setTile(int row, int col, byte value);
/**
* An abstract method for checking if the puzzle is solved.
* @return true if puzzle is solved, false otherwise.
*/
	public abstract boolean isSolved();
}
