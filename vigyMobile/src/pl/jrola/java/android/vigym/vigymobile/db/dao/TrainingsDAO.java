package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.DeleteTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetTrainingListException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateTrainingException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;

public interface TrainingsDAO {

	List<TransferObject> getTrainingList(Long userId) throws GetTrainingListException;

	void addTraining(TrainingTransferObject trainingTO) throws AddTrainingException;

	void updateTraining(TrainingTransferObject trainingTO) throws UpdateTrainingException;

	void delete(List<TrainingTransferObject> trainingList) throws DeleteTrainingException;

}
