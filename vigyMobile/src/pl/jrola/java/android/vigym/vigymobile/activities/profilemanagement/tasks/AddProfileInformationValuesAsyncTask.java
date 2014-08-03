package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationValuesDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class AddProfileInformationValuesAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private ProfileInformationValueTransferObject profileInformationValueTO;

	public AddProfileInformationValuesAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			ProfileInformationValueTransferObject profileInformationValueTO) {
		super(activity, loadingSpinner);

		this.profileInformationValueTO = profileInformationValueTO;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			ProfileInformationValuesDAO profileInformationValueDAO = DAOFactory
					.createProfileInformationValuesDAO(activity
							.getDatabaseHelper());

			profileInformationValueDAO
					.addProfileInformationValue(this.profileInformationValueTO);

		} catch (AddProfileInformationValueException e) {
			return activity.getStringRes(R.string.error_add_property_value)
					+ ": " + e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {
		if (success == null) {

			Utils.showToast(activity.getContext(),
					activity.getStringRes(R.string.success_add_property_value)
							.toString());
			activity.refresh();

		} else {
			Utils.showToast(activity.getContext(), success);
		}

		super.onPostExecute(success);
	}
}