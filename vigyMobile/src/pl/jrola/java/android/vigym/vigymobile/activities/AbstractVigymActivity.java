package pl.jrola.java.android.vigym.vigymobile.activities;

import pl.jrola.java.android.vigym.vigymobile.utils.DatabaseHelper;
import android.app.Activity;

/**
 * Main activity. All activities in this app should inherit from that abstract
 * class.
 * 
 */
public abstract class AbstractVigymActivity extends Activity {

	protected DatabaseHelper databaseHelper;

	/**
	 * Inits database connection.
	 */
	public boolean initDatabaseHelper() {
		if (this.databaseHelper == null) {
			databaseHelper = new DatabaseHelper(getApplicationContext());
			if (this.databaseHelper.open() != null)
				return true;
			else
				return false;

		}
		return true;
	}

	protected abstract void clearForm();
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		if (this.databaseHelper != null)
			this.databaseHelper.close();
	}

}