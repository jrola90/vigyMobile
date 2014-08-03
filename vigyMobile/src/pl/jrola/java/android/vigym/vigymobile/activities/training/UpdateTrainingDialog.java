package pl.jrola.java.android.vigym.vigymobile.activities.training;

import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import android.app.Activity;

public class UpdateTrainingDialog extends TrainingDialog {

	private TrainingTransferObject trainingTO;
	
	public UpdateTrainingDialog(Activity parentActivity, TrainingTransferObject trainingTO) {
		super(parentActivity);
		this.trainingTO = trainingTO;
	}

}
