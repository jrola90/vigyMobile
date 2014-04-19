package pl.jrola.java.android.vigym.vigymobile.activities;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAO;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AbstractVigymActivity {

	// form values
	String username = "";
	String password = "";
	String email = "";

	// UI references
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText mailEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		this.usernameEditText = (EditText) findViewById(R.id.usernameTextView);
		this.passwordEditText = (EditText) findViewById(R.id.passwordTextView);
		this.mailEditText = (EditText) findViewById(R.id.mailTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	/**
	 * Action on cancel button click.
	 * @param view view
	 */
	public void cancelButtonOnClick(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Action on register button click.
	 * @param view view
	 */
	public void registerButtonOnClick(View view) {

		// reset errors
		usernameEditText.setError(null);
		passwordEditText.setError(null);

		// get form values
		this.username = usernameEditText.getText().toString().trim();
		this.password = passwordEditText.getText().toString().trim();
		this.email = mailEditText.getText().toString().trim();

		// validate the form
		if (this.validateForm() == false)
			return;

		// create the profile
		UserTransferObject userTransferObject = new UserTransferObject();
		userTransferObject.setUser_mail(email);
		userTransferObject.setUser_nickname(username);
		userTransferObject.setUser_password(password);

		if (initDatabaseHelper() == true) {
			UsersDAO userDAO = new UsersDAO(databaseHelper);
			userTransferObject = userDAO.addUser(userTransferObject);

			if (userTransferObject != null) {
				Utils.showInfoMessage(
						getString(R.string.info_create_profile_ok), this);
				clearForm();
				// redirect to another activity
			} else {
				Utils.showErrorMessage(
						getString(R.string.error_create_profile), this);
			}
		} else {
			Utils.showErrorMessage(getString(R.string.error_opening_db_conn),
					this);
		}
	}

	protected void clearForm() {
		usernameEditText.setText("");
		passwordEditText.setText("");
		mailEditText.setText("");
	}

	/**
	 * Function makes a form validation.
	 * @return true if form is valid, otherwise false
	 */
	private boolean validateForm() {

		if (initDatabaseHelper() == true) {
			boolean cancel = false;

			if (TextUtils.isEmpty(username)) {
				usernameEditText.setError(getString(R.string.empty_username));
				cancel = true;
			}

			if (Utils.isUsernameVacant(username, databaseHelper) == false) {
				usernameEditText.setError(getString(R.string.username_exists));
				cancel = true;
			}

			if (Utils.isPasswordValid(password) == false) {
				passwordEditText.setError(getString(R.string.invalid_password));
				cancel = true;
			}

			if (TextUtils.isEmpty(email) == false) {
				if (Utils.isEmailVacant(email, databaseHelper) == false) {
					mailEditText.setError(getString(R.string.email_exists));
					cancel = true;
				}
			}

			if (TextUtils.isEmpty(email) == false
					&& Utils.isEmailValid(email) == false) {
				mailEditText.setError(getString(R.string.invalid_email));
				cancel = true;
			}

			if (cancel == true)
				return false;

			return true;
		} else {
			Utils.showErrorMessage(getString(R.string.error_opening_db_conn), this);
			return false;
		}
	}
}