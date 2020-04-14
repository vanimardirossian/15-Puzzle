/**
* Derived Exception class for handling malformed input strings such as wrong dimensions.
*/
public class ConfigurationFormatException extends Exception {
/**
* Constructor for ConfigurationFormatException. Sets the message to "Invalid configuration format: error",
* where error is the input String.
* @param error Error message to append to "Invalid configuration format".
*/
	public ConfigurationFormatException(String error) {
		super("Invalid configuration format: " + error);
	}
/**
* Constructor for ConfigurationFormatException. Sets the message to "Please specify a configuration".
*/
	public ConfigurationFormatException() {
		super("Please specify a configuration");
	}
	
}
