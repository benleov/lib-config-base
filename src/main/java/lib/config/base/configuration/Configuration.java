package lib.config.base.configuration;

public interface Configuration {

	
	/**
	 * 
	 * @return Returns a unique identifier for this configuration.
	 * 
	 */
	String getId();

	String getProperty(String key);
	
	void setProperty(String key, String value);
}
