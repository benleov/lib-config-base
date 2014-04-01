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

	public BasicConfiguration(String id) {
		this();
		setId(id);
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

	@Override
	public boolean hasProperty(String key) {
		return map.containsKey(key);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		BasicConfiguration other = (BasicConfiguration) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}

}
