package pl.jrola.java.android.vigym.vigymobile.activities.training;

import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;

public class GetTrainingListAsyncTask extends VigymAsyncTask<Void, Void, String> {

	public GetTrainingListAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner) {
		super(activity, loadingSpinner);
	}

	@Override
	protected String doInBackground(Void... params) {
		return null;
	}

	@Override
	protected void onPostExecute(String success) {
		super.onPostExecute(success);
	}
}