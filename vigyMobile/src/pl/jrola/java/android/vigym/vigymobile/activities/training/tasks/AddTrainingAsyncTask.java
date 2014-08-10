package pl.jrola.java.android.vigym.vigymobile.activities.training.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.TrainingsDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class AddTrainingAsyncTask extends VigymAsyncTask<Void, Void, String> {

	private VigymDialog parentDialog;

	public AddTrainingAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner, VigymDialog parentDialog) {
		super(activity, loadingSpinner);
		this.parentDialog = parentDialog;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			TrainingTransferObject trainingTO = (TrainingTransferObject) ((VigymForm) parentDialog)
					.submitForm();
			TrainingsDAO trainingDAO = DAOFactory.createTrainingsDAO(activity
					.getDatabaseHelper());
			trainingDAO.addTraining(trainingTO);
		} catch (AddTrainingException adE) {
			return activity.getStringRes(R.string.error_add_training) + " : "
					+ adE.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {
		super.onPostExecute(success);

		if (success == null) {
			parentDialog.hide();
			((VigymForm) parentDialog).clearForm();
			((VigymForm) parentDialog).clearFormErrors();
			this.activity.refresh();
			Utils.showToast(activity.getContext(),
					activity.getStringRes(R.string.success_add_training)
							.toString());
		} else {
			Utils.showToast(activity.getContext(), success);
		}

	}
}