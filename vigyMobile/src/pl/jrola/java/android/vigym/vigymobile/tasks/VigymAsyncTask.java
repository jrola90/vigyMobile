package pl.jrola.java.android.vigym.vigymobile.tasks;

import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.os.AsyncTask;

public abstract class VigymAsyncTask<T1, T2, T3> extends AsyncTask<T1, T2, T3> {

	protected VigymUIComponent activity;
	protected LoadingSpinner loadingSpinner; 
	
	public VigymAsyncTask(VigymUIComponent activity, LoadingSpinner loadingSpinner) {
		super();
		this.activity = activity;
		this.loadingSpinner = loadingSpinner;
	}
	
	public VigymAsyncTask(VigymUIComponent activity) {
		this(activity, null);
	}

	public VigymAsyncTask(LoadingSpinner loadingSpinner) {
		this(null, loadingSpinner);
	}
	
	public VigymUIComponent getActivity() {
		return activity;
	}

	public void setActivity(VigymUIComponent activity) {
		this.activity = activity;
	}

	public LoadingSpinner getLoadingSpinner() {
		return loadingSpinner;
	}

	public void setLoadingSpinner(LoadingSpinner loadingSpinner) {
		this.loadingSpinner = loadingSpinner;
	}
	
	@Override
	protected void onPostExecute(final T3 success) {
		loadingSpinner.stop();
	}

	@Override
	protected void onCancelled() {
		loadingSpinner.stop();
	}
}