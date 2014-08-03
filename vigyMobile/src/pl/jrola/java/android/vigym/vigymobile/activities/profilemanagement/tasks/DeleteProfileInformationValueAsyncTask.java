package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.ProfileInformationValueDialog;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DeleteProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationValuesDAO;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class DeleteProfileInformationValueAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private ProfileInformationValueTransferObject profileInformationValueTO;
	private VigymDialog confirmDialog;
	private VigymDialog parentDialog;

	public DeleteProfileInformationValueAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			ProfileInformationValueTransferObject profileInformationValueTO,
			VigymDialog parentDialog, VigymDialog confirmDialog) {
		super(activity, loadingSpinner);
		this.profileInformationValueTO = profileInformationValueTO;
		this.confirmDialog = confirmDialog;
		this.parentDialog = parentDialog;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			ProfileInformationValuesDAO profileInformationValuesDAO = DAOFactory
					.createProfileInformationValuesDAO(activity
							.getDatabaseHelper());

			boolean ret = profileInformationValuesDAO
					.deleteProfileInformationValue(this.profileInformationValueTO);

			if (ret == false)
				return activity
						.getStringRes(R.string.error_delete_profile_value);

		} catch (DeleteProfileInformationValueException e) {
			return activity.getStringRes(R.string.error_delete_profile_value)
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
					activity.getStringRes(R.string.success_delete_profile_value)
							.toString());
			this.confirmDialog.hide();
			((ProfileInformationValueDialog) this.parentDialog)
					.setSelectedProfileInformationValueTO(null);
			this.parentDialog.refresh();

		} else {
			Utils.showToast(activity.getContext(), success);
		}
	}

}