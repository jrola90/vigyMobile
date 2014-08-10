package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetExerciseException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetExerciseListException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class ExercisesDAOImpl implements ExercisesDAO {

	private DbHelper databaseHelper;

	public ExercisesDAOImpl(DbHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	@Override
	public List<TransferObject> getExerciseList()
			throws GetExerciseListException {
		List<TransferObject> exerciseList = new ArrayList<TransferObject>();

		try {
			Cursor cursor = databaseHelper.query(false,
					DbUtils.DB_TABLE_EXERCISES, null, null, null, null, null,
					DbUtils.DB_COLUMN_EXERCISE_ID, null, null);

			while (cursor.moveToNext()) {
				exerciseList.add(new ExerciseTransferObject(cursor));
			}

		} catch (Exception e) {
			Utils.logError(e);
			throw new GetExerciseListException(e);
		}

		return exerciseList;
	}

	@Override
	public ExerciseTransferObject getExercise(Long id)
			throws GetExerciseException {

		try {
			Cursor cursor = databaseHelper.query(false,
					DbUtils.DB_TABLE_EXERCISES, null,
					DbUtils.DB_COLUMN_EXERCISE_ID + "=?",
					new String[] { id.toString() }, null, null, null, 1, null);

			while (cursor.moveToNext()) {
				return new ExerciseTransferObject(cursor);
			}

		} catch (Exception e) {
			throw new GetExerciseException(e);
		}
		return null;

	}

}