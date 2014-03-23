lib_config_base
==================================================

lib_config_base provides an abstraction to software configuration, allowing Java applications to access settings without being concerned with the underlying file format. See [lib_config_web](https://github.com/benleov/lib_config_web) for an example that provides a CRUD Web interface. See below for an example that uses the code directly, or check out the JUnit tests.

Current supported formats are:

* Windows INI
* XML
* Mongo DB

Dependences:
--------------------------------------

Compile:

* [SLF4J](http://www.slf4j.org/‎) 
* [Simple-XML](http://simple.sourceforge.net/) 
* [Logback](http://logback.qos.ch/)
* [Ini4j](http://ini4j.sourceforge.net)
* [Stax API](http://stax.codehaus.org/)
* [XPP3](http://www.xmlpull.org/)
* [Mongo DB](https://github.com/mongodb/mongo-java-driver)

Testing:

* [JUnit](http://junit.org/)

Use the gradle task "copyToLib" to download all dependences, and see the gradle.build file for the required versions.

Usage
--------------------------------------

```java
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
```
