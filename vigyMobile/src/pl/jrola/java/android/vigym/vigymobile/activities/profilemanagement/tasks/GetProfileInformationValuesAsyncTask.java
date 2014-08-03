package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ProfileInformationValuesDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UnitsDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitListGetException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class GetProfileInformationValuesAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private List<TransferObject> unitList;
	private List<TransferObject> profileInformationValueList;

	private ProfileInformationTransferObject profileInformationTO;

	public GetProfileInformationValuesAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			ProfileInformationTransferObject profileInformationTO) {
		super(activity, loadingSpinner);

		this.profileInformationTO = profileInformationTO;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			UnitsDAO unitDAO = DAOFactory.createUnitsDAO(activity
					.getDatabaseHelper());
			this.unitList = unitDAO.getUnitList();

			ProfileInformationValuesDAO profileInformationValuesDAO = DAOFactory
					.createProfileInformationValuesDAO(activity
							.getDatabaseHelper());
			this.profileInformationValueList = profileInformationValuesDAO
					.getProfileInformationValueList(this.profileInformationTO
							.getId());

		} catch (UnitListGetException e) {
			Utils.logError(e);
			return activity.getStringRes(R.string.error_get_unit_list) + ": "
					+ e.getMessage();
		} catch (GetProfileInformationValueException e) {
			Utils.logError(e);
			return activity.getStringRes(R.string.error_get_piv_list) + ": "
					+ e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {

		super.onPostExecute(success);

		if (success == null) {
			activity.refresh(this.unitList);
			activity.refresh(this.profileInformationValueList);
		} else {
			Utils.showToast(activity.getContext(), success);
		}
	}
}