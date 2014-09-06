package pl.jrola.java.android.vigym.vigymobile.activities.register.validators;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class EmailValidator extends Validator {

	public EmailValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		
		EditText emailEditText = (EditText)uiComponent;
		String email = emailEditText.getText().toString().trim();
		
		if (TextUtils.isEmpty(email) == false
				&& this.isEmailValid(email) == false) {
			emailEditText.setError(emailEditText.getContext().getString(R.string.invalid_email));
			return false;
		}
		
		return true;
	}
}
