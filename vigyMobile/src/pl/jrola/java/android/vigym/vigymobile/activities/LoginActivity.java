package pl.jrola.java.android.vigym.vigymobile.activities;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAO;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends AbstractVigymActivity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt
	private String username;
	private String password;

	// UI references
	private EditText usernameEditText;
	private EditText passwordEditText;
	private View loginFormView;
	private View loginStatusView;
	private TextView loginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		// gets references to UI elements
		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		loginFormView = findViewById(R.id.login_form);
		loginStatusView = findViewById(R.id.login_status);
		loginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			loginStatusView.setVisibility(View.VISIBLE);
			loginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			loginFormView.setVisibility(View.VISIBLE);
			loginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {

			if (initDatabaseHelper() == true) {

				// creates a new user object and adds it to database
				UsersDAO userDAO = new UsersDAO(databaseHelper);
				UserTransferObject userTransferObject = new UserTransferObject();
				userTransferObject.setUser_nickname(username);
				userTransferObject.setUser_password(Utils
						.generateSHA1Hash(password));

				List<UserTransferObject> userTransferObjectList = userDAO
						.getUsers(userTransferObject);

				if (userTransferObjectList.size() == 1) {
					return true;
				} else
					return false;
			} else {
				Utils.showErrorMessage(getString(R.string.error_opening_db_conn), LoginActivity.this);
				return false;
			}

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
//				Utils.showInfoMessage(getString(R.string.info_login_ok),
//						LoginActivity.this);
				clearForm();

				// move to another activity
				Intent intent = new Intent(LoginActivity.this, UserMainActivity.class);
				startActivity(intent);

			} else {
				passwordEditText
						.setError(getString(R.string.error_incorrect_password));
				passwordEditText.requestFocus();
			}

		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
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
	 * Function clears UI elements.
	 */
	public void clearForm() {
		usernameEditText.setText("");
		passwordEditText.setText("");
	}

	/**
	 * Function is fired after clicking 'Sign in' button.
	 * 
	 * @param view
	 *            view
	 */
	public void signInButtonOnClick(View view) {

		mAuthTask = new UserLoginTask();

		// Reset errors.
		usernameEditText.setError(null);
		passwordEditText.setError(null);

		// Store values at the time of the login attempt.
		username = usernameEditText.getText().toString().trim();
		password = passwordEditText.getText().toString().trim();

		if (TextUtils.isEmpty(username)) {
			usernameEditText.setError(getString(R.string.error_field_required));
			return;
		}

		// Show a progress spinner, and kick off a background task to
		// perform the user login attempt.
		loginStatusMessageView.setText(R.string.login_progress_signing_in);
		showProgress(true);
		mAuthTask.execute((Void) null);
	}
}