package pl.jrola.java.android.vigym.vigymobile.activities.training.validators;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;

public class CountValidator extends Validator {

	public CountValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		EditText valueEditText = (EditText) uiComponent;
		
		if (valueEditText != null) {
			String value = valueEditText.getText().toString().trim();
			if(TextUtils.isEmpty(value) == false)
				return true;
			else {
				valueEditText.setError(valueEditText.getContext().getString(R.string.field_cannot_be_empty));
			}
		}
		return false;
	}

}
