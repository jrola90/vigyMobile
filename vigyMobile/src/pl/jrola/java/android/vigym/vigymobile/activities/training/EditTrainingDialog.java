package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.activities.training.tasks.EditTrainingAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.Button;

public class EditTrainingDialog extends TrainingDialog {

	private TrainingTransferObject trainingTO;
	
	public EditTrainingDialog(Activity parentActivity,
			List<ExerciseTransferObject> exerciseList,
			TrainingTransferObject trainingTO) {
		super(parentActivity, exerciseList);

		this.exerciseSpinner.setEnabled(false);
		this.initForm(trainingTO);

		this.trainingTO = trainingTO;
		
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(DialogInterface dialogu) {

				Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						if (validate() == false)
							return;

						new EditTrainingAsyncTask(
								(VigymUIComponent) EditTrainingDialog.this.parentActivity,
								loadingSpinner,
								(TrainingTransferObject) submitForm(),
								EditTrainingDialog.this).execute();
					}
				});
			}
		});
	}

	@Override
	public TransferObject submitForm() {
		TrainingTransferObject trainingTO = (TrainingTransferObject) super.submitForm();
		trainingTO.setId(this.trainingTO.getId());
		trainingTO.setUserId(this.trainingTO.getUserId());
		return trainingTO;
	}
	
	@Override
	protected boolean[] getMultiCheckedItems() {
		return null;
	}

	@Override
	protected CharSequence[] getMultiChoiceItems() {
		return null;
	}

	@Override
	protected OnMultiChoiceClickListener getOnMultiChoiceClickListener() {
		return null;
	}

	@Override
	protected OnClickListener getOnSingleChoiceClickListener() {
		return null;
	}

	@Override
	protected int getCheckedItem() {
		return 0;
	}
}