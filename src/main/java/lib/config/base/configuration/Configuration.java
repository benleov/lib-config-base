package lib.config.base.configuration;

import java.util.Set;

public interface Configuration {

	
	/**
	 * 
	 * @return Returns a unique identifier for this configuration.
	 * 
	 */
	void setId(String id);
	
	String getId();

	String getProperty(String key);
	
	void setProperty(String key, String value);
	
	Set<String> getKeys();
	
}
