package lib.config.base.configuration;

import java.io.File;
import java.util.List;

import org.simpleframework.xml.core.Persister;

public class ConfigurationPersister<E extends Configuration> {

	
	public void save(String filename, E... config) 
			throws ConfigurationException {
		Persister serializer = new Persister();
		File result = new File(filename);

		try {
			serializer.write(new ConfigurationList<E>(config), result);
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
	public E load(String filename) throws ConfigurationException {
		return loadAll(filename).get(0);
	}
	public List<E> loadAll(String filename) throws ConfigurationException {
		Persister serializer = new Persister();
		File toLoad = new File(filename);
		try {
			ConfigurationList<E> list = serializer.read(ConfigurationList.class, toLoad);
			return list.getConfigurations();
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
	
	
	
}
