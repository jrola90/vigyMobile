package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.training.tasks.DeleteTrainingAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.MultipleSelectionValidator;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.Button;

public class DeleteTrainingConfirmDialog extends TrainingConfirmDialog {

	private List<TrainingTransferObject> selectedItems = new ArrayList<TrainingTransferObject>();
	private OnMultiChoiceClickListener onMultiChoiceClickListener;

	public DeleteTrainingConfirmDialog(Activity parentActivity,
			TrainingItemModel trainingsItemModel) {
		super(parentActivity, trainingsItemModel);

		this.onMultiChoiceClickListener = new OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which,
					boolean isChecked) {

				TrainingTransferObject selectedItem = DeleteTrainingConfirmDialog.this.trainingsItemModel
						.getTrainingList().get(which);

				if (isChecked) {
					selectedItems.add(selectedItem);
				} else if (selectedItems.contains(selectedItem)) {
					selectedItems.remove(selectedItem);
				}

				validator.removeAll();
				validator.addValidator(new MultipleSelectionValidator(
						DeleteTrainingConfirmDialog.this.parentActivity
								.getCurrentFocus(), selectedItems));
			}
		};

		this.dialogBuilder.setMultiChoiceItems(getMultiChoiceItems(),
				getMultiCheckedItems(), getOnMultiChoiceClickListener());

		this.validator.addValidator(new MultipleSelectionValidator(
				DeleteTrainingConfirmDialog.this.parentActivity
						.getCurrentFocus(), selectedItems));

	}

	@Override
	public void clearForm() {
		this.selectedItems.clear();
	}

	@Override
	protected int getTitle() {
		return R.string.training_delete;
	}

	@Override
	protected OnClickListener getPositiveButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		};
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		};
	}

	@Override
	public void show() {
		dialog = dialogBuilder.create();

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(DialogInterface dialogu) {

				Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						if (validate() == false)
							return;

						hide();

						new DeleteTrainingAsyncTask(
								(VigymUIComponent) parentActivity,
								loadingSpinner, selectedItems,
								DeleteTrainingConfirmDialog.this).execute();

					}
				});
			}
		});

		dialog.show();
	}

	@Override
	protected OnClickListener getOnSingleChoiceClickListener() {
		return null;
	}

	@Override
	protected int getCheckedItem() {
		return 0;
	}

	@Override
	protected boolean[] getMultiCheckedItems() {
		return null;
	}

	@Override
	protected CharSequence[] getMultiChoiceItems() {
		return getItems();
	}

	@Override
	protected OnMultiChoiceClickListener getOnMultiChoiceClickListener() {
		return this.onMultiChoiceClickListener;
	}

}