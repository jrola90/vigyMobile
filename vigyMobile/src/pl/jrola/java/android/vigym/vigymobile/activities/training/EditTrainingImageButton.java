package pl.jrola.java.android.vigym.vigymobile.activities.training;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class EditTrainingImageButton extends TrainingImageButton {

	private EditTrainingConfirmDialog editDialog;

	public EditTrainingImageButton(Context context) {
		super(context);
	}

	public EditTrainingImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EditTrainingImageButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setTrainingsItemModel(TrainingItemModel trainingsItemModel) {
		super.setTrainingsItemModel(trainingsItemModel);

		this.editDialog = new EditTrainingConfirmDialog((Activity) getContext(),
				this.trainingsItemModel);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editDialog.show();
			}
		});
	}

}