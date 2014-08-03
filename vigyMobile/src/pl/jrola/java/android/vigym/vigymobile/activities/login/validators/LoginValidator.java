package pl.jrola.java.android.vigym.vigymobile.activities.login.validators;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class LoginValidator extends Validator {

	public LoginValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {

		EditText usernameEditText = (EditText) this.uiComponent;
		String username = usernameEditText.getText().toString().trim();
		
		if (TextUtils.isEmpty(username)) {
			usernameEditText.setError(uiComponent.getContext().getString(R.string.error_field_required));
			return false;
		}
		
		return true;
	}
}