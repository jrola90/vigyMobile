package pl.jrola.java.android.vigym.vigymobile.activities.login;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.login.tasks.LoginAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.login.validators.LoginValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.main.MainActivity;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.validator.ValidatorList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity which displays a login screen to the user, offering registration
 */
public class LoginActivity extends VigymActivity implements VigymForm {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private VigymAsyncTask<Void, Void, String> userLoginAsyncTask;

	// UI references
	private EditText usernameEditText;
	private EditText passwordEditText;

	private ValidatorList validator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// gets references to UI elements
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

		this.loadingSpinner.setMessage(getString(R.string.login_progress_signing_in));
		
		validator = new ValidatorList(getContext());
		validator.addValidator(new LoginValidator(usernameEditText));
	}

	@Override
	public TransferObject submitForm() {
		UserTransferObject uto = new UserTransferObject();
		uto.setUser_nickname(usernameEditText.getText().toString().trim());
		uto.setUser_password(Utils.generateSHA1Hash(passwordEditText.getText()
				.toString().trim()));
		return uto;
	}
	
	@Override
	public void clearForm() {
		usernameEditText.setText("");
		passwordEditText.setText("");
	}

	@Override
	public void clearFormErrors() {
		usernameEditText.setError(null);
		passwordEditText.setError(null);
	}

	/**
	 * Function is called after clicking on cancel button.
	 * 
	 * @param view
	 *            view
	 */
	public void cancelButtonOnClick(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Function is fired after clicking 'Sign in' button.
	 * 
	 * @param view
	 *            view
	 */
	public void signInButtonOnClick(View view) {
		this.clearFormErrors();

		if (this.validate() == false)
			return;

		loadingSpinner.start();

		userLoginAsyncTask = new LoginAsyncTask(this, this.loadingSpinner);
		userLoginAsyncTask.execute();
	}

	@Override
	public boolean validate() {
		return this.validator.validate();
	}

	@Override
	public void initForm(TransferObject transferObject) {

	}

	@Override
	public void refresh() {

	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		// TODO Auto-generated method stub
		
	}
}