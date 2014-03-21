package lib.config.base.configuration.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lib.config.base.configuration.Configuration;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root
public class BasicConfiguration implements Configuration {

	@Attribute
	private String id;

	@ElementMap(entry = "property", key = "key", attribute = true, inline = true)
	private Map<String, String> map;

	public BasicConfiguration() {
		map = new HashMap<String, String>();
	}

	public void setProperty(String key, String value) {
		map.put(key, value);
	}

	public String getProperty(String key) {
		return map.get(key);
	}

	public Set<String> getKeys() {
		return Collections.unmodifiableSet(map.keySet());
	}

	@Override
	public boolean removeProperty(String key) {
		return map.remove(key) != null;
	}

	public String getProperty(String key, String defaultValue) {
		String value = map.get(key);

		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("config[id=" + id);
		builder.append(" properties=(");

		for (String key : map.keySet()) {
			builder.append("[");
			builder.append(key);
			builder.append("=");
			builder.append(map.get(key));
			builder.append("] ");
		}

		builder.append(")]");
		return builder.toString();
	}

}
