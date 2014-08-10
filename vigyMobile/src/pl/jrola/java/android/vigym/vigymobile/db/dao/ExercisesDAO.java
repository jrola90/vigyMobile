package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetExerciseException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetExerciseListException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;

public interface ExercisesDAO {

	List<TransferObject> getExerciseList() throws GetExerciseListException;
	ExerciseTransferObject getExercise(Long id) throws GetExerciseException;

}
