package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.training.tasks.GetTrainingListAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class TrainingActivity extends VigymActivity {

	private TrainingDialog addTrainingDialog;

	private VigymAsyncTask<Void, Void, String> getTrainingListAsyncTask;
	private List<ExerciseTransferObject> exerciseList;
	public List<ExerciseTransferObject> getExerciseList() {
		return exerciseList;
	}

	private ListView exerciseListView;

	private TrainingsModel trainingsModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training);

		this.exerciseListView = (ListView) findViewById(R.id.exerciseList);

		this.refresh();
	}

	public void onAddTrainingItemClick(View view) {
		this.addTrainingDialog.show();
	}

	public void onUpdateTrainingItemClick(View view) {
		EditTrainingImageButton editButton = (EditTrainingImageButton) view;
		List<TrainingTransferObject> trainingList = editButton
				.getTrainingsItemModel().getTrainingList();
	}

	public void onDeleteTrainingItemClick(View view) {
		DeleteTrainingImageButton deleteButton = (DeleteTrainingImageButton) view;
		List<TrainingTransferObject> trainingList = deleteButton
				.getTrainingsItemModel().getTrainingList();
	}

	@Override
	public void refresh() {
		this.loadingSpinner.start();
		this.getTrainingListAsyncTask = new GetTrainingListAsyncTask(this,
				this.loadingSpinner);
		this.getTrainingListAsyncTask.execute();
	}

	@Override
	public void refresh(Collection<TransferObject> toList) {

		int size = toList.size();
		if (size > 0) {
			Object[] objectArray = toList.toArray();
			if (objectArray[0] instanceof ExerciseTransferObject) {
				ExerciseTransferObject[] exerciseTOArray = Arrays.copyOf(
						objectArray, objectArray.length,
						ExerciseTransferObject[].class);
				this.refresh(exerciseTOArray);
			} else if (objectArray[0] instanceof TrainingTransferObject) {
				TrainingTransferObject[] trainingTOArray = Arrays.copyOf(
						objectArray, objectArray.length,
						TrainingTransferObject[].class);
				this.refresh(trainingTOArray);
			}
		} else {
			this.exerciseListView.setAdapter(null);			
		}

	}

	private void refresh(TrainingTransferObject[] trainingTOArray) {
		this.trainingsModel = new TrainingsModel(Arrays.asList(trainingTOArray));
		this.exerciseListView.setAdapter(new TrainingAdapter(this,
				trainingsModel.getTrainingItemMap()));
	}

	private void refresh(ExerciseTransferObject[] exerciseTOArray) {
		this.exerciseList = Arrays.asList(exerciseTOArray);
		this.addTrainingDialog = new AddTrainingDialog(this, this.exerciseList);
	}

	public void showTimePickerDialog(View v) {
		addTrainingDialog.showTimePickerDialog(v);
	}
}