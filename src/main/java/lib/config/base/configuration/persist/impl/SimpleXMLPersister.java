package lib.config.base.configuration.persist.impl;

import java.io.File;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
import lib.config.base.configuration.persist.AbstractPersister;

import org.simpleframework.xml.core.Persister;

/**
 * Simple XML persister.
 * 
 * @author Benjamin Leov
 *
 * @param <E>
 */
public class SimpleXMLPersister<E extends Configuration> implements AbstractPersister<E> {

	private File file;
	
	public SimpleXMLPersister(File file) {
		this.file = file;
	}

	@Override
	public void write(ConfigurationList<E> configuration)
			throws ConfigurationException {
		Persister persister = new Persister();
		try {
			persister.write(configuration, file);
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}

	@Override
	public ConfigurationList<E> read()throws ConfigurationException {

		Persister persister = new Persister();
		try {
			return persister.read(ConfigurationList.class, file);
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
		
	}

}
