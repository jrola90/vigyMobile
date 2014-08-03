package pl.jrola.java.android.vigym.vigymobile.test.db.to;

import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class AbstractTransferObjectTest extends ActivityTestCase {

	@SmallTest
	public void testClassReturnAllPropertiesWithValues() throws Exception {

		String emptyProperty = "emptyProperty";
		String propertyString = "propertyString";
		String propertyIntPrimitive = "propertyIntPrimitive";
		String doubleObject = "doubleObject";
		String boolValuePrimitive = "boolValuePrimitive";
		String charValue = "charValue";
		
		TestClass testClass = new TestClass();
		Map<String, String> properties = testClass.getPropertiesMap();

		assertEquals(testClass.getEmptyProperty(), properties.get(emptyProperty));
		assertEquals(testClass.getPropertyString().toString(),
				properties.get(propertyString));
		assertEquals(Integer.toString(testClass.getPropertyIntPrimitive()),
				properties.get(propertyIntPrimitive));
		assertEquals(testClass.getDoubleObject().toString(),
				properties.get(doubleObject));
		assertEquals(Boolean.toString(testClass.isBoolValuePrimitive()),
				properties.get(boolValuePrimitive));
		assertEquals(testClass.getCharValue().toString(),
				properties.get(charValue));

	}

}

class TestClass extends TransferObject {

	private String emptyProperty;
	private String propertyString = "property_String_value";
	private int propertyIntPrimitive = 1;
	private Double doubleObject = 3.14;
	private boolean boolValuePrimitive = true;
	private Character charValue = Character.valueOf('h');

	public String getEmptyProperty() {
		return emptyProperty;
	}

	public void setEmptyProperty(String emptyProperty) {
		this.emptyProperty = emptyProperty;
	}

	public String getPropertyString() {
		return propertyString;
	}

	public void setPropertyString(String propertyString) {
		this.propertyString = propertyString;
	}

	public int getPropertyIntPrimitive() {
		return propertyIntPrimitive;
	}

	public void setPropertyIntPrimitive(int propertyIntPrimitive) {
		this.propertyIntPrimitive = propertyIntPrimitive;
	}

	public Double getDoubleObject() {
		return doubleObject;
	}

	public void setDoubleObject(Double doubleObject) {
		this.doubleObject = doubleObject;
	}

	public boolean isBoolValuePrimitive() {
		return boolValuePrimitive;
	}

	public void setBoolValuePrimitive(boolean boolValuePrimitive) {
		this.boolValuePrimitive = boolValuePrimitive;
	}

	public Character getCharValue() {
		return charValue;
	}

	public void setCharValue(Character charValue) {
		this.charValue = charValue;
	}

}
