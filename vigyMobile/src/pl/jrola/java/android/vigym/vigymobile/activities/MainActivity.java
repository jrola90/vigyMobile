package pl.jrola.java.android.vigym.vigymobile.activities;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Activity represents main menu of the application.
 * 
 */
public class MainActivity extends AbstractVigymActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Action on 'Login' button click.
	 * 
	 * @param view
	 *            view
	 */
	public void loginButtonOnClick(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	/**
	 * Action on 'Create profile' button click.
	 * 
	 * @param view
	 *            view
	 */
	public void createProfileButtonOnClick(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

	/**
	 * Action on 'Import' button click.
	 * 
	 * @param view
	 *            view
	 */
	public void importButtonOnClick(View view) {
		Intent intent = new Intent(this, ImportDataActivity.class);
		startActivity(intent);
	}

	/**
	 * Action on 'Exit' button click.
	 * 
	 * @param view
	 *            view
	 */
	public void exitButtonOnClick(View view) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	@Override
	protected void clearForm() {

	}
}