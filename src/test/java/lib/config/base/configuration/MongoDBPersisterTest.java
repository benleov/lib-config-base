package lib.config.base.configuration;

import java.io.IOException;

import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.MongoDBPersister;

import org.junit.Test;

public class MongoDBPersisterTest {

	@Test
	public void testLoadAndSave() throws ConfigurationException, IOException {

		MongoDBPersister<BasicConfiguration> persister = new MongoDBPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String name) {
						return new BasicConfiguration();
					}
					
				}, "localhost", 28017, "settings_db");

		PersisterTestUtil.testPersister(persister);
	}

}
