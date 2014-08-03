package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator;

import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.view.View;

public class PropertyDescValidator extends Validator {

	public PropertyDescValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		return true;
	}

}
