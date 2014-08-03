package pl.jrola.java.android.vigym.vigymobile.db.to;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * Base abstract class for every transfer ojbect.
 */
public abstract class TransferObject {

	/**
	 * Gets map with properties and values of the class. 
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public LinkedHashMap<String, String> getPropertiesMap()
			throws IllegalAccessException, IllegalArgumentException {

		LinkedHashMap<String, String> propertiesMap = new LinkedHashMap<String, String>();

		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object fieldVal = field.get(this);
			if (fieldVal != null) {
				propertiesMap.put(fieldName, fieldVal.toString());
			}
		}

		return propertiesMap;

	}

}
