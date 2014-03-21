package lib.config.base.configuration.persist;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;

public interface AbstractPersister<E extends Configuration> {

	void write(ConfigurationList<E> configuration)
			throws ConfigurationException;

	ConfigurationList<E> read() throws ConfigurationException;

}