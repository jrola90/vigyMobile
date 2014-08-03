package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.ProfileInformationValueDialog;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationValuesDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class UpdateProfileInformationValueAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private ProfileInformationValueTransferObject profileInformationValueTO;
	private VigymDialog parentDialog;

	public UpdateProfileInformationValueAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			ProfileInformationValueTransferObject profileInformationValueTO,
			VigymDialog parentDialog) {
		super(activity, loadingSpinner);

		this.profileInformationValueTO = profileInformationValueTO;
		this.parentDialog = parentDialog;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			ProfileInformationValuesDAO profileInformationValuesDAO = DAOFactory
					.createProfileInformationValuesDAO(activity
							.getDatabaseHelper());

			profileInformationValuesDAO
					.updateProfileInformationValue(this.profileInformationValueTO);

		} catch (UpdateProfileInformationValueException e) {
			return activity.getStringRes(R.string.error_update_property_value)
					+ ": " + e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {
		super.onPostExecute(success);
		if (success == null) {

			Utils.showToast(
					activity.getContext(),
					activity.getStringRes(R.string.success_update_profile_value)
							.toString());
			((ProfileInformationValueDialog) this.parentDialog)
					.setSelectedProfileInformationValueTO(null);
			this.parentDialog.refresh();

		} else {
			Utils.showToast(activity.getContext(), success);
		}
	}

}
