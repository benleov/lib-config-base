package lib.config.base.configuration;

import lib.config.base.configuration.impl.BasicConfiguration;

public class TestConfiguration extends BasicConfiguration {

	
	private String somethingElse;
	
	public TestConfiguration() {
		this.setId("Custom Unit test configuration");
	}
	
	public void doSomething() {
		//
	}
	
	public void setSomethingElse(String somethingElse) {
		this.somethingElse = somethingElse;
	}
	
}
