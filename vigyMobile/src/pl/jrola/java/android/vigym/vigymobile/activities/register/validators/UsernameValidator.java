package pl.jrola.java.android.vigym.vigymobile.activities.register.validators;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class UsernameValidator extends Validator {

	private DbHelper databaseHelper;

	public UsernameValidator(View uiComponent, DbHelper databaseHelper) {
		super(uiComponent);
		this.databaseHelper = databaseHelper;
	}

	@Override
	public boolean validate() {

		EditText usernameEditText = (EditText) uiComponent;
		String username = usernameEditText.getText().toString().trim();

		if (TextUtils.isEmpty(username)) {
			usernameEditText.setError(uiComponent.getContext().getString(
					R.string.empty_username));
			return false;
		}

		try {
			if (isUsernameVacant(username, this.databaseHelper) == false) {
				usernameEditText.setError(uiComponent.getContext().getString(
						R.string.username_exists));
				return false;
			}
		} catch (DatabaseHelperInitialiseException e) {
			Utils.logError(e);
			usernameEditText.setError(uiComponent.getContext().getString(
					R.string.error_create_profile)
					+ " : " + e.getMessage());
			return false;
		}

		return true;
	}
}