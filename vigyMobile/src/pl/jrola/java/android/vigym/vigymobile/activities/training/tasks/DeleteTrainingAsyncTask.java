package pl.jrola.java.android.vigym.vigymobile.activities.training.tasks;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.TrainingsDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.DeleteTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class DeleteTrainingAsyncTask extends VigymAsyncTask<Void, Void, String> {

	private List<TrainingTransferObject> trainingList;
	private VigymDialog vigymDialog;

	public DeleteTrainingAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner,
			List<TrainingTransferObject> trainingList, VigymDialog vigymDialog) {
		super(activity, loadingSpinner);
		this.trainingList = trainingList;
		this.vigymDialog = vigymDialog;
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			TrainingsDAO trainingsDAO = DAOFactory.createTrainingsDAO(activity
					.getDatabaseHelper());
			trainingsDAO.delete(this.trainingList);
		} catch (DeleteTrainingException e) {
			return activity.getStringRes(R.string.error_delete_training)
					+ " : " + e.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {

		if (success == null) {
			((VigymForm) this.vigymDialog).clearForm();
			vigymDialog.hide();
			this.activity.refresh();
			Utils.showToast(activity.getContext(),
					activity.getStringRes(R.string.success_delete_training));
		} else {
			Utils.showToast(activity.getContext(), success);
		}

		super.onPostExecute(success);
	}

}