package lib.config.base.configuration;

import java.io.IOException;

import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.impl.MongoDBPersister;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBPersisterTest {

	private static final String MONGO_HOST = "localhost";
	private static final int MONGO_PORT = 27017;
	private static final String MONGO_DB = "settings_db";

	@Before
	public void setUp() throws Exception {
		MongoClient mongoClient = null;

		mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
		DB db = mongoClient.getDB(MONGO_DB);
		
		DBCollection coll = db
				.getCollection(MongoDBPersister.MONGO_COLLECTION_NAME);
		coll.drop();
	}

	@Test
	public void testLoadAndSave() throws ConfigurationException, IOException {

		MongoDBPersister<BasicConfiguration> persister = new MongoDBPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String id) {
						BasicConfiguration bc = new BasicConfiguration();
						bc.setId(id);
						return bc;
					}

				}, MONGO_HOST, MONGO_PORT, MONGO_DB);

		PersisterTestUtil.testPersister(persister);
	}

}
