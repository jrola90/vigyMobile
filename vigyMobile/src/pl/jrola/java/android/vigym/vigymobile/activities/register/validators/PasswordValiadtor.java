package pl.jrola.java.android.vigym.vigymobile.activities.register.validators;

import android.view.View;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;

public class PasswordValiadtor extends Validator {

	public PasswordValiadtor(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		return true;
	}

}
