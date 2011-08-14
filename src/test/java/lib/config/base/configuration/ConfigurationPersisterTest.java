package lib.config.base.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import lib.config.base.configuration.ConfigurationException;
import lib.config.base.configuration.ConfigurationPersister;
import lib.config.base.configuration.impl.BasicConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ConfigurationPersisterTest {
	
	private final String FILE_NAME = "unit_test_config.xml";
	private File temp = new File(FILE_NAME);
	
	@Before
	public void setUp() throws Exception {
		temp.delete();
	}

	@After
	public void tearDown() throws Exception {
		//temp.delete();
	}
	
	@Test 
	public void testLoadNonExistantFile() throws ConfigurationException {
		ConfigurationPersister<BasicConfiguration> persister = 
				new ConfigurationPersister<BasicConfiguration>();
		
		BasicConfiguration loaded = 
				persister.load("should_not_exist.xml");
		
		
		fail();
	}
	
	@Test
	public void testLoadAndSave() throws ConfigurationException {
		
		BasicConfiguration config = new BasicConfiguration();
		
		config.setId("test_config");
		config.setProperty("some_key", "some_value");
		
		ConfigurationPersister<BasicConfiguration> persister = 
				new ConfigurationPersister<BasicConfiguration>();
		
		persister.save(temp.getAbsolutePath(), config);
		
		BasicConfiguration loaded = 
				persister.load(temp.getAbsolutePath());
		
		assertNotNull(loaded);
		
		assertEquals(config.getId(), loaded.getId());
		assertEquals(config.getProperty("some_key"), loaded.getProperty("some_key"));
	}
	
	@Test
	public void testSaveMultipleConfigs() throws ConfigurationException {
		
		BasicConfiguration config = new BasicConfiguration();
		config.setId("test_config_one");
		config.setProperty("some_key_one", "some_value_one");
		
		BasicConfiguration config2 = new BasicConfiguration();
		config2.setId("test_config_two");
		config2.setProperty("some_key_two", "some_value_two");
		
		ConfigurationPersister<BasicConfiguration> 
		persister = new ConfigurationPersister<BasicConfiguration>();
		
		persister.save(temp.getAbsolutePath(), config, config2);	
		
		List<BasicConfiguration> all = persister.loadAll(temp.getAbsolutePath());
		
		assertNotNull(all);
		assertEquals(2, all.size());
		
		
		
	}

}
