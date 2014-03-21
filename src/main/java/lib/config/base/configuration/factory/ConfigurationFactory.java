package lib.config.base.configuration.factory;

public interface ConfigurationFactory<E> {

	E buildConfiguration(String id);
}
