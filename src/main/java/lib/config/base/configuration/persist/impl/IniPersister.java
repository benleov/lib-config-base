package lib.config.base.configuration.persist.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.persist.AbstractPersister;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 * Chose to go with this over Apache Commons as it is much lighter and does not
 * have dependencies on Apache's logging system.
 * 
 * @author Benjamin Leov
 *
 * @param <E>
 */
public class IniPersister<E extends Configuration> implements
		AbstractPersister<E> {

	private ConfigurationFactory<E> factory;
	private File file;
	
	public IniPersister(ConfigurationFactory<E> factory, File file) {
		this.factory = factory;
		this.file = file;
	}
	
	@Override
	public void write(ConfigurationList<E> configuration)
			throws ConfigurationException {

		Wini ini = new Wini();

		List<E> configs = configuration.getConfigurations();

		for (E curr : configs) {
			String id = curr.getId(); // section id

			Section section = ini.add(id);
			for (String key : curr.getKeys()) {
				section.add(key, curr.getProperty(key));
			}
		}

		try {
			ini.store(file);
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	@Override
	public ConfigurationList<E> read() throws ConfigurationException {

		try {
			Wini ini = new Wini(file);
			Set<Entry<String, Section>> sections = ini.entrySet();

			List<E> configs = new ArrayList<E>();

			for (Entry<String, Section> entry : sections) {

				String id = entry.getKey();

				E newConfig = factory.buildConfiguration(entry.getKey());
				newConfig.setId(id);

				Section section = entry.getValue();

				for (String key : section.keySet()) {
					String value = section.get(key);
					newConfig.setProperty(key, value);
				}

				configs.add(newConfig);
			}

			ConfigurationList<E> list = new ConfigurationList<E>();
			list.setConfigurations(configs);

			return list;

		} catch (InvalidFileFormatException e) {
			throw new ConfigurationException(e);
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}

	}

}
