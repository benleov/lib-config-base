package lib.config.base.configuration.persist;

import java.io.File;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;

public interface AbstractPersister<E extends Configuration> {

	void write(ConfigurationList<E> configuration, File file)
			throws ConfigurationException;

	ConfigurationList<E> read(File toLoad) throws ConfigurationException;

}