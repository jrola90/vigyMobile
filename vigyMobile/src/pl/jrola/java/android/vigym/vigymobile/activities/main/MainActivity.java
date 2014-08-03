package pl.jrola.java.android.vigym.vigymobile.activities.main;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.importdata.ImportDataActivity;
import pl.jrola.java.android.vigym.vigymobile.activities.login.LoginActivity;
import pl.jrola.java.android.vigym.vigymobile.activities.register.RegisterActivity;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Activity represents main menu of the application.
 * 
 */
public class MainActivity extends VigymActivity {

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
		startActivity(Utils.closeApp());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		// TODO Auto-generated method stub
		
	}

}