package pl.jrola.java.android.vigym.vigymobile.activities.training.tasks;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.ExercisesDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.TrainingsDAO;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetExerciseListException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetTrainingListException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

public class GetTrainingListAsyncTask extends
		VigymAsyncTask<Void, Void, String> {

	private List<TransferObject> trainingListTO;
	private List<TransferObject> exerciseList;

	public GetTrainingListAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner) {
		super(activity, loadingSpinner);
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			ExercisesDAO exercisesDAO = DAOFactory
					.createExercisesDAO(this.activity.getDatabaseHelper());
			this.exerciseList = exercisesDAO.getExerciseList();

			TrainingsDAO trainingsDAO = DAOFactory
					.createTrainingsDAO(this.activity.getDatabaseHelper());
			this.trainingListTO = trainingsDAO
					.getTrainingList(LoggedUserSingleton
							.getUserTransferObject().getId());

		} catch (GetExerciseListException gelException) {
			return activity.getStringRes(R.string.error_get_exercise_list)
					+ " : " + gelException.getMessage();
		} catch (GetTrainingListException gtle) {
			return activity.getStringRes(R.string.error_get_trainings_list)
					+ " : " + gtle.getMessage();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String success) {

		if (success == null) {
			activity.refresh(this.exerciseList);
			activity.refresh(this.trainingListTO);
		} else {
			Utils.showToast(this.activity.getContext(), success);
		}

		super.onPostExecute(success);
	}
}