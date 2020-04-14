/**
* Derived Exception class for handling invalid input strings such as incorrect or repeated values.
*/
public class InvalidConfigurationException extends Exception {
/**
* Constructor for InvalidConfigurationException. Sets the message to "Invalid Configuration: error",
* where error is the input String.
* @param error Error message to append to "Invalid Configuration".
*/
	public InvalidConfigurationException(String error) {
		super("Invalid Configuration: " + error);
	}
/**
* Constructor for InvalidConfigurationException. Sets the message to "Invalid Configuration".
*/
	public InvalidConfigurationException() {
		super("Invalid Configuration");
	}
}
