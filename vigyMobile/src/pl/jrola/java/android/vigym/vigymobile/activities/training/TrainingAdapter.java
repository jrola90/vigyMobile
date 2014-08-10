package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.Arrays;
import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TrainingAdapter extends ArrayAdapter<TrainingItemModel> {

	public TrainingAdapter(Context context,
			Map<ExerciseTransferObject, TrainingItemModel> exerciseMap) {
		super(context, R.layout.list_item_training, Arrays.copyOf(exerciseMap
				.values().toArray(), exerciseMap.size(),
				TrainingItemModel[].class));
		inflater = LayoutInflater.from(context);
		this.trainingItemList = Arrays.copyOf(exerciseMap.values().toArray(),
				exerciseMap.size(), TrainingItemModel[].class);

	}

	private TrainingItemModel[] trainingItemList;
	private LayoutInflater inflater;

	@Override
	public int getCount() {
		return this.trainingItemList.length;
	}

	@Override
	public TrainingItemModel getItem(int position) {
		return this.trainingItemList[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ViewHolder viewHolder;

		if (row == null) {
			row = inflater.inflate(R.layout.list_item_training, null);
			viewHolder = new ViewHolder();

			viewHolder.exerciseTextView = (TextView) row
					.findViewById(R.id.exerciseTextView);
			viewHolder.maxSingleTextView = (TextView) row
					.findViewById(R.id.maxSingleTextView);
			viewHolder.maxTotalTextView = (TextView) row
					.findViewById(R.id.maxTotalTextView);
			viewHolder.avgSingleTextView = (TextView) row
					.findViewById(R.id.avgSingleTextView);
			viewHolder.avgTotalTextView = (TextView) row
					.findViewById(R.id.avgTotalTextView);
			viewHolder.lastTotalTextView = (TextView) row
					.findViewById(R.id.lastTextView);
			viewHolder.editTrainingImageButton = (EditTrainingImageButton) row
					.findViewById(R.id.updateTrainingButton);
			viewHolder.deleteTrainingImageButton = (DeleteTrainingImageButton) row
					.findViewById(R.id.deleteTrainingButton);

			row.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.exerciseTextView.setText(trainingItemList[position]
				.getLastTraining().getExerciseId().getName());
		viewHolder.maxSingleTextView.setText(trainingItemList[position]
				.getMaxSingleValue().toString());
		viewHolder.maxTotalTextView.setText(trainingItemList[position]
				.getMaxTotalValue().toString());
		viewHolder.avgSingleTextView.setText(trainingItemList[position]
				.getAvgSingleValue().toString());
		viewHolder.avgTotalTextView.setText(trainingItemList[position]
				.getAvgTotalValue().toString());
		viewHolder.lastTotalTextView.setText(trainingItemList[position]
				.getLastTraining().getTotalValue()
				+ " ("
				+ trainingItemList[position].getLastTraining().getCount()
				+ " x "
				+ trainingItemList[position].getLastTraining().getValue()
				+ ") - "
				+ trainingItemList[position].getLastTraining()
						.getDateAsString());
		viewHolder.editTrainingImageButton
				.setTrainingsItemModel(trainingItemList[position]);
		viewHolder.deleteTrainingImageButton
				.setTrainingsItemModel(trainingItemList[position]);

		return row;
	}

	static class ViewHolder {
		TextView exerciseTextView;
		TextView maxSingleTextView;
		TextView maxTotalTextView;
		TextView avgSingleTextView;
		TextView avgTotalTextView;
		TextView lastTotalTextView;
		EditTrainingImageButton editTrainingImageButton;
		DeleteTrainingImageButton deleteTrainingImageButton;
	}

}
