package lib.config.base.configuration;

import lib.config.base.configuration.impl.BasicConfiguration;

public class CustomConfiguration extends BasicConfiguration {

	private String testParam1, testParam2, testParam3, testParam4, testParam5;

	public CustomConfiguration() {
		this.setId("Custom Unit test configuration");
	}
	
	public String getTestParam1() {
		return testParam1;
	}

	public void setTestParam1(String testParam1) {
		this.testParam1 = testParam1;
	}

	public String getTestParam2() {
		return testParam2;
	}

	public void setTestParam2(String testParam2) {
		this.testParam2 = testParam2;
	}

	public String getTestParam3() {
		return testParam3;
	}

	public void setTestParam3(String testParam3) {
		this.testParam3 = testParam3;
	}

	public String getTestParam4() {
		return testParam4;
	}

	public void setTestParam4(String testParam4) {
		this.testParam4 = testParam4;
	}

	public String getTestParam5() {
		return testParam5;
	}

	public void setTestParam5(String testParam5) {
		this.testParam5 = testParam5;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((testParam1 == null) ? 0 : testParam1.hashCode());
		result = prime * result
				+ ((testParam2 == null) ? 0 : testParam2.hashCode());
		result = prime * result
				+ ((testParam3 == null) ? 0 : testParam3.hashCode());
		result = prime * result
				+ ((testParam4 == null) ? 0 : testParam4.hashCode());
		result = prime * result
				+ ((testParam5 == null) ? 0 : testParam5.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomConfiguration other = (CustomConfiguration) obj;
		if (testParam1 == null) {
			if (other.testParam1 != null)
				return false;
		} else if (!testParam1.equals(other.testParam1))
			return false;
		if (testParam2 == null) {
			if (other.testParam2 != null)
				return false;
		} else if (!testParam2.equals(other.testParam2))
			return false;
		if (testParam3 == null) {
			if (other.testParam3 != null)
				return false;
		} else if (!testParam3.equals(other.testParam3))
			return false;
		if (testParam4 == null) {
			if (other.testParam4 != null)
				return false;
		} else if (!testParam4.equals(other.testParam4))
			return false;
		if (testParam5 == null) {
			if (other.testParam5 != null)
				return false;
		} else if (!testParam5.equals(other.testParam5))
			return false;
		return true;
	}

}
