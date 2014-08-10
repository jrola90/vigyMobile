package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.activities.training.tasks.AddTrainingAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.Button;

public class AddTrainingDialog extends TrainingDialog {

	public AddTrainingDialog(Activity parentActivity,
			List<ExerciseTransferObject> exerciseList) {
		super(parentActivity, exerciseList);

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(DialogInterface dialogu) {

				Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						if (validate() == false)
							return;

						new AddTrainingAsyncTask(
								(VigymUIComponent) AddTrainingDialog.this.parentActivity,
								loadingSpinner, AddTrainingDialog.this)
								.execute();

					}
				});
			}
		});
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
