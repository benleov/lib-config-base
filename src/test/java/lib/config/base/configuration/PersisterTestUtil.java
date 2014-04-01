package lib.config.base.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.AbstractPersister;

public class PersisterTestUtil {

	public static void testPersister(
			AbstractPersister<BasicConfiguration> persister)
			throws ConfigurationException {

		BasicConfiguration savedConfig = new BasicConfiguration();

		savedConfig.setId("test_config");
		savedConfig.setProperty("some_key", "some_value");

		ConfigurationList<BasicConfiguration> savedList = new ConfigurationList<BasicConfiguration>();
		savedList.setConfigurations(savedConfig);

		persister.write(savedList);

		ConfigurationList<BasicConfiguration> loadedList = persister.read();

		assertNotNull(loadedList);

		assertTrue(loadedList.size() == savedList.size());

		BasicConfiguration loadedConfig = loadedList.getConfigurations().get(0);

		assertEquals(savedConfig.getId(), loadedConfig.getId());
		assertEquals(savedConfig.getProperty("some_key"),
				loadedConfig.getProperty("some_key"));

	}

	public static void testReadWithoutWritePersister(
			AbstractPersister<BasicConfiguration> persister)
			throws ConfigurationException {

		ConfigurationList<BasicConfiguration> loadedList = persister.read();
	}

	public static void testPersisterMultipleConfigs(
			AbstractPersister<BasicConfiguration> persister)
			throws ConfigurationException {

		BasicConfiguration config = new BasicConfiguration();
		config.setId("test_config_one");
		config.setProperty("some_key_one", "some_value_one");

		BasicConfiguration config2 = new BasicConfiguration();
		config2.setId("test_config_two");
		config2.setProperty("some_key_two", "some_value_two");

		ConfigurationList<BasicConfiguration> savedList = new ConfigurationList<BasicConfiguration>();
		savedList.add(config);
		savedList.add(config2);

		persister.write(savedList);

		ConfigurationList<BasicConfiguration> readList = persister.read();

		assertNotNull(readList);
		assertEquals(2, readList.size());
		assertEquals(savedList.getConfigurations(),
				readList.getConfigurations());

	}

	public static void testCustomConfig(
			AbstractPersister<CustomConfiguration> persister)
			throws ConfigurationException {
		ConfigurationList<CustomConfiguration> savedList = new ConfigurationList<CustomConfiguration>();

		CustomConfiguration config = new CustomConfiguration();
		config.setId("test_config");
		config.setProperty("some_key", "some_value");
		savedList.add(config);

		persister.write(savedList);

		ConfigurationList<CustomConfiguration> readList = persister.read();

		assertNotNull(readList);
		assertEquals(1, readList.size());

		assertEquals(savedList.getConfigurations(),
				readList.getConfigurations());

	}

}
