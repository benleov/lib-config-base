package lib.config.base.configuration;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import lib.config.base.configuration.impl.BasicConfiguration;
import lib.config.base.configuration.persist.AbstractPersister;
import lib.config.base.configuration.persist.impl.SimpleXMLPersister;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XMLPersisterTest {

	private final String FILE_NAME = "unit_test_config.xml";
	private File temp = new File(FILE_NAME);

	@Before
	public void setUp() throws Exception {
		temp.delete();
	}

	@After
	public void tearDown() throws Exception {
		// temp.delete();
	}

	@Test(expected = ConfigurationException.class)
	public void testLoadNonExistantFile() throws ConfigurationException {
		File file = new File("should_not_exist");
		file.delete();
		
		AbstractPersister<BasicConfiguration> persister = new SimpleXMLPersister<BasicConfiguration>(
				file);

		PersisterTestUtil.testReadWithoutWritePersister(persister);
	}

	@Test
	public void testLoadAndSave() throws ConfigurationException {

		AbstractPersister<BasicConfiguration> persister = new SimpleXMLPersister<BasicConfiguration>(
				temp);

		PersisterTestUtil.testPersister(persister);
	}

	@Test
	public void testSaveMultipleConfigs() throws ConfigurationException,
			IOException {

		AbstractPersister<BasicConfiguration> persister = new SimpleXMLPersister<BasicConfiguration>(
				temp);

		PersisterTestUtil.testPersisterMultipleConfigs(persister);
	}

	@Test
	public void testLoadAndSaveCustomConfiguration()
			throws ConfigurationException {


		AbstractPersister<CustomConfiguration> persister = new SimpleXMLPersister<CustomConfiguration>(
				temp);

		PersisterTestUtil.testCustomConfig(persister);
	}
}
