package lib.config.base.configuration.factory.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;

import lib.config.base.configuration.Configuration;
import lib.config.base.configuration.factory.ConfigurationFactory;

public class ReflectionConfigurationFactory<E extends Configuration> implements ConfigurationFactory<E> {

	public E buildConfiguration(String name) {
		Class<E> clazz = getTypeParameterClass();

		try {
			Constructor<E> constructor = clazz.getConstructor();
			return constructor.newInstance();

		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	private Class<E> getTypeParameterClass() {
		
		Class<E> clazz = (Class<E>) getClass();
		TypeVariable<Class<E>>[] type = clazz.getTypeParameters();
		return (Class<E>) type[0].getClass();
	}
}
