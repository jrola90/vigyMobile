package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.Date;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.CountValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.DateValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.ExerciseValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.training.validators.ValueValidator;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.DatePickerFragmentDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.validator.ValidatorList;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public abstract class TrainingDialog extends VigymDialog implements VigymForm {

	private ValidatorList validator;
	protected List<ExerciseTransferObject> exerciseList;
	protected ExerciseTransferObject selectedExercise;
	protected Spinner exerciseSpinner;
	protected TextView trainingDescTextView;
	protected EditText trainingCountEditText;
	protected EditText trainingValueEditText;
	protected EditText trainingDateEditText;

	public TrainingDialog(Activity parentActivity,
			List<ExerciseTransferObject> exerciseList) {
		super(parentActivity);

		this.exerciseSpinner = (Spinner) dialogView
				.findViewById(R.id.exercisesSpinner);
		this.trainingDescTextView = (TextView) dialogView
				.findViewById(R.id.trainingDescTextView);
		this.trainingCountEditText = (EditText) dialogView
				.findViewById(R.id.trainingCountEditText);
		this.trainingValueEditText = (EditText) dialogView
				.findViewById(R.id.trainingValueEditText);
		this.trainingDateEditText = (EditText) dialogView
				.findViewById(R.id.trainingDateEditText);

		this.initExerciseSpinner(exerciseList);

		this.validator = new ValidatorList(this.parentActivity);
		this.validator
				.addValidator(new ExerciseValidator(this.exerciseSpinner));
		this.validator.addValidator(new CountValidator(
				this.trainingCountEditText));
		this.validator.addValidator(new ValueValidator(
				this.trainingValueEditText));
		this.validator
				.addValidator(new DateValidator(this.trainingDateEditText));

		this.exerciseSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						if (position != AdapterView.INVALID_POSITION) {
							selectedExercise = TrainingDialog.this.exerciseList
									.get(position);
							trainingDescTextView.setText(selectedExercise
									.getDesc());
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

	}

	private void initExerciseSpinner(List<ExerciseTransferObject> exerciseList) {
		this.exerciseList = exerciseList;

		ArrayAdapter<ExerciseTransferObject> data = new ArrayAdapter<ExerciseTransferObject>(
				this.parentActivity, android.R.layout.simple_list_item_1,
				exerciseList);
		this.exerciseSpinner.setAdapter(data);
	}

	@Override
	protected int getLayout() {
		return R.layout.dialog_training;
	}

	@Override
	protected int getTitle() {
		return R.string.add_training_item;
	}

	@Override
	protected int getPositiveButtonText() {
		return R.string.ok;
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
	protected int getNegativeButtonText() {
		return R.string.cancel;
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				clearForm();
				clearFormErrors();
			}
		};
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
	public void clearForm() {
		this.exerciseSpinner.setSelection(0);
		this.trainingCountEditText.setText("");
		this.trainingValueEditText.setText("");
		this.trainingDateEditText.setText("");
	}

	@Override
	public void clearFormErrors() {
		this.trainingCountEditText.setError(null);
		this.trainingValueEditText.setError(null);
		this.trainingDateEditText.setError(null);
	}

	@Override
	public boolean validate() {
		return validator.validate();
	}

	@Override
	public TransferObject submitForm() {
		TrainingTransferObject trainingTO = new TrainingTransferObject();

		Integer count = Integer.parseInt(this.trainingCountEditText.getText()
				.toString().trim());
		trainingTO.setCount(count);

		Double value = Double.parseDouble(this.trainingValueEditText.getText()
				.toString().trim());
		trainingTO.setValue(value);

		Date date = Utils.convertStringToDate(this.trainingDateEditText
				.getText().toString().trim(), Utils.DATE_FORMAT);
		trainingTO.setDate(date);

		trainingTO.setExerciseId(this.selectedExercise);
		trainingTO.setUserId(LoggedUserSingleton.getUserTransferObject());

		return trainingTO;
	}

	@Override
	public void initForm(TransferObject transferObject) {

		TrainingTransferObject trainingTO = (TrainingTransferObject) transferObject;
		this.trainingCountEditText.setText(trainingTO.getCount().toString());
		this.trainingValueEditText.setText(trainingTO.getValue().toString());
		this.trainingDateEditText.setText(trainingTO.getDateAsString());
		this.exerciseSpinner.setSelection(trainingTO.getExerciseId().getId()
				.intValue() - 1);
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragmentDialog(
				(VigymUIComponent) parentActivity, (VigymForm) this,
				this.trainingDateEditText);
		newFragment.show(parentActivity.getFragmentManager(), "datePicker");
	}

}