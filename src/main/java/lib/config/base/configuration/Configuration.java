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

	/**
	 * Removes the specified property.
	 * 
	 * @param key
	 *            Key of property to remove.
	 * 
	 * @return True if a property was removed. False if no property existed with
	 *         that key.
	 */
	boolean removeProperty(String key);

	/**
	 * Returns true if this configuration has the specified property.
	 * 
	 * @param key
	 * @return
	 */
	boolean hasProperty(String key);

	void setProperty(String key, String value);

	Set<String> getKeys();

}
