package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Main activity. All activities in this app should inherit from that abstract
 * class.
 * 
 */
public abstract class VigymActivity extends Activity implements
		VigymUIComponent {

	protected DbHelper databaseHelper;
	protected LoadingSpinner loadingSpinner;

	public VigymActivity() {

	}

	public DbHelper getDatabaseHelper() {
		return databaseHelper;
	}

	public LoadingSpinner getLoadingSpinner() {
		return loadingSpinner;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.loadingSpinner = new LoadingSpinner(this);
		this.databaseHelper = new DbHelper(getApplicationContext());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		this.databaseHelper.releaseDatabaseHelper();
	}

	@Override
	public String getStringRes(int resId) {
		return getString(resId);
	}

	@Override
	public Context getContext() {
		return super.getApplicationContext();
	}
}