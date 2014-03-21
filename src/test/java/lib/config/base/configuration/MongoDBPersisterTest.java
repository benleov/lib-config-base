package lib.config.base.configuration;

import java.io.IOException;

import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.MongoDBPersister;

import org.junit.Test;

public class MongoDBPersisterTest {

	@Test
	public void testLoadAndSave() throws ConfigurationException, IOException {
		
		MongoDBPersister<BasicConfiguration> persister = new MongoDBPersister<BasicConfiguration>();
		PersisterTestUtil.testPersister(persister);
	}
	
}
