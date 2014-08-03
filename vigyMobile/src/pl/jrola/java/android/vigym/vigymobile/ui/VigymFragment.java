package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;

public abstract class VigymFragment extends Fragment implements VigymUIComponent {

	protected DbHelper databaseHelper;
	protected LoadingSpinner loadingSpinner;
	
	public VigymFragment() {
		
	}

	public DbHelper getDatabaseHelper() {
		return databaseHelper;
	}

	public LoadingSpinner getLoadingSpinner() {
		return loadingSpinner;
	}

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.loadingSpinner = new LoadingSpinner(getActivity());
		this.databaseHelper = new DbHelper(getActivity());
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
	
	public Context getContext() {
		return super.getActivity();
	}
}