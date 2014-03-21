package lib.config.base.configuration.persist.impl;

import java.io.File;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
import lib.config.base.configuration.persist.AbstractPersister;

public class MongoDBPersister<E extends Configuration> implements AbstractPersister<E> {

	@Override
	public void write(ConfigurationList<E> configuration, File file)
			throws ConfigurationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConfigurationList<E> read(File toLoad) throws ConfigurationException {
		// TODO Auto-generated method stub
		return null;
	}

}
