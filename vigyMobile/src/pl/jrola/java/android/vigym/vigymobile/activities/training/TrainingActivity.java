package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import android.os.Bundle;
import android.view.View;

public class TrainingActivity extends VigymActivity {

	private VigymAsyncTask<Void, Void, String> getTrainingListAsyncTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training);
		
		this.loadingSpinner.start();
		this.getTrainingListAsyncTask = new GetTrainingListAsyncTask(this, this.loadingSpinner);
		this.getTrainingListAsyncTask.execute();
	}

	public void onAddTrainingItemClick(View view) {
		
	}

	public void onUpdateTrainingItemClick(View view) {
		
	}
	
	public void onDeleteTrainingItemClick(View view) {
		
	}
	
	@Override
	public void refresh() {
		
	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		
	}
}