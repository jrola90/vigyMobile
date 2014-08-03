package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationDeleteException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class DeleteProfileInformationAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private ProfileInformationTransferObject profileInformationTransferObject;
	private VigymDialog vigymDialog;
	
	public DeleteProfileInformationAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			ProfileInformationTransferObject profileInformationTransferObject,
			VigymDialog vigymDialog
			) {
		super(activity, loadingSpinner);
		this.profileInformationTransferObject = profileInformationTransferObject;
		this.vigymDialog = vigymDialog;
	}

	@Override
	protected String doInBackground(Void... arg0) {

		try {
			ProfileInformationDAO profileInformationDAO = DAOFactory
					.createProfileInformationDAO(((VigymActivity) activity)
							.getDatabaseHelper());

			boolean ret = profileInformationDAO
					.deleteProfileInformation(this.profileInformationTransferObject);

			if (ret == false)
				return activity.getStringRes(R.string.error_delete_property) + ": "
				+ activity.getStringRes(R.string.error_delete_property);
			
		} catch (ProfileInformationDeleteException e) {
			return activity.getStringRes(R.string.error_delete_property) + ": "
					+ e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {
		super.onPostExecute(success);
		if (success == null) {

			this.vigymDialog.hide();
			Utils.showToast(activity.getContext(),
					activity.getStringRes(R.string.success_delete_property)
							.toString());
			activity.refresh();

		} else {
			Utils.showToast(activity.getContext(), success);
		}
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
		this.vigymDialog.hide();
	}

}
