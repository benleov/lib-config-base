package lib.config.base.configuration.persist.impl;

import java.net.UnknownHostException;
import java.util.Set;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationList;
import lib.config.base.configuration.factory.ConfigurationFactory;
import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.AbstractPersister;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBPersister<E extends Configuration> implements
		AbstractPersister<E> {

	private ConfigurationFactory<E> factory;

	private String host, dbName;
	private int port;

	public MongoDBPersister(ConfigurationFactory<E> factory, String host,
			int port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.factory = factory;
	}

	@Override
	public void write(ConfigurationList<E> list) throws ConfigurationException {

		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, port);
			DB db = mongoClient.getDB(dbName);
			DBCollection coll = db.getCollection("configuration");

			for (Configuration conf : list) {

				BasicDBObject doc = new BasicDBObject("name", conf.getId());

				for (String key : conf.getKeys()) {
					doc.append(key, conf.getProperty(key));
				}

				coll.insert(doc);

			}

		} catch (UnknownHostException e) {
			throw new ConfigurationException(e);
		} finally {

			if (mongoClient != null) {
				mongoClient.close();
			}
		}
	}

	@Override
	public ConfigurationList<E> read() throws ConfigurationException {
		MongoClient mongoClient = null;

		ConfigurationList<E> list = new ConfigurationList<E>();

		try {
			mongoClient = new MongoClient(host, port);
			DB db = mongoClient.getDB(dbName);
			DBCollection coll = db.getCollection("configuration");

			DBCursor cursor = coll.find();

			try {
				while (cursor.hasNext()) {

					DBObject obj = cursor.next();
					Set<String> keys = obj.keySet();

					E newConfig = factory.buildConfiguration(obj.get("name")
							.toString());

					for (String key : keys) {
						String value = obj.get(key).toString();
						newConfig.setProperty(key, value);
					}

					list.add(newConfig);
				}

			} finally {
				cursor.close();
			}

			return list;

		} catch (UnknownHostException e) {
			throw new ConfigurationException(e);
		} finally {

			if (mongoClient != null) {
				mongoClient.close();
			}
		}
	}

}
