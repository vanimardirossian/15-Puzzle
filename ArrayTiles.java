/**
* A child class of Tiles. Implements 15 Puzzle using a 1D array to represent the game board.
*/
public class ArrayTiles extends Tiles {
/**
* The 1D array representing the baord.
*/
	private byte[] tiles;
/**
* Represents the index of the empty tile.
*/
	private int emptyPos;
	
/**
* Constructor for ArrayTiles, initializes the tiles array and emptyPos.
* @param format The format String based on which the array is initialized.
* @throws ConfigurationFormatException Thrown by the initialise() method of Configuration.
* @throws InvalidConfigurationException Thrown by the initialise() method of Configuration.
*/
	public ArrayTiles(String format) throws ConfigurationFormatException, InvalidConfigurationException{
		super(format);
		this.tiles = new byte[SIZE * SIZE];
		
		getConfiguration().initialise(this);
		int tilesIndex = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(tiles[tilesIndex] == EMPTY) {
					this.emptyPos = tilesIndex;
				}
				tilesIndex++;
			}
		}
	}
/**
* Accessor method for the value of the tile in the given position.
* @param pos index of the tile of interest.
* @return The value at the given index.
*/
	public byte getTile(int pos) {
		if(pos < 0 || pos >= SIZE * SIZE) {
			System.out.println("Error: Position out of the board");
			System.exit(0);	
		}
		return this.tiles[pos];
	}
/**
* Accessor method inherited from Tiles, takes two dimensional arguments,
* a row and a column, returns the tile in the equivalent position in the 1D array.
* @param row Row of the tile of interest.
* @param col Column of the tile of interest.
*/
	public byte getTile(int row, int col) {
		if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			System.out.println("Error: Position out of the board");
			System.exit(0);
		}
		return getTile((row * SIZE) + col);
	}
/**
* Mutator method for the tile in the given position.
* @param pos The index of the tile of interest.
* @param value New value of tile in given position.
*/	
	public void setTile(int pos, byte value) {
		if(pos < 0 || pos >= tiles.length) {
			System.out.println("Error: Position out of the board");
			System.exit(0);
		}
		this.tiles[pos] = value;
	}
/**
* Mutator method inherited from Tiles, takes two dimensional arguments, a row and
* a column, calls other setTile() with the equivalent position in the 1D array.
* @param row Row of the tile of interest.
* @param col Column of the tile of interest.
* @param value New value of tile in given position.
*/	
	public void setTile(int row, int col, byte value) {
		if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			System.out.println("Error: Position out of the board");
			System.exit(0);
		}

		setTile((row * SIZE) + col, value);
	}
/**
* Inherited from Tiles, implements the moves made by the user.
* @param direction The direction in which the move is to be made.
*/
	public void moveImpl(Direction direction) {
		int tilePos = 0;
		switch(direction)
		{
			case UP:
				if(emptyPos >= SIZE * (SIZE - 1)) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tilePos = emptyPos + SIZE;
				break;
			case DOWN:
				if(emptyPos < SIZE) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tilePos = emptyPos - SIZE;
				break;
			case LEFT:
				if((emptyPos + 1) % SIZE == 0) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tilePos = emptyPos + 1;
				break;
			case RIGHT:
				if(emptyPos % SIZE == 0) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tilePos = emptyPos - 1;
				break;
			default:
				System.out.println("\nInvalid move\n");
				System.exit(0);
		}
		setTile(emptyPos, getTile(tilePos));
		setTile(tilePos, EMPTY);
		emptyPos = tilePos;
	}
/**
* Inherited from Tiles. Checks if the puzzle is solved.
* @return true if puzzle is solved, false otherwise.
*/
	public boolean isSolved() {
		if (emptyPos + 1 != SIZE * SIZE)
			return false;
		for(int i = 0; i + 1 < SIZE * SIZE; i++) {
			if(tiles[i] != i + 1)
				return false;
		}
		return true;
	}
}
