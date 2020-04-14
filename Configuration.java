/**
* Configuration takes care of taking the input configuration from the user in String format,
* and initializing the Tiles to correspond to the given input string. 
*/
public class Configuration {
/**
* Represents the format String for initalizing the Tiles.
*/
	private String data;
/**
* Constructor for Configuration, initializes data to the String format.
* @param format The String which data will be initalized to.
* @throws ConfigurationFormatException An Exception for malformed Configurations.
*/
	public Configuration(String format) throws ConfigurationFormatException {
		data = format;
	}
/**
* Accessor method for Configuration data.
* @return The Configuration data.
*/
	public String getData() {
		return data;
	}
/**
* Initializes Tiles based on the Configuration data.
* @param tiles An object of type Tiles, to be initialized.
* @throws ConfigurationFormatException An Exception for malformed Configurations.
* @throws InvalidConfigurationException An Exception for invalid Configurations.
*/
	public void initialise(Tiles tiles)  throws ConfigurationFormatException, InvalidConfigurationException  {
		int SIZE = Tiles.SIZE;
		String[] rows = data.split(": ");
		
		if(data.trim().length() == 0) {
			throw new ConfigurationFormatException();
		}
		if(rows.length != SIZE) {
			throw new ConfigurationFormatException("Incorrect number of rows in configuration (found " + rows.length + ").");
		}
		
		for(String row : rows) {
			String[] cols = row.split(" ");
			if(cols.length != SIZE) {
				throw new ConfigurationFormatException("Incorrect number of columns in configuration (found " + cols.length + ").");
			}
			for(String col : cols) {
				byte byteCol;
				try {
					byteCol = Byte.parseByte(col);
				}
				catch(NumberFormatException e) {
					throw new ConfigurationFormatException("Malformed Configuration" +  this.data);
				}
			}
		}
		
		String[] values = data.replaceAll(": ", "").split(" ");
		
		
		
		int k = 0;
		for(int i = 0; i < Math.sqrt(values.length); i++) {
			for(int j = 0; j < Math.sqrt(values.length); j++) {
				tiles.setTile(i, j, Byte.parseByte(values[k]));
				k++;
			}
		}
		
		tiles.ensureValidity();
	}
}
