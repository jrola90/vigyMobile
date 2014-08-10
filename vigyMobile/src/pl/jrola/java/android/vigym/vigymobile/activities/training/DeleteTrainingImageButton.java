package pl.jrola.java.android.vigym.vigymobile.activities.training;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class DeleteTrainingImageButton extends TrainingImageButton {

	private DeleteTrainingConfirmDialog deleteDialog;

	public DeleteTrainingImageButton(Context context) {
		super(context);
	}

	public DeleteTrainingImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DeleteTrainingImageButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setTrainingsItemModel(TrainingItemModel trainingsItemModel) {
		super.setTrainingsItemModel(trainingsItemModel);

		this.deleteDialog = new DeleteTrainingConfirmDialog(
				(Activity) getContext(), this.trainingsItemModel);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteDialog.show();
			}
		});

	}

}
