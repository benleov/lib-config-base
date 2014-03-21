package lib.config.base.configuration;

import java.io.File;
import java.io.IOException;

import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.PropertiesPersister;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertiesPersisterTest {
	final String FILE_NAME = "unit_test_file";

	@Before
	public void setUp() throws Exception {
		File temp = new File(FILE_NAME);
		temp.delete();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadAndSaveIniPersister() throws ConfigurationException,
			IOException {

		File temp = new File(FILE_NAME);

		PropertiesPersister<BasicConfiguration> persister = new PropertiesPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String id) {
						return new BasicConfiguration(id);
					}
				}, temp);

		PersisterTestUtil.testPersister(persister);
	}
}
