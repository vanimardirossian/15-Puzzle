/**
* A child class of Tiles. Implements 15 Puzzle using a 2D array to represent the game board.
*/
public class MatrixTiles extends Tiles {
/**
* The 1D array representing the baord.
*/
	private byte[][] tiles;
/**
* Represents the row index of the empty tile.
*/
	private int emptyRow;
/**
* Represents the column index of the empty tile.
*/
	private int emptyCol;
/**
* Constructor for MatrixTiles, initializes the tiles 2D array, emptyRow, and emptyCol.
* @param format The format String based on which the 2D array is initialized.
* @throws ConfigurationFormatException Thrown by the initialise() method of Configuration.
* @throws InvalidConfigurationException Thrown by the initialise() method of Configuration.
*/
	public MatrixTiles(String format) throws ConfigurationFormatException, InvalidConfigurationException {
		super(format);
		this.tiles = new byte[SIZE][SIZE];
		
		getConfiguration().initialise(this);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(tiles[i][j] == EMPTY) {
					this.emptyRow = i;
					this.emptyCol = j;
				}
			}
		}
	}

/**
* An accessor method for the tile value in the given position.
* @param row Row of tile of interest.
* @param col Column of tile of interest.
* @return Value of tile at given position.
*/
	public byte getTile(int row, int col) {
		if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			System.out.println("Error: Position out of the board");
			System.exit(0);
		}
		return this.tiles[row][col];
	}
/**
* A mutator method for the tile in the given position.
* @param row Row of tile of interest.
* @param col Column of tile of interest.
* @param value New value of tile in given position. 
*/
	public void setTile(int row, int col, byte value) {
		if(row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			System.out.println("Error: Position out of the board");
			System.exit(0);
		}
		this.tiles[row][col] = value;
	}
/**
* Inherited from Tiles, implements the moves made by the user.
* @param direction The direction in which the move is to be made.
*/
	public void moveImpl(Direction direction) {
		int tileCol = 0, tileRow = 0;
		switch(direction)
		{
			case UP:
				if(emptyRow == SIZE - 1) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tileCol = emptyCol;
				tileRow = emptyRow + 1;
				break;
			case DOWN:
				if(emptyRow == 0) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tileCol = emptyCol;
				tileRow = emptyRow - 1;
				break;
			case LEFT:
				if(emptyCol == SIZE - 1) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tileCol = emptyCol + 1;
				tileRow = emptyRow;
				break;
			case RIGHT:
				if(emptyCol == 0) {
					System.out.println("\nInvalid move\n");
					return;
				}
				tileCol = emptyCol - 1;
				tileRow = emptyRow;
				break;
			default:
				System.out.println("\nInvalid move\n");
				System.exit(0);
		}
		setTile(emptyRow, emptyCol, getTile(tileRow, tileCol));
		setTile(tileRow, tileCol, EMPTY);
		emptyCol = tileCol;
		emptyRow = tileRow;
	}
/**
* Inherited from Tiles. Checks if the puzzle is solved.
* @return true if puzzle is solved, false otherwise.
*/
	public boolean isSolved() {
		if (emptyCol + 1 != SIZE || emptyRow + 1 != SIZE)
			return false;
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if ((i + 1 < SIZE || j + 1 < SIZE)
						&& tiles[i][j] != i * SIZE + j + 1)
					return false;
		return true;
	}
