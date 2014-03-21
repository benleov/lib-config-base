package lib.config.base.configuration.persist.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.persist.AbstractPersister;

public class PropertiesPersister<E extends Configuration> implements
		AbstractPersister<E> {

	private static final String GROUP_SEPERATOR = "__";
	
	private File file;

	private ConfigurationFactory<E> factory;
	
	public PropertiesPersister(ConfigurationFactory<E> factory, File file) {
		this.file = file;
		this.factory = factory;
	}

	@Override
	public void write(ConfigurationList<E> list) throws ConfigurationException {

		try {

			for (Configuration curr : list) {

				Properties properties = new Properties();

				Set<String> keys = curr.getKeys();

				for (String key : keys) {
					properties.put(curr.getId() + GROUP_SEPERATOR + key, curr.getProperty(key));
				}

				properties.store(new FileOutputStream(file), curr.getId());
			}

		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	@Override
	public ConfigurationList<E> read() throws ConfigurationException {
		Properties properties = new Properties();

		ConfigurationList<E> list = new ConfigurationList<E>();
		
		
		try {
			
			properties.load(new FileInputStream(file));
			
			String currGroup = null;
			E config = null;
			
			for(Object key : properties.keySet()) {
				
				String[] keySplit = ((String)key).split(GROUP_SEPERATOR);
				String currKey = keySplit[1];
				String nextGroup = keySplit[0];
				
				if(currGroup == null || !currGroup.equals(nextGroup)) {
					// new group
					config = factory.buildConfiguration(nextGroup);
					list.add(config);
				}

				config.setProperty((String) currKey, (String) properties.getProperty((String) key));
				
				currGroup = nextGroup;
			}
						
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
		
		return list;
	}

}
