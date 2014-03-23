lib_config_base
==================================================

lib_config_base provides an object orientated abstraction to software configuration, to allow plugins to provide access, without being concerned about the underlying format. 

Dependences:
--------------------------------------

Use the gradle task "copyToLib" to download all dependences.

Compile:

* [SLF4J](www.slf4j.org/‎) 
* [Simple-XML](http://simple.sourceforge.net/) 
* [Logback](http://logback.qos.ch/)
* [Ini4j](ini4j.sourceforge.net)
* [Stax API] (http://stax.codehaus.org/)
* [XPP3] (http://www.xmlpull.org/)
* [Mongo DB] (https://github.com/mongodb/mongo-java-driver)

Testing:

* [JUnit](http://junit.org/)

See the gradle.build file for the required versions.

Usage
--------------------------------------

'''java
		final String FILE_NAME = "my_settings.ini";
		
		File temp = new File(FILE_NAME);
		
		IniPersister<BasicConfiguration> persister = new IniPersister<BasicConfiguration>(
				new ConfigurationFactory<BasicConfiguration>() {

					@Override
					public BasicConfiguration buildConfiguration(String id) {
						return new BasicConfiguration(id);
					}
				}, temp);

		// create some new settings
		BasicConfiguration savedConfig = new BasicConfiguration();

		savedConfig.setId("test_config");
		savedConfig.setProperty("some_key", "some_value");

		ConfigurationList<BasicConfiguration> savedList = new ConfigurationList<BasicConfiguration>();
		savedList.setConfigurations(savedConfig);

		// write them to disk
		persister.write(savedList);

		// load the saved settings back
		ConfigurationList<BasicConfiguration> loadedList = persister.read();
		BasicConfiguration loadedConfig = loadedList.getConfigurations().get(0);
'''
