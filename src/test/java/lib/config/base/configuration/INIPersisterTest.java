package lib.config.base.configuration;

import java.io.IOException;
import java.util.LinkedHashSet;

import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.IniPersister;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class INIPersisterTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadAndSaveIniPersister() throws ConfigurationException,
			IOException {

		IniPersister<BasicConfiguration> persister = new IniPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String name) {
						return new BasicConfiguration();
					}
				});

		PersisterTestUtil.testPersister(persister);
	}
}
