package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.DeleteTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetTrainingListException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.content.ContentValues;
import android.database.Cursor;

public class TrainingsDAOImpl implements TrainingsDAO {

	private DbHelper databaseHelper;

	public TrainingsDAOImpl(DbHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	@Override
	public List<TransferObject> getTrainingList(Long userId)
			throws GetTrainingListException {
		List<TransferObject> trainingList = new ArrayList<TransferObject>();

		try {

			Cursor cursor = databaseHelper.query(false,
					DbUtils.DB_TABLE_TRAININGS, null, null, null, null, null,
					DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID, null, null);

			while (cursor.moveToNext()) {
				TrainingTransferObject trainingTO = new TrainingTransferObject(
						cursor);

				Long exerciseId = trainingTO.getExerciseId().getId();
				ExercisesDAO exerciseDAO = DAOFactory
						.createExercisesDAO(databaseHelper);
				ExerciseTransferObject exerciseTO = exerciseDAO
						.getExercise(exerciseId);
				trainingTO.setExerciseId(exerciseTO);

				trainingList.add(trainingTO);
			}

		} catch (Exception e) {
			Utils.logError(e);
			throw new GetTrainingListException(e);
		}

		return trainingList;
	}

	@Override
	public void addTraining(TrainingTransferObject trainingTO)
			throws AddTrainingException {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(DbUtils.DB_COLUMN_TRAINING_COUNT,
					trainingTO.getCount());
			contentValues.put(DbUtils.DB_COLUMN_TRAINING_VALUE,
					trainingTO.getValue());
			contentValues.put(DbUtils.DB_COLUMN_TRAINING_DATE,
					trainingTO.getDateAsString());
			contentValues.put(DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID,
					trainingTO.getExerciseId().getId());
			contentValues.put(DbUtils.DB_COLUMN_TRAINING_USER_ID, trainingTO
					.getUserId().getId());

			databaseHelper.insert(DbUtils.DB_TABLE_TRAININGS, null,
					contentValues);

		} catch (Exception e) {
			throw new AddTrainingException(e);
		}
	}

	@Override
	public void updateTraining(TrainingTransferObject trainingTO)
			throws UpdateTrainingException {

		ContentValues cv = new ContentValues();
		cv.put(DbUtils.DB_COLUMN_TRAINING_COUNT, trainingTO.getCount());
		cv.put(DbUtils.DB_COLUMN_TRAINING_VALUE, trainingTO.getValue());
		cv.put(DbUtils.DB_COLUMN_TRAINING_DATE, trainingTO.getDateAsString());
		cv.put(DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID, trainingTO
				.getExerciseId().getId());

		try {
			databaseHelper.update(DbUtils.DB_TABLE_TRAININGS, cv,
					DbUtils.DB_COLUMN_TRAINING_ID + "= ?",
					new String[] { trainingTO.getId().toString() });
		} catch (Exception e) {
			Utils.logError(e);
			throw new UpdateTrainingException(e);
		}
	}

	@Override
	public void delete(List<TrainingTransferObject> trainingList)
			throws DeleteTrainingException {

		try {
			for (TrainingTransferObject tto : trainingList) {

				databaseHelper.delete(DbUtils.DB_TABLE_TRAININGS,
						DbUtils.DB_COLUMN_TRAINING_ID + "=?",
						new String[] { tto.getId().toString() });

			}
		} catch (Exception e) {
			throw new DeleteTrainingException(e);
		}

	}
}