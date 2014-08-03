package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationUpdateException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class UpdateProfileInformationAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	public UpdateProfileInformationAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner, ProfileInformationTransferObject profileInformationTransferObject) {
		super(activity, loadingSpinner);
		this.profileInformationTransferObject = profileInformationTransferObject;
	}

	private ProfileInformationTransferObject profileInformationTransferObject;

	@Override
	protected String doInBackground(Void... params) {
		try {
			ProfileInformationDAO profileInformationDAO = DAOFactory
					.createProfileInformationDAO(((VigymActivity) activity)
							.getDatabaseHelper());

			profileInformationDAO
					.updateProfileInformation(profileInformationTransferObject);

		} catch (ProfileInformationUpdateException e) {
			return activity.getStringRes(R.string.error_update_property) + ": " + e.getMessage();
		}

		return null;

	}

	@Override
	protected void onPostExecute(final String success) {

		if (success == null) {

			Utils.showToast(activity.getContext(),
					activity.getStringRes(R.string.success_update_property)
							.toString());
			activity.refresh();

		} else {
			Utils.showToast(activity.getContext(), success);
		}

		super.onPostExecute(success);
	}

	public ProfileInformationTransferObject getProfileInformationTransferObject() {
		return profileInformationTransferObject;
	}

	public void setProfileInformationTransferObject(
			ProfileInformationTransferObject profileInformationTransferObject) {
		this.profileInformationTransferObject = profileInformationTransferObject;
	}

}