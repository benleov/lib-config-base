package lib.config.base.configuration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.IniPersister;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class INIPersisterTest {
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
		
		IniPersister<BasicConfiguration> persister = new IniPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String name) {
						return new BasicConfiguration();
					}
				}, temp);

		PersisterTestUtil.testPersister(persister);
	}
}
