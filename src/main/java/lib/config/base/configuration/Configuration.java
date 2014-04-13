package lib.config.base.configuration;

import java.util.Set;

public interface Configuration {

	/**
	 * Sets the unique identifier for this configuration.
	 * 
	 * @param id
	 *            The configurations id.
	 */
	void setId(String id);

	/**
	 * 
	 * @return Returns a unique identifier for this configuration.
	 * 
	 */
	String getId();

	/**
	 * Returns the property associated with this key.
	 * 
	 * @param key
	 *            The key
	 * @return
	 */
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
	 * Returns true if this configuration has a property associated with the
	 * key.
	 * 
	 * @param key
	 * @return Returns true if this configuration
	 */
	boolean hasProperty(String key);

	/**
	 * Sets the property.
	 * 
	 * @param key
	 *            Key of the property.
	 * 
	 * @param value
	 *            Value of the property
	 */
	void setProperty(String key, String value);

	/**
	 * 
	 * @return Returns a set of all the configurations keys.
	 */
	Set<String> getKeys();

}
