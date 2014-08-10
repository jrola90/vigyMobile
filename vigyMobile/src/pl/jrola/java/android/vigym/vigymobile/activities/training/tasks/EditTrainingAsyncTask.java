package pl.jrola.java.android.vigym.vigymobile.activities.training.tasks;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.TrainingsDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class EditTrainingAsyncTask extends VigymAsyncTask<Void, Void, String> {

	private TrainingTransferObject trainingTO;
	private VigymDialog vigymDialog;

	public EditTrainingAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner, TrainingTransferObject trainingTO,
			VigymDialog vigymDialog) {
		super(activity, loadingSpinner);
		this.trainingTO = trainingTO;
		this.vigymDialog = vigymDialog;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {

			TrainingsDAO trainingDAO = DAOFactory.createTrainingsDAO(activity
					.getDatabaseHelper());
			trainingDAO.updateTraining(this.trainingTO);

		} catch (UpdateTrainingException e) {
			return activity.getStringRes(R.string.error_delete_training)
					+ " : " + e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {

		if (success == null) {
			((VigymForm)this.vigymDialog).clearForm();
			vigymDialog.hide();
			this.activity.refresh();
			Utils.showToast(this.activity.getContext(),
					this.activity.getStringRes(R.string.success_edit_training));
		} else {
			Utils.showToast(this.activity.getContext(), success);
		}

		super.onPostExecute(success);
	}

}
