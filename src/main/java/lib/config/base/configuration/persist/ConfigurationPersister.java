package lib.config.base.configuration.persist;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
// implements
// AbstractPersister<E>

public class ConfigurationPersister<E extends Configuration> {

	private AbstractPersister<E> persister;

	public ConfigurationPersister(AbstractPersister<E> persister) {
		this.persister = persister;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lib.config.base.configuration.persist.Persister#save(java.lang.String, E)
	 */
	public void save(String filename, E... config)
			throws ConfigurationException, IOException {

		File result = new File(filename);

		if (!result.exists()) {
			result.createNewFile();
		}

		persister.write(new ConfigurationList<E>(config), result);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lib.config.base.configuration.persist.Persister#load(java.lang.String)
	 */
	public E load(String filename) throws ConfigurationException {
		List<E> loaded = loadAll(filename);

		if (loaded.size() > 0) {
			return loaded.get(0);
		} else {
			throw new ConfigurationException("Configuration does not exist");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lib.config.base.configuration.persist.Persister#loadAll(java.lang.String)
	 */
	public List<E> loadAll(String filename) throws ConfigurationException {

		try {

			File toLoad = new File(filename);
			if (toLoad.exists()) {
				try {
					ConfigurationList<E> list = persister.read(toLoad);

					return list.getConfigurations();
				} catch (Exception e) {
					throw new ConfigurationException(e);
				}
			} else {
				return Collections.emptyList();
			}

		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
}
