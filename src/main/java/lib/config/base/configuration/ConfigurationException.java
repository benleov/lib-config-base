package lib.config.base.configuration;

public class ConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}
}
