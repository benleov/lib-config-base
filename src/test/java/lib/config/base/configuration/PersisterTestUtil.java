package lib.config.base.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.AbstractPersister;

public class PersisterTestUtil {

	public static void testPersister(AbstractPersister<BasicConfiguration> persister)
			throws ConfigurationException, IOException {

		final String FILE_NAME = "unit_test_file";
		File temp = new File(FILE_NAME);
		temp.delete();
		
		try {
		BasicConfiguration savedConfig = new BasicConfiguration();

		savedConfig.setId("test_config");
		savedConfig.setProperty("some_key", "some_value");

		ConfigurationList<BasicConfiguration> savedList = new ConfigurationList<BasicConfiguration>();
		savedList.setConfigurations(savedConfig);
		
		persister.write(savedList, temp);

		ConfigurationList<BasicConfiguration> loadedList = persister.read(temp);

		assertNotNull(loadedList);

		assertTrue(loadedList.size() == savedList.size());
		
		BasicConfiguration loadedConfig = loadedList.getConfigurations().get(0);
		
		assertEquals(savedConfig.getId(), loadedConfig.getId());
		assertEquals(savedConfig.getProperty("some_key"),
				loadedConfig.getProperty("some_key"));
		
		} finally {
			temp.delete();
		}
	}
}
