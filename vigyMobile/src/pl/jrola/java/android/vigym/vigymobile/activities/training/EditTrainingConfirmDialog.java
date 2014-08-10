package pl.jrola.java.android.vigym.vigymobile.activities.training;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.SingleSelectionValidator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.Button;

public class EditTrainingConfirmDialog extends TrainingConfirmDialog {

	private OnClickListener onClickListener = null;
	private Integer selectedItem = null;

	public EditTrainingConfirmDialog(Activity parentActivity,
			TrainingItemModel trainingsItemModel) {
		super(parentActivity, trainingsItemModel);

		this.onClickListener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedItem = which;
				validator.removeAll();
				validator.addValidator(new SingleSelectionValidator(
						EditTrainingConfirmDialog.this.parentActivity
								.getCurrentFocus(), selectedItem));
			}
		};

		dialogBuilder.setSingleChoiceItems(getItems(), getCheckedItem(),
				getOnSingleChoiceClickListener());

		validator
				.addValidator(new SingleSelectionValidator(
						EditTrainingConfirmDialog.this.parentActivity
								.getCurrentFocus(), selectedItem));

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

						new EditTrainingDialog(parentActivity, exerciseList,
								trainingsItemModel.getTrainingList().get(
										selectedItem)).show();

					}
				});
			}
		});

		dialog.show();
	}

	@Override
	protected boolean[] getMultiCheckedItems() {
		return null;
	}

	@Override
	protected CharSequence[] getMultiChoiceItems() {
		return this.getItems();
	}

	@Override
	protected OnMultiChoiceClickListener getOnMultiChoiceClickListener() {
		return null;
	}

	@Override
	protected OnClickListener getOnSingleChoiceClickListener() {
		return onClickListener;
	}

	@Override
	protected int getCheckedItem() {
		return -1;
	}

	@Override
	protected int getTitle() {
		return R.string.training_edit;
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
	public void clearForm() {
		this.selectedItem = null;
	}
}