package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.db.to.ExerciseTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;

public class TrainingsModel {

	private List<TrainingTransferObject> trainingList;
	private Map<ExerciseTransferObject, TrainingItemModel> trainingItemMap = new LinkedHashMap<ExerciseTransferObject, TrainingItemModel>();

	public TrainingsModel(List<TrainingTransferObject> trainingList) {
		super();
		this.trainingList = trainingList;
		this.initItems(trainingList);
		this.calcValues();
	}

	private void initItems(List<TrainingTransferObject> trainingList) {
		if (trainingList != null) {
			for (TrainingTransferObject tto : trainingList) {
				ExerciseTransferObject exerciseTO = tto.getExerciseId();
				if (this.trainingItemMap.containsKey(exerciseTO) == false) {
					this.trainingItemMap.put(exerciseTO,
							new TrainingItemModel());
				}
				TrainingItemModel item = this.trainingItemMap.get(exerciseTO);
				item.addItem(tto);
			}
		}
	}

	private void calcValues() {
		for (Map.Entry<ExerciseTransferObject, TrainingItemModel> entry : trainingItemMap
				.entrySet()) {
			entry.getValue().calcValues();
		}
	}

	public Map<ExerciseTransferObject, TrainingItemModel> getTrainingItemMap() {
		return trainingItemMap;
	}
	
	
}