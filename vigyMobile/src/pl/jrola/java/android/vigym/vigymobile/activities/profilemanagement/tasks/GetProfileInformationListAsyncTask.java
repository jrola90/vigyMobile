package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.ProfileBioFragmentActivity;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationGetListException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class GetProfileInformationListAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private UserTransferObject userTransferObject;
	private List<TransferObject> profileInformationTransferObjectList;

	public GetProfileInformationListAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner, UserTransferObject userTransferObject) {
		super(activity, loadingSpinner);
		this.userTransferObject = userTransferObject;
	}

	@Override
	protected String doInBackground(Void... arg0) {

		try {
			ProfileInformationDAO profileInformationDAO = DAOFactory
					.createProfileInformationDAO(((ProfileBioFragmentActivity) activity)
							.getDatabaseHelper());

			this.profileInformationTransferObjectList = profileInformationDAO
					.getProfileInformationList(userTransferObject.getUser_id());

		} catch (ProfileInformationGetListException e) {
			return e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(final String success) {

		if (success == null) {
			activity.refresh(profileInformationTransferObjectList);
		} else {
			Utils.showToast(activity.getContext(), success);
		}

		super.onPostExecute(success);
	}
}