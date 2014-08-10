package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.validator.ValidatorList;
import android.app.Activity;
import android.content.DialogInterface.OnClickListener;

public abstract class TrainingConfirmDialog extends VigymDialog implements
		VigymForm {

	protected TrainingItemModel trainingsItemModel;
	protected ValidatorList validator;
	protected List<ExerciseTransferObject> exerciseList;

	public TrainingConfirmDialog(Activity parentActivity,
			TrainingItemModel trainingsItemModel) {
		super(parentActivity, true);
		this.trainingsItemModel = trainingsItemModel;
		this.validator = new ValidatorList(parentActivity);
		
		this.exerciseList = ((TrainingActivity) this.parentActivity)
				.getExerciseList();
		
	}

	protected CharSequence[] getItems() {
		List<TrainingTransferObject> trainingList = this.trainingsItemModel
				.getTrainingList();
		int size = trainingList.size();

		CharSequence[] items = new String[size];

		int i = 0;
		for (TrainingTransferObject tto : trainingList) {
			items[i] = tto.toString();
			i++;
		}
		return items;
	}

	@Override
	protected int getLayout() {
		return 0;
	}

	@Override
	protected int getPositiveButtonText() {
		return R.string.ok;
	}

	@Override
	protected int getNegativeButtonText() {
		return R.string.cancel;
	}

	@Override
	protected int getNeutralButtonText() {
		return 0;
	}

	@Override
	protected OnClickListener getNeutralButtonListener() {
		return null;
	}

	@Override
	protected int getMessage() {
		return 0;
	}

	@Override
	public void clearFormErrors() {

	}

	@Override
	public TransferObject submitForm() {
		return null;
	}

	@Override
	public void initForm(TransferObject transferObject) {
		
	}

	@Override
	public boolean validate() {
		return this.validator.validate();
	}
}