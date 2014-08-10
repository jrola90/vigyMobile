package pl.jrola.java.android.vigym.vigymobile.activities.register;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.main.MainActivity;
import pl.jrola.java.android.vigym.vigymobile.activities.register.tasks.RegisterAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.register.validator.EmailValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.register.validator.PasswordValiadtor;
import pl.jrola.java.android.vigym.vigymobile.activities.register.validator.UsernameValidator;
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

public class RegisterActivity extends VigymActivity implements VigymForm {

	// UI references
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText mailEditText;

	private ValidatorList validator;
	
	private VigymAsyncTask<Void, Void, String> addUserAsyncTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		this.usernameEditText = (EditText) findViewById(R.id.usernameTextView);
		this.passwordEditText = (EditText) findViewById(R.id.passwordTextView);
		this.mailEditText = (EditText) findViewById(R.id.mailTextView);
		
		this.loadingSpinner.setMessage(getString(R.string.register_in_progress));
		
		this.validator = new ValidatorList(getContext());
		this.validator.addValidator(new UsernameValidator(usernameEditText, this.databaseHelper));
		this.validator.addValidator(new PasswordValiadtor(passwordEditText));
		this.validator.addValidator(new EmailValidator(mailEditText));
	}

	/**
	 * Action on cancel button click.
	 * 
	 * @param view
	 *            view
	 */
	public void cancelButtonOnClick(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Action on register button click.
	 * 
	 * @param view
	 *            view
	 */
	public void registerButtonOnClick(View view) {
		this.clearFormErrors();

		if (this.validate() == false)
			return;
		
		loadingSpinner.start();
		
		addUserAsyncTask = new RegisterAsyncTask(this, this.loadingSpinner);
		addUserAsyncTask.execute();
	}

	@Override
	public void refresh() {

	}

	public void clearForm() {
		this.usernameEditText.setText("");
		this.passwordEditText.setText("");
		this.mailEditText.setText("");
	}
	
	@Override
	public void clearFormErrors() {
		this.usernameEditText.setError(null);
		this.passwordEditText.setError(null);
		this.mailEditText.setError(null);	
	}

	@Override
	public boolean validate() {
		return validator.validate();
	}

	@Override
	public TransferObject submitForm() {
		
		UserTransferObject userTO = new UserTransferObject();
		
		String username =  usernameEditText.getText().toString().trim();
		String password = passwordEditText.getText().toString().trim();
		String email = mailEditText.getText().toString().trim();
		
		userTO.setUser_nickname(username);
		userTO.setUser_password(Utils.generateSHA1Hash(password));
		userTO.setUser_mail(email);
		
		return userTO;
	}

	@Override
	public void initForm(TransferObject transferObject) {
		
	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		
	}
}